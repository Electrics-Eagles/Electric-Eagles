package app.API;

import javax.swing.*;

public class TestCode implements Runnable{

    @Override
    public void run() {
        int value=   JOptionPane.showConfirmDialog(null,"Are you ready to integrate to this drone configuration");
        if(value == 1) {
            System.out.println("Intergating...");
        }

    }
}
