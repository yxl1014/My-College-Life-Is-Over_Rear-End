package org.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exception.PowerExceptions;
import org.apache.ibatis.annotations.Mapper;
import org.mysql.domain.Power;

/**
 * @author yxl17
 * @Package : org.mysql.mapper
 * @Create on : 2023/12/17 14:09
 **/
@Mapper
public interface PowerMapper extends BaseMapper<Power> {
}
