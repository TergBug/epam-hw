use uboafn2j5p8ew05f;
insert into skills(id, name)
values ('1', 'Java');
insert into skills(id, name)
values ('2', 'C#');
insert into skills(id, name)
values ('3', 'JDBC');
commit;
insert into accounts(id, name, status)
values ('1', 'LiXiao', 'ACTIVE');
insert into accounts(id, name, status)
values ('2', 'Din', 'DELETED');
insert into accounts(id, name, status)
values ('3', 'Geek', 'BANNED');
commit;
insert into developers(id, first_name, last_name, account_id)
values ('1', 'Din', 'Ford', '2');
insert into developers(id, first_name, last_name, account_id)
values ('2', 'Xiaoming', 'Li', '1');
insert into developers(id, first_name, last_name, account_id)
values ('3', 'Gird', 'Long', '3');
commit;
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
commit;