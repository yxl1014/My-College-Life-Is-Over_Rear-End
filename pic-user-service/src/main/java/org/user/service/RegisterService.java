package org.user.service;

import org.commons.response.ReBody;
import org.springframework.stereotype.Service;
import org.user.entity.response.UuidResponse;

import java.text.ParseException;

/**
 * 注册服务类
 *
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */

@Service
public class RegisterService {

    public ReBody register(Object object) throws ParseException {
        ReBody reBody = new ReBody();
        reBody.setData(new UuidResponse());
        return reBody;
    }


}


