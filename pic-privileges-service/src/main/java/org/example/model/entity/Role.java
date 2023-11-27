package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//用于生成全参构造函数和无参构造函数
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonProperty("role_id")
    private Integer roleId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("role_create_time")
    private LocalDateTime roleDate;

    @JsonProperty("role_status_flag")
    private String roleFlag;

    @JsonProperty("role_remark")
    private String roleMark;

    //一个角色下可以有多个用户，一个用户只能有一种角色
    @OneToMany(mappedBy = "role")
    private List<User> users;

}