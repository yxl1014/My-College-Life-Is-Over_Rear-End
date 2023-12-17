package org.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exception.FormatException;
import lombok.Getter;
import org.mysql.domain.*;
import org.mysql.entity.MysqlBuilder;
import org.mysql.entity.MysqlOptType;
import org.mysql.entity.MysqlResultType;
import org.mysql.mapper.*;
import org.mysql.util.MysqlCommonUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yxl17
 * @Package : org.mysql
 * @Create on : 2023/12/17 16:14
 **/

@Component
@Getter
public class BaseMysqlComp {

    private final MysqlCommonUtil mysqlCommonUtil;
    private final AnnouncementMapper announcementMapper;
    private final MailInnerMapper mailInnerMapper;
    private final PowerMapper powerMapper;
    private final RoleMapper roleMapper;
    private final RolePowerRefMapper rolePowerRefMapper;
    private final TransactionRecordMapper transactionRecordMapper;
    private final UserMapper userMapper;
    private final UserRoleRefMapper userRoleRefMapper;

    public BaseMysqlComp(AnnouncementMapper announcementMapper, MailInnerMapper mailInnerMapper,
                         PowerMapper powerMapper, RoleMapper roleMapper,
                         RolePowerRefMapper rolePowerRefMapper, TransactionRecordMapper transactionRecordMapper,
                         UserMapper userMapper, UserRoleRefMapper userRoleRefMapper, MysqlCommonUtil mysqlCommonUtil) {
        this.announcementMapper = announcementMapper;
        this.mailInnerMapper = mailInnerMapper;
        this.powerMapper = powerMapper;
        this.roleMapper = roleMapper;
        this.rolePowerRefMapper = rolePowerRefMapper;
        this.transactionRecordMapper = transactionRecordMapper;
        this.userMapper = userMapper;
        this.userRoleRefMapper = userRoleRefMapper;
        this.mysqlCommonUtil = mysqlCommonUtil;
    }


    public <T> List<T> selectList(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        mysqlBuilder.setOptType(MysqlOptType.SELECT);
        mysqlBuilder.setResultType(MysqlResultType.LIST);
        Object o = doOpt(mysqlBuilder);
        if (o == null) {
            return null;
        }
        return (List<T>) o;
    }

    public <T> T selectOne(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        mysqlBuilder.setOptType(MysqlOptType.SELECT);
        mysqlBuilder.setResultType(MysqlResultType.ONE);
        Object o = doOpt(mysqlBuilder);
        if (o == null) {
            return null;
        }
        return (T) o;
    }

    public <T> Integer delete(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        mysqlBuilder.setOptType(MysqlOptType.DELETE);
        mysqlBuilder.setResultType(MysqlResultType.NULL);
        Object o = doOpt(mysqlBuilder);
        if (o == null) {
            return -1;
        }
        return (Integer) o;
    }

    public <T> Integer update(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        mysqlBuilder.setOptType(MysqlOptType.UPDATE);
        mysqlBuilder.setResultType(MysqlResultType.NULL);
        Object o = doOpt(mysqlBuilder);
        if (o == null) {
            return -1;
        }
        return (Integer) o;
    }

    public <T> Integer insert(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        mysqlBuilder.setOptType(MysqlOptType.INSERT);
        mysqlBuilder.setResultType(MysqlResultType.NULL);
        Object o = doOpt(mysqlBuilder);
        if (o == null) {
            return -1;
        }
        return (Integer) o;
    }

    public <T> Object doOpt(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        BaseMapper<T> baseMapper = getMapperByClass(mysqlBuilder.getClz());
        switch (mysqlBuilder.getOptType()) {
            case DELETE: {
                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                buildQueryWrapperData(queryWrapper, mysqlBuilder.getClz(), mysqlBuilder.getIn(), mysqlBuilder.getNoEqual());
                return baseMapper.delete(queryWrapper);
            }
            case SELECT: {
                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                buildQueryWrapperData(queryWrapper, mysqlBuilder.getClz(), mysqlBuilder.getIn(), mysqlBuilder.getNoEqual());
                buildQueryWrapperOutData(queryWrapper, mysqlBuilder.getClz(), mysqlBuilder.getOut());
                if (mysqlBuilder.getResultType() == MysqlResultType.LIST) {
                    return baseMapper.selectList(queryWrapper);
                } else {
                    return baseMapper.selectOne(queryWrapper);
                }
            }
            case UPDATE: {
                UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
                buildUpdateWrapperData(updateWrapper, mysqlBuilder.getClz(), mysqlBuilder.getIn(), mysqlBuilder.getNoEqual());
                return baseMapper.update(mysqlBuilder.getUpdate(), updateWrapper);
            }

            case INSERT: {
                return baseMapper.insert(mysqlBuilder.getIn());
            }
            default:
                return null;
        }
    }


    private <T> void buildUpdateWrapperData(UpdateWrapper<T> updateWrapper, Class<T> clz, T in, T noEqual) throws IllegalAccessException, FormatException {
        Field[] fields = clz.getDeclaredFields();
        if (in != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(in);
                if (o != null) {
                    updateWrapper.eq(mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName()), o);
                }
            }
        }
        if (noEqual != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object no = field.get(noEqual);
                if (no != null) {
                    updateWrapper.ne(mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName()), no);
                }
            }
        }
    }

    private <T> void buildQueryWrapperData(QueryWrapper<T> queryWrapper, Class<T> clz, T in, T noEqual) throws IllegalAccessException, FormatException {
        Field[] fields = clz.getDeclaredFields();
        if (in != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(in);
                if (o != null) {
                    queryWrapper.eq(mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName()), o);
                }
            }
        }
        if (noEqual != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object no = field.get(noEqual);
                if (no != null) {
                    queryWrapper.ne(mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName()), no);
                }
            }
        }
    }

    private <T> void buildQueryWrapperOutData(QueryWrapper<T> queryWrapper, Class<T> clz, T out) throws IllegalAccessException, FormatException {
        if (out == null) {
            return;
        }
        Field[] fields = clz.getDeclaredFields();
        List<String> selectColumns = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(out);
            if (o != null) {
                selectColumns.add(mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName()));
            }
        }
        queryWrapper.select(selectColumns.toArray(new String[0]));
    }

    private BaseMapper getMapperByClass(Class<?> clz) {
        if (clz.equals(Announcement.class)) {
            return announcementMapper;
        } else if (clz.equals(MailInner.class)) {
            return mailInnerMapper;
        } else if (clz.equals(Power.class)) {
            return powerMapper;
        } else if (clz.equals(Role.class)) {
            return roleMapper;
        } else if (clz.equals(RolePowerRef.class)) {
            return rolePowerRefMapper;
        } else if (clz.equals(TransactionRecord.class)) {
            return transactionRecordMapper;
        } else if (clz.equals(User.class)) {
            return userMapper;
        } else if (clz.equals(UserRoleRef.class)) {
            return userRoleRefMapper;
        }
        throw new IllegalStateException("Unexpected value: " + clz);
    }

    private <T> Wrapper<T> getWrapperByOptType(MysqlOptType optType) {
        switch (optType) {
            case DELETE:
            case SELECT:
                return new QueryWrapper<T>();
            case UPDATE:
                return new UpdateWrapper<T>();
            default:
                return null;
        }
    }
}
