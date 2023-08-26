package com.bus.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bus.Beans.Customer;
import com.bus.Colours.Colours;
import com.bus.UseCases.AdminUseCase1;
import com.bus.UseCases.CustomerUseCase1;

public class Main {
	
	public static Colours c=new Colours();
	
	public static Scanner sc=new Scanner(System.in);
	
    public static AdminUseCase1 a=new AdminUseCase1();
    
    public static CustomerUseCase1 cu=new CustomerUseCase1();
	
	
	
	static void adminOrCustomer() {
		System.out.println(c.RED_BOLD + "+---------------------------+" + "\n"
						 						   + "| 1. Login As Administrator |" + "\n"
						 						   + "| 2. Login As Customer      |" + "\n"
						 						   + "| 3. Exit                   |" + "\n"
						 						   + "+---------------------------+" +c.GREEN_BOLD);
	    
		                                           
		choice();
		
	//	System.out.println(c.Reset);
		
	}
	
//***************************************MAIN METHOD OF MAIN CHOICE ***************************************************************************	
	
	public static void choice() {
		
		int choice = 0;
		while (true) {
            try {
                choice = sc.nextInt();
                break; // Break out of the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println(c.WHITE + c.RED_BACKGROUND + "Input type should be a number" + c.Reset);
                sc.nextLine(); // Clear the invalid input from the buffer
            }
        }

		
		if (choice == 1) {
			System.out.println(c.BOXING+c.PURPLE_BOLD+"Welcome Admin ! Please Login to your account" +c.Reset+"\n");
			adminLogin();
		}
		else if (choice == 2) {
			System.out.println(c.BOXING+c.BLUE_BOLD_BRIGHT + "Welcome Customer !" +c.Reset);
			customerLoginOrSignup();
		}
		else if (choice == 3) {
			System.out.println(c.BOXING+c.RED_BOLD_BRIGHT+ "Thank you ! Visit again" +c.Reset);
			System.exit(0);
		}
		else {
			System.out.println(c.WHITE+c.RED_BACKGROUND + "Please choose a number from below options"+c.Reset);
			adminOrCustomer();
		}
	}
	
//******************************************LOGIN ADMIN***************************************************************************	
	
	public static void adminLogin() {
		
	  boolean result=a.LoginAdmin();

		if(result) {
			adminMethods();
		}
		else {
			adminLogin();
		}
		
	}
	
//********************************************ADMIN METHOD OF SELECT ADMIN DETAILS*****************************************************************************	
	
	static void adminMethods() {
		System.out.println(c.PURPLE_BOLD + "+--------------------------------+" + "\n"
						 + "| Welcome Admin                  |" + "\n"
						 + "| 1. Add Bus                     |" + "\n"
						 + "| 2. Confirm Tickets of Customer |" + "\n"
						 + "| 3. View All Bookings           |" + "\n"
						 + "| 4. Back                        |" + "\n"
						 + "| 5. Exit                        |" + "\n"
						 + "+--------------------------------+" + c.GREEN_BOLD);
		
		int choice = 0;
		
		while(true) {
			
			try {
				choice = sc.nextInt();
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
					System.out.println(c.WHITE+c.RED_BACKGROUND + "Please choose a number from below options" +c.Reset);
					adminMethods();
				}
				else {
					adminChoice(choice);
				}
			}
			catch (InputMismatchException e) {
				
				System.out.println(c.WHITE+c.RED_BACKGROUND + "Input type should be number" +c.Reset);
				sc.nextLine();
				adminMethods();
			}
		}
		
	}

//**************************************ADMIN CHOICE METHOD********************************************************************************	
	
		public static void adminChoice(int choice) {
			
			switch(choice) {
				case 1 : {
					a.addBus();
					adminMethods();
				}
				break;
				case 2 : {
					a.updateStatus();
					adminMethods();
				}
				break;
				case 3 : {
					a.viewAllTickets();
					adminMethods();
				}
				break;
				case 4 : adminOrCustomer();
				break;	
				case 5 : {
					System.out.println(c.BOXING+c.RED_BOLD_BRIGHT + "Thank you ! Visit again" +c.Reset);
					System.exit(0);
				}
			}
	    }
		
		
