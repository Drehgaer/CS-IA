package org.example;

public class Product {
    String ean;
    String name;
    String seriesName;
    ProductCategory category;
    String subCategory;
    String shortDescription;
    String longDescription;
    String Vat;
    double grossPrice;
    double netPrice;



    boolean available;
    Product (String ean, String name, ProductCategory category, boolean available) {
        this.ean = ean;
        this.name = name;
        this.category = category;
        this.available = available;
    }
    /*public static boolean isValidEAN(String ean) {
        // Check if the input is exactly 13 characters long and numeric
        if (ean == null || ean.length() != 13 || !ean.matches("\\d{13}")) {
            return false;
        }
    */

    public String getEan() {
        return ean;
    }
    public String getName() {
        return name;
    }
    public ProductCategory getCategory() {return category;}
    public boolean isAvailable() {
        return available;
    }
}
/*TO D0
- ean walidacja
- zmienić category na enum
*/