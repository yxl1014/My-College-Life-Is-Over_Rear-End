package org.example.service.impl;
import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.PowerMapper;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Service("PowerMapper")
public class PowerMapperImpl implements PowerMapper {
    private SqlSession sqlSession;

    public PowerMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insert(Power power) {
        sqlSession.insert("AddPower", power);
    }

    @Override
    public void delete(Power power) {
        sqlSession.delete("DeletePowerById", power);
    }

    @Override
    public void update(Power power) {
        sqlSession.update("UpdatePower", power);
    }

    @Override
    public Power selectOne(Integer powerId) {
        return sqlSession.selectOne("selectOne", powerId);
    }
    @Override
    public List<Power> selectAll() {
        return sqlSession.selectList("SelectAll");
    }
}