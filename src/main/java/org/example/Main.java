package org.example;

import static org.example.ProductCategory.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MaineFrame frame = new MaineFrame();
        Product testproduct1 = new Product("1234567890123", "test_name1", test_category1, true);
        Product testproduct2 = new Product("9087654321908", "test_name2", test_category2, true);
        Product testproduct3 = new Product("9987657821907", "test_name3", test_category1, true);
        Product testproduct4 = new Product("5586657521021", "test_name4", test_category3, false);
        //Product productList [] = new Product[100000];
        //productList[0] = testproduct1;
        //productList[1] = testproduct2;
        //productList[2] = testproduct3;
        //productList[3] = testproduct4;
        //System.out.println(testproduct1.ean);
        //frame.showTable(productList);

    }
}