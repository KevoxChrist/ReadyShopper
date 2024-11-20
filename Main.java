import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

public static void main (String [] args) {

    Scanner scanner = new Scanner(System.in);
    ShoppingListManager homelist = new ShoppingListManager();
    
    //Set up for the start of a new list
    System.out.println("Welcome Shopper!");
    System.out.println("Create a new shopping list:\n[1]-YES ");


    while (true) {// Double while loop so that when the catch is activated, it will loop
        // right back to our user input (int c). The catch is the last statement in the loop.
        try {
        while (true){
            int c = scanner.nextInt(); //User input (Looking for a typed answer of 1 to
            // continue

            if (!(c == 1)) {
                System.out.println("(CHOICE) Type '1' to continue: ");
                continue;
            } else {
                System.out.println("Name your List :");
                scanner.nextLine();
            }
            while (true){
            String n = scanner.nextLine();

            if (n.isEmpty()) {
                System.out.println("(NAME) Type a 'name' to continue:");
            } else {
                System.out.println(n + " has been created!\n");
                homelist.startingList(homelist, n, homelist.rootlist.get(n));

                }
            }
        }
            } catch(InputMismatchException e){
                 System.out.println("(CHOICE CATCH) Type '1' to continue: ");
                 scanner.nextLine();// Consumes any new line character if user happens to
            // type a character for (int c), which is a user input asking for an integer.
        }

         }
    }
}
