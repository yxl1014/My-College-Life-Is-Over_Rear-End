package org.database.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import org.commons.exception.FormatException;
import org.database.mysql.domain.*;
import org.database.mysql.domain.task.Task;
import org.database.mysql.entity.ConditionType;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.entity.MysqlOptType;
import org.database.mysql.entity.MysqlResultType;
import org.database.mysql.mapper.*;
import org.database.mysql.util.MysqlCommonUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yxl17
 * @Package : org.mysql
 * @Create on : 2023/12/17 16:14
 * <p>
 * 基于mysql数据库操作的组件，提供了对数据库的查询、插入、更新和删除操作
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
    private final TaskMapper taskMapper;
    private final TaskUserRefMapper taskUserRefMapper;

    public BaseMysqlComp(AnnouncementMapper announcementMapper, MailInnerMapper mailInnerMapper,
                         PowerMapper powerMapper, RoleMapper roleMapper,
                         RolePowerRefMapper rolePowerRefMapper, TransactionRecordMapper transactionRecordMapper,
                         UserMapper userMapper, UserRoleRefMapper userRoleRefMapper, MysqlCommonUtil mysqlCommonUtil, TaskMapper taskMapper, TaskUserRefMapper taskUserRefMapper) {
        this.announcementMapper = announcementMapper;
        this.mailInnerMapper = mailInnerMapper;
        this.powerMapper = powerMapper;
        this.roleMapper = roleMapper;
        this.rolePowerRefMapper = rolePowerRefMapper;
        this.transactionRecordMapper = transactionRecordMapper;
        this.userMapper = userMapper;
        this.userRoleRefMapper = userRoleRefMapper;
        this.mysqlCommonUtil = mysqlCommonUtil;
        this.taskMapper = taskMapper;
        this.taskUserRefMapper = taskUserRefMapper;
    }

    public <T> List<T> selectPage(MysqlBuilder<T> mysqlBuilder, int pageIndex, int pageSize) throws FormatException, IllegalAccessException {
        mysqlBuilder.setOptType(MysqlOptType.SELECT);
        mysqlBuilder.setPage(true);
        mysqlBuilder.setPageIndex(pageIndex);
        mysqlBuilder.setPageSize(pageSize);
        Object o = doOpt(mysqlBuilder);
        if (o == null) {
            return null;
        }
        return (List<T>) o;
    }


    //接收一个泛型参数T使用MysqlBuilder<T>对象来构建数据库
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

    //根据传过来的MysqlBuilder对象的操作类型，使用相应的Mapper对象执行具体的数据库操作

    public <T> Object doOpt(MysqlBuilder<T> mysqlBuilder) throws FormatException, IllegalAccessException {
        BaseMapper<T> baseMapper = getMapperByClass(mysqlBuilder.getClz());
        switch (mysqlBuilder.getOptType()) {
            case DELETE: {
                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                buildQueryWrapperData(queryWrapper, mysqlBuilder.getClz(), mysqlBuilder.getCondition(), mysqlBuilder.getNoEqual(), mysqlBuilder.getBetween(), mysqlBuilder.getQueryType());
                return baseMapper.delete(queryWrapper);
            }
            case SELECT: {
                QueryWrapper<T> queryWrapper = new QueryWrapper<>();
                buildQueryWrapperData(queryWrapper, mysqlBuilder.getClz(), mysqlBuilder.getCondition(), mysqlBuilder.getNoEqual(), mysqlBuilder.getBetween(), mysqlBuilder.getQueryType());
                buildQueryWrapperOutData(queryWrapper, mysqlBuilder.getClz(), mysqlBuilder.getOut());
                if (mysqlBuilder.isPage()) {
                    return baseMapper.selectPage(new Page<T>(mysqlBuilder.getPageIndex(), mysqlBuilder.getPageSize()), queryWrapper).getRecords();
                } else if (mysqlBuilder.getResultType() == MysqlResultType.LIST) {
                    return baseMapper.selectList(queryWrapper);
                } else {
                    return baseMapper.selectOne(queryWrapper);
                }
            }
            case UPDATE: {
                UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
                buildUpdateWrapperData(updateWrapper, mysqlBuilder.getClz(), mysqlBuilder.getCondition(), mysqlBuilder.getNoEqual());
                return baseMapper.update(mysqlBuilder.getUpdate(), updateWrapper);
            }

            case INSERT: {
                return baseMapper.insert(mysqlBuilder.getCondition());
            }
            default:
                return null;
        }
    }

    //根据传入的对象和字段信息，构建了相应查询条件和更新条件

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

    private <T> void buildQueryWrapperData(QueryWrapper<T> queryWrapper, Class<T> clz, T in, T noEqual, T between, ConditionType type) throws IllegalAccessException, FormatException {
        Field[] fields = clz.getDeclaredFields();
        if (in != null) {
            if (type == ConditionType.BWT) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object left = field.get(in);
                    Object right = field.get(between);
                    String name = mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName());
                    if (left != null && right != null) {
                        queryWrapper.between(name, left, right);
                    } else if (left != null) {
                        queryWrapper.le(name, left);
                    } else if (right != null) {
                        queryWrapper.ge(name, right);
                    }
                }
            } else {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object o = field.get(in);
                    if (o != null) {
                        String name = mysqlCommonUtil.javaFieldName2MysqlColumnsName(field.getName());
                        switch (type) {
                            case IN: {
                                queryWrapper.in(name, o);
                                break;
                            }
                            case EQ: {
                                queryWrapper.eq(name, o);
                                break;
                            }
                            case LIKE: {
                                queryWrapper.like(name, o);
                                break;
                            }
                            case NEQ: {
                                queryWrapper.ne(name, o);
                                break;
                            }
                        }
                    }
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

    //根据传入的类对象，选择相应的Mapper对象进行返回
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
        } else if (clz.equals(Task.class)) {
            return taskMapper;
        } else if (clz.equals(TaskUserRef.class)) {
            return taskUserRefMapper;
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
