import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ShoppingList{
//make a class or method that carries the shopping list "Updates". Call it notifications.


    Scanner s = new Scanner(System.in);
    protected Robot robot;
    protected ArrayList<String> shoppinglist;
    protected String listname;
    protected String additem;
    private int itemcount;

    ShoppingList(String name) {
        //Shopping list (ARRAY)
        this.listname = name;
        this.shoppinglist = new ArrayList<>();
        itemcount = 0;
    }

    public String getName() {
        return listname;
    }

    public void setName(String names) {
        this.listname = names;
    }


    //--------------------------------ADD+----------------------------------------------
    public void addItem(ShoppingListManager manager) {
        System.out.println("Add item:"+"\n[0]- BACK");

        while (true) {

            additem = s.nextLine();

            switch (additem.trim()) { //Trim method is being used because it looks for
                // characters, removing leading and trailing spaces, looking only for
                // characters which the computer refers to as a "String".
                case "0": this.displayList(manager);
                break;
            }

            if (additem.isEmpty()) {
                System.out.println("Add item:");

            } else if (!additem.trim().isEmpty()) {

                itemcount++; // called here so that this integer is always incrementing when
                // called in our print statement. In other words, itemcount will always
                // increment by 1 when the user input is not empty.This is a better solution
                // then stating itemcount in our shoplist() method where itemcount will only
                // increment if shoplist is called or if we go back to our "main menu";
                shoppinglist.add(itemcount + "- " + additem);
                // item count changes "data" when an item is added, incrementing by 1. If I add
                // Ketchup as my first item, it will display as Ex: "1. Ketchup". From this point
                // itemcount is now starting from the most recent incremented number when an item
                // is added, which is now 1. Before, itemcount was only 0 until being called in
                // our argument (), which it will then increment by 1 since it's being called.
                System.out.println("ADDED ITEM!");
                displayList(manager);
            }
        }

    }

    //----------------------------------DELETE----------------------------------------------
    public void deleteItem(ShoppingListManager manager) {

        //Printing elements in shoppinglist ArrayList
        System.out.println("SHOPPING LIST: ");
        for (String x : shoppinglist) {
            System.out.println(x);
        }
        System.out.println("Delete Item (Enter an item number)"+"\n[0]- BACK");




        while (true) { //Double while loop so when the exception is triggered, it will jump
            // back up to deleteIndex for a user input.
            try {
                while (true) {
                    int deleteIndex = s.nextInt();

                    //Back "button"
                    switch (deleteIndex) {
                        case 0 : this.displayList(manager);
                            break;
                    }

                    if (deleteIndex < 1 || deleteIndex > shoppinglist.size()) {
                        System.out.println("(DELETE) Invalid. Please enter a number:");
                    } else if (deleteIndex <= shoppinglist.size()) {
                        // if the user's input is greater than element 1 in shopping list and
                        // less than or equal to shoppinglist greatest element, then remove
                        // (remove works with indexes not elements in an arraylist) the index
                        // number in shoppinglist that the user typed minus 1. ( minus 1
                        // since itemcount starts from 1 (if an item is added) then goes on.

                        // So while the list may have 3 items, it really has an index of 2
                        // [0,1,2], and 3 elements [1,2,3].).
                        shoppinglist.remove(deleteIndex - 1);
                        System.out.println("DELETED ITEM!");
                        renumberList();// Reprints list with a proper numeric sequence. Ex:
                        // 1,2,3
                        displayList(manager);//our exit
                    }
                }// WHILE LOOP END
            } catch (InputMismatchException e) {
                System.out.println("(DELETE CATCH) Invalid. Please enter a number:");
                s.nextLine();
            }
        }
    }

    //----------------------------------EDIT-----------------------------------------------
    public void editItem(ShoppingListManager manager) {
        //Printing elements in shoppinglist ArrayList
        System.out.println("SHOPPING LIST: ");
        for (String e : shoppinglist) {
            System.out.println(e);
        }
        //EDIT
        System.out.println("Edit item (Enter an item number):" + "\n[0]- BACK");

        //int editchoice;// works without initializing a value (ex. -1;)
        while (true) {
            try {
                while (true) {
                    int editchoice = s.nextInt();

                    //Back "button"
                    switch (editchoice) {
                        case 0 : this.displayList(manager);
                            break;
                    }

                    if (editchoice < 1 || editchoice > shoppinglist.size()) {
                        System.out.println("(EDIT) Invalid. Please enter a number:");
                    } else {
                        System.out.println("Edit item " + editchoice + ": ");
                        s.nextLine();
                        String choiceItem = s.nextLine();
                        itemcount = editchoice;
                        shoppinglist.set(editchoice - 1, itemcount + "- " + choiceItem);
                        itemcount = shoppinglist.size(); //set back to size of shoppinglist so
                        // when an item is added, the appropriate number will display. For
                        // example, if I chose to edit item 5 but my list has 10 items, not
                        // appropriating itemcount to the numerical size of shoppinglist
                        // will result in a false number when an item is prompt by the user
                        // to be added. If I add an item the item number will be displayed
                        // as 6 though I now have 11 items in my list.

                        System.out.println("EDIT DONE!");
                        displayList(manager);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("(EDIT CATCH) Invalid. Please enter a number:");
                s.nextLine();
            }
        }
    }

    //-------------------------------------DISPLAY LIST--------------------------------------------
    public void displayList(ShoppingListManager manager) {

        //Printing elements in shoppinglist ArrayList
        System.out.println(listname + " list");
        for (String x : shoppinglist) {
            System.out.println(x);
        }
        System.out.println("\n[1]- EDIT ITEM" + "  [2]- ADD ITEM" + "  [3]- DELETE ITEM" +
                " [4]- MAIN MENU");

        while (true) {

            try {
                int listchoice = s.nextInt();


                //Edit & Deletion precaution (returns back to display menu if shopping list is
                // empty)
                if (listchoice == 1 && this.shoppinglist.isEmpty()) {
                    System.out.println("(Unavailable because shopping list is empty)");
                    this.displayList(manager);
                } else if (listchoice == 3 && this.shoppinglist.isEmpty()) {
                    System.out.println("(Unavailable because shopping list is empty)");
                    this.displayList(manager);
                } else if (listchoice < 1 || listchoice > 4) {
                    System.out.println("(DISPLAY) Invalid. Please enter a number:");
                    continue;
                }

                //Cases for keys 1 - 4
                switch (listchoice) {

                    case 1:
                        s.nextLine();
                        editItem(manager);
                        break;
                    case 2:
                        s.nextLine();
                        addItem(manager);
                        break;
                    case 3:
                        s.nextLine();
                        deleteItem(manager);
                        break;
                    case 4:
                        s.nextLine();
                        mainMenu(manager, this);
                }


            } catch (InputMismatchException e) {
                System.out.println("(DISPLAY CATCH) Invalid. Please enter a number:");
                s.nextLine();
            }
        }
    }

    //-----------------------------------MAIN MENU---------------------------------------------
    public void mainMenu(ShoppingListManager manager, ShoppingList shop) {

        System.out.println(listname + " list");
        System.out.println("1. SHOW LIST");
        System.out.println("2. RENAME");
        System.out.println("3. CREATE NEW LIST");
        System.out.println("4. VIEW ALL");

        while (true) {
            //int choice=-1; // works without initializing a value (ex. -1;)
            try {
                int choice = s.nextInt();

                if (choice < 1 || choice > 4) {
                    System.out.println("(MAIN MENU) Invalid. Please enter a number:");
                    s.nextLine();
                    continue; // stops executing the code in this if statement and
                    // skips to the next iteration (loop of sort) which would be int choice
                    // inside of this while loop.
                }

                //Cases for keys 1-4
                switch (choice) {

                    case 1:
                        s.nextLine();
                        displayList(manager);
                        break;
                    case 2:
                        s.nextLine();
                        manager.renameList(manager, this);
                        break;
                    case 3:
                        s.nextLine();
                        newList(manager, shop);
                        break;
                    case 4:
                        s.nextLine();
                        viewallLists(manager, shop);
                        break;
                    default:
                        System.out.println("Invalid");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("(MAIN MENU CATCH) Invalid. Please enter a number: ");
                s.nextLine();
            }

        }

    }

    //----------------------------------RENUMBERED LIST-----------------------------------
    //comeback to this!!! Learn how this works. EDIT: IT WORKKKSSSSSSSSS. THANK YOU JESUS MY
    // LORDDDD
    public void renumberList() {
        itemcount = 0;

        //incrementing through every element in shoppinglist to print
        for (int i = 0; i < shoppinglist.size(); i++) {
            String element = shoppinglist.get(i);

            String modifiedElement = element.replaceAll("^\\d-\\s*", ++itemcount + "- ");
            //the regex is looking for a sequence of characters at the beginning (^) of the
            // given string, as well as an integer(/d), and whitespaces(/s). (*) indicating
            // whitespaces will appear zero or more times.

            shoppinglist.set(i, modifiedElement);// prints shopping list elements with a
            // updated numerical sequence.

        }

    }

    public void newList(ShoppingListManager manager, ShoppingList shop) {
        System.out.println("Name of List: "+"\n[0]- BACK");
        String x = s.nextLine();

        //Back "button"
        switch (x.trim()) {
            case "0" : this.mainMenu(manager,shop);
                break;
        }

        manager.createNewList(manager, x, shop);
    }

    public void viewallLists(ShoppingListManager manager, ShoppingList shop) {
        System.out.println("All Shopping Lists: ");
        manager.viewAll();
        System.out.println("\n[1]- RENAME " + "[2]- SELECT " + "[3]- DELETE " + "[4]- MAIN MENU");

        int x = s.nextInt();

        switch (x) {
            case 1:
                s.nextLine();
                manager.renameAnyList(manager,shop);
                break;
            case 2:
                s.nextLine();
                manager.selectList(manager,shop);
                break;
            case 3:
                s.nextLine();
                manager.deleteAnyList(manager, shop);
                mainMenu(manager, this);
                break;
            case 4:
                s.nextLine();
                mainMenu(manager, this);
                break;
        }

    }

    public void clearArray() { //clears any objects from the arraylist. Good for memory.
        shoppinglist.clear();
    }

}
