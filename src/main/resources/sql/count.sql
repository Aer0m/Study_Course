SELECT
	COUNT(DISTINCT CASE WHEN age >= 18 AND age <= 25  THEN age END) as young,
	COUNT(DISTINCT CASE WHEN age >= 26 AND age <= 30 THEN age END) as middle,
	COUNT(DISTINCT CASE WHEN age >= 31 AND age <= 45 THEN age END) as mature,
	COUNT(DISTINCT CASE WHEN age > 44 THEN age END) as old
FROM employers;