import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CUSTOMER = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int SHOW_CUSTOMER_WISHLIST = 3;
  private static final int ADD_PRODUCT_TO_WISHLIST = 4;
  private static final int ADD_STOCK = 5;
  private static final int SHOW_CUSTOMERS = 6;
  private static final int SHOW_PRODUCTS = 7;
  private static final int RETRIEVE = 8;
  private static final int SAVE = 9;
  private static final int HELP = 10;
  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CUSTOMER + " to add a customer");
    System.out.println(ADD_PRODUCT + " to  add a product");
    System.out.println(SHOW_CUSTOMER_WISHLIST + " to  print customers for wishlist");
    System.out.println(ADD_PRODUCT_TO_WISHLIST + " to  add product to a customers wishlist ");
    System.out.println(ADD_STOCK + " to  add stock to a product ");
    System.out.println(SHOW_CUSTOMERS + " to  print customers");
    System.out.println(SHOW_PRODUCTS + " to  print products");
    System.out.println(SAVE + " to  save data");
    System.out.println(RETRIEVE + " to  retrieve");
    System.out.println(HELP + " for help");
  }

  public void addCustomer() {
    String name = getToken("Enter customer name");
    String address = getToken("Enter address");
    double balance = Double.parseDouble(getToken("Enter balance"));
    Customer result;
    result = warehouse.addCustomer(name, address, balance);
    if (result == null) {
      System.out.println("Could not add member");
    }
    System.out.println(result);
  }

  public void addProducts() {
    Product result;
    do {
      String name = getToken("Enter name");
      double price = Double.parseDouble(getToken("Enter price"));
      int quantity = Integer.parseInt(getToken("Enter quantity"));
      result = warehouse.addProduct(name, price, quantity);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Book could not be added");
      }
      if (!yesOrNo("Add more books?")) {
        break;
      }
    } while (true);
  }
  public void addToWishlist()
  {
    WishlistItem result;
    int cid = Integer.parseInt(getToken("Enter customer Id"));
    int pid = Integer.parseInt(getToken("Enter product Id"));
    int qantity = Integer.parseInt(getToken("Enter quantity"));
    result = warehouse.addToWishlist(cid, pid, qantity);
    if (result == null) {
      System.out.println("Could not add to wishlist");
    }
    System.out.println(result);
  }

  public void showProducts() {
      Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
        Product product = (Product)(allProducts.next());
        System.out.println(product.toString());
      }
  }

  public void showCustomers() {
      Iterator allCustomers = warehouse.getCustomers();
      while (allCustomers.hasNext()){
        Customer customer = (Customer)(allCustomers.next());
        System.out.println(customer.toString());
      }
  }
  public void showWishlist() {
    int cid = Integer.parseInt(getToken("Enter customer Id"));
    Iterator w = warehouse.getWishlist(cid); 
    while (w.hasNext()){
      WishlistItem wi = (WishlistItem)(w.next());
      System.out.println(wi.toString());
    }
}

  public void addStock() {
      System.out.println("Dummy Action");
  }
  public void getTransactions() {
      System.out.println("Dummy Action");   
  }

  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
    } else {
      System.out.println(" There has been an error in saving \n" );
    }
  }
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CUSTOMER:      addCustomer();
                                break;
        case ADD_PRODUCT:       addProducts();
                                break;
        case ADD_PRODUCT_TO_WISHLIST:      
                                addToWishlist();
                                break;
        case SHOW_CUSTOMER_WISHLIST:      
                                showWishlist();
                                break;
        case ADD_STOCK:         addStock();
                                break;
        case SAVE:              save();
                                break;
        case RETRIEVE:          retrieve();
                                break;
        case SHOW_CUSTOMERS:	  showCustomers();
                                break; 		
        case SHOW_PRODUCTS:	    showProducts();
                                break; 		
        case HELP:              help();
                                break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
