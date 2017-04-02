select * from employee;

select * from asset;

update asset set quantity = 0 where assetId = 1005;


select * from user_map_employee;

truncate table asset;

select * from request;

select * from Asset_Allocation;

truncate table Asset_Allocation;

desc Asset_Allocation;

select * from USER_MASTER;

insert into asset values(1001,'Pendrive','64Gb USB 3.0',110,'Available');
insert into asset values(1002,'Laptop','Thinkpad i7',15,'Available');
insert into asset values(1003,'Notepad','160 Pages',450,'Available');
insert into asset values(1004,'Notebook','160 Pages',1,'Available');



CREATE TABLE User_Master(
userid VARCHAR2(6) PRIMARY KEY,
username VARCHAR2(15),
userpassword VARCHAR2(50),
usertype VARCHAR2(10)
);

drop table Employee;



CREATE TABLE Employee(
empno NUMBER(6) PRIMARY KEY,
ename VARCHAR2(50),
job VARCHAR2(50),
mgr NUMBER(6),
hiredate DATE,
dept_id NUMBER 
);


INSERT INTO Employee values (101,'Uma','Analyst',104,'20-JAN-2017',1);
INSERT INTO Employee values (102,'Sadaf','Analyst',103,'15-FEB-2017',2);
INSERT INTO Employee values (103,'Shikha','Analyst',101,'26-FEB-2017',3);
INSERT INTO Employee values (104,'Chintan','Manager01',107,'26-FEB-2017',3);
INSERT INTO Employee values (105,'Akshay','Manager02',107,'26-FEB-2017',3);
INSERT INTO Employee values (106,'Kasbe','Trainee',107,'20-FEB-2017',3);
INSERT INTO Employee values (107,'Raunak','Trainee',107,'20-FEB-2017',3);
INSERT INTO Employee values (108,'Parth','Trainee',104,'26-FEB-2016',1);
INSERT INTO Employee values (109,'Neeraj','Trainee',104,'26-FEB-2016',1);

create table user_map_employee(
username varchar2(15),
empno number(6)
);


insert into user_map_employee values('Chintan',104);
insert into  user_map_employee values('Akshay',105);


CREATE TABLE Asset
(
assetid NUMBER UNIQUE,
assetname VARCHAR2(25),
assetdes VARCHAR2(25),
quantity NUMBER,
status VARCHAR2(15)
);


drop sequence asset_id_seq;

drop sequence request_id_seq;

drop sequence asset_allocation_id_seq;


create sequence asset_allocation_id_seq
START WITH 0001
INCREMENT BY 1;

CREATE SEQUENCE asset_id_seq
START WITH 1004
INCREMENT BY 1;

CREATE SEQUENCE request_id_seq 
start with 00001
increment by 1;


SELECT asset_id_seq.NEXTVAL FROM Asset;

INSERT INTO User_Master values ('1001','Chintan','cmistr','Manager');
INSERT INTO User_Master values ('1002','Rohit','rosawan','Admin');
INSERT INTO User_Master values ('1003','Akshay','asatam','Manager');



CREATE TABLE REQUEST(
requestid number primary key,
empno number(6), 
mgrId number(5),                   
assetid number,
requestdate date,
requestfordays number,
status varchar2(10)
);

drop table asset_Allocation;

CREATE TABLE Asset_Allocation
(
allocationid NUMBER ,
assetid NUMBER,
empno NUMBER(6),
allocation_date DATE,
release_date DATE
);





select asset_allocation_id_seq.nextval from dual;

TRUNCATE TABLE ASSET;

select sysdate,sysdate + 10  from dual;

desc table asset;