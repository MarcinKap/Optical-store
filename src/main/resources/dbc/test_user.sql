insert into user(id,active,password,username)
values (1, 1, '$2a$10$.v1FHmepKsArMMD7PP7rmOr5vID658MwyE0Y7lqA.YGl.DS5P7Mua', 'user');

insert into role(role_id, role) values ( 1,'ADMIN');
insert into role(role_id, role) values ( 2,'USER');
insert into role(role_id, role) values ( 3,'GUEST');

INSERT INTO user_role(user_id, role_id) VALUES ( 1,1 );
INSERT INTO user_role(user_id, role_id) VALUES ( 1,2 );
INSERT INTO user_role(user_id, role_id) VALUES ( 1,3 );
