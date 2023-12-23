package org.user.service;

import org.springframework.stereotype.Service;
import org.user.entity.request.ModifyPasswdRequest;
import org.user.entity.response.UuidResponse;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/28 下午1:47
 */
@Service
public class ModifyPasswordService {

    public UuidResponse modifyPasswd(ModifyPasswdRequest request){
        UuidResponse uuidResponse = new UuidResponse();

        return uuidResponse;
    }
}
