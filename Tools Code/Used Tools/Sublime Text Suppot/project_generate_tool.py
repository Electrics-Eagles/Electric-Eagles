# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'project_generate_tool.ui'
#
# Created by: PyQt5 UI code generator 5.13.0
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(410, 354)
        MainWindow.setStyleSheet("QPushButton { \n"
"box-shadow:inset 0px -3px 7px 0px #29bbff;\n"
"    background:linear-gradient(to bottom, #2dabf9 5%, #0688fa 100%);\n"
"    background-color:#257aa6;\n"
"    border-radius:0px;\n"
"    border:1px solid #0b0e07;\n"
"    display:inline-block;\n"
"    cursor:pointer;\n"
"    color:#ffffff;\n"
"    font-family:Arial Black;\n"
"    font-size:20px;\n"
"    padding:9px 23px;\n"
"    text-decoration:none;\n"
"    text-shadow:0px 1px 0px #263666;\n"
"}\n"
"\n"
"\n"
"QPushButton:hover {\n"
"    background-color:#257aa6;\n"
"}\n"
"QPushButton:active {\n"
"    position:relative;\n"
"    top:1px;\n"
"}\n"
"QMainWindow {\n"
"background-color: #06131a;\n"
"}\n"
"")
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout.setObjectName("gridLayout")
        self.pushButton_4 = QtWidgets.QPushButton(self.centralwidget)
        self.pushButton_4.setObjectName("pushButton_4")
        self.gridLayout.addWidget(self.pushButton_4, 1, 0, 1, 1)
        self.formLayout = QtWidgets.QFormLayout()
        self.formLayout.setObjectName("formLayout")
        self.pushButton = QtWidgets.QPushButton(self.centralwidget)
        self.pushButton.setObjectName("pushButton")
        self.formLayout.setWidget(0, QtWidgets.QFormLayout.LabelRole, self.pushButton)
        self.pushButton_2 = QtWidgets.QPushButton(self.centralwidget)
        self.pushButton_2.setObjectName("pushButton_2")
        self.formLayout.setWidget(1, QtWidgets.QFormLayout.LabelRole, self.pushButton_2)
        self.projectNameLabel = QtWidgets.QLabel(self.centralwidget)
        self.projectNameLabel.setStyleSheet("font: 87 18pt \"Arial Black\";\n"
"color: rgb(255, 255, 255);")
        self.projectNameLabel.setObjectName("projectNameLabel")
        self.formLayout.setWidget(2, QtWidgets.QFormLayout.LabelRole, self.projectNameLabel)
        self.projectNameLineEdit = QtWidgets.QLineEdit(self.centralwidget)
        self.projectNameLineEdit.setObjectName("projectNameLineEdit")
        self.formLayout.setWidget(2, QtWidgets.QFormLayout.FieldRole, self.projectNameLineEdit)
        self.controllerTypeLabel = QtWidgets.QLabel(self.centralwidget)
        self.controllerTypeLabel.setStyleSheet("font: 87 18pt \"Arial Black\";\n"
"color: rgb(255, 255, 255);")
        self.controllerTypeLabel.setObjectName("controllerTypeLabel")
        self.formLayout.setWidget(3, QtWidgets.QFormLayout.LabelRole, self.controllerTypeLabel)
        self.controllerTypeComboBox = QtWidgets.QComboBox(self.centralwidget)
        self.controllerTypeComboBox.setObjectName("controllerTypeComboBox")
        self.formLayout.setWidget(3, QtWidgets.QFormLayout.FieldRole, self.controllerTypeComboBox)
        self.cOMPortLabel = QtWidgets.QLabel(self.centralwidget)
        self.cOMPortLabel.setStyleSheet("font: 87 18pt \"Arial Black\";\n"
"color: rgb(255, 255, 255);")
        self.cOMPortLabel.setObjectName("cOMPortLabel")
        self.formLayout.setWidget(4, QtWidgets.QFormLayout.LabelRole, self.cOMPortLabel)
        self.cOMPortComboBox = QtWidgets.QComboBox(self.centralwidget)
        self.cOMPortComboBox.setObjectName("cOMPortComboBox")
        self.formLayout.setWidget(4, QtWidgets.QFormLayout.FieldRole, self.cOMPortComboBox)
        self.gridLayout.addLayout(self.formLayout, 0, 0, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "Create New Drone Project"))
        self.pushButton_4.setText(_translate("MainWindow", "Generate"))
        self.pushButton.setText(_translate("MainWindow", "Project path"))
        self.pushButton_2.setText(_translate("MainWindow", "Select Sublime Text 3  Path"))
        self.projectNameLabel.setText(_translate("MainWindow", "Project name"))
        self.controllerTypeLabel.setText(_translate("MainWindow", "Controller Type"))
        self.cOMPortLabel.setText(_translate("MainWindow", "COM Port"))
