package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 * 权限实体类
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_power")
public class Power {
    @Id

    @JsonProperty("power_id")
    private Integer powerId;

    @JsonProperty("power_name")
    private String powerName;

    @JsonProperty("power_type")
    private String powerType;

    @JsonProperty("power_create_time")
    private LocalDateTime powerDate;

    @JsonProperty("power_notes")
    private String powerNote;

    //和角色表的关系
    @ManyToMany(mappedBy = "powers")
    private List<Role> roles;
}





