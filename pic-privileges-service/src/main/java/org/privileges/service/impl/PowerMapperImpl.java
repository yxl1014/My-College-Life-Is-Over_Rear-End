package org.privileges.service.impl;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.commons.log.LogComp;
import org.commons.log.LogEnum;
import org.commons.log.LogType;
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
    private static final Logger log = LogComp.getLogger(PowerMapperImpl.class);

    @Autowired
    public PowerMapperImpl(PowerMapper powerMapper, BaseMysqlComp baseMysqlComp, RolePowerRefMapper rolePowerRefMapper, RoleMapper roleMapper) {
        this.powerMapper = powerMapper;
        this.baseMysqlComp = baseMysqlComp;
        this.rolePowerRefMapper = rolePowerRefMapper;

        this.roleMapper = roleMapper;
    }

    /**
     * 添加权限、通过权限id判断权限是否已存在
     * 参数校验：权限id不为空；
     * 权限id不能已存在；
     * 当前操作用户有添加权限的权限（未完成）
     *
     * @param power
     * @throws Exception
     */
    public void insertPower(Power power) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.POWER);
            if (power == null || power.getPowerId() == null) {
                logMessage.build(LogEnum.POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Power> insertPower = new MysqlBuilder<>(Power.class);
                insertPower.setCondition(power);
                if (powerMapper.selectById(power.getPowerId()) != null) {
                    logMessage.build(LogEnum.POWER_EXISTS);
                    log.error(logMessage.log());
                } else {
                    baseMysqlComp.insert(insertPower);
                }
            }
        } catch (Exception e) {
            log.error("Failed to insert power!", e);
        }
    }

    /**
     * 查找权限，由于权限名可能有多个故只能通过权限id查询
     * 参数校验：权限id不为空；
     * 当前操作用户有查找权限的权限（未完成)
     *
     * @param power
     * @throws Exception
     */
    public void selectOnePower(Power power) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.POWER);

            if (power.getPowerId() == null) {
                logMessage.build(LogEnum.POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Power> selectOnePower = new MysqlBuilder<>(Power.class);
                selectOnePower.setCondition(power);
                Power selectedPower = baseMysqlComp.selectOne(selectOnePower);

                if (selectedPower == null) {
                    logMessage.build(LogEnum.POWER_NO_EXISTS);
                    log.error(logMessage.log());
                }
            }

        } catch (Exception e) {
            log.error("Failed to select power!", e);
        }
    }

    /**
     * 查询权限列表信息
     *
     * @return
     * @throws Exception
     */
    public List<Power> selectAllPower() throws Exception {
        MysqlBuilder<Power> selectAllPower = new MysqlBuilder<>(Power.class);
        return baseMysqlComp.selectList(selectAllPower);
    }


    /**
     * 删除权限（根据权限id）
     * 参数校验：权限id不为空；
     * 当前操作用户有删除权限的权限（未完成）
     *
     * @param power
     * @throws Exception
     */
    public void deletePower(Power power) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.POWER);
            if (power.getPowerId() == null || power == null) {
                logMessage.build(LogEnum.POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Power> deletePower = new MysqlBuilder<>(Power.class);
                deletePower.setCondition(power);
                if (baseMysqlComp.selectOne(deletePower) == null) {
                    logMessage.build(LogEnum.POWER_NO_EXISTS);
                    log.error(logMessage.log());
                } else {
                    baseMysqlComp.delete(deletePower);

                }
            }
        } catch (Exception e) {
            log.error("Failed to delete power!", e);
        }
    }

    /**
     * 更新权限内容(根据powerId)
     * 参数校验：权限id不为空；
     * 当前操作用户有更新权限的权限（未完成）；
     *
     * @param power
     * @throws Exception
     */
    public void updatePower(Power power) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.POWER);
            if (power == null || power.getPowerId() == null) {
                logMessage.build(LogEnum.POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<Power> updatePower = new MysqlBuilder<>(Power.class);
                Power power1 = powerMapper.selectById(power.getPowerId());
                updatePower.setCondition(power1);
                updatePower.setUpdate(power);
                if (powerMapper.selectById(power.getPowerId()) == null) {
                    logMessage.build(LogEnum.POWER_NO_EXISTS);
                    log.error(logMessage.log());
                } else {
                    baseMysqlComp.update(updatePower);
                }
            }
        } catch (Exception e) {
            log.error("Failed to update power!", e);
        }
    }

    /**
     * 查找角色的权限
     * 参数校验：角色id不为空；
     * 当前操作用户有查看角色权限的权限（未完成）；
     *
     * @param rolePowerRef
     * @return
     * @throws Exception
     */
    public List<RolePowerRef> getRolePowers(RolePowerRef rolePowerRef) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.ROLE);
            if (rolePowerRef == null || rolePowerRef.getRefRoleId() == null) {
                logMessage.build(LogEnum.ROLE_EMPTY);
                log.warn(logMessage.log());
            } else {
                MysqlBuilder<RolePowerRef> getRolePowers = new MysqlBuilder<>(RolePowerRef.class);
                getRolePowers.setCondition(rolePowerRef);
                if (roleMapper.selectById(rolePowerRef.getRefRoleId()) == null) {
                    logMessage.build(LogEnum.POWER_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    return baseMysqlComp.selectList(getRolePowers);
                }
            }
        } catch (Exception e) {
            log.error("Failed to getRolePowers!", e);
        }
        return null;
    }

    /**
     * 判断权限的状态为可操作还是可访问(1为可操作，0为可访问)
     * 参数校验：权限id不为空；
     * 当前操作用户有查看角色权限状态的权限（未完成）；
     *
     * @param power
     * @return
     * @throws Exception
     */

    public Boolean checkPowerOperate(Power power) throws Exception {
        try {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.POWER);
            if (power == null || power.getPowerId() == null) {
                logMessage.build(LogEnum.POWER_EMPTY);
                log.warn(logMessage.log());
            } else {
                if (powerMapper.selectById(power.getPowerId()) == null) {
                    logMessage.build(LogEnum.POWER_NO_EXISTS);
                    log.warn(logMessage.log());
                } else {
                    Power power1 = powerMapper.selectById(power.getPowerId());
                    return power1.getPowerType() == 1;
                }
            }
        } catch (Exception e) {
            log.error("Failed to checkPowerOperate!", e);
        }
        return null;
    }
}
