package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PimCSVReader {
    public static Product[] readCSVFile(String fileToLoad) throws IOException, CsvException {
        CSVReader testReader = new CSVReader(new FileReader(fileToLoad));
        List myEntries = testReader.readAll();
        Object[] test = myEntries.toArray();
        System.out.println(test.length);
        int l = test.length;
        int newId = 0;
        String newEan = "";
        String newSku = "";
        String newName = "";
        String newSeriesName = "";
        String newDescription = "";
        ProductCategory newCategory = null;
        double newGrossPrice = 0;
        double newNetPrice = 0;
        String newUrl = "";
        String newParameters = "";
        Boolean newAvailable = false;
        //TODO - adjust column numbers when necessary
        /*if ((l) % 4 != 0) {
            System.out.println("Wrong number of entries in CSV file");
            return new Product[]{};
        }
         */
        int rowCount = l / 12;
        testReader.close();
        CSVReader reader = new CSVReader(new FileReader(fileToLoad));
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 12; j++) {
                if(j == 0){
                    newId = Integer.parseInt((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                } else if (j == 1) {
                    newEan = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j == 2) {
                    newSku = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j == 3) {
                    newName = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                }  else if (j == 4) {
                    newSeriesName = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j==5) {
                    newDescription = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "").replace("¶", "\n");
                } else if (j == 6) {
                    newCategory = ProductCategory.valueOf((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                } else if (j == 7) {
                    newGrossPrice = Double.parseDouble((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                } else if (j == 8) {
                    newNetPrice = Double.parseDouble((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                } else if (j == 9) {
                    newUrl = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j == 10) {
                    newParameters = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j == 11) {
                    newAvailable = Boolean.valueOf((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                }
            }
            list.add(new Product(newId, newEan, newSku, newName, newSeriesName, newDescription, newCategory, newGrossPrice, newNetPrice, newUrl, newParameters, newAvailable));        }
        Product [] newProductList = list.toArray(new Product[list.size()]);
        return newProductList;
    }
}
