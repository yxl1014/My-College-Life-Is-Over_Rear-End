create table sys_task_user_ref
(
    ref_id          int auto_increment
        primary key,
    ref_user_id     varchar(135)     not null,
    ref_task_id     varchar(135)     not null,
    ref_all_req     bigint default 0 null,
    ref_success_req bigint default 0 null,
    ref_start_time  bigint           null,
    ref_end_time    bigint           null,
    ref_test_time   bigint           null,
    ref_state       int    default 0 null comment '（0、等待中，1、测试中，2、暂停，3、结束测试）'
);

create index sys_task_user_ref_8_ref_state_index
    on sys_task_user_ref (ref_state);

create index sys_task_user_ref_8_ref_task_id_index
    on sys_task_user_ref (ref_task_id);

create index sys_task_user_ref_8_ref_user_id_index
    on sys_task_user_ref (ref_user_id);

create index sys_task_user_ref_8_ref_user_id_ref_task_id_index
    on sys_task_user_ref (ref_user_id, ref_task_id);

