package org.user.service;

import org.commons.response.ReBody;
import org.springframework.stereotype.Service;
import org.user.entity.response.UuidResponse;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/28 下午5:38
 */
@Service
public class LogoffService {

    public ReBody logOff(String uuid) {
        //TODO 删除redis中的uuid
        ReBody reBody = new ReBody();
        reBody.setData(new UuidResponse(null));
        return reBody;
    }
}
