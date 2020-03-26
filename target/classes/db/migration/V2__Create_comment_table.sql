-- auto-generated definition
create table comment
(
    id             int auto_increment
        primary key,
    commentText    varchar(1024) null,
    question_id    int           not null comment '评论所在的问题ID',
    comment_create bigint        null,
    comment_count  int           null,
    sub_count      int           null,
    creatorId      bigint        not null
);

