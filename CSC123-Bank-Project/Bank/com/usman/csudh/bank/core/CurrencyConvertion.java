

package com.usman.csudh.bank.core;


import java.util.HashMap;
//My imports 🡻
import java.util.Scanner;
import java.util.Formatter;

import java.net.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


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
	
	public double Convertion () throws Exception
	{
		Formatter f = new Formatter();
		
		double result = 0;
		 double result2;
		
		 String ur = "http://www.usman.cloud/banking/exchange-rate.csv";
	        
		    if(urlExists2(ur))
	        {
			   
			 
			    URL url = new URL("http://www.usman.cloud/banking/exchange-rate.csv");
	            BufferedReader inp = new BufferedReader(new InputStreamReader(url.openStream()));

	            String inputLine;
	            
	            
	            while ((inputLine = inp.readLine()) != null) {
	               
					String st = inp.toString();
					
					String [] array = inputLine.split(",");
					
					exchange.put(array[0] , Double.parseDouble(array[2]));
					
	            }
	         
	            inp.close();
	            
				/*String str = "exchange-rate.csv"; 
				File myf = new File (str); 
				
				Scanner scan = new Scanner (myf);
			
				while (scan.hasNext())
				{
					String st = scan.nextLine();
					
					String [] array = st.split(",");
					
					exchange.put(array[0] , Double.parseDouble(array[2]));
					
				
					
	             }**/
				
				
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
					
					if (currencyBuying.equalsIgnoreCase("USD"))
					{
						System.out.println("The amount reminds the same**");
						
						result = amountSelling * 1; //just in case
						
						
					}
					 
				}
				
			
			
	        }
	        
		    else
	        
	        	System.out.println(" Unknown ERROR! File could not be loaded ! Conversion has stopped!");
	        
		 
		 return result;
	        
	}
	// returning methods 🡻
	
		
		
		public String toStringC() throws Exception 
		{
			
			
			String convertion = "The exchange rate is " + currencySelling +" " + amountSelling + " you will get " + currencyBuying + " " + Convertion() ;
			System.out.println(convertion);
			
			
			return convertion;
		}
		
		public static boolean urlExists2(String uString) 
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