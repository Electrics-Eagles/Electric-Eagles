import os

import temp


def generate_firmware_for_setup_drone(self):
    try:
        code_gen_file = open("./input_code/Main.ino", "r", encoding="UTF-8")
        raw_data = code_gen_file.readlines()
        settings = []
        if (self.checkBox.isChecked()):  # debug
            settings.append("#define DEBUG 1 \n")
        else:
            settings.append("#define DEBUG 0 \n")
        if (self.checkBox_2.isChecked()):
            settings.append("#define MANUAL_SUPPORT 1 \n")
        else:
            settings.append("#define MANUAL_SUPPORT 0\n")
        if (self.checkBox_3.isChecked()):
            settings.append("#define MANUAL_API_LOGO 1\n")
        else:
            settings.append("#define MANUAL_API_LOGO 0\n")
        if (self.checkBox_4.isChecked()):
            settings.append("#define ULTROSONICS_SUPPORT  1\n")
        else:
            settings.append("#define ULTROSONICS_SUPPORT  0\n")
        if (self.checkBox_5.isChecked()):
            settings.append("#define BUZZER_SUPPORT 1\n")
        else:
            settings.append("#define BUZZER_SUPPORT 0\n")
        if (self.checkBox_6.isChecked()):
            settings.append("boolean auto_level = true;                 //Auto level on (true) or off (false) \n")
        else:
            settings.append("boolean auto_level = false;                 //Auto level on (true) or off (false) \n")

        final_massive = settings + raw_data
        out_path=temp.tempfile()
        file = open(out_path, "a", encoding="UTF-8")
        for data_out in range(len(final_massive)):
            file.write(final_massive[data_out])
        file.flush()
        file.close()
        file = open(out_path, 'r')
        with file:
            text = file.read()
            self.plainTextEdit_3.appendPlainText(text)
    except Exception as error:
        print(error)

pass
