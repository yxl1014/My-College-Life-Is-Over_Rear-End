package example.service.transaction;

import example.entity.request.transaction.RechargeRequest;
import example.entity.response.transaction.RechargeResponse;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/2 下午11:43
 */
@Service
public class RechargeService {
    /**
     * description: 充值方法
     * @paramType [example.entity.request.transaction.RechargeRequest]
     * @param request:
     * @returnType: example.entity.response.transaction.RechargeResponse
     * @author: GodHammer
     * @date: 2023-12-02 下午11:44
     */
    public RechargeResponse recharge(RechargeRequest request){
        RechargeResponse rechargeResponse = new RechargeResponse();

        return rechargeResponse;
    }

}
