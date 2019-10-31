
package lab1;

import java.util.ArrayList;


public class Products {
    int dishID;
    int productID;
    String name;
    String allergens;
    ArrayList<Products> allProducts = new ArrayList();
    Products(int productID,String name, String allergens,int dishID){
        this.productID=productID;
        this.name=name;
        this.allergens=allergens;
        this.dishID=dishID;
    }
    public void addProducts(Products products){
        if (!allProducts.contains(products)) {
            allProducts.add(products);
        } 
    }
    public ArrayList<Products> getListOfProducts(){
        return allProducts;
    }
    
    public int getDishID(){
        return dishID;
    }
    public String getDishName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getAllergens() {
        return allergens;
    }
    public void setAllergens(String allergens) {
        this.allergens=allergens;
    }
}
