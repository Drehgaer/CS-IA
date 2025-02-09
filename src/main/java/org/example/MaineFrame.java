package org.example;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MaineFrame extends JFrame implements ActionListener {
    JMenu menu, export;
    JMenuItem open, filter, toCSV, toXML;
    Dimension dimension = new Dimension(1000,1000);
    JPanel left;
    JPanel top;
    BorderLayout layout;
    JScrollPane scrollableTable;
    JLabel label;
    JTextField textField;
    JTable table;
    DefaultTableModel model;
    FilterSelectorFrame filterFrame;
    Thread thread1;
    Thread thread2;
    int numberOfRows;
    JButton filterClearButton;
    Product [] currentProducts;

    MaineFrame(){
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        model = new DefaultTableModel(0, 12){
            final String [] columnNames = {"ID","EAN","SKU", "Name", "Series Name", "Description", "Category", "Price(gross)", "Price(net)", "URL", "Parameters", "Available"};
            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table = new JTable(model);
        //this.setLayout(layout);
        left = new JPanel();
        top = new JPanel();
        filterClearButton = new JButton("Clear all Filters");
        filterClearButton.addActionListener(this);
        top.add(filterClearButton);
        scrollableTable = new JScrollPane(table);
        scrollableTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        layout = new BorderLayout();
        label = new JLabel();
        //textField = new JTextField();
        //textField.setSize(new Dimension (900, 900));
        left.setPreferredSize(new Dimension (100, 1000));
        left.setLocation(0,0);
        //left.setLayout(layout);
        left.setBackground(Color.gray);
        top.setPreferredSize(new Dimension (2000, 100));
        top.setLocation(100,0);
        table.setLocation(100, 100);
        table.setPreferredSize(dimension);
        top.setBackground(Color.darkGray);
        this.add(top, BorderLayout.NORTH);
        this.add(left, BorderLayout.WEST);

        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Menu");
        //submenu=new JMenu("Sub Menu");
        open =new JMenuItem("Open...");
        export =new JMenu("Export...");
        filter =new JMenuItem("Filter");
        toCSV =new JMenuItem("to CSV");
        toXML =new JMenuItem("to XML");
        menu.add(open); menu.add(export);menu.add(filter);
        export.add(toCSV); export.add(toXML);
        open.addActionListener(this);
        filter.addActionListener(this);
        toCSV.addActionListener(this);
        toXML.addActionListener(this);
        mb.add(menu);

        //this.add(textField, BorderLayout.CENTER);
        //this.add(table);

        this.getContentPane().add(scrollableTable);
        this.setJMenuBar(mb);
        this.setVisible(true);
        this.pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== open) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); //sets current directory
            int response = fileChooser.showOpenDialog(null); //select file to open
            //int response = fileChooser.showSaveDialog(null); //select file to save
            if(response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    this.showTable(ExcelReader.readExcelFile(file));
                    CsvExport.storeTable(table, "tempMainTable.csv");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(e.getSource()== filter) {
            thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    filterFrame = new FilterSelectorFrame();
                }
            });
            thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    int t = filterFrame.getT();
                    //FilterSelectorFrame window does not load without this while loop
                    while (t==0){
                    t = filterFrame.getT();
                        System.out.print("");
                    }
                    Boolean[] filters = filterFrame.getFilters();
                    System.out.println(filters[0]);
                    System.out.println(filters[1]);
                    System.out.println(filters[2]);
                    showFilteredTable(filters);
                }
            });
            thread1.start();
            thread2.start();
        }
        if(e.getSource()== toCSV) {
            long time = System.currentTimeMillis();
            java.util.Date timeAndDate =new java.util.Date(time);
            CsvExport.exportToCSV(table, ("report-" + timeAndDate + ".csv"));
        }
        if(e.getSource()== toXML) {
            long time = System.currentTimeMillis();
            java.util.Date timeAndDate =new java.util.Date(time);
            XmlExporter.exportToXml(currentProducts, ("report-" + timeAndDate + ".xml"));
        }
        if(e.getSource()== filterClearButton) {
            try {
                this.showTable(PimCSVReader.readCSVFile("tempMainTable.csv"));
            } catch (IOException | CsvException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void showTable(Product[] products){
        currentProducts = products;
        numberOfRows = products.length;
        model.setRowCount(products.length);
        table.setPreferredSize(new Dimension(1000, 16*numberOfRows));
        for (int i = 0; i < products.length; i++) {
            //model.addRow(new Vector<>());
            //numberOfRows++;
            //System.out.println(numberOfRows);
            table.setValueAt(products[i].getId(), i, 0);
            table.setValueAt(products[i].getEan(), i, 1);
            table.setValueAt(products[i].getSku(), i, 2);
            table.setValueAt(products[i].getName(), i, 3);
            table.setValueAt(products[i].getSeriesName(), i, 4);
            table.setValueAt(products[i].getDescription(), i, 5);
            table.setValueAt(products[i].getCategory(), i, 6);
            table.setValueAt(products[i].getGrossPrice(), i, 7);
            table.setValueAt(products[i].getNetPrice(), i, 8);
            table.setValueAt(products[i].getUrl(), i, 9);
            table.setValueAt(products[i].getParameters(), i, 10);
            table.setValueAt(products[i].isAvailable(), i, 11);
        }
        //System.out.println(table.getValueAt(0, 2));
    }
    public void showFilteredTable(Boolean[] selectedFilters){
        if(selectedFilters[0] == null){
            System.out.println("No Filters Selected");
        }else{
            List<Product> list = new ArrayList<Product>();
            String filteredEan;
            String filteredName;
            ProductCategory filteredCategory;
            Boolean filteredAvailable;
            for (int i = 0; i < numberOfRows; i++) {
                if(selectedFilters[Product.getCategoryIndex((ProductCategory) table.getValueAt(i, 6))]){
                    filterTableAddProduct(list, i);
                }
            }
            Product [] filteredProductList = list.toArray(new Product[list.size()]);
            //model.setRowCount(0);
            showTable(filteredProductList);
            model.fireTableDataChanged();
        }

    }

    private void filterTableAddProduct(List<Product> list, int i) {
        int filteredId;
        String filteredEan;
        String filteredSku;
        String filteredName;
        String filteredSeriesName;
        String filteredDescription;
        ProductCategory filteredCategory;
        double filteredGrossPrice;
        double filteredNetPrice;
        String filteredUrl;
        String filteredParameters;
        boolean filteredAvailable;

        filteredId = (int) table.getValueAt(i, 0);
        filteredEan = table.getValueAt(i, 1).toString();
        filteredSku = table.getValueAt(i, 2).toString();
        filteredName = table.getValueAt(i, 3).toString();
        filteredSeriesName = table.getValueAt(i, 4).toString();
        filteredDescription = table.getValueAt(i, 5).toString();
        filteredCategory = ProductCategory.valueOf(table.getValueAt(i, 6).toString());
        filteredGrossPrice = Double.parseDouble(table.getValueAt(i, 7).toString());
        filteredNetPrice = Double.parseDouble(table.getValueAt(i, 8).toString());
        filteredUrl = table.getValueAt(i, 9).toString();
        filteredParameters = table.getValueAt(i, 10).toString();
        filteredAvailable = Boolean.parseBoolean(table.getValueAt(i, 11).toString());
        //TODO - adjust it for current table size
        list.add(new Product(filteredId, filteredEan, filteredSku, filteredName, filteredSeriesName, filteredDescription, filteredCategory, filteredGrossPrice, filteredNetPrice, filteredUrl, filteredParameters, filteredAvailable));
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }

}






