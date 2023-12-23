package org.user.service.transaction;

import org.commons.response.ReBody;
import org.database.mysql.BaseMysqlComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.request.transaction.RechargeRequest;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/2 下午11:43
 */
@Service
public class RechargeService {

    @Autowired
    private BaseMysqlComp mysqlComp;

    /**
     * description: 充值方法
     * @paramType [example.entity.request.transaction.RechargeRequest]
     * @param request:
     * @returnType: example.entity.response.transaction.RechargeResponse
     * @author: GodHammer
     * @date: 2023-12-02 下午11:44
     */
    public ReBody recharge(RechargeRequest request){
        return new ReBody();
    }
}
