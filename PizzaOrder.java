import java.util.Date;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 


public class PizzaOrder {
	
	public static void main(String [] args){		
		Scanner keyboard = new Scanner(System.in);	
		boolean discount = false;     				
		char choice;                  		
		String input;
		int numOfOrders = 0;            				   
		String[] orders = new String[10];	
						
		System.out.println("====================================");
		System.out.println("Welcome to \"Eat&Chat\" Pizza Order!");
		System.out.println("====================================");
		
		System.out.print("Today is: ");
		printCurrentDate();		
		System.out.println();
		System.out.print("> Is it your BIRTHDAY? (10% discount available on presenting ID)  (Y/N):  ");
		input = keyboard.nextLine();

// TASK-1: Discount	Eligibility
		if(input.startsWith("Y") || input.startsWith("y")) {
			discount = true;
		}
		orders[numOfOrders++] = orderPizza();	
		previewOrder(orders);

// TASK-2: Repeated Menu Options 	
		while(true) {
			printMenu();

			input = keyboard.nextLine();
			choice = input.charAt(0);

			switch(choice){

				case '1': 
					confirmOrder(orders, discount);
					System.exit(0);
					break;
					

				case '2':					
					orders[numOfOrders++] = orderPizza();	
					previewOrder(orders);					
					break;

				case '3': 
					System.out.println("Good bye!");
					System.exit(0);

				default: 
					System.exit(0);
			}
		}
	}

	public static void printMenu(){		
		System.out.println("------------MENU-------------");
		System.out.println("1 - Complete");
		System.out.println("2 - Add another order");
		System.out.println("3 - Exit"); 
		System.out.print("> Choose one of the above (Enter a number): ");
	}

	public static String orderPizza(){ 
		Scanner keyboard = new Scanner(System.in);
		String input;                 	
		char choice;                  	
		int size;                   	
		int cost = 0;          			
		int numberOfToppings = 0;     	
		String toppings = "Cheese";  	
		String result = "";				
		final int toppingCost = 200;	

		System.out.println("-----------------------------");
		System.out.println("Pizza Size (cm)      Cost");
		System.out.println("       20            1000 T");
		System.out.println("       30            1500 T");
		System.out.println("       40            2000 T");
		System.out.println("What size pizza would you like?"); 
		System.out.print("> 20, 30, or 40 (enter the number only): ");
		size = keyboard.nextInt();

// TASK-3: Set Price
		switch(size) {
			case 20: cost = 1000; break;
			case 30: cost = 1500; break;
			case 40: cost = 2000; break;
		}
		
		keyboard.nextLine(); 
		                
		System.out.println("-----------------------------");              
		System.out.println("All pizzas come with cheese."); 
		System.out.println("Additional toppings are 200T each," + " choose from:");
		System.out.println("- Meat, Sausage, Vegetables, Mushroom");

		System.out.print("> Do you want Meat?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Meat";
		}
		System.out.print("> Do you want Sausage?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Sausage";
		}
		System.out.print("> Do you want Vegetables?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Vegetables";
		}
		System.out.print("> Do you want Mushroom?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Mushroom";
		}

// TASK-4: Toppings Cost 
		cost = (numberOfToppings * toppingCost) + cost;
		result += size + "cm pizza, " + toppings;
		result += ":" + cost;
		return result;
   	}

	public static void previewOrder(String[] orders){
		System.out.println("-----------------------------");
		System.out.println("Your order: ");

// TASK-5: Order Info
		for (int i = 0; i < orders.length; i++) {
			int count = i + 1;
			if(orders[i] != null) {
				System.out.println(count + ")" + orders[i]);
			}
		}
		System.out.println("Total: " + getTotalCost(orders) + " T");
	}

// TASK-6: Total Cost
    public static int getTotalCost(String[] orders){
		int total_cost = 0;
		for(int i = 0; i < orders.length; i++) {
			if(orders[i] != null) {
				String[] pizzaDetails = orders[i].split(":");
				total_cost += Integer.parseInt(pizzaDetails[1]);
			}
		}
		return total_cost;
	}

	public static void confirmOrder(String[] orders, boolean discount){
		final int DISCOUNT_AMOUNT = 10;	

		System.out.println("#############################################");
		previewOrder(orders);

		int cost = getTotalCost(orders);

// TASK-7: Discount Calculation 
		if(discount) {
			System.out.println("-----------------------------");
			System.out.println("TOTAL with DISCOUNT (on presenting ID only!):");
			cost -= (cost / DISCOUNT_AMOUNT);
			System.out.println("Total: " +  cost + "T");
		}
		System.out.println("-----------------------------");
		System.out.println("Your order will be ready for pickup in 30 minutes.");

		System.out.print("Date: ");
		printCurrentDate();			

		System.out.print("\tTime: ");
		printCurrentTime();	
		System.out.println();

		System.out.println("Order ID: " + generateCode());
	}

// TASK-8: Current Date 
	public static void printCurrentDate(){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");  
		LocalDateTime currentTime = LocalDateTime.now();  
		System.out.print(dateTimeFormatter.format(currentTime));
	} 

// TASK-9: Current Time 
	public static void printCurrentTime(){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");  
		LocalDateTime currentTime = LocalDateTime.now();  
		System.out.print(dateTimeFormatter.format(currentTime));
	}

// TASK-10: Generate Code
	public static String generateCode(){
		String pattern = "";
		for(int i = 0; i < 4; i++) {
			pattern += String.valueOf((int)(Math.random() * 10));
		}
		return pattern;
	}
}