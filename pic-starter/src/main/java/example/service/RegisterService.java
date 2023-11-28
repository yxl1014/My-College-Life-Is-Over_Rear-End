package example.service;

import example.entity.database.User;
import example.entity.request.RegisterRequest;
import example.entity.response.UuidResponse;
import example.mapper.UserMapper;
import example.tools.PasswordEncrypt;
import example.tools.RegexValidator;
import example.tools.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 注册服务类
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */

@Service
public class RegisterService {
    private User castToUser(RegisterRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserTelephone(request.getUserTelephone());
        user.setUserSysEmail(request.getUserSysEmail());
        user.setUserPassword(request.getUserPassword());
        user.setUserNickName(request.getUserNickName());
        user.setUserGender(request.getUserGender());
        user.setUserBornDay(new Date(request.getUserBornDayTimeStamp()));
        user.setUserIdCard(request.getUserIdCard());
        user.setUserCompany(request.getUserCompany());
        user.setUserHome(request.getUserHome());

        user.setUserIp(request.getUserIp());
        user.setUserPersonalProfile(request.getUserPersonalProfile());
        user.setUserSecProblem1(request.getUserSecProblem1());
        user.setUserSecAnswer1(request.getUserSecAnswer1());
        user.setUserSecProblem2(request.getUserSecProblem2());
        user.setUserSecAnswer2(request.getUserSecAnswer2());
        user.setUserSecProblem3(request.getUserSecProblem3());
        user.setUserSecAnswer3(request.getUserSecAnswer3());

        return user;
    }
    @Autowired
    UserMapper userMapper;
    public UuidResponse register(Object object) {
            UuidResponse uuidResponse = new UuidResponse();
            User user = castToUser((RegisterRequest) object);

            if(!RegexValidator.regexEmail(user.getUserSysEmail())){
                uuidResponse.setMsg("errorMsg: 邮箱格式错误,正确："+RegexValidator.EMAIL_FM);
                uuidResponse.setCode(401);
                return uuidResponse;
            }
            if(!RegexValidator.regexTelephone(user.getUserTelephone())){
                uuidResponse.setMsg("errorMsg: 手机号码格式错误,正确："+RegexValidator.TELEPHONE_FM);
                uuidResponse.setCode(401);
                return uuidResponse;
            }
            if(!RegexValidator.regexUsername(user.getUserName()) && (!RegexValidator.regexUsernameChinese(user.getUserName()))){

                uuidResponse.setMsg("errorMsg: 用户名格式错误,正确："+RegexValidator.USERNAME_FM);
                uuidResponse.setCode(401);
                return uuidResponse;

            }

            if(!RegexValidator.isLowPasswd(user.getUserPassword())){
                uuidResponse.setMsg("errorMsg: 注册密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                uuidResponse.setCode(401);
                return uuidResponse;
            }

            user.setUserId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
            user.setUserPassword(PasswordEncrypt.hashPassword(user.getUserPassword()));
            user.setUserCreateTime(new Timestamp(System.currentTimeMillis()));
            User user1 = userMapper.selectOne(user);
            if(user1 == null){
                userMapper.insert(user);
                uuidResponse.setMsg("注册成功！");
                uuidResponse.setCode(200);
                uuidResponse.setUuid(user.getUserId());
                return uuidResponse;
            }

            uuidResponse.setMsg("注册失败！此账户已经存在");
            uuidResponse.setCode(200);
            uuidResponse.setUuid(user1.getUserId());
            return uuidResponse;

        }


    }


