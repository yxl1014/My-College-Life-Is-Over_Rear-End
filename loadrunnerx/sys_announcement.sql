create table sys_announcement
(
    anno_id           varchar(135)  not null comment '公告单号 包含创建时间 UUID'
        primary key,
    anno_publisher_id varchar(135)  not null comment '发布者ID',
    anno_create_time  datetime      not null comment '创建时间',
    anno_publish_time datetime      null comment '发布时间',
    anno_expire_time  datetime      null comment '过期时间',
    anno_title        varchar(130)  not null comment '公告标题',
    anno_content_body varchar(5000) not null comment '公告内容',
    anno_priority     tinyint       not null comment '公告显示优先级: 0代表最普通 1代表vip  2代表s_vip 3代表ss_vip',
    anno_link         varchar(255)  null comment '可选的公告链接',
    constraint sys_announcement_ibfk_1
        foreign key (anno_publisher_id) references sys_user (user_id)
)
    comment '公告单' charset = utf8mb3;

create index anno_publisher_id
    on sys_announcement (anno_publisher_id);

