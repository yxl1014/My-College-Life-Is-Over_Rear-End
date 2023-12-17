package org.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mysql.domain.Announcement;

/**
 * @author yxl17
 * @Package : org.mysql.mapper
 * @Create on : 2023/12/17 13:55
 **/
@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
}
