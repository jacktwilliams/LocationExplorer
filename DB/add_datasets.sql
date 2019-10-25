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

INSERT INTO county (name, population, avg_income, state_name)
        SELECT countyname, population, income, state_name
    FROM excel_table
    ORDER BY countyname;

select * from county where name='Autauga County';


DROP TABLE IF EXISTS excel_table;
CREATE temporary TABLE excel_table (
ignoreId INT,
countname VARCHAR(255),
statename VARCHAR(255),
sizerank INT,
ignore0 INT,
ignore1 INT,
ignore2 INT,
ignore3 INT,
ignore4 INT,
ignore5 INT,
ignore6 INT,
ignore7 INT,
ignore8 INT,
ignore9 INT,
ignore10 INT,
ignore11 INT,
ignore12 INT,
ignore13 INT,
ignore14 INT,
ignore15 INT,
ignore16 INT,
ignore17 INT,
ignore18 INT,
ignore19 INT,
ignore20 INT,
ignore21 INT,
ignore22 INT,
ignore23 INT,
ignore24 INT,
ignore25 INT,
ignore26 INT,
ignore27 INT,
ignore28 INT,
ignore29 INT,
ignore30 INT,
ignore31 INT,
ignore32 INT,
ignore33 INT,
ignore34 INT,
ignore35 INT,
ignore36 INT,
ignore37 INT,
ignore38 INT,
ignore39 INT,
ignore40 INT,
ignore41 INT,
ignore42 INT,
ignore43 INT,
ignore44 INT,
ignore45 INT,
ignore46 INT,
ignore47 INT,
ignore48 INT,
ignore49 INT,
ignore50 INT,
ignore51 INT,
ignore52 INT,
ignore53 INT,
ignore54 INT,
ignore55 INT,
ignore56 INT,
ignore57 INT,
ignore58 INT,
ignore59 INT,
ignore60 INT,
ignore61 INT,
ignore62 INT,
ignore63 INT,
ignore64 INT,
ignore65 INT,
ignore66 INT,
ignore67 INT,
ignore68 INT,
ignore69 INT,
ignore70 INT,
ignore71 INT,
ignore72 INT,
ignore73 INT,
ignore74 INT,
ignore75 INT,
ignore76 INT,
ignore77 INT,
ignore78 INT,
ignore79 INT,
ignore80 INT,
ignore81 INT,
ignore82 INT,
ignore83 INT,
ignore84 INT,
ignore85 INT,
ignore86 INT,
ignore87 INT,
ignore88 INT,
ignore89 INT,
ignore90 INT,
ignore91 INT,
ignore92 INT,
ignore93 INT,
ignore94 INT,
ignore95 INT,
ignore96 INT,
ignore97 INT,
ignore98 INT,
ignore99 INT,
ignore100 INT,
ignore101 INT,
ignore102 INT,
ignore103 INT,
ignore104 INT,
ignore105 INT,
ignore106 INT,
ignore107 INT,
ignore108 INT,
ignore109 INT,
ignore110 INT,
ignore111 INT,
ignore112 INT,
ignore113 INT,
ignore114 INT,
ignore115 INT,
ignore116 INT,
ignore117 INT,
ignore118 INT,
ignore119 INT,
ignore120 INT,
ignore121 INT,
ignore122 INT,
ignore123 INT,
ignore124 INT,
ignore125 INT,
ignore126 INT,
ignore127 INT,
ignore128 INT,
ignore129 INT,
ignore130 INT,
ignore131 INT,
ignore132 INT,
ignore133 INT,
ignore134 INT,
ignore135 INT,
ignore136 INT,
ignore137 INT,
recentSalesPrice INT);

LOAD DATA LOCAL INFILE '/home/jack/dev/School/LocationExplorer/DB/datasets/Sales_Price_County.csv' 
INTO TABLE excel_table 
CHARACTER SET utf8
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
IGNORE 1 LINES;