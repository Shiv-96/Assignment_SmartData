-- 1) Find total claimed_charge of the exported documents.

SELECT SUM(d.claimed_charge)AS 'Total Clamed Charged'
FROM document d 
JOIN batch b on b.id = d.batch_id 
WHERE b.status='COMPLETED';


-- 2) Find insured_name, insured_address and claimed_charge for the documents that have status "TO_REPRICE" and customer id 1 and 2.

SELECT d.insured_name, d.insured_address, d.claimed_charge
    FROM document d
    JOIN batch b ON b.id = d.batch_id
    WHERE d.status = 'TO_REPRICE'
    AND
    b.customer_id IN (1,2)