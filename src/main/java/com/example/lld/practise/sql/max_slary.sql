--To get the 3rd highest salary from an employee table, you can use multiple approaches.
select salary
from (
select salary, DENSE_RANK() over (order by salary desc) as rank
from employees
) as temp
where rank = 3;
----------------------------------

-- MySQL
select distinct salary
from employees
order by salary desc
limit 1 offset 2;

--SQL Server/Oracle
select distinct salary
from employees
order by salary desc
offset 2 rows
fetch next 1 row only;