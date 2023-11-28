package example.service;

import example.entity.database.User;
import example.entity.response.SecProblemResponse;
import example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/28 下午5:27
 */
@Service
public class UserInfoQueryService {

    @Autowired
    UserMapper userMapper;

    public SecProblemResponse passwdSecQuery(String uuid)  {
        if (uuid.startsWith("\"") && uuid.endsWith("\"")) {
            uuid = uuid.substring(1, uuid.length() - 1);
        }

        User user = new User();
        user.setUserId(uuid);
        User user1 = userMapper.selectOne(user);

        if(user1 == null){
            SecProblemResponse secProblemResponse = new SecProblemResponse();
            secProblemResponse.setUuid(uuid);
            secProblemResponse.setCode(200);
            secProblemResponse.setMsg("请求失败，账号不存在");
            return secProblemResponse;
        }

        return getSecProblemResponse(user1);

    }

    private SecProblemResponse getSecProblemResponse(User user) {
        SecProblemResponse secProblemResponse = new SecProblemResponse();

        secProblemResponse.setUserSecProblem1(user.getUserSecProblem1());
        secProblemResponse.setUserSecAnswer1(user.getUserSecAnswer1());
        secProblemResponse.setUserSecProblem2(user.getUserSecProblem2());
        secProblemResponse.setUserSecAnswer2(user.getUserSecAnswer2());
        secProblemResponse.setUserSecProblem3(user.getUserSecProblem3());
        secProblemResponse.setUserSecAnswer3(user.getUserSecAnswer3());
        secProblemResponse.setCode(200);
        secProblemResponse.setUuid(user.getUserId());
        secProblemResponse.setMsg("请求成功");

        return secProblemResponse;
    }
}
