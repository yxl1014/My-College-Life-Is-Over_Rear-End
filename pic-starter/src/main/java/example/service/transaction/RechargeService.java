package example.service.transaction;

import common.uuid.UuidGenerator;
import example.entity.database.Transaction;
import example.entity.database.User;
import example.entity.request.transaction.RechargeRequest;
import example.entity.response.transaction.RechargeResponse;
import example.mapper.UserMapper;
import example.mapper.transaction.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.ReBody;

import java.sql.Timestamp;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/2 下午11:43
 */
@Service
public class RechargeService {
    @Autowired
    TransactionMapper transactionMapper;
    @Autowired
    UserMapper userMapper;
    /**
     * description: 充值方法
     * @paramType [example.entity.request.transaction.RechargeRequest]
     * @param request:
     * @returnType: example.entity.response.transaction.RechargeResponse
     * @author: GodHammer
     * @date: 2023-12-02 下午11:44
     */
    public ReBody recharge(RechargeRequest request){
        ReBody reBody = new ReBody();
        RechargeResponse rechargeResponse = new RechargeResponse();
        //查询出来目标用户余额 和 打款用户余额
        User sender = new User();
        User receiver = new User();
        sender.setUserId(request.getSenderId());
        receiver.setUserId(request.getReceiverId());
        sender = userMapper.selectOne(sender);
        receiver = userMapper.selectOne(receiver);
        //运算得到两人交易过后的余额
        sender.setUserMoney(sender.getUserMoney() - request.getHowMuch());
        receiver.setUserMoney(receiver.getUserMoney() + request.getHowMuch());
        userMapper.update(sender);
        userMapper.update(receiver);
        //创建订单信息，插入数据库
        Transaction transactionSend = requestToTransaction(request,sender.getUserMoney());
        Transaction transactionReceiver = requestToTransaction(request,receiver.getUserMoney());
        transactionMapper.insert(transactionSend);
        transactionMapper.insert(transactionReceiver);

        //一共十个属性
        rechargeResponse.setTransId(transactionSend.getTransId());
        rechargeResponse.setTransReceiverId(transactionReceiver.getTransReceiverId());
        rechargeResponse.setTransSenderId(rechargeResponse.getTransSenderId());
        rechargeResponse.setTransPayWay(transactionSend.getTransPayWay());
        rechargeResponse.setTransState(transactionSend.getTransState());

        rechargeResponse.setTransType(transactionSend.getTransType());
        rechargeResponse.setTransTime(transactionSend.getTransTime());
        rechargeResponse.setTransHowMuch(transactionReceiver.getTransHowMuch());
        rechargeResponse.setTransRestMoney(transactionSend.getTransRestMoney());
        rechargeResponse.setTransNotes(transactionSend.getTransNote());

        reBody.setData(rechargeResponse);
        return reBody;
    }

    public Transaction requestToTransaction(RechargeRequest request,double restMoney){
        Transaction transaction = new Transaction();
        transaction.setTransId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        transaction.setTransPayWay("管理员充值");
        transaction.setTransState("processing");
        transaction.setTransType("充值");
        transaction.setTransTime(new Timestamp(System.currentTimeMillis()));
        //应该一次交易产生两条记录 一个是打款方，一个是收款方
        transaction.setTransRestMoney(restMoney);
        transaction.setTransSenderId(request.getSenderId());
        transaction.setTransReceiverId(request.getReceiverId());
        transaction.setTransNote(request.getTransNotes());
        transaction.setTransHowMuch(request.getHowMuch());
        return transaction;
    }
}
