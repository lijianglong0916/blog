alter table question
    modify gmt_create date null;

alter table question
    modify gmt_modified date null comment '最近的修改时间';
