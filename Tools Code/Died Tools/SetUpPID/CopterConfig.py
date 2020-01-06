import sys  # sys нужен для передачи argv в QApplication
from PyQt5 import QtWidgets
import Main_GUI  # Это наш конвертированный файл дизайна
import bluetooth
import serial as serialport
import threading
import ino

class ExampleApp(QtWidgets.QMainWindow, Main_GUI.Ui_MainWindow):




    def __init__(self):
        # Это здесь нужно для доступа к переменным, методам
        # и т.д. в файле design.py
        super().__init__()
        self.setupUi(self)  # Это нужно для инициализации нашего дизайна
        self.comboBox.addItem("Bluetooth")
        self.comboBox.addItem("USB")
        for ports in range (60) :
            self.comboBox_2.addItem("COM"+str(ports))
        for baudrates in range ( 24):
            self.comboBox_3.addItem(str(baudrates*1200))
        #self.commandLinkButton_2.clicked.connect(self.click)
        self.pushButton_2.clicked.connect(self.readthread)
        self.pushButton.clicked.connect(self.define_generator)
        self.pushButton_3.clicked.connect(self.control)
    def readthread(self):
        mainreadusb = threading.Thread(target=self.readfunicion)
        mainreadusb.start()

    pass

    def map(self,x, in_min, in_max, out_min, out_max):
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min

    def define_generator(self):
        arduinocode=open("./Final_Code.ino","a+",encoding="UTF-8")
        arduinocode.write("#define TRANSIVER_PIN_X "+str(self.plainTextEdit.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define TRANSIVER_PIN_Y " + str(self.plainTextEdit_2.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define TRANSIVER_PIN_Z " + str(self.plainTextEdit_3.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define ULTRASONIC_1_PIN_1 " + str(self.plainTextEdit_4.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define ULTRASONIC_1_PIN_2 " + str(self.plainTextEdit_5.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define MOTOR_ESC_1_PIN " + str(self.plainTextEdit_6.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define MOTOR_ESC_2_PIN " + str(self.plainTextEdit_7.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define MOTOR_ESC_3_PIN " + str(self.plainTextEdit_8.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define MOTOR_ESC_4_PIN " + str(self.plainTextEdit_9.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define GYROSCOPE_SENSOR_PIN " + str(self.plainTextEdit_10.toPlainText()))
        arduinocode.write("\n")
        arduinocode.write("#define DEBUG " + str(self.comboBox_4.currentIndex()))
        arduinocode.write("\n")
        arduinocode.close()
        with open("./Dependencies/code.h") as f:
            with open("./Final_Code.ino", "a") as f1:
                for line in f:
                    f1.write(line)
            f.close()
            f1.close()
    def read_trear(self):
        while True:
            try:
                serialportusb = serialport.Serial(self.comboBox_2.currentText(), int(self.comboBox_3.currentText()))
                serialportusb.write(str(self.map(int(self.horizontalSlider.value()),0,99,1000,2000)))
                print(str(self.map(int(self.horizontalSlider.value()),0,99,1000,2000)))
                #print("THRAd")

            except Exception as error:
                print(error)

    def readff(self):
        data = threading.Thread(target=self.read_trear)
        data.start()





    def control(self):
        self.pushButton_3.setText("Unlocked")
        self.pushButton_3.setStyleSheet("background-color: rgb(20, 135, 76);")
        if self.comboBox.currentText() == "USB":
            self.readff()

    def readfunicion(self):
        if self.comboBox.currentText()== "USB":
            print("USB MODE")
            serialportusb = serialport.Serial(self.comboBox_2.currentText(), int(self.comboBox_3.currentText()))
            # serialportusb.open()
            self.textBrowser.setText(str(serialportusb.readline()))
            # append = threading.Thread(target=self.readfunicion,args=str(serialportusb.readline()))
            # append.start()
    pass
def main():
    app = QtWidgets.QApplication(sys.argv)  # Новый экземпляр QApplication
    window = ExampleApp()  # Создаём объект класса ExampleApp
    window.show()  # Показываем окно
    app.exec_()  # и запускаем приложение

if __name__ == '__main__':  # Если мы запускаем файл напрямую, а не импортируем
    main()  # то запускаем функцию main()