//***********************************************CUSTOMER METHOD START ********************************************************************
		
		
	    public static void customerLoginOrSignup() {
			System.out.println(c.GREEN_BOLD + "+--------------------------------+" + "\n"
					                                + "| 1. Login to your Account       |" + "\n"
					                                + "| 2. Don't have Account? Sign Up |" + "\n"
					                                + "| 3. Back                        |" + "\n"
					                                + "| 4. Exit                        |" + "\n"
					                                + "+--------------------------------+" + c.CYAN_BOLD);
			
			
			int choice=0;
			
			while (true) {
	            try {
	                choice = sc.nextInt();
	                break; // Break out of the loop if input is valid
	            } catch (InputMismatchException e) {
	                System.out.println(c.WHITE + c.RED_BACKGROUND + "Input type should be a number" + c.Reset);
	                sc.nextLine(); // Clear the invalid input from the buffer
	                customerLoginOrSignup();
	            }
	        }
				if (choice == 1) {
					customerLogin();
				}
				else if (choice == 2) {
					customerSignup();
				}
				else if (choice == 3) {
					adminOrCustomer();
				}
				else if (choice == 4) {
					System.out.println(c.BOXING+c.BLUE_BOLD + "Thank you ! Visit again" + c.Reset);
					System.exit(0);
				}
				else {
					System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + "Please choose a number from below options" + c.Reset);
					customerLoginOrSignup();
				}
			}

//******************************************CUSTOMER LOGIN METHOD************************************************************************	    
		
	   public static void customerLogin() {
		   Customer customer = cu.LoginCustomer();
		    
		    if (customer == null) {
		        System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND+"Login failed. Please try again."+c.Reset);
		        
		        customerLogin();
		    } else {
		    	System.out.println(c.BLACK+c.BackgroundBrightGreen+"Login Successfull Wlcome "+customer.getFirstName()+" "+customer.getLastName()+c.Reset+"\n");
		        customerMethods(customer);
		    }
			
		}

//******************************************CUSTOMER SING-UP METHOD************************************************************************************	   
	    
	  public static void customerSignup() {
			boolean flag = cu.SingUpCustomer();
			
			if (flag) {
				System.out.println(c.GREEN_BOLD + "Login to your Account" + c.Reset);
				customerLogin();
			}
			else {
				System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + "Data not interd" + c.Reset+"\n");
				customerSignup();
			}
		}
	
//*******************************************CUSTOMER METHOD TO SELECT CUSTOMER DETAILS****************************************************************************************	  
	  
	  static void customerMethods(Customer customer) {
			System.out.println(c.BLACK_BOLD + "+--------------------------------+" + "\n"
	                         + "| 1. Get your Customer ID        |" + "\n"
					 		 + "| 2. Book Bus Ticket             |" + "\n"
					         + "| 3. Cancel Bus Ticket           |" + "\n"
					         + "| 4. View Status of your Tickets |" + "\n"
					         + "| 5. View All Bus Details        |" + "\n" 
					         + "| 6. Check available Ticket      |" + "\n"
					         + "| 7. Update Password             |" + "\n"
					         + "| 8. Back                        |" + "\n"
					         + "| 9. Exit                        |" + "\n"
					         + "+--------------------------------+" + c.GREEN_BOLD);
			
			
			
			int choice = 0;
			
			while (true) {
				try {
					choice = sc.nextInt();
					if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice !=6 && choice !=7 && choice !=8 && choice !=9) {
						System.out.println(c.RED_BACKGROUND + "Please choose a number from below options" + c.Reset);
						customerMethods(customer);
						break;
					}
					else customerChoice(choice, customer);
				}
				catch (InputMismatchException e) {
					
					System.out.println(c.RED_BACKGROUND + "Input type should be number" + c.Reset);
					customerMethods(customer);
					sc.nextLine();
				}
		    }	
	    }

	  
//******************************************CUSTOMER CHOICE METHOD*********************************************************************************	  
	
	  static void customerChoice(int choice, Customer customer) {
			switch(choice) {
			case 1 : {
				cu.getCustomerID();;;
				customerMethods(customer);
			}
			case 2 : {
				cu.bookTicket();;
				customerMethods(customer);
			}
			break;
			case 3 : {
				cu.calcleTicket();
				customerMethods(customer);
			}
			break;
			case 4 : {
				cu.statusOfTicket();
				customerMethods(customer);
			}
			break;
			case 5  : {
				cu.viewAllBusDetais();
				customerMethods(customer);
			}
			break;
			case 6  : {
				cu.checkAval();;
				customerMethods(customer);
			}
			break;
			case 7  : {
				cu.updatePassword();;
				customerMethods(customer);
			}
			break;
			case 8 : {
				customerLoginOrSignup();
			}
			break;
			case 9 : {
				System.out.println(c.BOXING+c.BLUE_BOLD+ "Thank you ! Visit again" + c.Reset);
				System.exit(0);
			}
		}
	 }
	
	
	public static void main(String[] args) {
		
		Main.adminOrCustomer();
		
		
		
	}
	
	
	

}
