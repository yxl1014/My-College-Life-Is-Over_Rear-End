package example.service.userservice.impl;


import example.entity.database.User;
import example.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.R_Code;
import response.ReBody;


@Service
public class VerifyUserIdCard {
    @Autowired
    private UserDao userDao;


    public ReBody verifyIdCard(String userId){
        ReBody reBody = new ReBody();

        User user1 = userDao.selectById(userId);
        if (user1 != null){
            reBody.setRCode(R_Code.R_Ok);
            return reBody;
        }else {
            reBody.setRCode(R_Code.R_Error);
            reBody.setData("查无此人");
            return reBody;
        }
    }
}
