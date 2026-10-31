-- Find accounts with more than 3 failed transactions in the last 24 hours.

Table: transactions
transaction_id	account_id	amount	status	created_at
1	                A101	    500	    FAILED	2026-10-31 10:00:00
2	                A101	    200	    FAILED	2026-10-31 11:00:00
3	                A101	    300	    FAILED	2026-10-31 12:00:00
4	                A101	    400	    FAILED	2026-10-31 13:00:00
5	                A102	    500	    FAILED	2026-10-31 10:00:00

select account_id, count(*) as failed_transaction_counts
from transactions
where status = 'FAILED'
and created_at >= sysdate - interval '24' hour
group by account_id
having count(*) > 3

--OR

select account_id, count(*) as failed_transaction_counts
from transactions
where status = 'FAILED'
and created_at >= sysdate - 1
group by account_id
having count(*) > 3