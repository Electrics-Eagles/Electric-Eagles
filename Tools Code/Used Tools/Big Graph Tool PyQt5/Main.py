import threading
from PySide2 import QtWidgets
import easygui
import sys
from PyQt5 import QtWidgets, QtCore
import graph_tool
import pyqtgraph as python_graph_plotter
import pyqtgraph.exporters
class GraphPlotter(QtWidgets.QMainWindow, graph_tool.Ui_MainWindow):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.showMaximized()
        self.actionOpen_FIle.triggered.connect(self.create_ploting_thread)
        self.actionExport_as_PNG.triggered.connect(self.save_file)

    def save_file(self):
        try:
            exporter = python_graph_plotter.exporters.ImageExporter(self.graphicsView.plotItem)
            # set export parameters if needed
            exporter.params.param('width').setValue(self.centralwidget.width(), blockSignal=exporter.widthChanged)
            exporter.params.param('height').setValue(self.centralwidget.height(), blockSignal=exporter.heightChanged)
            # save to file
            exporter.export(easygui.filesavebox("Save Graph"))
        except:
            easygui.exceptionbox()
    pass
    def ask_file(self):
        try:
            data_file = easygui.fileopenbox("Open a file with 3 colums and delimeter ;")
            data = open(data_file, "r", encoding='UTF-8')
            raw_data = data.readlines()
            x_axis = []
            y_axis = []
            z_axis = []
            for list_of_strings in range(0, len(raw_data)):
                data_too_graph = raw_data[list_of_strings].split(";")
                x_axis.append(float(data_too_graph[0]))
                y_axis.append(float(data_too_graph[1]))
                z_axis.append(float(data_too_graph[2]))
            self.graphicsView.plot(z_axis,pen=python_graph_plotter.mkPen('r', width=1))
            self.graphicsView.plot(y_axis,pen=python_graph_plotter.mkPen('y', width=1))
            self.graphicsView.plot(x_axis,pen=python_graph_plotter.mkPen('g', width=1))
        except:
            easygui.exceptionbox()
    def create_ploting_thread(self):
        x = threading.Thread(target=self.ask_file)
        x.start()
    pass

def main():
    app = QtWidgets.QApplication(sys.argv)  # Новый экземпляр QApplication
    window = GraphPlotter()  # Создаём объект класса ExampleApp
    window.show()  # Показываем окно
    app.exec_()  # и запускаем приложение

if __name__ == '__main__':  # Если мы запускаем файл напрямую, а не импортируем
    main()  # то запускаем функцию main()