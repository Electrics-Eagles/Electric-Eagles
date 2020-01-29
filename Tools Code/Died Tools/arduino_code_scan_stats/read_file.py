def read_file_as_string(path):
	file = open(path,mode='r')
 
# read all lines at once
	all_of_it = file.read()
 
# close the file
	file.close()
	return all_of_it

def read_file_as_array(path):
	file=open(path,mode='r')
	return file.readlines()
