/*
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

*/
/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 *//*

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//用于生成全参构造函数和无参构造函数
@Table(name = "sys_role")
public class Role {
    @Id

    @JsonProperty("role_id")
    private Integer roleId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("role_create_time")
    private LocalDateTime roleDate;

    @JsonProperty("role_status_flag")
    private int roleFlag;

    @JsonProperty("role_remark")
    private String roleMark;

    //一个角色下可以有多个用户，一个用户只能有一种角色
    @OneToMany(mappedBy = "role")
    private List<User> users;

    //一个角色可有多种权限，且一个权限可被多个角色使用
    @ManyToMany
    @JoinTable(name = "sys_role_power_ref",
            joinColumns = @JoinColumn(name = "ref_role_id"),
            inverseJoinColumns = @JoinColumn(name = "ref_power_id"))
    private List<Power> powers;


}*/
