-- 코드를 입력하세요
select
    date as MONTH,
    CAR_ID,
    record as RECORDS
from (
    select 
        date_format(start_date, "%m") + 0 as date,
        car_id,
        count(*) as record
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where start_date between '2022-08-01' and '2022-10-31'
    group by car_id, date_format(start_date, "%Y-%m")
) monthly
where car_id in (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where start_date between '2022-08-01' and '2022-10-31'
    group by car_id
    having count(*) >= 5
)
order by MONTH, car_id desc;
    