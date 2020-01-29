# import modules
import os 
import sys 
import read_file
import scan 
# Ask user file path
file_name=input("Enter File Path for Scan: ")
# Read file as array
input_string=read_file.read_file_as_array(file_name)
# Read file as array
print(input_string.replace("\n"," "))
list_of_data=read_file.read_file_as_array()
for scan_data in range(0,len(list_of_data)):
	input_data=input_string.replace("\n"," ")
	data_fo_found=list_of_data[scan_data].replace("()","")
	count=scan.count_substr({})
	print(list_of_data[count]+ ' :'+count)