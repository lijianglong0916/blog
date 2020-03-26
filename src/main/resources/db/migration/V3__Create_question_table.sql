-- auto-generated definition
create table question
(
    title         varchar(50)   null,
    id            int auto_increment
        primary key,
    description   mediumtext    null,
    gmt_create    bigint        null,
    gmt_modified  bigint        null,
    creatorId     bigint        not null,
    comment_count int default 0 not null,
    read_count    int default 0 not null,
    like_count    int default 0 null,
    tag           varchar(20)   null
);

