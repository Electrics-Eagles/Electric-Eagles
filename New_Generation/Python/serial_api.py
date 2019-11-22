import serial.tools.list_ports
import serial

global port
def detect_ports():
    ports = list(serial.tools.list_ports.comports())
    for p in ports:
        return p[0]
pass

def generate_baudrates(self):
    data_list = [300, 1200, 4800, 9600, 38400, 57600, 115200, 256000, 100000, 200000]
    for data in range(0,len(data_list)):
        self.comboBox_2.addItem(str(data_list[data]))
    pass

