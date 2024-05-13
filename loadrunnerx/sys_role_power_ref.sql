create table sys_role_power_ref
(
    ref_role_id  smallint not null comment '角色ID',
    ref_power_id int      not null comment '权限ID',
    primary key (ref_role_id, ref_power_id),
    constraint sys_role_power_sys_power_power_id_fk
        foreign key (ref_power_id) references sys_power (power_id),
    constraint sys_role_power_sys_role_role_id_fk
        foreign key (ref_role_id) references sys_role (role_id)
)
    comment '角色和权限关联表' charset = utf8mb3;

