DROP TABLE ERS_REIMBURSEMENT_STATUS; 
DROP TABLE ERS_REIMBURSEMENT_TYPE;
DROP TABLE ERS_REIMBURSEMENTS; 
DROP TABLE ERS_USER_ROLES; 
DROP TABLE ERS_USERS; 

CREATE TABLE ERS_USERS
( 
    U_ID        NUMBER(15),  
    U_USERNAME  VARCHAR2(40) UNIQUE, 
    U_PASSWORD  VARCHAR2(40),
    U_FIRSTNAME VARCHAR2(30),
    U_LASTNAME  VARCHAR2(30), 
    U_EMAIL     VARCHAR2(100), 
    UR_ID       NUMBER(5),
    PRIMARY KEY(u_id)
);

DROP SEQUENCE ERS_USERS_SEQUENCE; 

--Create my sequence for the id's 
CREATE SEQUENCE ERS_USERS_SEQUENCE
    START WITH 1
    INCREMENT BY 1;

--Create my trigger for inserting accounts with a null ID 
CREATE OR REPLACE TRIGGER ERS_USERS_INSERT --add b or a for before or after
BEFORE INSERT ON ERS_USERS --choice of AFTER or BERFORE
FOR EACH ROW --require if you want to see/manipulate rows of data
BEGIN
    IF :NEW.U_ID IS NULL THEN
        SELECT ERS_USERS_SEQUENCE.NEXTVAL INTO :NEW.U_ID FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE createUser(m_username VARCHAR2, m_password VARCHAR2, m_firstname VARCHAR2, m_lastname VARCHAR2, m_email VARCHAR2, m_role VARCHAR2) 
IS 
    USER_TYPE NUMBER(15); 
BEGIN 
    IF m_role = 'manager' THEN
      USER_TYPE := 2;
    ELSE
      USER_TYPE := 1;
    END IF;

    INSERT INTO ERS_USERS VALUES 
    (NULL, m_username, m_password, m_firstname, m_lastname, m_email, USER_TYPE);
    /** (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS);  **/ 
    /** SELECT UR_ID INTO M_UR_ID FROM ERS_USERS WHERE U_USERNAME = m_username;**/ 
    COMMIT;
END;
/

CREATE TABLE ERS_USER_ROLES 
(
    UR_ID NUMBER(15),
    UR_ROLE VARCHAR(40)
);

CREATE TABLE ERS_REIMBURSEMENTS
(
    R_ID          NUMBER(15), 
    R_AMOUNT      NUMBER(20), 
    R_DESCRIPTION VARCHAR2(100 BYTE), 
    R_RECEIPT     BLOB, 
    R_SUBMITTED   TIMESTAMP, 
    R_RESOLVED    TIMESTAMP, 
    U_ID_AUTHOR   NUMBER(15) REFERENCES ERS_USERS(U_ID), 
    U_ID_RESOLVER NUMBER(15), 
    RT_TYPE       NUMBER(15), 
    RT_STATUS     NUMBER, 
    PRIMARY KEY(R_ID)
);

DROP SEQUENCE ERS_REIMBURSEMENT_SEQUENCE;

CREATE SEQUENCE ERS_REIMBURSEMENT_SEQUENCE
    START WITH 1
    INCREMENT BY 1;

--Create my trigger for inserting accounts with a null ID 
CREATE OR REPLACE TRIGGER ERS_REIMBURSEMENT_INSERT --add b or a for before or after
BEFORE INSERT ON ERS_REIMBURSEMENTS --choice of AFTER or BERFORE
FOR EACH ROW --require if you want to see/manipulate rows of data
BEGIN
    IF :NEW.R_ID IS NULL THEN
        SELECT ERS_REIMBURSEMENT_SEQUENCE.NEXTVAL INTO :NEW.R_ID FROM dual;
    END IF;
END;
/

create or replace PROCEDURE createReimbursement(m_username VARCHAR2, m_ammount NUMBER, m_description VARCHAR2, m_reciept BLOB, m_type NUMBER)
IS 
    m_current_time TIMESTAMP; 
    author_id NUMBER; 
BEGIN 
    SELECT U_ID INTO author_id FROM ERS_USERS WHERE U_USERNAME = m_username; 

    SELECT CURRENT_TIMESTAMP INTO m_current_time FROM DUAL; 
    INSERT INTO ERS_REIMBURSEMENTS VALUES 
    (NULL, m_ammount, m_description, m_reciept, m_current_time, NULL, author_id, NULL, m_type, 0); 
    /** (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS);  **/ 
    COMMIT;
END;
/

create or replace PROCEDURE approveReimbursement(m_resolver_id NUMBER, m_rei_id NUMBER)
IS 
    m_current_time TIMESTAMP;
BEGIN 
    SELECT CURRENT_TIMESTAMP INTO m_current_time FROM DUAL; 
        
    UPDATE ERS_REIMBURSEMENTS
    SET R_RESOLVED = m_current_time,
        U_ID_RESOLVER = m_resolver_id, 
        RT_STATUS = 1
    WHERE R_ID = m_rei_id;

    COMMIT;
END;
/

create or replace PROCEDURE denyReimbursement(m_resolver_id NUMBER, m_rei_id NUMBER)
IS 
    m_current_time TIMESTAMP;
BEGIN 
    SELECT CURRENT_TIMESTAMP INTO m_current_time FROM DUAL; 
        
    UPDATE ERS_REIMBURSEMENTS
    SET R_RESOLVED = m_current_time,
        U_ID_RESOLVER = m_resolver_id, 
        RT_STATUS = 2
    WHERE R_ID = m_rei_id;

    COMMIT;
END;
/

CREATE TABLE ERS_REIMBURSEMENT_STATUS
(
    RS_ID NUMBER(15) REFERENCES ERS_REIMBURSEMENTS(R_ID), 
    RS_STATUS VARCHAR2(30), 
    PRIMARY KEY(RS_ID)
);

CREATE TABLE ERS_REIMBURSEMENT_TYPE
(
    RT_ID NUMBER(15) REFERENCES ERS_REIMBURSEMENTS(R_ID), 
    RT_TYPE VARCHAR2(30),
    PRIMARY KEY(RT_ID)
);

