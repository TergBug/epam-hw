use uboafn2j5p8ew05f;
insert into security_users (username, password, enabled)
values ('user', '$2y$12$COBXcOilGx5kG8WoEVmANuwyl9PHO1jfXMH9Xz.zFYJuYFDRHmNne', 1);
commit;
insert into security_authorities(username, authority)
values ('user', 'USER');
commit;
insert into security_users (username, password, enabled)
values ('admin', '$2y$12$r2WOi137X1H3aZxTKFGWm.hHrzYoEyMC4.sTDPxfvORrHC1MskOge', 1);
commit;
insert into security_authorities(username, authority)
values ('admin', 'ADMIN');
commit;