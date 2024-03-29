package org.database.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.database.mysql.domain.task.Task;

/**
 * @author Administrator
 * @Package : org.database.mysql.mapper
 * @Create on : 2024/3/29 下午2:04
 **/


@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
