
import java.util.Scanner;
import java.util.Stack;
//library
//import stack, nagpasabot nga naggamit ka og stack nga data structure ug naggamit kag mga stack operations like pop push
// import scanner, nagpasabot nga gigamit nimo ang scanner class sa pagkuha sa input sa mga user pinaagi sa console.


public class ShoppingCart {
    //nagdefine og usa ka class nga gitawag og ShoppingCart
    // sud ani nga class kay nagbutang tag access modifier nga private
    private Stack<CartItem> cartStack;
    //private nagpasabot nga ang variable ma access ra sulod sa ShoppingCart class og dili  sa gawas
    // STACK<CARTITEM> nagpasabot nga ang cartStack ang magdala sa mga object sa class nga CartItem
    // CARTSTACK ngan sa variable
    private double totalAmount;
    //private member siya sa ShoppingCart nga class
    //nagrepresent sa total sa tanang items sulod sa cart
    //private nagpasabot nga ang totalAmount dili diretso nga maaccess og mabag o sa uban class gawas sa ShoppingCart
    //totalAmount total ni sa items sulod sa cart while nagremove or nag add og items sulod sa cart
    //gihimo ni siyang private aron dili direktang naa sa public access aron naay integrity ang data sa imong shoppingcart
    
    
    
    private class CartItem {
        //bag ong class nga ginganlag CartItem
        //public inner class sulod sa ShoppingCart
        //public, meaning pwede sa ma access sa ubang class or objects sa program
        String itemName;
        double price;
// nagdefine og duha ka variable wherein ang ITEMBAME kay naay datatype nga  string & PRICE kay double
            
        CartItem(String itemName, double price) {
            this.itemName = itemName;
            this.price = price;
            // use to set and assign values to the instance variable of CARTITEM which is the itemName and price
        }
    }
    
    public ShoppingCart() {
        // automatically called
        cartStack = new Stack<>();
        //cartStack is of type Stack<CartItem>,
        //which means it's a stack data structure that can hold objects of the CartItem class
        //NEWSTACK<>(); an empty stack
        totalAmount = 0.0;
        // object variable so kung magcreate kag bag ong shopping cart wala siya daan items og cost
    }

    public void addToCart(String itemName, double price) {
        // nagdeclare og variable 
        //part of ShoppingCart class aron maka add ta sa cart
        CartItem item = new CartItem(itemName, price);
        // creates an item with its name and price
        cartStack.push(item);
        //push means putting the item you just created at the top of the stack
        System.out.println(itemName + "  added to the cart.");
        //prints a message to the console, informing the user that the specified item has been added to the cart
        totalAmount += price;
        //adds the price with the items you just added 
        // keeps running the total cost of the items inside ur cart
    }

    public double displayCart() {
        //responsible for displaying the items in the shopping cart and calculating the total amount of the items in the cart
        System.out.println("  Shopping Cart Contents: \n");
        double totalAmount = 0.0 ;
        //keep track of the total cost.
        for (CartItem item : cartStack) {
            //foreach loop
            //for-each" loop to iterate over the cartStack, u are essentially going through each item 
            //in the cart, one item at a time, and performing some action on it.
            //For example, you might be displaying the item's name and price, calculating the total cost, or making changes to the items in the cart.
            System.out.println("  " + item.itemName + " - ₱ " + String.format("%.2f \n", item.price));
            totalAmount += item.price;
            //prints its name and price in a formatted manner, and it also adds the price of that item to the totalAmount
        }
        return totalAmount;
        //represents the total cost of all items in the cart
    }
    public int getSize() {
        //returns the size of the cartStack, which is the number of items currently in the shopping cart.
        return cartStack.size();
        //method from the Stack class to determine the number of items.
    }

