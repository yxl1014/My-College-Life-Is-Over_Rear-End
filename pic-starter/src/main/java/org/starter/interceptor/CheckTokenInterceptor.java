package org.starter.interceptor;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.commons.annotation.ControllerLog;
import org.commons.annotation.NeedCheck;
import org.commons.common.ThreadLocalComp;
import org.commons.common.verify.JWTUtil;
import org.commons.domain.LoginCommonData;
import org.commons.domain.constData.RedisConstData;
import org.commons.domain.constData.ThreadLocalConstData;
import org.commons.log.LogComp;
import org.commons.log.LogType;
import org.commons.response.RepCode;
import org.database.mysql.domain.User;
import org.database.mysql.service.UserMysqlComp;
import org.database.redis.RedisComp;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yxl17
 * @Package : org.starter.interceptor
 * @Create on : 2023/12/24 17:01
 **/

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    private final Logger logger = LogComp.getLogger(CheckTokenInterceptor.class);

    private final ThreadLocalComp threadLocalComp;

    private final JWTUtil jwtUtil;

    private final RedisComp redisComp;

    private final UserMysqlComp userMysqlComp;


    @Resource
    private Environment env;

    public CheckTokenInterceptor(ThreadLocalComp threadLocalComp, JWTUtil jwtUtil, RedisComp redisComp, UserMysqlComp userMysqlComp) {
        this.threadLocalComp = threadLocalComp;
        this.jwtUtil = jwtUtil;
        this.redisComp = redisComp;
        this.userMysqlComp = userMysqlComp;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LogComp.LogMessage logMessage = LogComp.buildData(LogType.INTERCEPTOR);
        logMessage.build("url", request.getRequestURI());
        logger.info(logMessage.log());
//        if ("test".equals(env.getProperty("spring.profiles.active")))
//        {
//            return true;
//        }
        if (!(handler instanceof HandlerMethod)) {
            response.sendError(RepCode.R_ControllerError.getCode(), RepCode.R_ControllerError.getMsg());
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (!method.isAnnotationPresent(ControllerLog.class)) {
            response.sendError(RepCode.R_ControllerError.getCode(), RepCode.R_ControllerError.getMsg());
            return false;
        }

        // 这个取出来几个日志
        ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);
        //获取这个方法是否有这个注释
        if (method.isAnnotationPresent(NeedCheck.class)) {
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            for (Cookie c : cookies) {
                if ("token".equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
            if (cookie == null) {
                response.sendError(RepCode.R_TokenError.getCode(), "访问未携带token");
                return false;
            }
            //1、验证cookie的有效期
            if (cookie.getMaxAge() != -1 && System.currentTimeMillis() >= cookie.getMaxAge()) {
                response.sendError(RepCode.R_TokenError.getCode(), RepCode.R_TokenError.getMsg());
                return false;
            }
            String token = cookie.getValue();
            if (token == null) {
                response.sendError(RepCode.R_TokenError.getCode(), "访问未携带token");
                return false;
            }
            // 这个是解出来的数据
            LoginCommonData unSignData = jwtUtil.unSign(token, LoginCommonData.class);
            if (unSignData == null) {
                response.sendError(RepCode.R_TokenError.getCode(), RepCode.R_TokenError.getMsg());
                return false;
            }
            // 这个是本地threadlocal存的
            LoginCommonData tlData = threadLocalComp.getLoginCommonData();
            // 这里如果有 那么就比较一下
            if (tlData != null) {
                if (!unSignData.equals(tlData)) {
                    response.sendError(RepCode.R_TokenError.getCode(), RepCode.R_TokenError.getMsg());
                    return false;
                }
            } else
            // 如果没有 就去redis里比较一下 version 这个version会在登陆的时候随机生成
            {
                String version = redisComp.get(RedisConstData.USER_LOGIN_VERSION + unSignData.getUserId());
                if (Strings.isEmpty(version) || !version.equals(String.valueOf(unSignData.getVersion()))) {
                    response.sendError(RepCode.R_TokenError.getCode(), RepCode.R_TokenError.getMsg());
                    return false;
                }
                // 完事没问题了 就set进ThreadLocal
                threadLocalComp.setThreadLocalData(ThreadLocalConstData.USER_COMMON_DATA_NAME, unSignData);
            }
            User user = userMysqlComp.findUserByUserId(unSignData.getUserId());
            if (user == null) {
                response.sendError(RepCode.R_UserNotFound.getCode(), RepCode.R_UserNotFound.getMsg());
                return false;
            }
            if (user.getUserFlag() < controllerLog.roleType().ordinal()) {
                response.sendError(RepCode.R_UserPrivilegesNotEnough.getCode(), RepCode.R_UserPrivilegesNotEnough.getMsg());
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        threadLocalComp.removeThreadLocal();
    }
}
