package org.example.service.impl;
import org.example.model.dao.LimitsMapper;
import org.example.model.entity.Power;
import org.example.service.LimitsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/26
 */
@Service("LimitsService")
public class LimitsServiceImpl implements LimitsService{
    @Resource
    private LimitsMapper limitsMapper;
    @Override
    public Power selectOne(Integer pId){
        return this.limitsMapper.selectOne(pId);
    }
}

