# Graph Ploter For Really Big Data.
# Written for Elecrics Eagles
# Tested on Python 3.7 on Windows 10 1909
# It opens file with three columms and build graph.

import easygui
import os
import matplotlib.pyplot as plot_system
import sys
def clear_graph():
	plot_system.cla()
	plot_system.clf()
	plot_system.close()
pass
def ask_file():
	try:
		data_file=easygui.fileopenbox("Open a file with 3 colums and delimeter ;")
		data=open(data_file,"r",encoding='UTF-8')
		os.chdir(easygui.diropenbox("Place to Savew Graphs"))
		raw_data=data.readlines()
		x_axis=[]
		y_axis=[]
		z_axis=[]
		for list_of_strings in range (0,len(raw_data)):
			data_too_graph=raw_data[list_of_strings].split(";")
			x_axis.append(int(data_too_graph[0]))
			y_axis.append(int(data_too_graph[1]))
			z_axis.append(int(data_too_graph[2]))
		plot_system.rc('grid', linestyle="-", color='black')
		
		plot_system.plot(x_axis,linewidth=2, markersize=12,color='green')
		plot_system.savefig('x_axis_no_grid.png')
		clear_graph()
		plot_system.grid(True)
		plot_system.plot(x_axis,linewidth=2, markersize=12,color='green')
		plot_system.savefig('x_axis_grid.png')
		clear_graph()
		plot_system.plot(y_axis,linewidth=2, markersize=12,color='red')
		plot_system.savefig('y_axis_no_grid.png')
		clear_graph()
		plot_system.plot(y_axis,linewidth=2, markersize=12,color='red')
		plot_system.grid(True)
		plot_system.savefig('y_axis_grid.png')
		clear_graph()
		plot_system.plot(z_axis,linewidth=2, markersize=12,color='yellow')
		plot_system.savefig('z_axis_no_grid.png')
		clear_graph()
		plot_system.grid(True)
		plot_system.plot(z_axis,linewidth=2, markersize=12,color='yellow')
		plot_system.savefig('z_axis_grid.png')
		clear_graph()
		plot_system.plot(z_axis,linewidth=2, markersize=12,color='yellow')
		plot_system.plot(y_axis,linewidth=2, markersize=12,color='red')
		plot_system.plot(x_axis,linewidth=2, markersize=12,color='green')
		plot_system.savefig('all_togeter_no_grid.png')
		clear_graph()
		plot_system.grid(True)
		plot_system.plot(z_axis,linewidth=2, markersize=12,color='yellow')
		plot_system.plot(y_axis,linewidth=2, markersize=12,color='red')
		plot_system.plot(x_axis,linewidth=2, markersize=12,color='green')
		plot_system.savefig('all_togeter_grid.png')
		clear_graph()
	

	except :
		easygui.exceptionbox()


ask_file()




