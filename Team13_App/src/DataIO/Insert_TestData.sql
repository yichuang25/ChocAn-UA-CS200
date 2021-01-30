DELETE from Login where username is not NULL; 

INSERT into Login (username, password, lastlogin, role)
VALUES('manager1', 'pass123', '11/01/2019', 'm');
INSERT into Login (username, password, lastlogin, role)
VALUES('operator1', 'pass1234', '11/01/2019', 'o');
INSERT into Login (username, password, lastlogin, role)
VALUES('manger2', 'pass12333', '11/01/2019', 'm');
INSERT into Login (username, password, lastlogin, role)
VALUES('manger3', 'password111', '11/01/2019', 'm');
INSERT into Login (username, password, lastlogin, role)
VALUES('operator2', 'oopass123', '11/01/2019', 'o');
INSERT into Login (username, password, lastlogin, role)
VALUES('200000004', 'providerpass', '11/01/2019', 'p');

--SELECT * from Login; 

DELETE from Member where memberid is not NULL;

INSERT into Member (memberid, MemberName, MemberEmail, MemberAddress, MemberCity, MemberState, MemberZipCode, Memberbalance, ValidMember)
VALUES (100000001, 'John Dow', 'jd@ua.edu', '100 University Rd', 'Tuscaloosa', 'AL', '35411', 0, false);
INSERT into Member (MemberID, MemberName, MemberEmail, MemberAddress, MemberCity, MemberState, MemberZipCode, Memberbalance, ValidMember)
VALUES (100000002, 'Jane Dow', 'jd@crimson.ua.edu', '123 University Rd', 'Tuscaloosa', 'AL', '35411', 100, true);
INSERT into Member (MemberID, MemberName, MemberEmail, MemberAddress, MemberCity, MemberState, MemberZipCode, Memberbalance, ValidMember)
VALUES (100000003, 'Mr. Nobody', 'nobody@ua.edu', 'middle', 'Nowhere', 'XX', '40404', 0, false);
INSERT into Member (MemberID, MemberName, MemberEmail, MemberAddress, MemberCity, MemberState, MemberZipCode, Memberbalance, ValidMember)
VALUES (100000004, 'Im Rich', 'rich@crimson.ua.edu', '123 Mony Rd', 'RichLand', 'RR', '99999', 999999, true);

--SELECT * from Member;

DELETE from Provider where providerid is not NULL; 

insert into Provider (ProviderID, ProviderName, ProviderEmail, ProviderAddress, ProviderCity, ProviderState, ProviderZipCode)
VALUES (200000001, 'Very Capable', 'vc@chocan.com', 'Next door', 'yourtown', 'AL', '12345');
insert into Provider (ProviderID, ProviderName, ProviderEmail, ProviderAddress, ProviderCity, ProviderState, ProviderZipCode)
VALUES (200000002, 'Do One Thing', 'dot@chocan.com', '1000 provider drive', 'ChocAn City', 'AL', '10000');
insert into Provider (ProviderID, ProviderName, ProviderEmail, ProviderAddress, ProviderCity, ProviderState, ProviderZipCode)
VALUES (200000003, 'Work Hard', 'wh@chocan.com', '996 provider drive', 'ChocAn City', 'AL', '10000');
insert into Provider (ProviderID, ProviderName, ProviderEmail, ProviderAddress, ProviderCity, ProviderState, ProviderZipCode)
VALUES (200000004, 'Provide 4', 'p4@chocan.com', '4 provider drive', 'ChocAn City', 'AL', '10000');

--SELECT * from Provider; 

DELETE from Service where servicecode is not NULL;

INSERT into Service (ServiceCode, ServiceName, ServicePrice)
VALUES (100001, "service1", 19.99);
INSERT into Service (ServiceCode, ServiceName, ServicePrice)
VALUES (100002, "service2", 99.99);
INSERT into Service (ServiceCode, ServiceName, ServicePrice)
VALUES (100003, "service3", 999.99);
INSERT into Service (ServiceCode, ServiceName, ServicePrice)
VALUES (100004, "service4", 0.99);

--SELECT * from Service;

DELETE from Billing where billingid is not null; 

INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000001, 200000001, 100002, 100000004,'','11/10/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000002, 200000003, 100001, 100000002,'c1','11/11/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000003, 200000002, 100003, 100000001,'c2','11/1/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000004, 200000003, 100002, 100000004,'c3','11/10/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000005, 200000004, 100004, 100000003,'c4','11/2/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000006, 200000001, 100002, 100000004,'c5','11/10/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000007, 200000002, 100003, 100000002,'c6','11/10/2019',CURRENT_TIMESTAMP);
INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime)
values (300000008, 200000004, 100004, 100000004,'test','11/10/2019',CURRENT_TIMESTAMP);

--SELECT * from Billing;
/*
SELECT * from Billing b 
LEFT join Provider p on b.ProviderID = p.ProviderID 
LEFT join Service s on b.ServiceCode = s.ServiceCode
WHERE p.ProviderID = 200000004;
*/