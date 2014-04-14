--Create Employee table
CREATE TABLE "EMPLOYEE"
  (
    "EMPID"   NUMBER NOT NULL ENABLE,
    "NAME"    VARCHAR2(10 BYTE) DEFAULT NULL,
    PRIMARY KEY ("EMPID")
  );
 
 -- insert some sample data
Insert into EMPLOYEE (EMPID,NAME) values (10,'Pankaj');
Insert into EMPLOYEE (EMPID,NAME) values (5,'Kumar');
Insert into EMPLOYEE (EMPID,NAME) values (1,'Pankaj');
commit;