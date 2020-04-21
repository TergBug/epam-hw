drop table if exists movements;
commit;
drop table if exists products;
commit;
drop table if exists storages;
commit;

create table if not exists storages
(
    id   int primary key auto_increment,
    name varchar(255) not null default '',
    unique (name)
);
create table if not exists products
(
    id   int primary key auto_increment,
    name varchar(255) not null default '',
    unique (name)
);
create table if not exists movements
(
    id int primary key auto_increment,
    date date,
    storage_id int,
    product_id int,
    quantity int,
    foreign key (storage_id) references storages (id),
    foreign key (product_id) references products (id)
);
commit;