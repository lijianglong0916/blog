-- auto-generated definition
create table fabulous
(
    id          int primary key auto_increment,
    user_id     bigint     not null comment '点赞用户名',
    question_id int        not null comment '被点赞文章id',
    exist_state tinyint(1) not null comment '逻辑删除状态',
    constraint fabulous_id_uindex
        unique (id)
);