    public void updateItem(String oldName, String newName, double newPrice) {
    // Create a temporary stack to hold items during the update process.
    Stack<CartItem> tempStack = new Stack<>();
    boolean found = false;
    // Convert the oldName to lowercase for case-insensitive comparison.
    String oldNameLower = oldName.toLowerCase();

    while (!cartStack.isEmpty()) {
        // Pop an item from the original cart stack.
        CartItem item = cartStack.pop();
        // Check if the item's name matches the oldName (case-insensitive comparison).
        if (item.itemName.equalsIgnoreCase(oldNameLower)) {
            // Update the item's name and price.
            item.itemName = newName;
            totalAmount -= item.price;
            item.price = newPrice;
            totalAmount += newPrice;
            found = true;
        }
        // Push the item to the temporary stack.
        tempStack.push(item);
    }

    while (!tempStack.isEmpty()) {
        // Pop items from the temporary stack and push them back to the original cart stack.
        cartStack.push(tempStack.pop());
    }

    if (found) {
        // If an item was found and updated, display a message.
        System.out.println(oldName + " updated to " + newName);
    } else {
        // If no matching item was found, display a message.
        System.out.println(oldName + " not found in the cart.");
    }

    // Display the items left in the cart after the update.
    System.out.println("\nItems left in the cart after update:\n");
    for (CartItem item : cartStack) {
        // Print the updated item details.
        System.out.println("  " + item.itemName + " - ₱ " + String.format("%.2f", item.price));
    }
}
public void removeItem(String itemName) {
    // Convert the input item name to lowercase for case-insensitive comparison.
    String itemToRemove = itemName.toLowerCase();
    // Create a temporary stack to hold items during the removal process.
    Stack<CartItem> tempStack = new Stack<>();
    boolean found = false;

    while (!cartStack.isEmpty()) {
        // Pop an item from the original cart stack.
        CartItem item = cartStack.pop();
        // Check if the item's name (in lowercase) matches the item to be removed.
        if (!item.itemName.toLowerCase().equals(itemToRemove)) {
            // If the item's name doesn't match, push it to the temporary stack.
            tempStack.push(item);
        } else {
            // If a matching item is found, mark it as found and subtract its price from the total.
            found = true;
            totalAmount -= item.price;
        }
    }

    while (!tempStack.isEmpty()) {
        // Pop items from the temporary stack and push them back to the original cart stack.
        cartStack.push(tempStack.pop());
    }

    if (found) {
        // If a matching item was found and removed, display a message.
        System.out.println(itemName + " removed from the cart.");
    } else {
        // If no matching item was found, display a message.
        System.out.println(itemName + " not found in the cart.");
    }
}

  public void searchItem(String itemName) {
    // Initialize a boolean variable to track whether the item is found in the cart.
    boolean found = false;
    // Convert the search input (itemName) to lowercase for case-insensitive comparison.
    itemName = itemName.toLowerCase();

    // Iterate through each CartItem in the cartStack using an enhanced for loop.
    for (CartItem item : cartStack) {
        // Convert the itemName of the current item in the loop to lowercase for comparison.
        if (item.itemName.toLowerCase().equals(itemName)) {
            // If the current item's name matches the search term (case-insensitive), execute the following block.
            // Display the found item's name and price.
            System.out.println("  Items found in the cart: " + item.itemName + " - ₱" + String.format("%.2f", item.price));
            // Mark that an item was found.
            found = true;
        }
    }
    // After looping through all items, check if any matching item was found.
    if (!found) {
        // If no matching item was found, display a message.
        System.out.println("Item not found in the cart.");
    }
}

    public void createItem() {
    // Create a new Scanner object for user input.
    Scanner scanner = new Scanner(System.in);

    // Prompt the user to enter the item name and display a message.
    System.out.print("  Enter item name: ");
    
    // Read the entered item name as a String from the user.
    String itemName = scanner.nextLine();
    
    // Prompt the user to enter the item price and display a message.
    System.out.print("  Enter item price: ");
    
    // Read the entered item price as a double from the user.
    double price = scanner.nextDouble();
    
    // Consume the newline character left in the input buffer to prevent issues when reading the next line.
    scanner.nextLine();
    
    // Prompt the user to confirm whether they want to add the item to the cart (Yes/No) and display a message.
    System.out.print("  Add this item to the cart? (Yes/No): ");
    
    // Read the user's choice (Yes or No) as a String from the user.
    String choice = scanner.nextLine();
    
    // Check if the user's choice is case-insensitive "yes" using the `equalsIgnoreCase` method.
    if (choice.equalsIgnoreCase("yes")) {
        // If the user's choice is "yes," add the item to the cart by calling the `addToCart` method.
        addToCart(itemName, price);
    }
}

