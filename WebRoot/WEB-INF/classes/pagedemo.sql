--ɾ�����ݿ�
drop database if exists pagedemo;
--�������ݿ�
create database pagedemo;
use pagedemo;
--ɾ����
drop table if exists t_page;
--������
create table t_page 
(
	id int auto_increment primary key not null,
	stu_num int(10) not null,
	username varchar(30) not null,
	age int(2) not null
);
--��������
insert into t_page(stu_num,username,age) values
(
	2005112103,'������',23
);
insert into t_page(stu_num,username,age) values
(
	2005112118,'������',22
);
insert into t_page(stu_num,username,age) values
(
	2005112101,'�ú���',22
);
insert into t_page(stu_num,username,age) values
(
	2005112102,'����ΰ',22
);
insert into t_page(stu_num,username,age) values
(
	2005112104,'Ҷ��·',22
);
insert into t_page(stu_num,username,age) values
(
	2005112106,'����',22
);
insert into t_page(stu_num,username,age) values
(
	2005112107,'������',22
);
--�����ύ
commit;
