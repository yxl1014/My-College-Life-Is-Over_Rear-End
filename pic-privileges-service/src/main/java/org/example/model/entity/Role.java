package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_role")
public class Role {
    @Id
    @JsonProperty("role_id")
    private Integer rId;
    @JsonProperty("role_name")
    private String rName;
    @JsonProperty("role_create_time")
    private String rDate;
    @JsonProperty("role_status_flag")
    private String rFlag;
    @JsonProperty("role_remark")
    private String rMark;
    //一个角色下可以有多个用户，一个用户只能有一种角色
    @OneToMany(mappedBy = "sys_role")
    private List<User> users;

}
