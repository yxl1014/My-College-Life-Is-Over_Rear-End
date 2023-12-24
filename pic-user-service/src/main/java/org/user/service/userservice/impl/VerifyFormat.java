package org.user.service.userservice.impl;

import org.commons.common.string.UselessCharRemove;
import org.commons.common.verify.RegexValidator;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.request.CRUDRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author yxl17
 */
@Service
public class VerifyFormat {

    @Autowired
    private BaseMysqlComp mysqlComp;

    public ReBody verify(User user) throws Exception {
        ReBody reBody = new ReBody();
        CRUDRequest request = new CRUDRequest();

        if (request.getUserName().isEmpty()) {
            reBody.setData("errorMsg: 请填写用户名信息");
            reBody.setRepCode(RepCode.R_ParamError);
            return reBody;
        } else {
            if (!RegexValidator.regexUsername(request.getUserName()) && (!RegexValidator.regexUsernameChinese(request.getUserName()))) {
                reBody.setData("errorMsg: 用户名格式错误,正确：" + RegexValidator.USERNAME_FM);
                reBody.setRepCode(RepCode.R_ParamError);
                return reBody;
            }
            user.setUserName(request.getUserName());
        }

        if (request.getUserPassword().isEmpty()) {
            reBody.setData("errorMsg: 请输入密码信息");
            reBody.setRepCode(RepCode.R_ParamError);
            return reBody;
        } else {
            if (!RegexValidator.isLowPasswd(request.getUserPassword())) {
                reBody.setData("errorMsg: 注册密码格式错误,正确：" + RegexValidator.PASSWD_LFM);
                reBody.setRepCode(RepCode.R_ParamError);
                return reBody;
            }
            user.setUserPassword(request.getUserPassword());
        }

        user.setUserSecProblemOne(request.getUserSecProblem1());
        user.setUserSecAnswerOne(request.getUserSecAnswer1());
        user.setUserSecProblemTwo(request.getUserSecProblem2());
        user.setUserSecAnswerTwo(request.getUserSecAnswer2());
        user.setUserSecProblemThree(request.getUserSecProblem3());
        user.setUserSecAnswerThree(request.getUserSecAnswer3());


        if (!(request.getUserTelephone().isEmpty())) {
            if (!RegexValidator.regexTelephone(request.getUserTelephone())) {
                reBody.setData("errorMsg: 手机号码格式错误,正确：" + RegexValidator.TELEPHONE_FM);
                reBody.setRepCode(RepCode.R_ParamError);
                return reBody;
            }
            user.setUserTelephone(request.getUserTelephone());
        }
        if (!(request.getUserSysEmail().isEmpty())) {
            if (!RegexValidator.regexEmail(request.getUserSysEmail())) {
                reBody.setData("errorMsg: 邮箱格式错误,正确：" + RegexValidator.EMAIL_FM);
                reBody.setRepCode(RepCode.R_ParamError);
                return reBody;
            }
            user.setUserSysEmail(request.getUserSysEmail());
        }

        if (!(request.getUserNickName().isEmpty())) {
            user.setUserNickName(request.getUserNickName());
        }

        if (!(request.getUserGender().isEmpty())) {
            user.setUserGender(request.getUserGender());
        }

        if (!(request.getUserIdCard().isEmpty())) {
            user.setUserIdCard(request.getUserIdCard());
        }

        if (!(request.getUserCompany().isEmpty())) {
            user.setUserCompany(request.getUserCompany());
        }

        if (!(request.getUserHome().isEmpty())) {
            user.setUserHome(request.getUserHome());
        }

        if (!(request.getUserPersonalProfile().isEmpty())) {
            user.setUserPersonalProfile(request.getUserPersonalProfile());
        }

        if (!(request.getUserBornDay().isEmpty())) {
            String bornDay = UselessCharRemove.remove1(request.getUserBornDay());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            user.setUserBornDay(new Timestamp(System.currentTimeMillis()));
        }

        return reBody;
    }
}
