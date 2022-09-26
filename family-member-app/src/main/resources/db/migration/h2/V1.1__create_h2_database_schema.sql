drop table if exists family_member;

create table family_member
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY not null primary key,
    family_id   varchar(36),
    family_name varchar(255),
    given_name  varchar(255),
    age         INTEGER
);
