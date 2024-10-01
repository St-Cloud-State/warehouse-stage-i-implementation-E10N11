import java.io.*;

/**
 * Class representing a customer.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private double balance;
    private Wishlist wishlist;

    // Constructor to initialize a new customer
    public Customer(String name, String address, double balance) {
        this.id = CustomerIdServer.getInstance().getNextId();
        this.name = name;
        this.address = address;
        this.balance = balance;
        this.wishlist = new Wishlist(id);
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }
    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString()
    {
        return "Customer ID: " + id + "\nName: " + name + "\nAddress: " + address +"\nBalance: " + balance;
    }
    public void display() {
        System.out.println(this.toString());
    }
}
