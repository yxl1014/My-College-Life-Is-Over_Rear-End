create table sys_task
(
    task_id          varchar(135)     not null
        primary key,
    task_author_id   varchar(135)     not null comment '发布者ID',
    task_money       double default 0 not null comment '报酬',
    task_create_time bigint           not null comment '发布时间',
    task_type        int    default 0 not null comment '（0、一直发，1、某个时间点发，2、间隔时间发）',
    task_shell       mediumtext       null comment '脚本（Json序列化后的List<TaskShell>）',
    task_state       int    default 0 null comment '状态 (0、规划中，1、报名中，2、测试中，3、暂停，4、结束)',
    task_start_time  bigint           null,
    task_end_time    bigint           null,
    task_test_time   bigint           null comment '任务持续时间',
    task_test_result mediumtext       null comment '任务测试结果'
);

create index sys_task_task_author_id_index
    on sys_task (task_author_id);

create index sys_task_task_state_index
    on sys_task (task_state);

