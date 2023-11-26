package org.example.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.entity.Power;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Mapper
public interface LimitsMapper {

    int insert (Power power);
    int  delete (Power power);
    int update (Power power);
    Power selectOne(Integer pType);
    List<Power> selectAll();
}
