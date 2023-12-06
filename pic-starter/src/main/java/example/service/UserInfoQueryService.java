package example.service;

import common.verify.RegexValidator;
import example.entity.database.User;
import example.entity.response.SecProblemResponse;
import example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/28 下午5:27
 */
@Service
public class UserInfoQueryService {

    @Autowired
    UserMapper userMapper;


    public SecProblemResponse passwdSecQuery(String request)  {
        SecProblemResponse secProblemResponse = new SecProblemResponse();
        int flag =0 ;
        if(RegexValidator.regexEmail(request)){
            flag = 3;
        }else {
            if(RegexValidator.regexTelephone(request)){
                flag =2;
            }else {
                flag = 1;
            }
        }
        switch (flag){
            case 1:return queryByUserName(request);
            case 2:return queryByTelephone(request);
            case 3:return queryByEmail(request);
            default:secProblemResponse.setCode(500);secProblemResponse.setMsg("请求失败，请输入正确的用户名");return secProblemResponse;
        }


    }

    private SecProblemResponse getSecProblemResponse(User user) {
        SecProblemResponse secProblemResponse = new SecProblemResponse();

        secProblemResponse.setUserSecProblem1(user.getUserSecProblem1());
        secProblemResponse.setUserSecProblem2(user.getUserSecProblem2());
        secProblemResponse.setUserSecProblem3(user.getUserSecProblem3());

        secProblemResponse.setCode(200);
        secProblemResponse.setMsg("请求成功");

        return secProblemResponse;
    }

    private SecProblemResponse queryByUserName(String request){
        SecProblemResponse secProblemResponse = new SecProblemResponse();
        User user = new User();user.setUserName(request);
        user = userMapper.selectOne(user);
        if(user == null){
            secProblemResponse.setMsg("请求失败！请检查您的用户名是否正确");
            secProblemResponse.setCode(500);
            return secProblemResponse;
        }

        return getSecProblemResponse(user);
    }
    private SecProblemResponse queryByTelephone(String request){
        SecProblemResponse secProblemResponse = new SecProblemResponse();
        User user = new User();user.setUserTelephone(request);
        user = userMapper.selectOne(user);
        if(user == null){
            secProblemResponse.setMsg("请求失败！请检查您的电话号码是否正确");
            secProblemResponse.setCode(500);
            return secProblemResponse;
        }

            return getSecProblemResponse(user);

    }
    private SecProblemResponse queryByEmail(String request){
        SecProblemResponse secProblemResponse = new SecProblemResponse();

        User user = new User();user.setUserSysEmail(request);
        user = userMapper.selectOne(user);
        if(user == null){
            secProblemResponse.setMsg("请求失败！请检查您的邮箱地址是否正确");
            secProblemResponse.setCode(500);
            return secProblemResponse;
        }

            return getSecProblemResponse(user);

    }
}
