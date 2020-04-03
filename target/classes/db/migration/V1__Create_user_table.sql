-- auto-generated definition
create table user
(
    id           int auto_increment,
    account_id   bigint primary key not null,
    name         varchar(50)        null,
    gmt_creat    bigint             null,
    gmt_modified bigint             null,
    password     varchar(50)        not null,
    constraint account_id
        unique (account_id),
    constraint user_id_uindex
        unique (id),
    constraint user_name_uindex
        unique (name)
);



