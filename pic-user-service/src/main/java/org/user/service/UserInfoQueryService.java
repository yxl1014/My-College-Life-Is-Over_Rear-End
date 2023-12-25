package org.user.service;

import org.commons.common.verify.RegexValidator;
import org.database.mysql.domain.User;
import org.springframework.stereotype.Service;
import org.user.entity.response.SecProblemResponse;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/28 下午5:27
 */
@Service
public class UserInfoQueryService {


    public SecProblemResponse passwdSecQuery(String request) {
        SecProblemResponse secProblemResponse = new SecProblemResponse();
        int flag = 0;
        if (RegexValidator.regexEmail(request)) {
            flag = 3;
        } else {
            if (RegexValidator.regexTelephone(request)) {
                flag = 2;
            } else {
                flag = 1;
            }
        }
        switch (flag) {
            case 1:
                return queryByUserName(request);
            case 2:
                return queryByTelephone(request);
            case 3:
                return queryByEmail(request);
            default:
                return secProblemResponse;
        }


    }

    private SecProblemResponse getSecProblemResponse(User user) {
        SecProblemResponse secProblemResponse = new SecProblemResponse();

        secProblemResponse.setUserSecProblemOne(user.getUserSecProblemOne());
        secProblemResponse.setUserSecProblemTwo(user.getUserSecProblemTwo());
        return secProblemResponse;
    }

    private SecProblemResponse queryByUserName(String request) {
        return null;
    }

    private SecProblemResponse queryByTelephone(String request) {
        return null;
    }

    private SecProblemResponse queryByEmail(String request) {
        return null;
    }
}
