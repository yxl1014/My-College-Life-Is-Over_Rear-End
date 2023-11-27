package org.example.service.impl;
import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.PowerMapper;
import org.example.model.entity.Power;
import org.example.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Service
public class PowerMapperImpl {
    private final PowerMapper powerMapper;
    @Autowired
    public PowerMapperImpl(PowerMapper powerMapper){
        this.powerMapper=powerMapper;
    }
    public void insertPower(Power power){
        powerMapper.insertPower(power);
    }
    public void deletePower(Integer powerId){
        powerMapper.deletePower(powerId);
    }
    public void updatePower(Power power){powerMapper.updatePower(power);}
    public Power selectOnePower(Integer powerId){
        return powerMapper.selectOnePower(powerId);
    }
    public List<Power> selectAllPower(){
        return powerMapper.selectAllPower();
    }

}