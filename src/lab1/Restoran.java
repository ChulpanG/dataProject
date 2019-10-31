package lab1;

import java.util.ArrayList;
import java.util.Date;
import lab1.Person;
import java.util.List;
//import static lab1.Gender.FEMALE;


public class Restoran {
   
    Integer codeForSystem;
    String address;
    String phoneNumber;
    List<Person> people;
    List<Dish> menu;
    static ArrayList<String> numberDayOrders = new ArrayList();
    ArrayList <ArrayList <String>> orders;
    
    /*public static void main(String[] args){
        
        //проверка всех методов
        
        Products products = new Products(1,"кофейное зерно","кофеин",1);
        products.allProducts.add(products);
        Products products2 = new Products(2,"молоко","лактоза",1);
        products.allProducts.add(products2);
        ProductDish product = new ProductDish(products,1,"шт");
        Dish dish = new Dish(1,"капучино", 170.0);
        List<Dish> orderDish;
        ArrayList<String> list= new ArrayList();
        dish.addDish(dish);
        Person person = new Person(1,"Галимова","Чулпан","директор","22.03.1999",FEMALE);
        person.people.add(person);
        Person person2 = new Person(2,"Г","Ч","директор","22.03.1999",FEMALE);
        person.people.add(person2);
        Date date = new Date();
        list = getOrderList(new Order(1,1,"2019-10-13T19:13:48.141148",dish.order));
        
        System.out.println(list);
        System.out.println("Информация об официанте: "+getWaiterInfo(2,person.people));
        System.out.println("Состав блюда: "+ getDishInfo(1,products.allProducts));
        
        
    }*/
    public List getPeople(){
        
        return people;
    
    }
    public static String getDishInfo(int dishID, ArrayList<Products> list){
        String strName="";
        for(int i=0;i<list.size();i++){
            if(list.get(i).dishID == dishID){
                strName = strName + list.get(i).name + ",";
            }
        }
        return strName;
    }
    public static String getWaiterInfo(int waiterID,ArrayList<Person> list){
        String str="";
        for(int i=0; i<list.size(); i++){
            if(list.get(i).personID == waiterID){
                str = " Имя: " + list.get(i).name + " Фамилия: "+ list.get(i).surname + " Позиция: " + list.get(i).position + " Дата рождения: " + list.get(i).birthDate;
                
            }
        }
        return str;
    }
    public static ArrayList getOrderList(Order order){
        
        numberDayOrders.clear();
        numberDayOrders.add("Order Time: "+order.getOrderTime());
        numberDayOrders.add("\nOrderID: "+String.valueOf(order.getOrderID()));
        numberDayOrders.add("\nWaiterID: "+String.valueOf(order.getWaiterID()));
        numberDayOrders.add("\nDishes: "+order.getDish());
        numberDayOrders.add("\nTotal Price: "+String.valueOf(order.calculateOrderPrice()));
        
    return numberDayOrders;    
    }
    public void reloadOrders(Order order){
        orders.add(getOrderList(order));
    }
   
} 

