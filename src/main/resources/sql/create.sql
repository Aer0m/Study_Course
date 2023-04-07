WITH new_address AS (
	INSERT INTO addresses (full_address, neighbourhood, county)
	VALUES ('Address322', 'Strogino', 'СЗАО')
	RETURNING id
), new_time AS(
	INSERT INTO schedule (begintime, endtime)
	VALUES('09:00:00', '19:00:00')
	RETURNING id
)
INSERT INTO employers (fullname, age, id_address, id_schedule)
VALUES ('Ivan Ivanov', 54, (SELECT id FROM new_address), (SELECT id FROM new_time));