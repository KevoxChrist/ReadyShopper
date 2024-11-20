import java.util.*;

public class ShoppingListManager {

   LinkedHashMap<String,ShoppingList> rootlist = new LinkedHashMap<>(); //Responsible for
    // holding all lists (that's why it's called the root)

    ShoppingListManager() {


    }

    //Home List, This is only used to start us off with a list in "main"
    public void startingList(ShoppingListManager manager,String name, ShoppingList shop) {
        ShoppingList startlist = new ShoppingList(name); // All of the Shopping list
        // Characteristics
        rootlist.put(name,startlist);
        startlist.mainMenu(manager, shop); //Any feature from the ShoppingList class is
        // unique to the object being instantiated from it. No copies or duplicates. This
        // will go to the new shopping list object main menu which this object had space
        // made for it in the heap when it was instantiated.
    }

    //Creates a new List
    public void createNewList(ShoppingListManager manager, String name, ShoppingList shop) {
        ShoppingList createnewlist = new ShoppingList(name);
        rootlist.put(name,createnewlist);
        System.out.println(name + " has been created!\n");
        createnewlist.mainMenu(manager, shop);

    }

    //View all list
    public void viewAll() {
        for (String key: rootlist.keySet()) {
            System.out.println(key);

        }
    }

    //Target a specific list
    public void selectList(ShoppingListManager manager, ShoppingList shop) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                System.out.println("Select a list: (Enter the name)" + "\n[0]- BACK");
                String s = scanner.nextLine();

                //Back "button"
                switch (s.trim()) {
                    case "0":
                        try {
                            rootlist.get(shop.listname).viewallLists(manager, shop); // Brings us back
                            // to view all lists page for our current selected list.
                        } catch (
                                NullPointerException e) {// in case a list is empty, which means that
                            // object is empty. This will go to the first object that is in rootlist,
                            // displayed at the view all lists page.
                            for (String x : rootlist.keySet()) {
                                rootlist.get(x).viewallLists(manager, shop);
                            }
                        }
                        break;
                }

                if (s.isEmpty()) {
                    System.out.println("(SELECT LIST) NOT VALID");
                    continue;
                }
                if (rootlist.containsKey(s)) {
                    rootlist.get(s).displayList(manager);
                }
            }catch (Exception e){
                System.out.println("(SELECT CATCH) INVALID INPUT");
                scanner.nextLine();
            }
        }
    }

    //Rename a list
    public void renameList(ShoppingListManager manager, ShoppingList shop) {
        Scanner scanner = new Scanner(System.in);

      while(true) {
          if (rootlist.containsKey(shop.getName())) {
              System.out.println("Enter a new name: "+"\n[0]- BACK");
              String name = scanner.nextLine();

              //Back "button"
              switch (name.trim()) {
                  case "0" : shop.mainMenu(manager,shop);
                      break;
              }

              if (name.isEmpty()) {
                  System.out.println("(RENAMELIST) NOT VALID");
                  continue;
              }
              rootlist.remove(shop.getName()); // If this is not added, rootlist will create a
              // new space (adding it to it's map) for the new name which is
              rootlist.put(name, shop);// .... here!
              shop.setName(name);
              System.out.println("RENAMED!");
              shop.mainMenu(manager, shop);
          }
      }
    }

    //Rename any chosen list
    public void renameAnyList(ShoppingListManager manager, ShoppingList shop) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a list to rename: (Enter the name)"+"\n[0]- BACK");

        while (true){

            String select = scanner.nextLine();

            //Back "button"
            switch (select.trim()) {
                case "0" :  try {
                    rootlist.get(shop.listname).viewallLists(manager, shop); // Brings us back
                    // to view all lists page for our current selected list.
                } catch (NullPointerException e){// in case a list is empty, which means that
                    // object is empty. This will go to the first object that is in rootlist,
                    // displayed at the view all lists page.
                    for (String x: rootlist.keySet()){
                        rootlist.get(x).viewallLists(manager, shop);
                    }
                }
                    break;
            }

        if (select.isEmpty()) {
            System.out.println("(RENAMEANYLIST) NOT VALID");
        } else if (!rootlist.containsKey(select)) {
            System.out.println("(RENAMEANY N LIST) NOT VALID");

        } else if (rootlist.containsKey(select)) {
            manager.renameList(manager, rootlist.get(select));
            }
        }
    }


    //Delete any chosen list
    public void deleteAnyList(ShoppingListManager manager, ShoppingList shop) {

        Scanner scanner = new Scanner(System.in);
        String delete;
        while (true) {
            System.out.println("Select a list to delete: (Enter the name)"+"\n[0]- BACK");
            delete = scanner.nextLine();

            //Back "button"
            switch (delete.trim()) {
                case "0":
                    try {
                        rootlist.get(shop.listname).viewallLists(manager, shop); // Brings us back
                        // to view all lists page for our current selected list.
                    } catch (NullPointerException e){// in case a list is empty, which means that
                        // object is empty. This will go to the first object that is in rootlist,
                        // displayed at the view all lists page.
                        for (String x: rootlist.keySet()){
                            rootlist.get(x).viewallLists(manager, shop);
                        }
                    }
                break;
            }

            if (delete.isEmpty()) {
                System.out.println("(DELETE LIST) NOT VALID");
                continue;
            } else if (!rootlist.containsKey(delete)) {
                System.out.println("(!DELETE LIST) NOT VALID");
                continue;
            }
            if (rootlist.containsKey(delete)) { // Nested if statement to jump back to user
                // input when the inner if statement is 'true'.
                rootlist.get(delete).clearArray();
                rootlist.remove(delete);
                System.out.println("DELETED LIST!");

                if (rootlist.isEmpty()) {
                    while (true) {
                        System.out.println("Create a new list\n" + "Enter a name:");
                        String n = scanner.nextLine();
                        if (n.isEmpty()) {
                            System.out.println("(DELETE CREATE NEW) NOT VALID");
                            continue;
                        }
                        manager.createNewList(manager, n, shop);
                    }
                }
            }

            try {
                rootlist.get(shop.listname).viewallLists(manager, shop); // Brings us back
                // to view all lists page
            } catch (NullPointerException e){// in case a list is empty, which means that
                // object is empty. This will go to the first object that is in rootlist,
                // displayed at the view all lists page.
                for (String x: rootlist.keySet()){
                    rootlist.get(x).viewallLists(manager, shop);
                }
            }

        }
    }
}
