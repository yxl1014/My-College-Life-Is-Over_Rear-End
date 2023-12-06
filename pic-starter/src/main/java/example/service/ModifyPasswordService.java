package example.service;

import example.entity.request.ModifyPasswdRequest;
import example.entity.response.UuidResponse;
import org.springframework.stereotype.Service;

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
