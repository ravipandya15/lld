
ROW_NUMBER() vs RANK() vs DENSE_RANK()
Suppose we have these transactions:
transaction_id	account_id	created_at
101	            A1	        12:00
102	            A1	        12:00
103	            A1	        11:00
104	            A1	        10:00


The ranking would be:
transaction_id	ROW_NUMBER()	RANK()	DENSE_RANK()
101	            1	            1	    1
102	            2	            1	    1
103	            3	            3	    2
104	            4	            4	    3

Which one should you use?
Requirement	                                    Recommended function
Exactly one latest transaction per account	    ROW_NUMBER()
All transactions tied for the latest timestamp	RANK()
All transactions having the latest timestamp	    DENSE_RANK()

RANK is least used, as it skip the next number