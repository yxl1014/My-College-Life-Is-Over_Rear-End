create table sys_power
(
    power_id          int auto_increment comment '权限ID 200位 两百种权限'
        primary key,
    power_name        varchar(30)             not null comment '权限名称',
    power_type        tinyint                 not null comment '权限类型 1个字节 8位 8种类型 比如1000 0000是可操作 0100 0000是可读',
    power_create_time datetime                null comment '创建时间 用于排序',
    power_notes       varchar(500) default '' null comment '备注'
)
    comment '权限表' charset = utf8mb3;

