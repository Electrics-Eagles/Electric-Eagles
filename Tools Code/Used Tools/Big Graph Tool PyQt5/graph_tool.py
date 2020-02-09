# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'C:\Users\JustProgrammer\PycharmProjects\GraphPloter\graph_tool.ui'
#
# Created by: PyQt5 UI code generator 5.13.0
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets
import pyqtgraph as python_graph_plotter


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout_2 = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout_2.setObjectName("gridLayout_2")
        self.gridLayout_3 = QtWidgets.QGridLayout()
        self.gridLayout_3.setObjectName("gridLayout_3")
        self.gridLayout_2.addLayout(self.gridLayout_3, 0, 2, 1, 1)
        self.gridLayout = QtWidgets.QGridLayout()
        self.gridLayout.setObjectName("gridLayout")
        python_graph_plotter.setConfigOption('background', 'w')
        python_graph_plotter.setConfigOption('foreground', 'k')
        self.graphicsView = python_graph_plotter.PlotWidget(self.centralwidget)
        self.graphicsView.setObjectName("graphicsView")
        self.gridLayout.addWidget(self.graphicsView, 0, 0, 1, 1)
        self.gridLayout_2.addLayout(self.gridLayout, 0, 1, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 800, 26))
        self.menubar.setObjectName("menubar")
        self.menuFiles = QtWidgets.QMenu(self.menubar)
        self.menuFiles.setObjectName("menuFiles")
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)
        self.actionOpen_FIle = QtWidgets.QAction(MainWindow)
        self.actionOpen_FIle.setObjectName("actionOpen_FIle")
        self.actionExport_as_PNG = QtWidgets.QAction(MainWindow)
        self.actionExport_as_PNG.setObjectName("actionExport_as_PNG")
        self.menuFiles.addSeparator()
        self.menuFiles.addAction(self.actionOpen_FIle)
        self.menuFiles.addAction(self.actionExport_as_PNG)
        self.menubar.addAction(self.menuFiles.menuAction())
        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.menuFiles.setTitle(_translate("MainWindow", "Files"))
        self.actionOpen_FIle.setText(_translate("MainWindow", "Open FIle"))
        self.actionExport_as_PNG.setText(_translate("MainWindow", "Export as PNG"))
