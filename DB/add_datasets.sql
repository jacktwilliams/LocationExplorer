LOAD DATA
INFILE '/var/lib/mysql-files/2015MedianIncomeByCounty.csv'
INTO TABLE county
COLUMNS terminated by ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY ' \n'
STARTING BY ''
IGNORE 1 LINES
(@ignore, name, avg_income, population, @ignore, state_name);

select * from county where name='Autauga County';

select * from county;