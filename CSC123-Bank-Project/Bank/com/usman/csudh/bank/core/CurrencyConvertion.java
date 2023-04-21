

package com.usman.csudh.bank.core;


import java.io.File; 
import java.io.FileNotFoundException;
import java.util.HashMap;
//My imports 🡻
import java.util.Scanner;
import java.util.Formatter;
//Kathia Hernandez

/*This is a new Class for the option 8 (Currency Conversion**/

public class CurrencyConvertion
{
	//Fields
	
	private static String currencySelling;
	private static double amountSelling;
	private static String currencyBuying;
	
	private static HashMap<String , Double> exchange = new HashMap <String , Double > ();
	
	
	public CurrencyConvertion ( String cs , Double as , String cb)
	{
		currencySelling = cs;
		amountSelling = as;
		currencyBuying = cb;
		
		
	}
	
	public double Convertion ()
	{
		Formatter f = new Formatter();
		
		double result = 0;
		 double result2;
		 try {
			   
				String str = "exchange-rate.csv"; 
				File myf = new File (str); 
				
				Scanner scan = new Scanner (myf);
			
				while (scan.hasNext())
				{
					String st = scan.nextLine();
					
					String [] array = st.split(",");
					
					exchange.put(array[0] , Double.parseDouble(array[2]));
					
				
					
	             }
				
				
				//From USD to any Currency (Currency Selling)
				if (currencySelling.equalsIgnoreCase("USD"))
				{
					
					if (!(currencyBuying.equalsIgnoreCase("USD")))
					{
						result = amountSelling / exchange.get(currencyBuying);
						 
					}
					
				}
				
				//From Currency to USD (Currency Selling)
				else if (!(currencySelling.equalsIgnoreCase("USD")))
				{
					
					if (currencyBuying.equalsIgnoreCase("USD"))
					{
						result = amountSelling * exchange.get(currencySelling);
					}
					
				}
				
				if (currencySelling.equalsIgnoreCase("USD")  )
				{
					System.out.println("Im in");
					if (currencyBuying.equalsIgnoreCase("USD"))
					{
						System.out.println("The amount reminds the same**");
						
						result = amountSelling * 1; //just in case
						
						
					}
					 
				}
				
			
			
	        }
	        
	        catch(FileNotFoundException e)
	        {
	        	System.out.println(" Unknown ERROR! File could not be loaded ! Conversion has stopped!");
	        	
	        }
		 
		 return result;
	        
	}
	// returning methods 🡻
	
		
		
		public String toStringC() {
			
			
			String convertion = "The exchange rate is " + currencySelling +" " + amountSelling + " you will get " + currencyBuying + " " + Convertion() ;
			System.out.println(convertion);
			
			
			return convertion;
		}
		
}