create database School;
use school;

create table student(id integer primary key auto_increment, name varchar(20));
select * from student;
select * from address;

create table address(id integer primary key auto_increment, address_lines varchar(200));

truncate student;
truncate address;
drop table address;
drop table student;