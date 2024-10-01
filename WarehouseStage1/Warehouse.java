import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int CUSTOMER_NOT_FOUND  = 1;
    public static final int PRODUCT_NOT_FOUND  = 2;
    /* 
    public static final int BOOK_HAS_HOLD  = 3;
    public static final int BOOK_ISSUED  = 4;
    public static final int HOLD_PLACED  = 5;
    public static final int NO_HOLD_FOUND  = 6;
    public static final int OPERATION_COMPLETED= 7;
    public static final int OPERATION_FAILED= 8;
    public static final int NO_SUCH_MEMBER = 9;
    */
    private CustomersList customersList;
    private ProductList productList;
    private static Warehouse warehouse;

    private Warehouse() {
        customersList = CustomersList.getInstance();
        productList = ProductList.getInstance();
      }
      public static Warehouse instance() {
        if (warehouse == null) {
          CustomerIdServer.getInstance(); // instantiate all singletons
          ProductIdServer.getInstance();
          return (warehouse = new Warehouse());
        } else {
          return warehouse;
        }
      }
      public Customer addCustomer(String name, String address, double balance) {
        Customer customer = new Customer(name, address, balance);
        if (customersList.addCustomer(customer)) {
          return (customer);
        }
        return null;
      }
      public Product addProduct(String name, double price, int quantity) {
        Product product = new Product(name, quantity, price);
        if (productList.addProduct(product)) {
          return (product);
        }
        return null;
      }
    
      public Iterator getCustomers() {
          return customersList.getCustomers();
      }
    
      public Iterator getProducts() {
          return productList.getProducts();
      }

      public WishlistItem addToWishlist(int cid, int pid, int quantity)
      {
        Wishlist userWishlist = customersList.find(cid).getWishlist();
        WishlistItem item = new WishlistItem(productList.find(pid), quantity); 
        userWishlist.add(item);
        return item;
      }
      public Iterator getWishlist(int cid)
      {
        Wishlist userWishlist = customersList.find(cid).getWishlist();
        return userWishlist.getItems();
      }

      public static Warehouse retrieve() {
        try {
          FileInputStream file = new FileInputStream("WarehouseData");
          ObjectInputStream input = new ObjectInputStream(file);
          input.readObject();
          CustomerIdServer.retrieve(input);
          return warehouse;
        } catch(IOException ioe) {
          ioe.printStackTrace();
          return null;
        } catch(ClassNotFoundException cnfe) {
          cnfe.printStackTrace();
          return null;
        }
      }
      public static  boolean save() {
        try {
          FileOutputStream file = new FileOutputStream("WarehouseData");
          ObjectOutputStream output = new ObjectOutputStream(file);
          output.writeObject(warehouse);
          output.writeObject(CustomerIdServer.getInstance());
          output.writeObject(ProductIdServer.getInstance());
          return true;
        } catch(IOException ioe) {
          ioe.printStackTrace();
          return false;
        }
      }
      private void writeObject(java.io.ObjectOutputStream output) {
        try {
          output.defaultWriteObject();
          output.writeObject(warehouse);
        } catch(IOException ioe) {
          System.out.println(ioe);
        }
      }
      private void readObject(java.io.ObjectInputStream input) {
        try {
          input.defaultReadObject();
          if (warehouse == null) {
            warehouse = (Warehouse) input.readObject();
          } else {
            input.readObject();
          }
        } catch(IOException ioe) {
          ioe.printStackTrace();
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
      public String toString() {
        return customersList + "\n" + productList;
      } 
}
