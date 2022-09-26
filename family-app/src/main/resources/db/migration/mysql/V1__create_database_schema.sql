drop table if exists family;

create table family
(
    id             varchar(36) not null,
    family_name    varchar(255) not null,
    nr_of_adults   INTEGER,
    nr_of_children INTEGER,
    nr_of_infants  INTEGER
) engine = InnoDB;
