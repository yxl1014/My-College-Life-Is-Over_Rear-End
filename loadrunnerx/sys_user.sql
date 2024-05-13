create table sys_user
(
    user_id                varchar(135)      not null comment '不作为用户登录的id 只是唯一标识用户id 包含有用户建立账号时间,javaUUID类生成的UUID为128位 多7位扩展'
        primary key,
    user_name              varchar(25)       not null comment '用户用户名 邮箱 电话都可作为用户账号登录',
    user_telephone         varchar(18)       null comment '用户电话:11位纯数字  电话必须填写',
    user_sys_email         varchar(25)       null comment '用户邮箱:xxx@qq.com 邮箱可不填写 不通过邮箱登录',
    user_password          varchar(160)      not null comment '用户密码 20位英文+字符+数字组合  经过hash过后的密码长度为149个字节',
    user_nick_name         varchar(40)       null comment '用户昵称 最多40个字符',
    user_gender            varchar(10)       null comment '用户性别：男 女',
    user_born_day          date              null comment '用户出生年月日: 2000/12/1',
    user_id_card           varchar(20)       null comment '用户身份证号:18位',
    user_money             double  default 0 null comment '用户余额 ',
    user_company           varchar(100)      null comment '用户单位',
    user_home              varchar(100)      null comment '用户住址',
    user_ip                varchar(20)       null comment '用户IP',
    user_flag              tinyint default 0 not null comment '0000 0000 首位是1:1xxx xxxx代表已删除  第二位是1:x1xx xxxx代表停用 后六位：xx 000000代表管理员  000001普通用户 000010VIP用户',
    user_personal_profile  varchar(500)      null comment '用户个人简介',
    user_create_time       datetime          not null comment '用户账号的创立日期 用此字段给user们排序呈现 并作为UUID生成的一个参数',
    user_sec_problem_one   varchar(100)      null comment '密保问题1  鉴于密保问题都是10几个字20个字左右的问题 unicode编码一个汉字三个字节，就用100个字符varchar',
    user_sec_answer_one    varchar(60)       null comment '密保问题1答案 答案一般都比较简短10几个字左右 就用varchar60',
    user_sec_problem_two   varchar(100)      null comment '密保问题2',
    user_sec_answer_two    varchar(60)       null comment '密保问题2答案',
    user_sec_problem_three varchar(100)      null comment '密保问题3',
    user_sec_answer_three  varchar(60)       null comment '密保问题3答案',
    constraint user_name
        unique (user_name),
    constraint user_sys_email
        unique (user_sys_email),
    constraint user_telephone
        unique (user_telephone)
)
    comment '用户表' charset = utf8mb3;

