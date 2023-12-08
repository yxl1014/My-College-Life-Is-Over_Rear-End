package org.example.service.impl;

import org.apache.ibatis.annotations.Param;
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

    @Autowired
    public PowerMapperImpl(PowerMapper powerMapper) {
        this.powerMapper = powerMapper;
    }

    //添加权限
    public void insertPower(Power power) {
        // 参数校验,权限id不能重复
        if (power == null || power.getPowerId() == null) {
            throw new IllegalArgumentException("权限信息不能为空");
        } else if (powerMapper.selectOnePower(power.getPowerId())!=null) {
            throw new IllegalArgumentException("权限信息已存在");
        }
        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权限插入权限信息");
        }*/
        //执行插入
        powerMapper.insertPower(power);
    }

    //删除权限
    public void deletePower(Integer powerId) {
        // 参数校验
        if (powerId == null) {
            throw new IllegalArgumentException("权限信息不能为空");
        }

        // 权限验证
           /* if(!checkPowers()) {
                throw new SecurityException("无权限删除权限信息");
            }*/

        // 执行删除
        powerMapper.deletePower(powerId);
    }

    //更新权限
    public void updatePower(Power power) {

        // 参数校验
        if (power == null || power.getPowerId() == null) {
            throw new IllegalArgumentException("权限信息不能为空");
        }else if(powerMapper.selectOnePower(power.getPowerId())==null)
        {
            throw new IllegalArgumentException("权限id不存在");
        }

        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权更新权限");
        }*/

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
       /* if (!checkPowers()) {
            throw new SecurityException("无权查找权限");
        }*/

        // 执行查找
        return powerMapper.selectOnePower(powerId);
    }

    //查找权限，由于权限名可能有多个故只能通过权限id查询
    public Power findPowerByPowerName(String powerName) {
        // 参数校验
        if (powerName == null) {
            throw new IllegalArgumentException("权限名不能为空");
        }

        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权查找权限");
        }*/

        // 执行查找
        return powerMapper.findPowerByPowerName(powerName);
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
       /* if (!checkPowers()) {
            throw new SecurityException("无权查询权限列表信息");
        }*/
        // 执行查询
        return powerMapper.getRolePowers(roleId);
    }


    //判断权限的状态为可操作还是可访问(1为可操作，2为可访问)
    public Boolean isStatusToPower(Integer powerId) {
        if (powerId == null) {
            throw new IllegalArgumentException("权限ID不能为空");
        }
        // 权限验证
       /* if (!checkPowers()) {
            throw new SecurityException("无权限判断权限的状态为可操作还是可访问");
        }*/
        Power power1 = powerMapper.selectOnePower(powerId);
        int powerType = power1.getPowerType();
        return powerType == 1;
    }





}
