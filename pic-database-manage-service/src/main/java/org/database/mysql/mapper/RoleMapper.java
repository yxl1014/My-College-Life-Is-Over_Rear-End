package org.database.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.database.mysql.domain.Role;

/**
 * @author yxl17
 * @Package : org.mysql.mapper
 * @Create on : 2023/12/17 14:11
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
