create table sys_user_role_ref
(
    ref_user_id varchar(135) not null comment '用户ID',
    ref_role_id smallint     not null comment '角色ID',
    primary key (ref_user_id, ref_role_id),
    constraint sys_user_role_sys_role_id_fk
        foreign key (ref_role_id) references sys_role (role_id),
    constraint sys_user_role_sys_user_id_fk
        foreign key (ref_user_id) references sys_user (user_id)
)
    comment '用户和角色关联表' charset = utf8mb3;

