
//Professor's stuff 🡳
package com.usman.csudh.bank;
import com.usman.csudh.bank.core.Account;
import com.usman.csudh.bank.core.AccountClosedException;
import com.usman.csudh.bank.core.Bank;
import com.usman.csudh.bank.core.CurrencyConvertion;
import com.usman.csudh.bank.core.InsufficientBalanceException;
import com.usman.csudh.bank.core.NoSuchAccountException;
import com.usman.csudh.util.UIManager;


//My imports 🡳
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainBank  




{
     
	//Professor's Stuff
	//All messages are declared as constants to make it easier to change. Also, to ensure future proofing in case the application need to be made available
	//in more than one languages
	public static final String MSG_ACCOUNT_OPENED = "%nAccount opened, account number is: %s%n%n";
	public static final String MSG_ACCOUNT_CLOSED = "%nAccount number %s has been closed, balance is %s%n%n";
	public static final String MSG_ACCOUNT_NOT_FOUND = "%nAccount number %s not found! %n%n";
	public static final String MSG_FIRST_NAME = "Enter first name:  ";
	public static final String MSG_LAST_NAME = "Enter last name:  ";
	public static final String MSG_SSN = "Enter Social Security Number:  ";
	public static final String MSG_ACCOUNT_NAME = "Enter account name:  ";
	public static final String MSG_ACCOUNT_OD_LIMIT = "Enter overdraft limit:  ";
	public static final String MSG_ACCOUNT_CREDIT_LIMIT = "Enter credit limit:  ";
	public static final String MSG_AMOUNT = "Enter amount: ";
	public static final String MSG_ACCOUNT_NUMBER = "Enter account number: ";
	public static final String MSG_ACCOUNT_ACTION = "%n%s was %s, account balance is: %s%n%n";
	
	//My fields 🡳
	public static final String MSG_ACCOUNT_CURRENCY = "Account Currency: \n";
	public static final String MSG_CURRENCY_ADDED = "Currency was added";
	public static final String MSG_CURRENCY_SELLING = "Currency you are selling : ";
	public static final String MSG_AMOUNT_SELLING = "Amount you are selling: ";
	public static final String MSG_CURRENCY_BUYING= "Currency you are buying: ";
	
	//My changes 🡳
	
	private static HashMap<String , Double> exchange = new HashMap <String , Double > (); // 1) TreeMap to store the csv file
    /* I'm not sure how to store the csv file into the TreeMap , or Hashmap, IDK which one is better to do this **/
	
	private static String f;
	private static URL u;
	
	//Declare main menu and prompt to accept user input
	public static final String[] menuOptions = { "Open Checking Account%n","Open Saving Account%n", "List Accounts%n","View Statement%n","Show Account Information%n" ,"Deposit Funds%n", "Withdraw Funds%n",
			"Currency Conversion%n","Close an Account%n","Exit%n" };
	public static final String MSG_PROMPT = "%nEnter choice: ";

	
	//Declare streams to accept user input / provide output
	InputStream in;
	OutputStream out;
	
	
	//Constructor
	public MainBank(InputStream in, OutputStream out) {
		this.in=in;
		this.out=out;
	}
	
	
	//Main method. 
	public static void main(String[] args) throws IOException , Exception // 🡰 I added this
	{
		
		// 1. I added this 🡳 (This part is the part where the driver reads the url and checks if it exists or if is able to load).
       
		
		 
		
		      // Verify if the file exists 
		           
		         String ur = "http://www.usman.cloud/banking/exchange-rate.csv";
		        if(urlExists(ur))
		        {
		        	
		        	URL url = new URL("http://www.usman.cloud/banking/exchange-rate.csv");
		            BufferedReader inp = new BufferedReader(new InputStreamReader(url.openStream()));

		            String inputLine;
		            
		            
		            while ((inputLine = inp.readLine()) != null) {
		               // System.out.println(inputLine);
		            	

						String st = inp.toString();
						
						String [] array = inputLine.split(",");
						
						exchange.put(array[0] , Double.parseDouble(array[2]));
						
		            }
		            
		           // File myf = new File (inputLine); 
		            inp.close();
		            
		            u = url;

					/*String str = "exchange-rate.csv"; 
					File myf = new File (str); 
					
					Scanner scan = new Scanner (myf);
					 
					f = str;
					
					while (scan.hasNext())
					{
						String st = scan.nextLine();
						
						String [] array = st.split(",");
						
						exchange.put(array[0] , Double.parseDouble(array[2]));
						
						
		             }**/
					
					
					
			
		        }
		        
		        else
		        
		        	System.out.println("**Currency information Could Not Be Loaded , Currency Conversion Service and Foreign"
					+ "Currency accounts are not available** \n ");
		        	
		        
		        
		       
		    	
		
		
		//Professor's Stuff 🡳

		new MainBank(System.in,System.out).run(); //This class is professor's driver 

	}
	
	
	//The core of the program responsible for providing user experience.
	public void run() throws Exception
	{

		Account acc;
		int option = 0;

		UIManager ui = new UIManager(this.in,this.out,menuOptions,MSG_PROMPT);
		try {

			do {
				option = ui.getMainOption(); //Render main menu

				switch (option) {
				case 1:
					
					
					//Compact statement to accept user input, open account, and print the result including the account number
					
					
                       try {

						
						
						String uStr = "http://www.usman.cloud/banking/exchange-rate.csv";
						
						if(urlExists(uStr))
						{
							
							Scanner sc = new Scanner (System.in);
							String input;
							
						
							do {

								System.out.println("Enter account currency: ");
								input= sc.nextLine();
								
								
								if(exchange.containsKey(input))
								{

									Bank.setCurrency(input);
								
								}
								
								else if (!(exchange.containsKey(input)))
								{
									System.out.println("Error");
								}
								
							}while (!(exchange.containsKey(input)));
							
							ui.print(MSG_ACCOUNT_OPENED,
									new Object[] { Bank.openCheckingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),
											ui.readDouble(MSG_ACCOUNT_OD_LIMIT)).getAccountNumber() });
						
						}
						
						else 
						{
							
							System.out.println("Sorry URL was not found");
							ui.print(MSG_ACCOUNT_OPENED,
									new Object[] { Bank.openCheckingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),
											ui.readDouble(MSG_ACCOUNT_OD_LIMIT)).getAccountNumber() });
						}
						
			        }
			        
			        catch(FileNotFoundException e)
			        {
			        	System.out.println("");
			        	
			        }
					
					
					break;
					
			
				case 2:
					
					//Compact statement to accept user input, open account, and print the result including the account number
					
					
					try {

						
						String uStr = "http://www.usman.cloud/banking/exchange-rate.csv";
						
						if(urlExists(uStr))
						{
							
							Scanner sc = new Scanner (System.in);
							String input;
							
						
							do {

								System.out.println("Enter account currency: ");
								input= sc.nextLine();
								
								
								if(exchange.containsKey(input))
								{

									Bank.setCurrency(input);
								
								}
								
								else if (!(exchange.containsKey(input)))
								{
									System.out.println("ERROR! Possible Causes :");
									System.out.println("**The currency may not exist or it's misspelled** ");
								}
								
							}while (!(exchange.containsKey(input)));
							
							ui.print(MSG_ACCOUNT_OPENED,
									new Object[] { Bank.openSavingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN))
											.getAccountNumber() });
							
						
						}
						
						else 
						{
							ui.print(MSG_ACCOUNT_OPENED,
									new Object[] { Bank.openSavingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN))
											.getAccountNumber() });
						}
						
			        }
			        
			        catch(FileNotFoundException e)
			        {
			        	System.out.println("");
			        	
			        }
					
					
					
					break;

				case 3:
					
					//Get bank to print list of accounts to the output stream provided as method arguemnt
					Bank.listAccounts(this.out);
					break;
					
				case 4:
					
					//find account and get the account to print transactions to the  output stream provided in method arguments
					try {
						Bank.printAccountTransactions(ui.readInt(MSG_ACCOUNT_NUMBER),this.out);
					} catch (NoSuchAccountException e1) {
						this.handleException(ui, e1);

					}		
					
					break;

				case 5:  
					
					/* This option is the one listed on assignment 6 , it needs to display more details about the account**/
					try 
					{
						//int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.accountInfo(ui.readInt(MSG_ACCOUNT_NUMBER),this.out);
						
					}
					catch(NoSuchAccountException | AccountClosedException e) 
					{
						this.handleException(ui, e);

					}
					//Bank.accountInfo(accountNumber);
					break;
					
				case 6:
                   //find account, deposit money and print result
					
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.makeDeposit(accountNumber, ui.readDouble(MSG_AMOUNT));
						ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Deposit","successful",Bank.getBalance(accountNumber)});
					}
					catch(NoSuchAccountException | AccountClosedException e) {
						this.handleException(ui, e);

					}
					break;

				case 7:
					//find account, withdraw money and print result
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.makeWithdrawal(accountNumber, ui.readDouble(MSG_AMOUNT));
						ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Withdrawal","successful",Bank.getBalance(accountNumber)});
						
					}
					catch(NoSuchAccountException | InsufficientBalanceException e) {
						this.handleException(ui, e);

					}
					break;
					
				case 8:
					
					/* This option is in charge of the currency convertion     **/
					
					
					//try {

						
						//File myfile = new File (f); 
						
						//Scanner scan = new Scanner (myfile);
						
						//if(u.getContent().equals("http://www.usman.cloud/banking/exchange-rate.csv"))
						//{
							
							
							Scanner sc = new Scanner (System.in);
							Scanner k = new Scanner (System.in);
							String input;
							String input2;
							double input3 ;
							String input4;
						
							do {
								   System.out.println("Currency you are selling:");
								   input= sc.nextLine();
								
								   try {
									   
										
										System.out.println("Amount you are selling:");
										input4 = k.nextLine();
										
										
										
										if(exchange.containsKey(input))
										{
											input3 = Double.parseDouble(input4);
											/*note : if both are USD is going to be passed because nothing about that was
											 * stated in the assignment 6 part 2 , but is going to send a message back**/
											 
											System.out.println("Currency you are buying:");
											input2 = sc.nextLine();
											
											if (exchange.containsKey(input2))
											{
												if(input.equalsIgnoreCase("USD") || input2.equalsIgnoreCase("USD"))
			                                     {
			                                    	 CurrencyConvertion c = new CurrencyConvertion (input , input3, input2);
			                                    	 
			                                    	 c.toStringC();
			                                    	 
			                                     }
												
												else if(input.equalsIgnoreCase("USD") && input2.equalsIgnoreCase("USD"))
			                                     {
			                                    	 CurrencyConvertion c = new CurrencyConvertion (input , input3, input2);
			                                    	 
			                                    	 c.toStringC();
			                                    	 
			                                     }
			                                     
			                                     else if (!(input.equalsIgnoreCase("USD") && input2.equalsIgnoreCase("USD")))
			                                     {
			                                    	 System.out.println("**SORRY! One of the currencies should be USD!");
			                                     }
											}
											
											else if (!(exchange.containsKey(input2)))
											{
												System.out.println("**ERROR! The currency selected could not be founded!**");
											}
		                                     
											
										}
										
										else if (!(exchange.containsKey(input)))
										{
											System.out.println("**ERROR! The currency selected could not be founded!**");
										}

								   }
								   
								   catch (NumberFormatException i)
								   {
									   System.out.println("**The amount you entered is not a numeric quantity**");
								   }
									
								
								
							}while (!(exchange.containsKey(input)));
							

						
						//}
						
						/*else 
						{
							ui.print(MSG_ACCOUNT_OPENED,
									new Object[] { Bank.openSavingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN))
											.getAccountNumber() });
						}**/
						
			       // }
			        
			        /*catch(FileNotFoundException e)
			        {
			        	System.out.println("**This Option is not available**");
			        	
			        }**/
					
			
				
					
					break;
					
				case 9 :
                    //find account and close it
					
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.closeAccount(accountNumber);
						ui.print(MSG_ACCOUNT_CLOSED,
								new Object[] { accountNumber, Bank.getBalance(accountNumber) });
						
					} catch (NoSuchAccountException e) {
						this.handleException(ui, e);

					}
					break;
					
					
				case 10: //Exit
					
						
					
					System.out.println("You have exit , bye!");
					
					break;
					
			
				}

			} while (option != menuOptions.length);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	
	private  void handleException(UIManager ui, Exception e) throws IOException{
		ui.print(e.getMessage(), new Object[] { });
	}
	
	
	
	//This method is to check if the url exists
	public static boolean urlExists(String uString) 
	{
        try {
            URL url = new URL(uString);
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            return (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }


}
