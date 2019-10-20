/*
LOAD DATA
LOCAL INFILE '/home/jack/dev/School/LocationExplorer/DB/datasets/2015MedianIncomeByCounty.csv'
INTO TABLE county
CHARACTER SET utf8
COLUMNS terminated by ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@ignoreb, @ignorex, name, avg_income, population, @ignorea, state_name);
*/

DROP TABLE IF EXISTS excel_table;
CREATE temporary TABLE excel_table (
	fullname VARCHAR(255),
    countyname VARCHAR(255),
    income INT UNSIGNED,
    population INT UNSIGNED,
    abrev VARCHAR(2),
    state_name VARCHAR(255)
);

LOAD DATA LOCAL INFILE '/home/jack/dev/School/LocationExplorer/DB/datasets/2015MedianIncomeByCounty.csv' 
INTO TABLE excel_table 
CHARACTER SET utf8
FIELDS TERMINATED BY ',' 
OPTIONALLY ENCLOSED BY '"' 
LINES TERMINATED BY '\n' 
IGNORE 1 LINES;

select * from excel_table;

INSERT INTO county (name, population, avg_income, state_name)
        SELECT countyname, population, income, state_name
    FROM excel_table
    ORDER BY countyname;

select * from county where name='Autauga County';
