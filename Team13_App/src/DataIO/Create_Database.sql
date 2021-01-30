#--USING Master; 

drop TABLE IF EXISTS Login;

CREATE TABLE IF NOT EXISTS Login(
    UserName varchar(32) NOT NULL UNIQUE, 
    PassWord varchar(32) NOT NULL,
    LastLogin date NULL,
  	role char(1) not NULL,
  	PRIMARY KEY (UserName)
);

drop TABLE if EXISTS Service; 

CREATE TABLE IF NOT EXISTS Service(
    ServiceCode int Not NULL UNIQUE, 
  	ServiceName varchar(20) not NULL,
    ServicePrice float NOT NULL,
  	PRIMARY KEY (ServiceCode)
);

drop TABLE if EXISTS Member;

CREATE TABLE if NOT EXISTS Member(
  	MemberID int not NULL UNIQUE,
  	MemberName varchar(25) not NULL, 
  	MemberEmail varchar(64) not NULL, 
  	MemberAddress varchar(25) not NULL, 
  	MemberCity varchar(14) not NULL, 
  	MemberState char(2) not NULL, 
  	MemberZipCode int not NULL, 
  	MemberBalance float DEFAULT 0 not NULL,
  	ValidMember boolean not NULL,
  	PRIMARY KEY (MemberID)
);

drop TABLE if EXISTS Provider; 

CREATE TABLE if NOT EXISTS Provider(
  	ProviderID int not NULL UNIQUE,
  	ProviderName varchar(25) not NULL, 
  	ProviderEmail varchar(64) not NULL, 
  	ProviderAddress varchar(25) not NULL, 
  	ProviderCity varchar(14) not NULL, 
  	ProviderState varchar(2) not NULL, 
  	ProviderZipCode int not NULL, 
  	PRIMARY KEY (ProviderID)
);

drop TABLE if EXISTS Billing; 

CREATE TABLE if NOT EXISTS Billing(
  	BillingID int not NULL UNIQUE, 
  	ProviderID int NOT NULL, 
  	ServiceCode int NOT NULL, 
  	MemberID int NOT NULL, 
  	Comment varchar(100) NULL, 
  	ServicePrivideDate Date not NULL,
  	CurrentDateTime Datetime NULL, 
  	FOREIGN key (ProviderID) REFERENCES Provider(ProviderID), 
  	FOREIGN KEY (MemberID) REFERENCES Member(MemberID),
  	FOREIGN key (ServiceCode) REFERENCES Service(ServiceCode),
  	PRIMARY KEY (BillingID)
);

