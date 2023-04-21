package com.usman.csudh.bank.core;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;

public class Bank {
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	private static ArrayList <String> a = new ArrayList <String>();
	
	private static String [] array = new String[1];
	
	//My added things
	private static String accountCurrency;
	
	
	
	public static void setCurrency(String c) //constructor
	{
		accountCurrency = c;
		
		
	}
	
	
	
	//Professor's Stuff
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit ) {
		
		Customer c=new Customer(firstName,lastName, ssn , accountCurrency);
		Account a=new CheckingAccount(c,overdraftLimit , accountCurrency);
	
		accounts.put(a.getAccountNumber(), a );
		
        
		return a;
		
	}
	
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn ) {
		
		
		String cur = accountCurrency;
		//AccountCurrency ac = new AccountCurrency (accountCurrency);
		Customer c=new Customer(firstName,lastName, ssn , cur);
		Account a=new SavingAccount(c , accountCurrency);
		
		
		accounts.put(a.getAccountNumber(), a);
		
		
		//
		
		return a;
		
	}

	
	
	public static Account lookup(int accountNumber) throws NoSuchAccountException{
		if(!accounts.containsKey(accountNumber)) {
			throw new NoSuchAccountException("\nAccount number: "+accountNumber+" nout found!\n\n");
		}
		
		return accounts.get(accountNumber);
	}
	
	public static void makeDeposit(int accountNumber, double amount) throws AccountClosedException, NoSuchAccountException{
		
		lookup(accountNumber).deposit(amount);
		
	}
	
	public static void makeWithdrawal(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdraw(amount);
	}
	
	public static void closeAccount(int accountNumber) throws NoSuchAccountException {
		lookup(accountNumber).close();
	}

	
	public static double getBalance(int accountNumber) throws NoSuchAccountException {
		return lookup(accountNumber).getBalance();
	}

	public static void listAccounts(OutputStream out) throws IOException{
		
		out.write((byte)10);
		Collection<Account> col=accounts.values();
		
		for (Account a:col) {
			out.write(a.toString().getBytes());
			out.write((byte)10);
		}
		
		out.write((byte)10);
		out.flush();
	}
	
	public static void printAccountTransactions(int accountNumber, OutputStream out) throws IOException,NoSuchAccountException{
		
		lookup(accountNumber).printTransactions(out);
	}
				
	
	//my added methods
	
	
     public static void accountInfo ( int accountNumber , OutputStream out) throws IOException , NoSuchAccountException , AccountClosedException
     {
    	 
    	 
    	 
    	 if(!accounts.containsKey(accountNumber)) {
 			throw new NoSuchAccountException("\nAccount number: "+accountNumber+" nout found!\n\n");
 			
 			
 		}
    	 
    	 else if (accounts.containsKey(accountNumber))
    	 {
    		
    		 
    		 Collection<Account> col=accounts.values() ; //Creation of collection with  value of accounts map
    			
    			for (Account a:col) {
    				//System.out.println(a.toString2());
    				out.write(a.toString2().getBytes());
    				out.write((byte)10);
    			
    			
    			  out.write((byte)10);
    			  out.flush();
    				
    			}
    	 }
		
	
     }
	
}
