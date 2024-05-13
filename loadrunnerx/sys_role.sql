create table sys_role
(
    role_id          smallint auto_increment comment '角色ID 2个字节 32位 32种角色 '
        primary key,
    role_name        varchar(30)  not null comment '角色名称',
    role_create_time datetime     not null comment '角色创建时间用来排序',
    role_status_flag tinyint(1)   not null comment '角色状态（0正常 1停用比如需要patch 2删除）',
    role_remark      varchar(500) null comment '备注',
    constraint role_create_time
        unique (role_create_time)
)
    comment '角色表' charset = utf8mb3;

