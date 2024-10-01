import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.LinkedList;
public class Wishlist implements Serializable{
    private int customerId;
    private List<WishlistItem> items  = new LinkedList<WishlistItem>();
    
    //constructor
    public Wishlist(int cId)
    {
        this.customerId = cId;
    }
    //get operations
    public int getCustomerId() {
        return customerId;
    }
    public Iterator getItems() {
        return items.iterator();
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public boolean add(WishlistItem w)
    {
        items.add(w);
        return true;
    }

    public WishlistItem find(int pid)
    {
        for (int i=0; i < items.size(); i++)
        {
            if (items.get(i).getProductId() == pid) 
            {
                return items.get(i);
            }
        }
        return null;
    }

    public String toString()
    {
        return items.toString();
    }
}
