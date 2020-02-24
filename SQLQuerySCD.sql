Let�s consider an Employee dimension (EMPLOYEE_D) of SCD Type 2 which is sourced from a table called EMPLOYEE in the source system.

EMPLOYEE Table has the following columns
ROW_ID
EMP_NO
FIRST_NAME
LAST_NAME
SSN
DOB
JOB_TITLE
SALARY

EMPLOYEE_DIM SCD Type 2 Dimension Table has the following columns
ROW_WID
EMP_NO
FIRST_NAME
LAST_NAME
SSN
DOB
JOB_TITLE
SALARY
START_DT
END_DT
CURRENT_FLG

Test 1: Verifying the Current Data
Use a Query Compare test case in ETL Validator to compare the current data records in the SCD Type 2 Employee_Dim with the data in the source Employee table. 

Source Query : select ROW_ID, EMP_NO, FIRST_NAME, LAST_NAME, SSN, DOB, JOB_TITLE, SALARY from EMPLOYEE

Target Query : select ROW_ID, EMP_NO, FIRST_NAME, LAST_NAME, SSN, DOB, JOB_TITLE, SALARY from EMPLOYEE_DIM where CURRENT_FLG = �Y�

Test 2: Verifying the uniqueness of the key columns in the SCD
The combination of the key columns in the SCD should be Unique. For the above example, the columns EMP_NO, FIRST_NAME, LAST_NAME, SSN, DOB, JOB_TITLE, SALARY comprise of an unique key in the EMPLOYEE_DIM dimension. This can be easily verified using the Duplicate Check Rule in the Data Rules test plan of ETL Validator. The query generated by ETL Validator using the Duplicate Check Rule should be something like below: 

Select EMP_NO, FIRST_NAME, LAST_NAME, SSN, DOB, JOB_TITLE, SALARY, COUNT(*) CNT from EMPLOYEE_DIM group by EMP_NO, FIRST_NAME, LAST_NAME, SSN, DOB, JOB_TITLE, SALARY having COUNT(*)>1
This query should not return any row