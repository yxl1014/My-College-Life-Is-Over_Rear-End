package org.example.service.impl;

import org.example.model.dao.PowerMapper;
import org.example.model.dao.RoleMapper;
import org.example.model.dao.UserMapper;
import org.example.model.entity.Power;
import org.example.model.entity.User;
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
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public PowerMapperImpl(PowerMapper powerMapper, UserMapper userMapper, RoleMapper roleMapper) {
        this.powerMapper = powerMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    public void insertPower(Power power) {
        powerMapper.insertPower(power);
    }

    public void deletePower(Power power) {
        powerMapper.deletePower(power);
    }

    public void updatePower(Power power) {
        powerMapper.updatePower(power);
    }

    public Power selectOnePower(Power power) {
        return powerMapper.selectOnePower(power);
    }

    public List<Power> selectAllPower() {
        return powerMapper.selectAllPower();
    }

    public List<Power> getRolePowers(Integer roleId){
        return powerMapper.getRolePowers(roleId);
    }

}
