set schema public;
insert into skills(name)
values ('Java');
insert into skills(name)
values ('C#');
insert into skills(name)
values ('JDBC');
insert into skills(name)
values ('JSON');
insert into accounts(name, status)
values ('LiXiao', 'ACTIVE');
insert into accounts(name, status)
values ('Din', 'DELETED');
insert into accounts(name, status)
values ('Geek', 'BANNED');
insert into accounts(name, status)
values ('Ford', 'ACTIVE');
insert into developers(first_name, last_name, account_id)
values ('Din', 'Ford', '2');
insert into developers(first_name, last_name, account_id)
values ('Xiaoming', 'Li', '1');
insert into developers(first_name, last_name, account_id)
values ('Gird', 'Long', '3');
insert into developers(first_name, last_name, account_id)
values ('Gordon', 'Fong', '1');
insert into developer_skill(developer_id, skill_id)
values ('1', '1');
insert into developer_skill(developer_id, skill_id)
values ('1', '3');
insert into developer_skill(developer_id, skill_id)
values ('2', '2');
insert into developer_skill(developer_id, skill_id)
values ('3', '1');
insert into developer_skill(developer_id, skill_id)
values ('3', '2');