create table sys_transaction_record
(
    transaction_id    varchar(135)                           not null comment '交易单号唯一标识符号 UUID生成 包含交易时间'
        primary key,
    sender_id         varchar(135)                           not null comment '发款方ID',
    receiver_id       varchar(135)                           not null comment '收款方ID',
    changed_money     double                                 not null comment '变动金额 单位RMB',
    transaction_time  datetime                               not null comment '交易时间',
    rest_money        double                                 not null comment '余额 单位RMB',
    pay_way           varchar(40)                            not null comment '支付来源：余额 zfb  wx  bankCard 管理员充值',
    transaction_type  varchar(30)                            not null comment '交易类型：充值 消费 转账 提现',
    transaction_state enum ('pending', 'success', 'failure') not null comment '交易状态 ：正处理 成功 失败 ',
    transaction_notes varchar(500)                           null comment '交易备注：如：来自老赵给你的农机租赁款',
    constraint sys_transaction_record_ibfk_1
        foreign key (sender_id) references sys_user (user_id),
    constraint sys_transaction_record_ibfk_2
        foreign key (receiver_id) references sys_user (user_id)
)
    comment '交易记录' charset = utf8mb3;

create index receiver_id
    on sys_transaction_record (receiver_id);

create index sender_id
    on sys_transaction_record (sender_id);

