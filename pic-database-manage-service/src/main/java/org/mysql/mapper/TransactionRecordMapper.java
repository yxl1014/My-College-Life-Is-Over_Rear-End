package org.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mysql.domain.TransactionRecord;

/**
 * @author yxl17
 * @Package : org.mysql.mapper
 * @Create on : 2023/12/17 14:16
 **/
@Mapper
public interface TransactionRecordMapper extends BaseMapper<TransactionRecord> {
}
