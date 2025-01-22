package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvExport {
    public static void storeTable(JTable tableToExport, String fileName) {
        try {
            TableModel model = tableToExport.getModel();
            FileWriter csv = new FileWriter(fileName);
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString());
                    csv.write("\n");
                }
            }
            csv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exportToCSV(JTable tableToExport, String fileName) {
        try {
            TableModel model = tableToExport.getModel();
            FileWriter csv = new FileWriter(fileName);
            System.out.println(model.getColumnCount());
            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
                System.out.println(model.getColumnName(i));
            }
            csv.write("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csv.write(model.getValueAt(i, j).toString());
                    csv.write("\n");
                }
                csv.write("\n");
            }
            csv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
