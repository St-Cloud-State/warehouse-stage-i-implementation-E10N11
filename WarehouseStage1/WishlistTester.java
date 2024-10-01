import java.io.*;
import java.lang.*;

public class WishlistTester {
    public static void main(String[] args) {
        Wishlist w = new Wishlist(0);
        Product p1 = new Product("Test Item 1", 6, 36.36);
        Product p2 = new Product("Test Item 2", 3, 12.12);
        WishlistItem w1 = new WishlistItem(p1, 3);
        w.add(w1);
        System.out.println("Wishlist Item added:");
        System.out.println(w1.toString());
        WishlistItem w2 = new WishlistItem(p2, 4);
        System.out.println("Wishlist Item added:");
        System.out.println(w2.toString());
        w.add(w2);
        System.out.println("Final Wishlist:");
        System.out.println(w.toString());
    }
}
