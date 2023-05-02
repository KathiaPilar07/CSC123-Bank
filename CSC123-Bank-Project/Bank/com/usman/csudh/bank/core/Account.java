package com.usman.csudh.bank.core;
import java.io.File;   
import java.io.FileNotFoundException;
import java.io.IOException;   
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


//
import java.util.Formatter;

import com.usman.csudh.util.UniqueCounter;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String accountName;
	private Customer accountHolder;
	private ArrayList<Transaction> transactions;
	private static HashMap<String , Double> exchange2 = new HashMap <String , Double > ();
	
	private boolean open=true;
	private int accountNumber;
	
	//Added fields
	
	private String currency; 
	private String faith;
	private static double usdB;
	
	

	protected Account(String name, Customer customer , String cu ) 
	{
		accountName=name; //Checking or Saving
		accountHolder=customer;
		currency = cu;
		transactions=new ArrayList<Transaction>();
		accountNumber=UniqueCounter.nextValue();
		
		String str = customer.toString();
		faith = str;
		
	}
	
	public String getAccountName() {
		return accountName;
	}

	public Customer getAccountHolder() {
		return accountHolder;
	}
	
	//Added things
	
	public String getCurrency()
	{
		return currency;
	}
	
	
	public   double usdBalance()
	{
		double uBalance = 0;
		Formatter fr = new Formatter();
		 
		 try {
			    
				String str = "exchange-rate.csv"; 
				File myf = new File (str); 
				
				Scanner scan = new Scanner (myf);
				 
				
				while (scan.hasNext())
				{
					String st = scan.nextLine();
					
					String [] array = st.split(",");
					
					exchange2.put(array[0] , Double.parseDouble(array[2]));
					
					 //System.out.println(exchange.get(array[0]));
					
				
	             }
				
				
				
				
				
				
			    
				uBalance = getBalance() * exchange2.get(currency);
				
				if (currency == null)
				{
					//System.out.println("NULL");
				}
				
				
				fr.format("%.2f", uBalance);
		
	        }
	        
	        catch(FileNotFoundException e)
	        {
	        	System.out.println("");
	        	
	        }
		 
		 return uBalance;
	}
	//Professor's stuff

	public double getBalance() {
		double workingBalance=0;
		
		for(Transaction t: transactions) {
			if(t.getType()==Transaction.CREDIT)workingBalance+=t.getAmount();
			else workingBalance-=t.getAmount();
		}
		
		return workingBalance;
	}
	
	
	
	
	public void deposit(double amount)  throws AccountClosedException{
		double balance=getBalance();
		if(!isOpen()&&balance>=0) {
			throw new AccountClosedException("\nAccount is closed with positive balance, deposit not allowed!\n\n");
		}
		transactions.add(new Transaction(Transaction.CREDIT,amount));
	}
	
	
	
	
	public void withdraw(double amount) throws InsufficientBalanceException {
			
		double balance=getBalance();
			
		if(!isOpen()&&balance<=0) {
			throw new InsufficientBalanceException("\nThe account is closed and balance is: "+balance+"\n\n");
		}
		
		transactions.add(new Transaction(Transaction.DEBIT,amount));
	}
	
	public void close() {
		open=false;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public String toString() {
		
		String aName=accountNumber+"("+accountName+")"+" : "+accountHolder.toString() +" : " + currency +" : "+getBalance()+ " : " + "USD"+" : " +usdBalance() + " : "+(open?"Account Open":"Account Closed");
		
		
		return aName;
	}
	 
	public void printTransactions(OutputStream out) throws IOException {
		
		out.write("\n\n".getBytes());
		out.write("------------------\n".getBytes());
	
		for(Transaction t: transactions) {
			out.write(t.toString().getBytes());
			out.write((byte)10);
		}
		out.write("------------------\n".getBytes());
		out.write(("Balance: "+getBalance()+"\n\n\n").getBytes());
		out.flush();
		
	}
	
	//My added methods
	
	public Account ()
	{
		
	}
	
	

	public String toString2() //This second toString is for option 5
	{
		String[] d = faith.split("\\s");
		
		String aName= "Account Number: " + accountNumber+ "\n" + "Type: "+ "("+accountName+")"+" \n"+ "Name:"  +d[0]+" " + d[1] + "\n" +"SSN: " +d[2] + "\n" +"Balance in " +currency +" : " +getBalance()+ "\n" +"Balance in " + "USD"+" : " +usdBalance() + "\n"+ "Status: " + ( open?"Account Open":"Account Closed");
		//System.out.println(aName);
		
		return aName;
	} 
	
	
}
