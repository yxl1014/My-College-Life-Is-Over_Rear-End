package org.user.service;

import org.apache.logging.log4j.util.Strings;
import org.commons.common.verify.RegexValidator;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.service.UserMysqlComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.inner.UniqueTypes;
import org.user.entity.request.findPasswd.FindPasswdRequest;
import org.user.entity.request.findPasswd.SecAnswerRequest;

/**
 * @description: 找回密码服务
 * @author: HammerRay
 * @date: 2023/11/23 下午8:54
 */
@Service
public class FindPasswordService {

    @Autowired
    private BaseMysqlComp mysqlComp;

    @Autowired
    private UserMysqlComp userMysqlComp;

    public ReBody checkExist(String key) {
        ReBody reBody = new ReBody();
        if (Strings.isEmpty(key)){
            reBody.setRepCode(RepCode.R_ParamError);
        }
        else
        {
            if (userMysqlComp.checkUserIsExist(key)){
                reBody.setRepCode(RepCode.R_Ok);
                reBody.setMsg(checkWhichOne(key).getData());
            }
            else {
                reBody.setRepCode(RepCode.R_Ok);
                reBody.setMsg("不存在");
            }
        }
        return reBody;
    }

    private UniqueTypes checkWhichOne(String key) {

        if (RegexValidator.regexEmail(key)) {
            return UniqueTypes.EMAIL;
        }
        if (RegexValidator.regexTelephone(key)) {
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
