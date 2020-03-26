create table user_message
(
    id             int auto_increment primary key,
    user_account   bigint                     not null,
    user_name      varchar(50)                not null comment '昵称',
    user_true_name varchar(50)                null,
    sex            enum ('男','女') default '男' null,
    birthday       bigint                     null,
    position       varchar(20)                null comment '职位',
    company        varchar(50)                null,
    education      varchar(20)                null,
    school         varchar(20)                null,
    industry       varchar(20)                null comment '行业',
    introduction   text                       null comment '简介'
);

create unique index user_message_id_uindex
    on user_message (id);

create unique index user_message_user_account_uindex
    on user_message (user_account);

create unique index user_message_user_name_uindex
    on user_message (user_name);



