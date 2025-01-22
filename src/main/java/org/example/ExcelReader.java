package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static Product[] readExcelFile(File excelFile) throws FileNotFoundException {
        String string = "";
        String temp = "";
        String newEan = "";
        String newName = "";
        ProductCategory newCategory = null;
        Boolean newAvailable = false;
        List<Product> list = new ArrayList<Product>();
        Integer i = -1;
        int cont = 0;
        int oldCont = -1;
        try {
        FileInputStream file = new FileInputStream(excelFile);

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();


            while (rowIterator.hasNext())
        {
            i = -1;
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType())
                {
                    case CellType.NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        string = string + cell.getNumericCellValue() + "\t";
                        temp = cell.getNumericCellValue() + "\t";
                        i++;
                        cont++;
                        break;
                    case CellType.STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        string = string + cell.getStringCellValue() + "\t";
                        temp = cell.getStringCellValue();
                        i++;
                        cont++;
                        break;
                    /*case CellType.BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        string = string + cell.getBooleanCellValue() + "\t";
                        temp = cell.getBooleanCellValue() + "\t";
                        i++;
                     */
                }
                if (i == 0){
                    newEan = temp;
                }else if (i == 1) {
                    newName = temp;
                }else if (i == 2) {
                    //System.out.println(temp);
                    //System.out.println(i);
                   /* if (temp == "test_category1") {
                        newCategory = test_category1;
                    } else if (temp == "test_category2") {
                        newCategory = test_category2;
                    } else if (temp == "test_category3") {
                        newCategory = test_category3;
                    } else {
                        System.out.println("Wrong product category");
                    }
                    */
                    newCategory = ProductCategory.valueOf(temp);
                }else if (i == 3) {
                    newAvailable = Boolean.valueOf(temp);
                }
                string = string + "\n";
            }
            oldCont = oldCont + 4;
            //System.out.println(cont);
            //System.out.println(oldCont);
            if (cont > oldCont) {
                list.add(new Product(newEan, newName, newCategory, newAvailable));
            }
            System.out.print("");
        }
        file.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
        System.out.println(list.size());
        //System.out.println(list);
        Product [] newProductList = list.toArray(new Product[list.size()]);
        return newProductList;

}
}
