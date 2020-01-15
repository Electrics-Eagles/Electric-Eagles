import os
import sys  # sys нужен для передачи argv в QApplication
import threading
import urllib.request
from pathlib import Path
from zipfile import ZipFile
import easygui
from PyQt5 import QtWidgets

import project_generate_tool  # Это наш конвертированный файл дизайна


class ExampleApp(QtWidgets.QMainWindow, project_generate_tool.Ui_MainWindow):
    def __init__(self):
        # Это здесь нужно для доступа к переменным, методам
        # и т.д. в файле design.py
        super().__init__()
        self.setupUi(self)  # Это нужно для инициализации нашего дизайна
        self.controllerTypeComboBox.addItems(['atmega328', 'atmega328old', 'atmega168'])  # add values to combobox
        for com_ports in range(0, 99):  # make loop
            self.cOMPortComboBox.addItem("COM" + str(com_ports))  # add values to combox
        self.pushButton.clicked.connect(self.project_path_select)  # add halder to button
        self.pushButton_4.clicked.connect(self.main_thread)  # add halder to button

    def project_path_select(self):
        self.path = easygui.diropenbox("Select Project Path")  # make dir open box dialog
        self.label.setText(self.path)  # display a path to text in display

    pass

    def read_config(self):
        config_file = open("./software_config.config")  # open config file
        raw = config_file.readlines()  # read it lines
        self.link = raw[1].split(";")[1]  # parse a link

    pass

    def main_thread(self):
        kernel = self.inciliace_project_thread  # create object
        call_kernel = threading.Thread(target=kernel)  # make a thread
        call_kernel.start()  # start it

    pass

    def make_config(self):
        build_file = open(self.path + "//" + self.projectNameLineEdit.text() + "_project.confg", "a",
                          encoding='UTF-8')  # create new file
        build_file.write("{ \n"
                         "\n "
                         "Drone Project : \n "
                         "Hardware:  " + self.controllerTypeComboBox.currentText() + "\n "
                                                                                     ""
                                                                                     "Port : " + self.cOMPortComboBox.currentText() + "\n"
                         + "Software : Is custom software based on Electrics Eagles software . All API and it softaware is using GPL V3 License \n \n }")

    pass

    def inciliace_project_thread(self):
        self.read_config()  # read config file
        os.system("start sublime_text_perpare.cmd")
        os.mkdir(self.path + "\\" + self.projectNameLineEdit.text())  # create project folder
        os.chdir(self.path + "\\" + self.projectNameLineEdit.text())  # change dir to created folder dir
        self.make_config()  # make config file
        urllib.request.urlretrieve(self.link,
                                   "./kernel.zip")  # downland a kernel
        with ZipFile('kernel.zip', 'r') as zipObj:  # open it as zip object
            zipObj.extractall('Main')  # extract it to folder
        os.chdir(str(Path.home()))  # return to home user folder
        os.system("sublime_text " + self.path + "//" + self.projectNameLineEdit.text())  # run sublime text


pass


def main():
    app = QtWidgets.QApplication(sys.argv)  # Новый экземпляр QApplication
    window = ExampleApp()  # Создаём объект класса ExampleApp
    window.show()  # Показываем окно
    app.exec_()  # и запускаем приложение


if __name__ == '__main__':  # Если мы запускаем файл напрямую, а не импортируем
    main()  # то запускаем функцию main()
