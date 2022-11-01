drop table if exists Passwds;

create table Passwds
(
    id         identity,
    resource_n    varchar(140) not null,
    passwd    varchar(140) not null,
    creation_date timestamp    not null
);

drop table if exists PasswcUser;

create table PasswcUser
(
    id       identity,
    username varchar(20) unique not null,
    passwd varchar(20)        not null
);