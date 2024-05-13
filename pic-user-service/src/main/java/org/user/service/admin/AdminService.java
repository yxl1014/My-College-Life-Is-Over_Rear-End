package org.user.service.admin;

import org.apache.logging.log4j.util.Strings;
import org.commons.common.encrypt.PasswordEncrypt;
import org.commons.common.random.VerifyCodeGenerator;
import org.commons.domain.RoleType;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.domain.User;
import org.database.mysql.service.UserMysqlComp;
import org.springframework.stereotype.Service;
import org.user.service.IAdminService;
import org.user.service.common.CommonUserComp;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/2 下午11:43
 */
@Service
public class AdminService implements IAdminService {

    private final UserMysqlComp userMysqlComp;

    private final CommonUserComp commonUserComp;

    public AdminService(UserMysqlComp userMysqlComp, CommonUserComp commonUserComp) {
        this.userMysqlComp = userMysqlComp;
        this.commonUserComp = commonUserComp;
    }


    /**
     * 充值接口
     *
     * @param user 请求
     * @return 返回
     */
    @Override
    public ReBody recharge(User user) {
        if (Strings.isEmpty(user.getUserId()) || user.getUserMoney() == null || user.getUserMoney() < 0) {
            return new ReBody(RepCode.R_ParamNull);
        }
        User user1 = userMysqlComp.findUserByUserId(user.getUserId());
        if (user1 == null) {
            return new ReBody(RepCode.R_UserNotFound);
        }

        User update = new User();
        update.setUserId(user.getUserId());
        update.setUserMoney(user.getUserMoney() + user1.getUserMoney());
        boolean suc = userMysqlComp.updateUserByUserId(update);

        return new ReBody(suc ? RepCode.R_Ok : RepCode.R_Fail);
    }

    @Override
    public ReBody makeAdmin(String userId) {
        if (Strings.isEmpty(userId)) {
            return new ReBody(RepCode.R_ParamNull);
        }
        User user = userMysqlComp.findUserByUserId(userId);
        if (user == null) {
            return new ReBody(RepCode.R_UserNotFound);
        }

        User update = new User();
        update.setUserId(user.getUserId());
        update.setUserFlag((short) RoleType.ADMIN.ordinal());
        boolean suc = userMysqlComp.updateUserByUserId(update);

        return new ReBody(suc ? RepCode.R_Ok : RepCode.R_Fail);
    }

    @Override
    public ReBody resetPassword(String userId) {
        if (Strings.isEmpty(userId)) {
            return new ReBody(RepCode.R_ParamNull);
        }
        User user = userMysqlComp.findUserByUserId(userId);
        if (user == null) {
            return new ReBody(RepCode.R_UserNotFound);
        }

        User update = new User();
        update.setUserId(user.getUserId());
        String password = VerifyCodeGenerator.digitLetterCode(10);
        String encryptPwd = PasswordEncrypt.hashPassword(password);
        update.setUserPassword(encryptPwd);
        boolean suc = userMysqlComp.updateUserByUserId(update);
        if (!suc) {
            return new ReBody(RepCode.R_Fail);
        }

        commonUserComp.AfterChangePassword(userId);
        return new ReBody(RepCode.R_Ok, password);
    }
}