    public static void main(String[] args) {
    // Create a new ShoppingCart object to manage the user's cart.
    ShoppingCart cart = new ShoppingCart();
    
    // Create a new Scanner object for user input.
    Scanner scanner = new Scanner(System.in);
    
    // Start an infinite loop to display the menu and process user choices.
    while (true) {
        // Display the program's header and menu options.
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
        
        // Read the user's choice as an integer.
        int choice = scanner.nextInt();
        
        // Process the user's choice using a switch statement.
        switch (choice) {
            case 1:
                // If the user chooses 1, call the createItem method to add an item to the cart.
                cart.createItem();
                break;
            case 2:
                // If the user chooses 2, prompt for the item to remove and call the removeItem method.
                System.out.print("\n  Enter the name of the item to delete: ");
                String itemToDelete = scanner.next();
                cart.removeItem(itemToDelete);
                break;
            case 3:
                // If the user chooses 3, prompt for the item to update and call the updateItem method.
                System.out.print(" Enter the name of the item to update: ");
                String oldName = scanner.next();
                System.out.print(" Enter the new name: ");
                String newName = scanner.next();
                System.out.print(" Enter the new price: ");
                double newPrice = scanner.nextDouble();
                cart.updateItem(oldName, newName, newPrice);
                break;
            case 4:
                // If the user chooses 4, call the displayCart method to view the items in the cart.
                System.out.println("\n  Viewing items in cart: \n");
                cart.displayCart();
                break;
            case 5:
                // If the user chooses 5, prompt for the item to search and call the searchItem method.
                System.out.print("  Enter the name of the item to search for: ");
                String itemToSearch = scanner.next();
                cart.searchItem(itemToSearch);
                break;
            case 6:
                // If the user chooses 6, simulate the checkout and payment process.
                System.out.println("\n  Shopping Cart Contents: ");
                for (CartItem item : cart.cartStack) {
                    System.out.println("  " + item.itemName + " - ₱ " + String.format("%.2f", item.price));
                }
                // Display total items and total amount in the cart.
                System.out.println("  Total items in cart: " + cart.cartStack.size());
                System.out.println("  Total amount in cart: ₱ " + String.format("%.2f\n", cart.totalAmount);
                // Prompt the user to confirm the order and choose a payment method.
                System.out.print("  Confirm order? (Yes/No): ");
                String confirmChoice = scanner.next();
                if (confirmChoice.equalsIgnoreCase("yes")) {
                    System.out.println("\n  Order confirmed.");
                    System.out.print("  Select payment method \n  (Maya / PayPal / GCash / Others): ");
                    String paymentMethod = scanner.next();
                    if (paymentMethod.equalsIgnoreCase("others")) {
                        System.out.print("  Enter your custom payment method: ");
                        paymentMethod = scanner.next();
                    }
                    // Simulate payment processing.
                    System.out.println("  Processing payment using " + paymentMethod);
                    System.out.println("  Payment successful.");
                    // Clear the cart after successful payment.
                    cart.cartStack.clear();
                    cart.totalAmount = 0.0; // Reset the total amount
                    System.out.println("  Cart cleared.");
                }
                break;
            case 7:
                // If the user chooses 7, exit the program and display a thank you message.
                System.out.println("  Thank you for shopping!");
                scanner.close(); // Close the Scanner.
                System.exit(0); // Terminate the program.
                break;
            default:
                // If the user enters an invalid choice, display an error message.
                System.out.println("  Invalid choice. Please try again.");
        }
    }
    }
