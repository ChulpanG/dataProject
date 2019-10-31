
package lab1;


public class ProductDish{
    //Dish dish;
    Products products;
    int quantity;
    String unit;
    
    ProductDish(Products products, int quantity, String unit){
        this.products=products;
        this.quantity=quantity;
        this.unit=unit;
    }
    public Products getProducts(){
        return products;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getUnit(){
        return unit;
    }
    
}
/*class Unit{
    double litr;
    double kg;
    double gr;
}*/
