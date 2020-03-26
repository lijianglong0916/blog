create table root_user
(
    id      int auto_increment primary key,
    user_id int not null
);

create unique index root_user_id_uindex
    on root_user (id);

create unique index root_user_user_id_uindex
    on root_user (user_id);


