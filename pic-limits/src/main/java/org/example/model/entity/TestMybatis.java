package org.example.model.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/20
 */
@Data
public class TestMybatis implements Serializable {
    private static final long serialVersionUID = -15945377879783826L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

