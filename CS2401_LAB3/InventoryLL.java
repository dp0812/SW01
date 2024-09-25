public class InventoryLL{

    // TODO: Create a Linked List with the correct Attributes
    Node head;
    Node tail;
    int size;

    // TODO: Create a Linked List Constructor 
    public InventoryLL(){
        this.head = null;
        this.tail = null;
        size = 0;
    }

    //bruh
    // TODO
    public void addToInventory(String[] item){
        Node it01 = new Node(item);
        if(this.size<=4){
            if (this.head == null) {
                this.head = it01;
                this.tail = this.head;
            } else{
                this.tail.next = it01;
                this.tail = it01;
            }
            this.size++; //increase the size when adding
        } else{
            System.out.println("Your inven already have 5 items, please sell some before adding more. ");
        }

    } 
    // TODO
    public boolean inInventory(String itemName){
        Node current = head;  // we make a copy of whatever the head has and put inside current. 
        while(current != null ){
            if(current.data[1].equals(itemName)){ 
                return true; 
            }
            current = current.next;
        }
        return false;
    }
    // TODO
    /*
     * when you remove data, let say we have A B and C. When removing B, we tell A to connect with C (thus ignoring B and the system will kill B)
     * meaning you need to check for the position of the data (hence "current")
     *  */
    public void removeItem(String itemName){
        //the current is not a must have part of the attribute, so it goes into here. 
        Node current = head;
        if(inInventory(itemName)){//making sure the item actually exist!
            while (current != null) { //while current is not pointing outside the list
                if (current.data[1].equals(itemName)) {
                    this.head = head.next;
                    current = head;
                    System.out.println("Successfully remove the item. ");
                    break;
                }
                if(current.data[1].equals(itemName)){  //check the data of the current position
                    current.next = current.next.next;
                    if (current.next == null) { //the case when removing the tail of the list
                        tail = current;
                        System.out.println("Successfully remove the item. ");
                    }
                    System.out.println("Successfully remove the item. ");
                }
                current = current.next;
            }
            this.size -=1; //decrease the size by 1 if a removal was successful; 
        } else{
            System.out.println("the item is not in your inventory. ");
        }
    } 
    // TODO
    public void displayItems() {
        Node current = head;
        //number of attribute is 5, so less than 5 is a condition
        if(current == null){
            System.out.println("There is nothing in your inventory.");    
        }
        while (current != null) { 
            System.out.println("Your item: ");
            for(int i = 0; i<5; i++){
                System.out.print(current.data[i] + " | ");
            }
            System.out.println("------");
            current = current.next;
        }
    }
    // TODO
    public String[] getFromInventory(int index) {
        Node current = head;
        if(index>=this.size){//i move the contraints condition to the add and remove method
            System.out.println("Sorry, that's something that doesn't exist in your inventory.");
        } else{

            int counter = 0;
            while (current!=null) { 
                if (index == counter){
                    return current.data;
                } 
                counter+=1;
                current = current.next; //move the pointer
            }
            
        }
        return null;
    }
}





