#!/bin/bash

file = open("Sale_Prices_County.csv", "r")
line = file.read().split("\n")[0]
print(line)
parts = line.split(",")

script = ""
i = 0
for part in parts:
    if part[0] == "2":
        script += "ignore" + str(i)
        script += " INT,\n"
        i += 1

print(script)
