package org.example;

import static org.example.ProductCategory.*;


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
    static ProductCategory[] categories = new ProductCategory[]{akcesoria_plastikowe_kostki_do_gry_ekstra, akcesoria_plastikowe_kostki_do_gry_zwykłe,gamebook, gry_dla_dzieci_edukacyjne, gry_dla_dzieci_inne, gry_dla_dzieci_karciane, gry_dla_dzieci_planszowe, gry_dla_dzieci_quizy_i_loteryjki, gry_karciane_akcesoria_do_kart, gry_karciane_dla_dzieci, gry_karciane_imprezowe, gry_karciane_kooperacyjne, gry_karciane_logiczne, gry_karciane_przygodowe, gry_karciane_rodzinne, gry_karciane_strategiczne, gry_planszowe_akcesoria, gry_planszowe_ekonomiczne, gry_planszowe_imprezowe, gry_planszowe_klasyczne, gry_planszowe_kooperacyjne, gry_planszowe_kościane, gry_planszowe_przygodowe, gry_planszowe_rodzinne, gry_planszowe_strategiczne, łamigłówki_Huzzle_Cast, łamigłówki_kostka_GAN, łamigłówki_zwariowane, puzzle_dla_dorosłych, puzzle_dla_dzieci};

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
    public ProductCategory[] getCategories() {return categories;}
    public static ProductCategory getCategoryAt(int index){return categories[index];}
    public static int getCategoryIndex(ProductCategory thisCategory){
        for (int index = 0; index < categories.length; index++) {
            if (categories[index] == thisCategory) {
                return index;
            }
        }
        return -1;
    }


}
/*TO D0
- ean walidacja
*/