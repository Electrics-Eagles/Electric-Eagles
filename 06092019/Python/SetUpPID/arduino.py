#!/usr/bin/env python

# simple program to compile and upload Arduino code using the Arduino command line

print
print ("====PyArduinoBuilder=====")
print

#===========================

import subprocess
import sys
import os
import shutil
import time
import fileinput

#===========================
	#	create some background data
	#		these need to reflect the details of your system

	#	where is the Arduino program
arduinoIdeVersion = {}
arduinoIdeVersion["1.5.6-r2"] = "/mnt/sda4/Programs/arduino-1.5.6-r2/arduino"
arduinoIdeVersion["1.6.3"] = "/mnt/sda4/Programs/arduino-1.6.3/arduino"

	#	where are libraries stored (/mnt/sdb1/SGT-Prog/Arduino/ is my Sketchbook directory)
arduinoExtraLibraries = "/mnt/sdb1/SGT-Prog/Arduino/libraries"

	#	where this program will store stuff
	#		these directories will be beside this Python program
compileDirName = "ArduinoTemp"
archiveDirName = "ArduinoUploadArchive"

	#	default build options
buildOptions = {}
buildOptions["action"] = "verify"
buildOptions["board"] = "arduino:avr:uno"
buildOptions["port"] = "/dev/ttyACM0"
buildOptions["ide"] = "1.5.6-r2"

	#	some other important variables - just here for easy reference
compileDir = ""
archiveDir = ""
arduinoProg = ""
inoFileName = ""
inoBaseName = ""
inoArchiveDirName = ""
archiveRequired = False
usedLibs = []
hFiles = []

#============================
	#	ensure directories exist
	#	and empty the compile directory

	#	first the directory used for compiling
pythonDir = os.path.dirname(os.path.realpath(__file__))
compileDir = os.path.join(pythonDir, compileDirName)
if not os.path.exists(compileDir):
	os.makedirs(compileDir)

existingFiles = os.listdir(compileDir)
for f in existingFiles:
	os.remove(os.path.join(compileDir,f))

	#	then the directory where the Archives are saved
archiveDir = os.path.join(pythonDir, archiveDirName)
if not os.path.exists(archiveDir):
	os.makedirs(archiveDir)

#=============================
	#	get the .ino file and figure out the build options
	#
	#	the stuff in the .ino file will have this format
	#	and will start at the first line in the file
	#		// python-build-start
	#		// action, verify
	#		// board, arduino:avr:uno
	#		// port, /dev/ttyACM0
	#		// ide, 1.5.6-r2
	#		// python-build-end

inoFileName = sys.argv[1]
inoBaseName, inoExt = os.path.splitext(os.path.basename(inoFileName))

numLines = 1  # in case there is no end-line
maxLines = 6
buildError = ""
if inoExt.strip() == ".ino":
	codeFile = open(inoFileName, 'r')

	startLine = codeFile.readline()[3:].strip()
	if startLine == "python-build-start":
		nextLine = codeFile.readline()[3:].strip()
		while nextLine != "python-build-end":
			buildCmd = nextLine.split(',')
			if len(buildCmd) > 1:
				buildOptions[buildCmd[0].strip()] = buildCmd[1].strip()
			numLines += 1
			if numLines >= maxLines:
				buildError = "No end line"
				break
			nextLine = codeFile.readline()[3:].strip()
	else:
		buildError = "No start line"
else:
	buildError = "Not a .ino file"

if len(buildError) > 0:
	print ("Sorry, can't process file - %s") %(buildError)
	sys.exit()

	#	print buid Options
print ("BUILD OPTIONS")
for n,m in buildOptions.iteritems():
	print ("%s  %s" %(n, m))


#=============================
	#	get the program filename for the selected IDE
arduinoProg = arduinoIdeVersion[buildOptions["ide"]]

#=============================
	#	prepare archive stuff
	#
	#	create name of directory to save the code = name-yyyymmdd-hhmmss
	#	this will go inside the directory archiveDir
