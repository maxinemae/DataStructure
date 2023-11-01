
import java.util.Scanner;
import java.util.Stack;

public class ShoppingCart {
    private Stack<CartItem> cartStack;
    private double totalAmount;

    private class CartItem {
        String itemName;
        double price;

        CartItem(String itemName, double price) {
            this.itemName = itemName;
            this.price = price;
        }
    }
    
    public ShoppingCart() {
        cartStack = new Stack<>();
        totalAmount = 0.0;
    }

    public void addToCart(String itemName, double price) {
        CartItem item = new CartItem(itemName, price);
        cartStack.push(item);
        System.out.println(itemName + " added to the cart.");
        totalAmount += price;
    }

    public void removeFromCart() {
        if (!cartStack.isEmpty()) {
            CartItem removedItem = cartStack.pop();
            System.out.println(removedItem.itemName + " removed from the cart.");
            totalAmount -= removedItem.price;
        } else {
            System.out.println("The cart is empty.");
        }
    }

    public double displayCart() {
        System.out.println("  Shopping Cart Contents: \n");
        double totalAmount = 0.0 ;
        for (CartItem item : cartStack) {
            System.out.println("  " + item.itemName + " - ₱ " + String.format("%.2f \n", item.price));
            totalAmount += item.price;
        }
        return totalAmount;
    }

    public int getSize() {
        return cartStack.size();
    }

    public void updateItem(String oldName, String newName, double newPrice) {
        Stack<CartItem> tempStack = new Stack<>();
        boolean found = false;

        while (!cartStack.isEmpty()) {
            CartItem item = cartStack.pop();
            if (item.itemName.equals(oldName)) {
                item.itemName = newName;
                totalAmount -= item.price;
                item.price = newPrice;
                totalAmount += newPrice;
                found = true;
            }
            tempStack.push(item);
        }

        while (!tempStack.isEmpty()) {
            cartStack.push(tempStack.pop());
        }

        if (found) {
            System.out.println(oldName + " updated to " + newName);
        } else {
            System.out.println(oldName + " not found in the cart.");
        }
    }

    public void removeItem(String itemName) {
    String itemToRemove = itemName.toLowerCase(); // Convert input to lowercase
    Stack<CartItem> tempStack = new Stack<>();
    boolean found = false;

    while (!cartStack.isEmpty()) {
        CartItem item = cartStack.pop();
        if (!item.itemName.toLowerCase().equals(itemToRemove)) {
            tempStack.push(item);
        } else {
            found = true;
            totalAmount -= item.price;
        }
    }

    while (!tempStack.isEmpty()) {
        cartStack.push(tempStack.pop());
    }

    if (found) {
        System.out.println(itemName + " removed from the cart.");
    } else {
        System.out.println(itemName + " not found in the cart.");
    }
}
    public void searchItem(String itemName) {
    boolean found = false;
    itemName = itemName.toLowerCase(); // Convert the search input to lowercase

    for (CartItem item : cartStack) {
        if (item.itemName.toLowerCase().equals(itemName)) { // Convert item names to lowercase for comparison
            System.out.println("  Items found in the cart: " + item.itemName + " - ₱" + String.format("%.2f", item.price));
            found = true;
        }}
    if (!found) {
        System.out.println("Item not found in the cart.");
    }
}
    public void createItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("  Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("  Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("  Add this item to the cart? (Yes/No): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            addToCart(itemName, price);
        }
    }
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n  SIMPLE SHOPPING CART SYSTEM");
            System.out.println("     Midterm in Data Struct");
            System.out.println("       Sayson & Serfino\n");
            System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("  ------");
            System.out.println(" | MENU |");
            System.out.println("  ------\n");
            System.out.println("  1. Create item.");
            System.out.println("  2. Remove items from cart.");
            System.out.println("  3. Update items in cart.");
            System.out.println("  4. View items in cart.");
            System.out.println("  5. Search for item in cart.");
            System.out.println("  6. Checkout and Pay.");
            System.out.println("  7. Quit.\n");
            System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.print("  Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cart.createItem();
                    break;
                case 2:
                    System.out.print("\n  Enter the name of the item to delete: ");
                    String itemToDelete = scanner.next();
                    cart.removeItem(itemToDelete);
                    break;
                case 3:
                    System.out.print(" Enter the name of the item to update: ");
                    String oldName = scanner.next();
                    System.out.print(" Enter the new name: ");
                    String newName = scanner.next();
                    System.out.print(" Enter the new price: ");
                    double newPrice = scanner.nextDouble();
                    cart.updateItem(oldName, newName, newPrice);
                    break;
                case 4:
                    System.out.println("\n  Viewing items in cart: ");
                    cart.displayCart();
                    break;
                case 5:
                    System.out.print(" Enter the name of the item to search for: ");
                    String itemToSearch = scanner.next();
                    cart.searchItem(itemToSearch);
                    break;
                case 6:
                    System.out.println("\n  Shopping Cart Contents: ");
                      for (CartItem item : cart.cartStack) {
                          System.out.println("  " + item.itemName + " - ₱ " + String.format("%.2f", item.price));
        }

                   System.out.println("  Total items in cart: " + cart.cartStack.size());
                   System.out.println("  Total amount in cart: ₱ " + String.format("%.2f\n", cart.totalAmount));
                        
                    System.out.print("  Confirm order? (Yes/No): ");
                    String confirmChoice = scanner.next();
                    if (confirmChoice.equalsIgnoreCase("yes")) {
                      
                   System.out.println("\n  Order confirmed.");
                   System.out.print("  Select payment method \n  (Maya / PayPal / GCash / Others): ");
                   String paymentMethod = scanner.next();
                     if (paymentMethod.equalsIgnoreCase("others")) {
                   System.out.print("  Enter your custom payment method: ");
                        paymentMethod = scanner.next();}
                   System.out.println("  Processing payment using " + paymentMethod);
                   System.out.println("  Payment successful.");
                   cart.cartStack.clear();
                   cart.totalAmount = 0.0; // Reset total amount
                   System.out.println("  Cart cleared.");
    }
                    break;
                case 7:
                    System.out.println("  Thank you for shopping!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("  Invalid choice. Please try again.");
            }}}}