CREATE OR REPLACE PROCEDURE init 
IS 
    m_id NUMBER; 
    m_u_id NUMBER; 
BEGIN 

DELETE FROM ERS_REIMBURSEMENTS; 
DELETE FROM ERS_USERS; 
DELETE FROM ERS_USER_ROLES;

INSERT INTO ERS_USER_ROLES VALUES (1, 'employee'); 
INSERT INTO ERS_USER_ROLES VALUES (2, 'manager'); 

createUser('user01', 'pass1234', 'Patrick', 'Kennedy', 'patrickjk94@gmail.com', 'employee');  
createUser('user02', 'pass1234', 'Talia', 'Raspody', 'talia12@gmail.com', 'manager');  
createUser('user03', 'pass1234', 'Josh', 'Radical', 'josh13@gmail.com', 'employee'); 

/** m_username VARCHAR2, m_ammount NUMBER, m_description VARCHAR2, m_reciept BLOB, m_type NUMBER) **/ 
createReimbursement('user01', 50, 'Food', NULL, 1);
createReimbursement('user03', 20, 'Weapon', NULL, 3); 
createReimbursement('user02', 30, 'Tool', NULL, 4); 

approveReimbursement(1, 2); 

COMMIT;

END; 
/

BEGIN 
    init();
END;
/

SELECT * FROM ERS_USER_ROLES; 
SELECT * FROM ERS_USERS; 
SELECT * FROM ERS_REIMBURSEMENTS; 

SELECT U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ROLE
    FROM ERS_USERS JOIN ERS_USER_ROLES
    ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID; 

SELECT * FROM ERS_USER_ROLES; 

SELECT *                        
FROM ERS_REIMBURSEMENTS
WHERE U_ID_AUTHOR = 1012;