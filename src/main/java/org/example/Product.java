package org.example;

public class Product {
    int id;
    String ean;
    String sku;
    String name;
    String seriesName;
    ProductCategory category;
    String description;
    double grossPrice;
    double netPrice;
    String url;
    String parameters;
    boolean available;


    Product (int id, String ean, String sku, String name, String seriesName, String description, ProductCategory category, double grossPrice, double netPrice, String url, String parameters, boolean available ) {
        this.id = id;
        this.ean = ean;
        this.sku = sku;
        this.name = name;
        this.seriesName = seriesName;
        this.description = description;
        this.category = category;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
        this.url = url;
        this.parameters = parameters;
        this.available = available;
    }
    /*public static boolean isValidEAN(String ean) {
        // Check if the input is exactly 13 characters long and numeric
        if (ean == null || ean.length() != 13 || !ean.matches("\\d{13}")) {
            return false;
        }
    */
    public int getId() {return id;}
    public String getEan() {
        return ean;
    }
    public String getSku() {return sku;}
    public String getName() {
        return name;
    }
    public String getSeriesName() {return seriesName;}
    public String getDescription() {return description;}
    public ProductCategory getCategory() {return category;}
    public double getGrossPrice(){return grossPrice;}
    public double getNetPrice(){return netPrice;}
    public String getUrl() {return url;}
    public String getParameters() {return parameters;}
    public boolean isAvailable() {return available;}


}
/*TO D0
- ean walidacja
*/