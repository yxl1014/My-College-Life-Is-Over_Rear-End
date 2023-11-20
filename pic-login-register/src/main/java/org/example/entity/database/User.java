package org.example.entity.database;

/**
 * @description:
 * @author: HammerRay
 * @date:2023/11/4
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user")
public class User {
    @Id
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_telephone")
    private String userTelephone;
    @JsonProperty("user_sys_email")
    private String userSysEmail;

    @JsonProperty("user_password")
    private String userPassword;
    @JsonProperty("user_nick_name")
    private String userNickName;
    @JsonProperty("user_gender")
    private String userGender;
    @JsonProperty("user_born_day")
    private Date userBornDay;

    @JsonProperty("user_id_card")
    private String userIdCard;
    @JsonProperty("user_money")
    private Double userMoney;
    @JsonProperty("user_company")
    private String userCompany;
    @JsonProperty("user_home")
    private String userHome;

    @JsonProperty("user_ip")
    private String userIp;
    @JsonProperty("user_flag")
    private int userFlag;
    @JsonProperty("user_personal_profile")
    private String userPersonalProfile;
    @JsonProperty("user_create_time")
    private Timestamp userCreateTime;

    @JsonProperty("user_sec_problem_1")
    private String userSecProblem1;
    @JsonProperty("user_sec_answer_1")
    private String userSecAnswer1;
    @JsonProperty("user_sec_problem_2")
    private String userSecProblem2;
    @JsonProperty("user_sec_answer_2")
    private String userSecAnswer2;
    @JsonProperty("user_sec_problem_3")
    private String userSecProblem3;
    @JsonProperty("user_sec_answer_3")
    private String userSecAnswer3;
}

