SELECT employers.fullname FROM employers 
JOIN addresses ON addresses.id = employers.id_address
WHERE addresses.neighbourhood = 'Neigh' 
AND addresses.county = 'UZAO';
