import os
import threading

import easygui
import serial
from PySide2 import QtWidgets, QtGui
from PySide2.QtGui import QPixmap, QFont
import web_api as web
from main_gui import Ui_MainWindow
import sys
import serial_api as serial_api
import setup_drone_api
from upload_firmware import unpack_files_from_zip_and_flash_firmware
from upload_firmware import upload_firmware_via_cable


class Calculator(QtWidgets.QMainWindow, Ui_MainWindow):
    def flash_zip(self):
        log = unpack_files_from_zip_and_flash_firmware()
        self.plainTextEdit.appendPlainText(log)

    pass

    def flash_calibrate_code(self):
        log = upload_firmware_via_cable(os.path.abspath("./codes/Calibrate.ino"))
        self.plainTextEdit.appendPlainText(log)

    pass

    def flash_check_code(self):
        log = upload_firmware_via_cable(os.path.abspath("./codes/Check.ino"))
        self.plainTextEdit.appendPlainText(log)

    pass

    def flash_clean_code(self):
        log = upload_firmware_via_cable(os.path.abspath("./codes/Clean.ino"))
        self.plainTextEdit.appendPlainText(log)

    pass

    def flash_zip_thread(self):
        therad_object = threading.Thread(target=self.flash_zip)
        therad_object.start()

    pass

    def flash_calibrate_code_thread(self):
        therad_object = threading.Thread(target=self.flash_calibrate_code)
        therad_object.start()

    pass

    def flash_check_code_thread(self):
        therad_object = threading.Thread(target=self.flash_check_code)
        therad_object.start()

    pass

    def flash_clean_code_thread(self, type):
        therad_object = threading.Thread(target=self.flash_clean_code)
        therad_object.start()

    pass

    def call_setup_funcion(self):
        setup_drone_api.generate_firmware_for_setup_drone(self)

    pass

    def save_code(self):
        save_code_path = easygui.filesavebox("Save Out Code")
        saved_file = open(save_code_path, "w", encoding="UTF-8")
        saved_file.write(self.plainTextEdit_3.toPlainText())
        saved_file.flush()
        saved_file.close()

    pass

    def connect_port(self):
        self.label.setText(" Connected")
        self.label.setStyleSheet("color:#447C2B;")
        self.label.setFont(QFont("Helvetica Neue", 20))

    pass

    def read_data_add_to_platin_text_box(self):
        port = self.comboBox.currentText()
        baudrate = int(self.comboBox_2.currentText())
        self.serial_port = serial.Serial(port, baudrate)
        while True:
            data = self.serial_port.readline()
            dat = data.decode('UTF-8').strip()
            self.plainTextEdit_2.insertPlainText(str(dat).replace("b'", "").replace("'", "") + "\n")
            QtGui.QGuiApplication.processEvents()

    pass

    def read_data_add_to_platin_text_box_thread(self):
        therad_object = threading.Thread(target=self.read_data_add_to_platin_text_box)
        therad_object.start()

    pass

    def __init__(self):
        super().__init__()
        # Создание формы и Ui (наш дизайн)
        self.setupUi(self)
        self.show()
        self.label.setText("Not  Connected")
        self.label.setStyleSheet("color:#EB3F0F;")
        self.label.setFont(QFont("Helvetica Neue", 20))
        web.web_api_thread()
        self.comboBox.addItem(serial_api.detect_ports())
        serial_api.generate_baudrates(self)
        self.web_api.load("http://localhost:3452/api")
        self.pushButton_2.clicked.connect(self.flash_zip_thread)
        self.pushButton_4.clicked.connect(self.flash_calibrate_code_thread)
        self.pushButton_3.clicked.connect(self.flash_check_code_thread)
        self.pushButton_5.clicked.connect(self.flash_clean_code_thread)
        self.pushButton_7.clicked.connect(self.call_setup_funcion)
        self.pushButton_8.clicked.connect(self.save_code)
        self.pushButton_6.clicked.connect(self.read_data_add_to_platin_text_box_thread)
        self.pushButton.clicked.connect(self.connect_port)


if __name__ == '__main__':
    # Новый экземпляр QApplication
    app = QtWidgets.QApplication(sys.argv)
    # Сздание инстанса класса
    calc = Calculator()
    # Запуск

    sys.exit(app.exec_())
