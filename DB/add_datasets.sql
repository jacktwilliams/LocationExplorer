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

SHOW VARIABLES LIKE "secure_file_priv";

DROP TABLE IF EXISTS excel_table1;
CREATE TABLE excel_table1 (
	fullname VARCHAR(255),
    countyname VARCHAR(255),
    income INT UNSIGNED,
    population INT UNSIGNED,
    abrev VARCHAR(2),
    state_name VARCHAR(255)
);

LOAD DATA INFILE '/var/lib/mysql-files/2015MedianIncomeByCounty.csv' 
INTO TABLE excel_table1 
CHARACTER SET utf8
FIELDS TERMINATED BY ',' 
OPTIONALLY ENCLOSED BY '"' 
LINES TERMINATED BY '\n' 
IGNORE 1 LINES;

DROP TABLE IF EXISTS excel_table2;
CREATE TABLE excel_table2 (
ignoreId INT,
countname VARCHAR(255),
statename VARCHAR(255),
sizerank INT,
ignore0 VARCHAR(20),
ignore1 VARCHAR(20),
ignore2 VARCHAR(20),
ignore3 VARCHAR(20),
ignore4 VARCHAR(20),
ignore5 VARCHAR(20),
ignore6 VARCHAR(20),
ignore7 VARCHAR(20),
ignore8 VARCHAR(20),
ignore9 VARCHAR(20),
ignore10 VARCHAR(20),
ignore11 VARCHAR(20),
ignore12 VARCHAR(20),
ignore13 VARCHAR(20),
ignore14 VARCHAR(20),
ignore15 VARCHAR(20),
ignore16 VARCHAR(20),
ignore17 VARCHAR(20),
ignore18 VARCHAR(20),
ignore19 VARCHAR(20),
ignore20 VARCHAR(20),
ignore21 VARCHAR(20),
ignore22 VARCHAR(20),
ignore23 VARCHAR(20),
ignore24 VARCHAR(20),
ignore25 VARCHAR(20),
ignore26 VARCHAR(20),
ignore27 VARCHAR(20),
ignore28 VARCHAR(20),
ignore29 VARCHAR(20),
ignore30 VARCHAR(20),
ignore31 VARCHAR(20),
ignore32 VARCHAR(20),
ignore33 VARCHAR(20),
ignore34 VARCHAR(20),
ignore35 VARCHAR(20),
ignore36 VARCHAR(20),
ignore37 VARCHAR(20),
ignore38 VARCHAR(20),
ignore39 VARCHAR(20),
ignore40 VARCHAR(20),
ignore41 VARCHAR(20),
ignore42 VARCHAR(20),
ignore43 VARCHAR(20),
ignore44 VARCHAR(20),
ignore45 VARCHAR(20),
ignore46 VARCHAR(20),
ignore47 VARCHAR(20),
ignore48 VARCHAR(20),
ignore49 VARCHAR(20),
ignore50 VARCHAR(20),
ignore51 VARCHAR(20),
ignore52 VARCHAR(20),
ignore53 VARCHAR(20),
ignore54 VARCHAR(20),
ignore55 VARCHAR(20),
ignore56 VARCHAR(20),
ignore57 VARCHAR(20),
ignore58 VARCHAR(20),
ignore59 VARCHAR(20),
ignore60 VARCHAR(20),
ignore61 VARCHAR(20),
ignore62 VARCHAR(20),
ignore63 VARCHAR(20),
ignore64 VARCHAR(20),
ignore65 VARCHAR(20),
ignore66 VARCHAR(20),
ignore67 VARCHAR(20),
ignore68 VARCHAR(20),
ignore69 VARCHAR(20),
ignore70 VARCHAR(20),
ignore71 VARCHAR(20),
ignore72 VARCHAR(20),
ignore73 VARCHAR(20),
ignore74 VARCHAR(20),
ignore75 VARCHAR(20),
ignore76 VARCHAR(20),
ignore77 VARCHAR(20),
ignore78 VARCHAR(20),
ignore79 VARCHAR(20),
ignore80 VARCHAR(20),
ignore81 VARCHAR(20),
ignore82 VARCHAR(20),
ignore83 VARCHAR(20),
ignore84 VARCHAR(20),
ignore85 VARCHAR(20),
ignore86 VARCHAR(20),
ignore87 VARCHAR(20),
ignore88 VARCHAR(20),
ignore89 VARCHAR(20),
ignore90 VARCHAR(20),
ignore91 VARCHAR(20),
ignore92 VARCHAR(20),
ignore93 VARCHAR(20),
ignore94 VARCHAR(20),
ignore95 VARCHAR(20),
ignore96 VARCHAR(20),
ignore97 VARCHAR(20),
ignore98 VARCHAR(20),
ignore99 VARCHAR(20),
ignore100 VARCHAR(20),
ignore101 VARCHAR(20),
ignore102 VARCHAR(20),
ignore103 VARCHAR(20),
ignore104 VARCHAR(20),
ignore105 VARCHAR(20),
ignore106 VARCHAR(20),
ignore107 VARCHAR(20),
ignore108 VARCHAR(20),
ignore109 VARCHAR(20),
ignore110 VARCHAR(20),
ignore111 VARCHAR(20),
ignore112 VARCHAR(20),
ignore113 VARCHAR(20),
ignore114 VARCHAR(20),
ignore115 VARCHAR(20),
ignore116 VARCHAR(20),
ignore117 VARCHAR(20),
ignore118 VARCHAR(20),
ignore119 VARCHAR(20),
ignore120 VARCHAR(20),
ignore121 VARCHAR(20),
ignore122 VARCHAR(20),
ignore123 VARCHAR(20),
ignore124 VARCHAR(20),
ignore125 VARCHAR(20),
ignore126 VARCHAR(20),
ignore127 VARCHAR(20),
ignore128 VARCHAR(20),
recentSalesPrice INT,
ignore130 VARCHAR(20),
ignore131 VARCHAR(20),
ignore132 VARCHAR(20),
ignore133 VARCHAR(20),
ignore134 VARCHAR(20),
ignore135 VARCHAR(20),
ignore136 VARCHAR(20),
ignore137 VARCHAR(20),
tossLatest VARCHAR(20)); /*Last column may be empty. Use the previous month.*/

LOAD DATA INFILE '/var/lib/mysql-files/Sale_Prices_County.csv' 
INTO TABLE excel_table2 
CHARACTER SET utf8
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 LINES;

drop view if exists allData;
create view allData (countyname, population, avg_income, avg_hprice, state_name)
as select excel_table1.countyname, excel_table1.population, excel_table1.income, excel_table2.recentSalesPrice, excel_table1.state_name
from excel_table1 join excel_table2 on excel_table1.countyname = excel_table2.countname and excel_table1.state_name = excel_table2.statename;

select * from allData where countyname = 'Goodhue County';

INSERT INTO county (name, population, avgIncome, avgHPrice, stateName)
        SELECT countyName, population, avg_income, avg_hprice, state_name
    FROM allData
    ORDER BY countyName;
    
select * from county;

