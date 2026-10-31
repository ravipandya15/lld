In SQL, the main difference between COUNT(*), COUNT(1), and COUNT(column_name) is what they count and how they handle NULL values.

Let's understand with an example.
1. Sample employee table
id	name	department	salary
1	Ravi	IT	100000
2	Amit	HR	90000
3	Rahul	IT	NULL
4	Priya	NULL	80000
5	Neha	IT	NULL

2. COUNT(*) vs COUNT(1) vs COUNT(column)
COUNT(*)
SELECT COUNT(*) FROM employees;


Output: 5
- Counts all rows.
- Includes rows containing NULL values.
COUNT(1)
SELECT COUNT(1) FROM employees;


Output: 5
- Counts all rows because 1 is a non-NULL constant.
- Functionally equivalent to COUNT(*) for ordinary row counting.
COUNT(salary)
SELECT COUNT(salary) FROM employees;


Output: 3
- Counts only rows where salary is NOT NULL.
- Ignores NULL values.
COUNT(department)
SELECT COUNT(department) FROM employees;


Output: 4

- Counts only rows where department is NOT NULL.
3. Other COUNT variations
SQL	Meaning	Output
COUNT(*)	Count all rows	5
COUNT(1)	Count all rows	5
COUNT(id)	Count non-NULL IDs	5
COUNT(salary)	Count non-NULL salaries	3
COUNT(department)	Count non-NULL departments	4
COUNT(DISTINCT department)	Count unique, non-NULL departments	2

4. COUNT with GROUP BY
For example, count employees in each department:
SELECT
    department,
    COUNT(*) AS total_employees,
    COUNT(salary) AS employees_with_salary
FROM employees
GROUP BY department;


Output:
department	total_employees	employees_with_salary
IT	3	1
HR	1	1
NULL	1	1

Notice that COUNT(*) counts all employees in a group, while COUNT(salary) ignores employees whose salary is NULL.
5. Does COUNT(1) perform better than COUNT(*)?
No. In modern database engines, both are generally optimized to perform equivalently.

You can use:
COUNT(*)


This is the preferred convention because it clearly communicates that you want to count rows.
6. Important interview question: COUNT(*) vs COUNT(column) with LEFT JOIN
Suppose you have:

accounts
account_id
A1
A2
A3

transactions
transaction_id	account_id
101	A1
102	A1
103	A2

Query:
SELECT
    a.account_id,
    COUNT(*) AS total_rows,
    COUNT(t.transaction_id) AS total_transactions
FROM accounts a
LEFT JOIN transactions t
    ON a.account_id = t.account_id
GROUP BY a.account_id;


Output:
account_id	total_rows	total_transactions
A1	2	2
A2	1	1
A3	1	0

Why does A3 have total_rows = 1?

Because LEFT JOIN generates one row for A3, with NULL values for all columns from the transactions table.
- COUNT(*) counts that generated row.
- COUNT(t.transaction_id) ignores its NULL transaction ID.
This is a very common SQL interview trap!