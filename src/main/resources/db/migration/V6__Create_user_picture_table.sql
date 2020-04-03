create table headPicture
(
    id           int auto_increment primary key,
    user_account bigint(20)   not null,
    picture_url  varchar(100) not null
);

create unique index head_picture_id_uindex
    on headPicture (id);

create unique index head_picture_user_account_uindex
    on headPicture (user_account);


