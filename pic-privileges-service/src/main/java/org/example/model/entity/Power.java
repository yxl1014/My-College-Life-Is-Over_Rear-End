package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
    private Integer pId;
    @JsonProperty("power_name")
    private String pName;
    @JsonProperty("power_type")
    private String pType;
    @JsonProperty("power_create_time")
    private String pDate;
    @JsonProperty("power_notes")
    private String pNote;
    //和用户表的关系
    @ManyToMany(mappedBy = "sys_power")
    private List<User> users;
    }



