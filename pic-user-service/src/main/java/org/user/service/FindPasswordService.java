package org.user.service;

import org.commons.common.verify.RegexValidator;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.inner.UniqueTypes;
import org.user.entity.request.findPasswd.FindPasswdRequest;
import org.user.entity.request.findPasswd.SecAnswerRequest;
import org.user.entity.response.CheckExistResponse;

/**
 * @description: 找回密码服务
 * @author: HammerRay
 * @date: 2023/11/23 下午8:54
 */
@Service
public class FindPasswordService {

    @Autowired
    private BaseMysqlComp mysqlComp;

    public ReBody checkExist(String string) {
        return new ReBody();
    }

    private ReBody getReBody(User user, ReBody reBody, CheckExistResponse response) {
        response.setUserTelephone(user.getUserTelephone());
        response.setUserName(user.getUserName());
        response.setUserSysEmail(user.getUserSysEmail());
        reBody.setData(response);
        reBody.setMsg("请求成功");
        reBody.setCode(RepCode.R_Ok.getCode());

        return reBody;
    }

    public UniqueTypes checkWhichOne(String string) {

        if (RegexValidator.regexEmail(string)) {
            return UniqueTypes.EMAIL;
        }
        if (RegexValidator.regexTelephone(string)) {

            return UniqueTypes.TELEPHONE;
        }
        return UniqueTypes.USERNAME;
    }

    public boolean checkProperSec(SecAnswerRequest request) {
        return true;
    }

    public boolean checkProperVal(String vcId, String validation) {
        return true;
    }

    public ReBody findPasswd(FindPasswdRequest request) {
        return new ReBody();
    }

}
