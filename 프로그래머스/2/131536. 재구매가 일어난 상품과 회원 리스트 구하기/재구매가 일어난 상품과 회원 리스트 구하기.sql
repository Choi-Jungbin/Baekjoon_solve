-- 코드를 입력하세요
SELECT USER_ID, PRODUCT_ID from online_sale group by user_id, product_id having count(*) > 1 order by user_id, product_id desc;