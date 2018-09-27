create table person
(
  id integer not null,
  birth_date timestamp,
  location varchar(255),
  name varchar(255),
  primary key (id)
);
	
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(10001,  'Roman', 'Mexico',sysdate());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(10002,  'James', 'New York',sysdate());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) VALUES(10003,  'Juan', 'Amsterdam',sysdate());

