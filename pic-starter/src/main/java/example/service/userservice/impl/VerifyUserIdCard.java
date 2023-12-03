package example.service.userservice.impl;


import example.entity.database.User;
import example.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.ReBody;
import response.RepCode;


@Service
public class VerifyUserIdCard {
    @Autowired
    private UserDao userDao;


    public ReBody verifyIdCard(String userId){
        ReBody reBody = new ReBody();

        User user1 = userDao.selectById(userId);
        if (user1 != null){
            reBody.setRepCode(RepCode.R_Ok);
            return reBody;
        }else {
            reBody.setRepCode(RepCode.R_Error);
            reBody.setData("查无此人");
            return reBody;
        }
    }
}
