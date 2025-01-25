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
        String newEan = "";
        String newName = "";
        ProductCategory newCategory = null;
        Boolean newAvailable = false;
        //TODO - adjust column numbers when necessary
        /*if ((l) % 4 != 0) {
            System.out.println("Wrong number of entries in CSV file");
            return new Product[]{};
        }
         */
        int rowCount = l / 4;
        testReader.close();
        CSVReader reader = new CSVReader(new FileReader(fileToLoad));
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    newEan = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j == 1) {
                    newName = (Arrays.toString(reader.readNext())).replace("[", "").replace("]", "");
                } else if (j == 2) {
                    newCategory = ProductCategory.valueOf((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                } else if (j == 3) {
                    newAvailable = Boolean.valueOf((Arrays.toString(reader.readNext())).replace("[", "").replace("]", ""));
                }
            }
            //TODO - adjust it for current table size
            //list.add(new Product(newEan, newName, newCategory, newAvailable));
        }
        Product [] newProductList = list.toArray(new Product[list.size()]);
        return newProductList;
    }
}
