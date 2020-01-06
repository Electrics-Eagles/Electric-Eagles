import sys  # sys нужен для передачи argv в QApplication
import time

from PyQt5 import QtWidgets
from PyQt5.QtGui import QPixmap

import proui  # Это наш конвертированный файл дизайна
import qdarkstyle
from OpenGL.GL import *
from OpenGL.GLU import *
import serial
import threading
import easygui


class ExampleApp(QtWidgets.QMainWindow, proui.Ui_MainWindow):
    def add_log(self, text, a):
        try:
            input_masive = []
            input_masive[a] = text
            self.label_9.setText(str(input_masive) + '\n')
        except Exception as error:
            easygui.msgbox("Fatal app error:" + str(error), "Error happend")

    def open_conneciton(self):
        try:
            self.serial_input = serial.Serial(self.comboBox.currentText(), int(self.comboBox_2.currentText()),
                                              timeout=1)
            self.serial_input.open()
        except Exception as error:
            easygui.msgbox("Fatal app error:" + str(error), "Error happend")

    pass

    def convert(self, x, in_min, in_max, out_min, out_max):  # Math map funcion
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min  # Math formula

    pass

    def drawimange(self, path_to_imange, label):
        try:
            pixmap = QPixmap(path_to_imange)
            scaledx = pixmap.scaledToWidth(label.width())
            scaledy = scaledx.scaledToWidth(label.height())
            label.setPixmap(scaledy)
            label.show()
        except Exception as error:
            easygui.msgbox("Fatal app error:" + str(error), "Error happend")

    def read_calibrate(self):
        try:
            self.a = 0
            while True:
                self.add_log(str(self.serial_input.readline(),self.a))
                self.a = self.a + 1

        except Exception as error:
            easygui.msgbox("Fatal app error:" + str(error), "Error happend")


    def get_data_thread(self):
        try:
            if (self.tabWidget.currentIndex() == 1):
                while True:
                    dat = self.serial_input.readline().decode('ISO-8859-1').strip()
                    self.add_log(str(dat).replace("b'", "").replace("'", ""))
            if (self.tabWidget.currentIndex() == 0):
                print("Fuxk progger")
                if (self.tabWidget.currentIndex() == 2):
                    while True:
                        # data example 1000,1000,1500,2000,-4.5,6.6,6.7,78.90
                        self.serial_input.write(b'D')  # recquect data
                        time.sleep(0.2)  # wait when arduino lads common
                        input_raw_data = self.serial_input.readline()  # read data to varible
                        input_raw_data_massive = input_raw_data.split(",")  # parse data
                        self.verticalSlider.setValue(self.convert(int(input_raw_data_massive[0]), 1000, 2000, 0, 100))
                        self.verticalSlider_2.setValue(self.convert(int(input_raw_data_massive[1]), 1000, 2000, 0, 100))
                        self.verticalSlider_3.setValue(self.convert(int(input_raw_data_massive[2]), 1000, 2000, 0, 100))
                        self.verticalSlider_4.setValue(self.convert(int(input_raw_data_massive[3]), 1000, 2000, 0, 100))
                        ax = float(input_raw_data_massive[4])
                        ay = float(input_raw_data_massive[5])
                        az = float(input_raw_data_massive[6])
                        ultro = float(input_raw_data_massive[7])
                        print(ultro)
                        print(ax)
                        print(ay)
                        print(az)
                        if (ax > 0):
                            if (ay > 0):
                                if (az > 0):
                                    self.drawimange("./ok.png", self.label_8)
                        if (ultro > 0):
                            self.drawimange("./ok.png", self.label_6)
                            self.drawimange("./ok.png", self.label_8)
                            time.sleep(2)
        except Exception as error:
            easygui.msgbox("Fatal app error:" + str(error), "Error happend")


    pass


    def runthrad(self):
        try:
            main_serial_loop = threading.Thread(
                target=self.get_data_thread)  # создаем поток автозапуска
            main_serial_loop.start()  # запускаем поток автозапуска
        except Exception as error:
            easygui.msgbox("Fatal app error:" + str(error), "Error happend")


    def __init__(self):
        # Это здесь нужно для доступа к переменным, методам
        # и т.д. в файле design.py
        super().__init__()
        self.setupUi(self)  # Это нужно для инициализации нашего дизайна.
        self.comboBox.addItem("Port name")
        self.comboBox_2.addItem("Baudrate")
        for count in range(50):
            self.comboBox.addItem("COM" + str(count))
        for count in range(50):
            self.comboBox_2.addItem(str(1200 * count))
        self.comboBox.setCurrentText("Port name")
        self.comboBox_2.setCurrentText("Baudrate")
        self.pushButton.clicked.connect(self.open_conneciton)
        self.pushButton_2.clicked.connect(self.runthrad)
        self.drawimange("./error.png", self.label_6)
        self.drawimange("./error.png", self.label_8)


def main():
    app = QtWidgets.QApplication(sys.argv)  # Новый экземпляр QApplication
    app.setStyleSheet(qdarkstyle.load_stylesheet_pyqt5())
    window = ExampleApp()  # Создаём объект класса ExampleApp
    window.show()  # Показываем окно
    app.exec_()  # и запускаем приложение


if __name__ == '__main__':  # Если мы запускаем файл напрямую, а не импортируем
    main()  # то запускаем функцию main()
