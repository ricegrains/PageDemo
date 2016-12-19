--删除数据库
drop database if exists pagedemo;
--创建数据库
create database pagedemo;
use pagedemo;
--删除表
drop table if exists t_page;
--创建表
create table t_page 
(
	id int auto_increment primary key not null,
	stu_num int(10) not null,
	username varchar(30) not null,
	age int(2) not null
);
--插入数据
insert into t_page(stu_num,username,age) values
(
	2005112103,'覃潘勇',23
);
insert into t_page(stu_num,username,age) values
(
	2005112118,'文明亮',22
);
insert into t_page(stu_num,username,age) values
(
	2005112101,'矫红青',22
);
insert into t_page(stu_num,username,age) values
(
	2005112102,'陈祖伟',22
);
insert into t_page(stu_num,username,age) values
(
	2005112104,'叶升路',22
);
insert into t_page(stu_num,username,age) values
(
	2005112106,'曾理',22
);
insert into t_page(stu_num,username,age) values
(
	2005112107,'刘世启',22
);
--事务提交
commit;
