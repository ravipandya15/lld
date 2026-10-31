--Return the most recent transaction for each account
--transactions(transaction_id, account_id, amount, status, created_at)

--1. With group by and max

select account_id, MAX(created_at)
from transactions
group by account_id

--2. With window functions
-- partition by, order by with the use of row_number() and dense_rank()
select *
from
(
    select *, row_number() over (partition by account_id order by created_at desc) as number
    from transactions
)
where number = 1;

