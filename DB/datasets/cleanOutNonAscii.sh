#!/bin/bash
perl -pe's/[[:^ascii:]]//g' < input.txt > output.txt
