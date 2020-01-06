--- C:\Users\DroneDeveloper\PycharmProjects\CopterSetUpTool\arduino.py	(original)
+++ C:\Users\DroneDeveloper\PycharmProjects\CopterSetUpTool\arduino.py	(refactored)
@@ -2,9 +2,9 @@
 
 # simple program to compile and upload Arduino code using the Arduino command line
 
-print
+print()
 print ("====PyArduinoBuilder=====")
-print
+print()
 
 #===========================
 
@@ -108,13 +108,13 @@
 	buildError = "Not a .ino file"
 
 if len(buildError) > 0:
-	print ("Sorry, can't process file - %s") %(buildError)
+	print(("Sorry, can't process file - %s") %(buildError))
 	sys.exit()
 
 	#	print buid Options
 print ("BUILD OPTIONS")
-for n,m in buildOptions.iteritems():
-	print ("%s  %s" %(n, m))
+for n,m in buildOptions.items():
+	print(("%s  %s" %(n, m)))
 
 
 #=============================
@@ -143,9 +143,9 @@
 if archiveRequired == True:
 	for line in fileinput.input(inoFileName, inplace = 1):
 		if "char archiveDirName[]" in line:
-			print ('char archiveDirName[] = "%s";' %(inoArchiveDirName))
+			print(('char archiveDirName[] = "%s";' %(inoArchiveDirName)))
 		else:
-			print (line.rstrip())
+			print((line.rstrip()))
 	fileinput.close()
 	#~ os.utime(inoFileName, None)
 
@@ -164,7 +164,7 @@
 fileinput.close()
 print ("#INCLUDE LINES")
 print (includeLines)
-print
+print()
 
 	#	now look for lines with < signifying libraries
 for n in includeLines:
@@ -178,7 +178,7 @@
 			usedLibs.append(libName)
 print ("LIBS TO BE ARCHIVED")
 print (usedLibs)
-print
+print()
 
 	#	then look for lines with " signifiying a reference to a .h file
 	#	NB the name will be a full path name
@@ -191,7 +191,7 @@
 		hFiles.append(hName)
 print (".h FILES TO BE ARCHIVED")
 print (hFiles)
-print
+print()
 
 #==============================
 	#	copy the .ino file to the directory compileDir and change its name to match the directory
@@ -206,12 +206,12 @@
 
 #===============================
 	#	call the IDE
-print ("STARTING ARDUINO -- %s\n" %(buildOptions["action"]))
+print(("STARTING ARDUINO -- %s\n" %(buildOptions["action"])))
 
 presult = subprocess.call(arduinoCommand, shell=True)
 
 if presult != 0:
-	print ("\nARDUINO FAILED - result code = %s \n" %(presult))
+	print(("\nARDUINO FAILED - result code = %s \n" %(presult)))
 	sys.exit()
 else:
 	print ("\nARDUINO SUCCESSFUL")
