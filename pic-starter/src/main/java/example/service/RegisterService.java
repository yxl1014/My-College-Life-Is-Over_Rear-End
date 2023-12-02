package example.service;

import common.encrypt.PasswordEncrypt;
import common.uuid.UuidGenerator;
import common.verify.RegexValidator;
import example.entity.database.User;
import example.entity.request.RegisterRequest;
import example.entity.response.UuidResponse;
import example.mapper.UserMapper;
import common.string.UselessCharRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 注册服务类
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */

@Service
public class RegisterService {

    @Autowired
    UserMapper userMapper;
    public UuidResponse register(Object object) throws ParseException {
            UuidResponse uuidResponse = new UuidResponse();
              RegisterRequest request = (RegisterRequest) object;
            User user = new User();
            //必填
            if(request.getUserName().isEmpty()){
                uuidResponse.setMsg("errorMsg: 请填写用户名信息");
                uuidResponse.setCode(500);
                return uuidResponse;
            }else {
                if(!RegexValidator.regexUsername(request.getUserName()) && (!RegexValidator.regexUsernameChinese(request.getUserName()))){
                    uuidResponse.setMsg("errorMsg: 用户名格式错误,正确："+RegexValidator.USERNAME_FM);
                    uuidResponse.setCode(500);
                    return uuidResponse;
                }
                user.setUserName(request.getUserName());
            }

            if(request.getUserPassword().isEmpty()){
                uuidResponse.setMsg("errorMsg: 请输入密码信息");
                uuidResponse.setCode(500);
                return uuidResponse;
            }else {
                if(!RegexValidator.isLowPasswd(request.getUserPassword())){
                    uuidResponse.setMsg("errorMsg: 注册密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                    uuidResponse.setCode(500);
                    return uuidResponse;
                }
                user.setUserPassword(request.getUserPassword());
            }

            user.setUserSecProblem1(request.getUserSecProblem1());
            user.setUserSecAnswer1(request.getUserSecAnswer1());
            user.setUserSecProblem2(request.getUserSecProblem2());
            user.setUserSecAnswer2(request.getUserSecAnswer2());
            //选填
            if(!(request.getUserTelephone().isEmpty())){
                if(!RegexValidator.regexTelephone(request.getUserTelephone())){
                    uuidResponse.setMsg("errorMsg: 手机号码格式错误,正确："+RegexValidator.TELEPHONE_FM);
                    uuidResponse.setCode(500);
                    return uuidResponse;
                }
                user.setUserTelephone(request.getUserTelephone());
            }
            if(!(request.getUserSysEmail().isEmpty())){
                if(!RegexValidator.regexEmail(request.getUserSysEmail())){
                    uuidResponse.setMsg("errorMsg: 邮箱格式错误,正确："+RegexValidator.EMAIL_FM);
                    uuidResponse.setCode(500);
                    return uuidResponse;
                }
                user.setUserSysEmail(request.getUserSysEmail());
            }
            if(!(request.getUserNickName().isEmpty())){
                user.setUserNickName(request.getUserNickName());
            }

            if(!(request.getUserGender().isEmpty())){
                user.setUserGender(request.getUserGender());
            }

            if(!(request.getUserIdCard().isEmpty())){
                user.setUserIdCard(request.getUserIdCard());
            }

            if(!(request.getUserCompany().isEmpty())){
                user.setUserCompany(request.getUserCompany());
            }

            if(!(request.getUserHome().isEmpty())){
                user.setUserHome(request.getUserHome());
            }

            //TODO userIp的话 得后端想办法得到 暂时不需要
            if(!(request.getUserPersonalProfile().isEmpty())){
                user.setUserPersonalProfile(request.getUserPersonalProfile());
            }

            if(!(request.getUserSecProblem3().isEmpty())){
                user.setUserSecProblem3(request.getUserSecProblem3());
            }

            if(!(request.getUserSecAnswer3().isEmpty())){
                user.setUserSecAnswer3(request.getUserSecAnswer3());
            }

            if(!(request.getUserBornDay().isEmpty())){
                String bornDay = UselessCharRemove.remove1(request.getUserBornDay());
                //TODO 检查一下格式bornDay格式是否正确
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setUserBornDay(new Date(dateFormat.parse(bornDay).getTime()));
            }


            user.setUserId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
            user.setUserPassword(PasswordEncrypt.hashPassword(user.getUserPassword()));
            user.setUserCreateTime(new Timestamp(System.currentTimeMillis()));
            User user1 = userMapper.selectOne(user);

            if(user1 == null){
                userMapper.insert(user);
                uuidResponse.setMsg("注册成功！");
                uuidResponse.setCode(200);
                uuidResponse.setUuid(user.getUserId());
                return uuidResponse;
            }

            uuidResponse.setMsg("注册失败！此账户已经存在");
            uuidResponse.setCode(500);
            uuidResponse.setUuid(user1.getUserId());
            return uuidResponse;

        }


    }


