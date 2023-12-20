package org.privileges.service.impl;

import exception.FormatException;
import exception.PowerExceptions;
import lombok.Getter;
import org.mysql.BaseMysqlComp;
import org.mysql.domain.Power;
import org.mysql.entity.MysqlBuilder;
import org.mysql.mapper.PowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Component
@Getter
public class PowerMapperImpl {
    private final PowerMapper powerMapper;
    private final BaseMysqlComp baseMysqlComp;

    @Autowired
    public PowerMapperImpl(PowerMapper powerMapper, BaseMysqlComp baseMysqlComp) {
        this.powerMapper = powerMapper;
        this.baseMysqlComp = baseMysqlComp;

    }

    // 添加权限
    public void insertPower(Power power) throws PowerExceptions.EmptyPowerException, FormatException, IllegalAccessException, PowerExceptions.PowerExistsException {

        // 参数校验：权限id不为空；权限id不能已存在；当前操作用户有添加权限的权限（未完成）；
        if (power == null || power.getPowerId() == null) {
            throw new PowerExceptions.EmptyPowerException();
            //抛出自定义的权限信息为空的异常
        } else {
            MysqlBuilder<Power> selectOnePower = new MysqlBuilder<>(Power.class);
            selectOnePower.setIn(power);
            selectOnePower.getIn().setPowerId(power.getPowerId());
            if (baseMysqlComp.selectOne(selectOnePower)!=null) {
                throw new PowerExceptions.PowerExistsException();
                //抛出自定义的权限信息已存在的异常
            }
        }
        // 创建 MysqlBuilder 对象，并设置插入数据
        MysqlBuilder<Power> insertPower = new MysqlBuilder<>(Power.class);
        insertPower.setIn(power);
        // 执行插入操作
        baseMysqlComp.insert(insertPower);
    }


    //根据权限id查找权限
    public void selectOnePower(Power power) throws FormatException, IllegalAccessException, PowerExceptions.PowerIdIsNullException {
        // 参数校验：权限id不为空；当前操作用户有查找权限的权限（未完成）；
        if (power.getPowerId() == null) {
            throw new PowerExceptions.PowerIdIsNullException();
            //抛出自定义的权限信息为空的异常
        } else {
            MysqlBuilder<Power> selectOnePower = new MysqlBuilder<>(Power.class);
            selectOnePower.setIn(power);
            selectOnePower.getIn().setPowerId(power.getPowerId());
            //执行查找操作
            baseMysqlComp.selectOne(selectOnePower);
        }
    }


}









   /* //删除权限
    public void deletePower(Integer powerId) {
        // 参数校验
        if (powerId == null) {
            throw new IllegalArgumentException("权限信息不能为空");
        }

        // 权限验证
           *//* if(!checkPowers()) {
                throw new SecurityException("无权限删除权限信息");
            }*//*

        // 执行删除
        powerMapper.deletePower(powerId);
    }

    //更新权限
    public void updatePower(Power power) {

        // 参数校验
        if (power == null || power.getPowerId() == null) {
            throw new IllegalArgumentException("权限信息不能为空");
        } else if (powerMapper.selectOnePower(power.getPowerId()) == null) {
            throw new IllegalArgumentException("权限id不存在");
        }

        // 权限验证
       *//* if (!checkPowers()) {
            throw new SecurityException("无权更新权限");
        }*//*

        // 执行更新
        powerMapper.updatePower(power);
    }

    //查找权限，由于权限名可能有多个故只能通过权限id查询
    public Power selectOnePower(Integer powerId) {
        // 参数校验
        if (powerId == null) {
            throw new IllegalArgumentException("权限Id不能为空");
        }

        // 权限验证
       *//* if (!checkPowers()) {
            throw new SecurityException("无权查找权限");
        }*//*

        // 执行查找
        return powerMapper.selectOnePower(powerId);
    }

    //查询权限列表信息
    public List<Power> selectAllPower() {
        return powerMapper.selectAllPower();
    }

    //查找角色的权限
    public List<Power> getRolePowers(Integer roleId) {
        // 参数校验
        if (roleId == null) {
            throw new IllegalArgumentException("权限ID不能为空");
        }
        // 权限验证
       *//* if (!checkPowers()) {
            throw new SecurityException("无权查询权限列表信息");
        }*//*
        // 执行查询
        return powerMapper.getRolePowers(roleId);
    }


    //判断权限的状态为可操作还是可访问(1为可操作，2为可访问)
    public Boolean isStatusToPower(Integer powerId) {
        if (powerId == null) {
            throw new IllegalArgumentException("权限ID不能为空");
        }
        // 权限验证
       *//* if (!checkPowers()) {
            throw new SecurityException("无权限判断权限的状态为可操作还是可访问");
        }*//*
        Power power1 = powerMapper.selectOnePower(powerId);
        int powerType = power1.getPowerType();
        return powerType == 1;
    }


}
*/