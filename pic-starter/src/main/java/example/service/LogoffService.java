package example.service;

import example.entity.response.UuidResponse;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/28 下午5:38
 */
@Service
public class LogoffService {

    public UuidResponse logOff(String uuid){
        //TODO 删除redis中的uuid
        return new UuidResponse(200,"登出成功,返回登录页面",null);
    }
}
