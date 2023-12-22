package org.privileges.service.impl;

import exception.FormatException;
import exception.PowerExceptions;
import exception.RoleExceptions;
import lombok.Getter;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.Power;
import org.database.mysql.domain.RolePowerRef;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.mapper.PowerMapper;
import org.database.mysql.mapper.RoleMapper;
import org.database.mysql.mapper.RolePowerRefMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Component
@Getter
public class PowerMapperImpl {
    private final PowerMapper powerMapper;
    private final RolePowerRefMapper rolePowerRefMapper;
    private final BaseMysqlComp baseMysqlComp;
    private final RoleMapper roleMapper;


    @Autowired
    public PowerMapperImpl(PowerMapper powerMapper, BaseMysqlComp baseMysqlComp, RolePowerRefMapper rolePowerRefMapper, RoleMapper roleMapper) {
        this.powerMapper = powerMapper;
        this.baseMysqlComp = baseMysqlComp;
        this.rolePowerRefMapper = rolePowerRefMapper;
        this.roleMapper = roleMapper;
    }

    // 添加权限
    public void insertPower(Power power) throws PowerExceptions.EmptyPowerException, FormatException, IllegalAccessException, PowerExceptions.PowerExistsException {

        // 参数校验：权限id不为空；权限id不能已存在；当前操作用户有添加权限的权限（未完成）；
        if (power == null || power.getPowerId() == null) {
            throw new PowerExceptions.EmptyPowerException();
            //抛出自定义的权限信息为空的异常
        } else {
            MysqlBuilder<Power> insertPower = new MysqlBuilder<>(Power.class);
            insertPower.setIn(power);
            insertPower.getIn().setPowerId(power.getPowerId());
            if (powerMapper.selectById(power.getPowerId()) != null) {
                throw new PowerExceptions.PowerExistsException();
                //抛出自定义的权限信息已存在的异常
            } else {
                // 执行插入操作
                baseMysqlComp.insert(insertPower);
            }
        }
    }


    ///查找权限，由于权限名可能有多个故只能通过权限id查询
    public Power selectOnePower(Power power) throws
            FormatException, IllegalAccessException, PowerExceptions.PowerIsNullException, PowerExceptions.PowerNoExistsException {
        // 参数校验：权限id不为空；当前操作用户有查找权限的权限（未完成）；
        if (power.getPowerId() == null) {
            throw new PowerExceptions.PowerIsNullException();
            //抛出自定义的权限信息为空的异常
        } else {
            MysqlBuilder<Power> selectOnePower = new MysqlBuilder<>(Power.class);
            selectOnePower.setIn(power);
            selectOnePower.getIn().setPowerId(power.getPowerId());
            if (baseMysqlComp.selectOne(selectOnePower) == null) {
                throw new PowerExceptions.PowerNoExistsException();
                //抛出自定义的权限不存在的异常
            } else {
                //执行查找操作
                return baseMysqlComp.selectOne(selectOnePower);
            }
        }
    }

    //查询权限列表信息
    public List<Power> selectAllPower() throws FormatException, IllegalAccessException {
        MysqlBuilder<Power> selectAllPower = new MysqlBuilder<>(Power.class);
        return baseMysqlComp.selectList(selectAllPower);
    }


    //删除权限（根据权限id）
    public void deletePower(Power power) throws
            PowerExceptions.PowerIsNullException, FormatException, IllegalAccessException, PowerExceptions.PowerNoExistsException {
        // 参数校验：权限id不为空；当前操作用户有删除权限的权限（未完成）；
        if (power.getPowerId() == null) {
            throw new PowerExceptions.PowerIsNullException();
            //抛出自定义的权限信息为空的异常
        } else {
            MysqlBuilder<Power> deletePower = new MysqlBuilder<>(Power.class);
            deletePower.setIn(power);
            deletePower.getIn().setPowerId(power.getPowerId());
            if (baseMysqlComp.selectOne(deletePower) == null) {
                throw new PowerExceptions.PowerNoExistsException();
                //抛出自定义的权限不存在的异常
            } else {
                //执行删除操作
                baseMysqlComp.delete(deletePower);

            }
        }
    }

    //更新权限内容(根据powerId)
    public void updatePower(Power power) throws PowerExceptions.EmptyPowerException, PowerExceptions.PowerNoExistsException, FormatException, IllegalAccessException {
        // 参数校验：权限id不为空；当前操作用户有更新权限的权限（未完成）；
        if (power == null || power.getPowerId() == null) {
            throw new PowerExceptions.EmptyPowerException();
            //抛出自定义的权限信息为空的异常
        } else {
            MysqlBuilder<Power> updatePower = new MysqlBuilder<>(Power.class);
            Power power1 = powerMapper.selectById(power.getPowerId());
            updatePower.setIn(power1);
            updatePower.setUpdate(power);
            if (powerMapper.selectById(power.getPowerId()) == null) {
                throw new PowerExceptions.PowerNoExistsException();
                //抛出自定义的权限不存在的异常
            } else {
                //执行更新操作
                baseMysqlComp.update(updatePower);
            }
        }
    }

    //查找角色的权限
    public List<RolePowerRef> getRolePowers(RolePowerRef rolePowerRef) throws Exception {
        // 参数校验：角色id不为空；当前操作用户有查看角色权限的权限（未完成）；
        if (rolePowerRef == null || rolePowerRef.getRefRoleId() == null) {
            throw new RoleExceptions.EmptyRoleException();
            //抛出自定义的角色信息为空的异常
        } else {
            MysqlBuilder<RolePowerRef> getRolePowers = new MysqlBuilder<>(RolePowerRef.class);
            getRolePowers.setIn(rolePowerRef);
            getRolePowers.getIn().setRefRoleId(rolePowerRef.getRefRoleId());
            if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                throw new RoleExceptions.RoleNoExistsException();
                //抛出自定义的权限不存在的异常
            } else {
                //执行查找操作
                return baseMysqlComp.selectList(getRolePowers);
            }
        }
    }


}
/*


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