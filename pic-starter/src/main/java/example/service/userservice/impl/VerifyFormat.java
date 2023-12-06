package example.service.userservice.impl;

import common.string.UselessCharRemove;
import common.verify.RegexValidator;
import example.entity.database.User;
import example.entity.request.CRUDRequest;
import example.entity.request.RegisterRequest;
import example.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.R_Code;
import response.ReBody;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class VerifyFormat {
    @Autowired
    private UserDao userDao;

    public ReBody verifyformat(User user) throws Exception {
        ReBody reBody = new ReBody();
        CRUDRequest request = new CRUDRequest();

        if(request.getUserName().isEmpty()){
            reBody.setData("errorMsg: 请填写用户名信息");
            reBody.setRCode(R_Code.R_ParamError);
            return reBody;
        }else {
            if(!RegexValidator.regexUsername(request.getUserName()) && (!RegexValidator.regexUsernameChinese(request.getUserName()))){
                reBody.setData("errorMsg: 用户名格式错误,正确："+RegexValidator.USERNAME_FM);
                reBody.setRCode(R_Code.R_ParamError);
                return reBody;
            }
            user.setUserName(request.getUserName());
        }

        if(request.getUserPassword().isEmpty()){
            reBody.setData("errorMsg: 请输入密码信息");
            reBody.setRCode(R_Code.R_ParamError);
            return reBody;
        }else {
            if(!RegexValidator.isLowPasswd(request.getUserPassword())){
                reBody.setData("errorMsg: 注册密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                reBody.setRCode(R_Code.R_ParamError);
                return reBody;
            }
            user.setUserPassword(request.getUserPassword());
        }

        user.setUserSecProblem1(request.getUserSecProblem1());
        user.setUserSecAnswer1(request.getUserSecAnswer1());
        user.setUserSecProblem2(request.getUserSecProblem2());
        user.setUserSecAnswer2(request.getUserSecAnswer2());
        user.setUserSecProblem3(request.getUserSecProblem3());
        user.setUserSecAnswer3(request.getUserSecAnswer3());


        if(!(request.getUserTelephone().isEmpty())){
            if(!RegexValidator.regexTelephone(request.getUserTelephone())){
                reBody.setData("errorMsg: 手机号码格式错误,正确："+RegexValidator.TELEPHONE_FM);
                reBody.setRCode(R_Code.R_ParamError);
                return reBody;
            }
            user.setUserTelephone(request.getUserTelephone());
        }
        if(!(request.getUserSysEmail().isEmpty())){
            if(!RegexValidator.regexEmail(request.getUserSysEmail())){
                reBody.setData("errorMsg: 邮箱格式错误,正确："+RegexValidator.EMAIL_FM);
                reBody.setRCode(R_Code.R_ParamError);
                return reBody;
            }
            user.setUserSysEmail(request.getUserSysEmail());
        }

        if(!(request.getUserNickName().isEmpty())){
            user.setUserNickName(request.getUserNickName());
        }

        if(!(request.getUserGender().isEmpty())){
            user.setUserGender(request.getUserGender());
        }

        if(!(request.getUserIdCard().isEmpty())){
            user.setUserIdCard(request.getUserIdCard());
        }

        if(!(request.getUserCompany().isEmpty())){
            user.setUserCompany(request.getUserCompany());
        }

        if(!(request.getUserHome().isEmpty())){
            user.setUserHome(request.getUserHome());
        }

        if(!(request.getUserPersonalProfile().isEmpty())){
            user.setUserPersonalProfile(request.getUserPersonalProfile());
        }

        if(!(request.getUserBornDay().isEmpty())){
            String bornDay = UselessCharRemove.remove1(request.getUserBornDay());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setUserBornDay(new Date(dateFormat.parse(bornDay).getTime()));
        }

      return reBody;
    }
}
