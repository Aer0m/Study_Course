SELECT employers.id, employers.fullname, employers.age,
addresses.county, addresses.neighbourhood, addresses.full_address, 
schedule.begintime, schedule.endtime
FROM employers
JOIN addresses ON 
addresses.id = employers.id_address
JOIN schedule ON
schedule.id = employers.id_schedule ORDER BY fullname;