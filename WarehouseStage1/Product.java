import java.util.*;
import java.io.*;
public class Product implements Serializable{
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(String p, int s, double v)
    {
        this.id = ProductIdServer.getInstance().getId();
        this.name = p;
        this.quantity = s;
        this.price = v;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return quantity;
    }
    public String toString()
    {
        String s =  "Id: " +this.id+ "\nName: " +this.name+ " \nPrice: " +this.price+ "\nStock: " +this.quantity;  
        return s;
    }
    public void display()
    {
        System.out.println(this.toString());
    }
}
