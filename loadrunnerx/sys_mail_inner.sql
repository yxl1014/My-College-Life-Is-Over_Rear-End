create table sys_mail_inner
(
    mail_id          varchar(135)  not null comment 'javaUUID生成共有135位 包含有发送时间'
        primary key,
    mail_sender_id   varchar(135)  not null comment '发件人的用户ID',
    mail_receiver_id varchar(135)  not null comment '收件人的用户ID',
    mail_subject     varchar(255)  not null comment '邮件主题',
    mail_message     varchar(5000) not null comment '邮件内容',
    mail_attachment  json          null comment '附件信息（可以存储物品、金币等）',
    mail_sent_date   datetime      not null comment '发送日期和时间  用此时间来做排序',
    mail_type        tinyint       not null comment '0系统邮件 1好友邮件(如王者中送砖石金币等信息) 3消息记录(如王者中组队邀请) 4礼物记录(如王者中赠送皮肤) 5感谢信(如王者中礼物记录里面的感谢信板块)',
    mail_status      tinyint       not null comment '-1已删除 0未读 1已读',
    constraint sys_mail_inner_ibfk_1
        foreign key (mail_sender_id) references sys_user (user_id),
    constraint sys_mail_inner_ibfk_2
        foreign key (mail_receiver_id) references sys_user (user_id)
)
    comment '邮件表' charset = utf8mb3;

create index mail_receiver_id
    on sys_mail_inner (mail_receiver_id);

create index mail_sender_id
    on sys_mail_inner (mail_sender_id);