inoArchiveDirName = inoBaseName + time.strftime("-%Y%m%d-%H:%M:%S")
	#	note this directory will only be created if there is a successful upload
	#	the name is figured out here to be written into the .ino file so it can be printed by the Arduino code
	#	it will appear as char archiveDirName[] = "nnnnn";

	#	if the .ino file does not have a line with char archiveDirName[] then it will be assumed
	#		that no archiving is required
	#	check for existence of line
for line in fileinput.input(inoFileName):
	if "char archiveDirName[]" in line:
		archiveRequired = True
		break
fileinput.close()

if archiveRequired == True:
	for line in fileinput.input(inoFileName, inplace = 1):
		if "char archiveDirName[]" in line:
			print ('char archiveDirName[] = "%s";' %(inoArchiveDirName))
		else:
			print (line.rstrip())
	fileinput.close()
	#~ os.utime(inoFileName, None)

#=============================
	#	figure out what libraries and .h files are used
	#	if there are .h files they will need to be copied to ArduinoTemp

	#	first get the list of all the extra libraries that exist
extraLibList = os.listdir(arduinoExtraLibraries)

	#	go through the .ino file to get any lines with #include
includeLines = []
for line in fileinput.input(inoFileName):
	if "#include" in line:
		includeLines.append(line.strip())
fileinput.close()
print ("#INCLUDE LINES")
print (includeLines)
print

	#	now look for lines with < signifying libraries
for n in includeLines:
	angleLine = n.split('<')
	if len(angleLine) > 1:
		libName = angleLine[1].split('>')
		libName = libName[0].split('.')
		libName = libName[0].strip()
			#	add the name to usedLibs if it is in the extraLibList
		if libName in extraLibList:
			usedLibs.append(libName)
print ("LIBS TO BE ARCHIVED")
print (usedLibs)
print

	#	then look for lines with " signifiying a reference to a .h file
	#	NB the name will be a full path name
for n in includeLines:
	quoteLine = n.split('"')
	if len(quoteLine) > 1:
		hName = quoteLine[1].split('"')
		hName = hName[0].strip()
			#	add the name to hFiles
		hFiles.append(hName)
print (".h FILES TO BE ARCHIVED")
print (hFiles)
print

#==============================
	#	copy the .ino file to the directory compileDir and change its name to match the directory
saveFile = os.path.join(compileDir, compileDirName + ".ino")
shutil.copy(inoFileName, saveFile)

#===============================
	#	generate the Arduino command
arduinoCommand = "%s --%s --board %s --port %s %s" %(arduinoProg, buildOptions["action"], buildOptions["board"] , buildOptions["port"], saveFile)
print ("ARDUINO COMMAND")
print (arduinoCommand)

#===============================
	#	call the IDE
print ("STARTING ARDUINO -- %s\n" %(buildOptions["action"]))

presult = subprocess.call(arduinoCommand, shell=True)

if presult != 0:
	print ("\nARDUINO FAILED - result code = %s \n" %(presult))
	sys.exit()
else:
	print ("\nARDUINO SUCCESSFUL")
		#	if we were not uploading that is the end of things
	if buildOptions["action"] != "upload":
		sys.exit()

#================================
	#	after a successful upload we may need to archive the code
if archiveRequired == True:
	print ("\nARCHIVING")
		#	create the Archive directory
	arDir = os.path.join(archiveDir, inoArchiveDirName)
	print (arDir)
		#	this ought to be a unique name - hence no need to check for duplicates
	os.makedirs(arDir)
		#	copy the code into the new directory
	shutil.copy(inoFileName, arDir)
		#	copy the .h files to the new directory
	for n in hFiles:
		shutil.copy(n, arDir)
		#	copy the used libraries to the new directory
	for n in usedLibs:
		libName = os.path.join(arduinoExtraLibraries, n)
		destDir = os.path.join(arDir, "libraries", n)
		shutil.copytree(libName, destDir)
	print ("\nARCHIVING DONE")

sys.exit()

#==============================
