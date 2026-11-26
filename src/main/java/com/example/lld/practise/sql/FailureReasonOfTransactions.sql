--- I want to return failure reasons of transactions, separated by commas, sorted first descending by number of occurrences, then ascending by reason
--- id, amount, status, failure reason
--- give me MySql query

1. Sample input data
Let's assume we have the following transactions table.
id	amount	status	failure_reason
1	100	success	NULL
2	200	failure	TIMEOUT
3	300	failure	INSUFFICIENT_FUNDS
4	150	failure	TIMEOUT
5	400	success	NULL
6	250	failure	INSUFFICIENT_FUNDS
7	350	failure	INVALID_ACCOUNT
8	500	failure	TIMEOUT
9	600	failure	NULL
10	700	success	NULL

We want the final result to look something like this:
status	total_transactions	total_amount	failure_reasons
failure	7	2400.00	TIMEOUT,INSUFFICIENT_FUNDS,INVALID_ACCOUNT,Unknown
success	3	1200.00	N/A

-- Group concat combines multiple rows into single string.

WITH reason_counts AS (
    SELECT
        COALESCE(failure_reason, 'Unknown') AS failure_reason,
        COUNT(*) AS occurrences
    FROM transactions
    WHERE status = 'failure'
    GROUP BY COALESCE(failure_reason, 'Unknown')
),
reason_list AS (
    SELECT
        GROUP_CONCAT(
            failure_reason
            ORDER BY occurrences DESC, failure_reason ASC
            SEPARATOR ','
        ) AS failure_reasons
    FROM reason_counts
),
transaction_summary AS (
    SELECT
        status,
        COUNT(*) AS total_transactions,
        CAST(SUM(amount) AS DECIMAL(18,2)) AS total_amount -- 18 total digits and 2 after decimal count
    FROM transactions
    GROUP BY status
)
SELECT
    ts.status,
    ts.total_transactions,
    ts.total_amount,
    CASE
        WHEN ts.status = 'success' THEN 'N/A'
        ELSE COALESCE(rl.failure_reasons, 'N/A')
    END AS failure_reasons
FROM transaction_summary ts
CROSS JOIN reason_list rl
ORDER BY ts.status ASC;

--To debug the result
-- use Select * from reason_count;
-- select * from reason_list;
-- select * from transaction_summary;
