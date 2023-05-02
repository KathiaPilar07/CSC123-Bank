package com.usman.csudh.bank.core;
/***********************
 * 1 - IMPORTS  *  
 ***********************/
 
import java.io.Serializable;


/**********************************
 * 2 - Class declaration2
 **********************************/

public class Customer {
	
	private static final long serialVersionUID = 1L;
	/**********************************
	 * 2 -FIELDS
	 * 
	 * Fields are just variables. Variables are called fields 
	 * when they are declared in this area. Fields are "global" variables 
	 * they are available throughout the class. The "public" fields are available within the class as well as 
	 * outside the class 
	 **********************************/
	
	private String firstName;
	private String lastName;
	private String ssn;
	
	//added fields
	
	private static String currency;
	
	
	/**********************************
	 * 3 -CONSTRUCTORS
	 * 
	 * Constructors are special methods that are automatically executed when an object is created. Constructors have same name as the class.
	 * If no constructor is defined then a default constructor is automatically created and executed by the JVM.
	 * A class can have multiple constructors as long as their signatures stay unique 
	 **********************************/
	
	//This method is mine 
	public static void addCurrency(String aCurrency)
	{
		//currency = aCurrency;
	}
	
	//professor's methods

	public Customer(String fName, String lName, String ssN , String cur ) {
		
		
		
		if (cur == null) {
			 
			setFirstName(fName);
			setLastName(lName);
			setSSN(ssN);
		}
		
		else 
		{
			setFirstName(fName);
			setLastName(lName);
			setSSN(ssN);
			setCurrency(cur);
		}
		
		
		
	}
	
	public Customer() {}

	public Customer(Customer p) {
		
		/*this.firstName=p.getFirstName();
		this.lastName=p.getLastName();
		this.ssn=p.getSSN();**/
		
		if (!(currency == null))
		{
			this.firstName=p.getFirstName();
			this.lastName=p.getLastName();
			this.ssn=p.getSSN();
			this.currency = p.getCurrency();
			
			//Account send = new Account (this.firstName, this.ssn , this.lastName);
			
		}
		
		else 
		{
			this.firstName=p.getFirstName();
			this.lastName=p.getLastName();
			this.ssn=p.getSSN();
			
		}
		
			
	}	
	
	/**********************************
	 * 4 -METHODS
	 * 
	 * There are "getter" / "setter" methods, the "setter" methods are also known as "manipulator" methods. Apart from these two types of methods the class can have 
	 * any number of methods that it needs to provide the desired functionality
	 * 
	 * private methods are available only within the class
	 * protected methods are available only within the package
	 * public methods are available outside the class 
	 * 
	 **********************************/
	
	
	public void setFirstName(String s) {
		firstName=s;
		
	}
	
	public void setLastName(String s) {
		lastName=s;
	}

	public void setSSN(String s) {
		ssn=s;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	

	public String getSSN() {
		return ssn;
	}
	
	//Added methods

	public void setCurrency(String ca)
	{
		currency = ca;
	}
	public String getCurrency() {
		return currency;
	}
	
	


	
	
	/**
	 * 
	 * toString() method is inherited by the super class Object. This returns a String representation of the object. 
	 */
	
	
	@Override
	public String toString() {
		
		if (currency == null)
		{
			return firstName+" : "+lastName+" : "+ssn; 
		}
		
		else 
		{
			return firstName+" : "+lastName+" : "+ssn ; 
		}
		
			
		
	
		
	}
	
	/**
	 * 
	 * equals() method is inherited by the super class Object. This returns a String representation of the object. 
	 */

	@Override
	public boolean equals(Object obj)
	{
		if (currency == null)
		{
			Customer other=(Customer)obj;
			return this.firstName.equalsIgnoreCase(other.getFirstName())
					&&this.lastName.equalsIgnoreCase(other.getLastName())
							&&this.ssn.equalsIgnoreCase(other.getSSN());
		}
		else 
		{
			Customer other=(Customer)obj;
			return this.firstName.equalsIgnoreCase(other.getFirstName())
					&&this.lastName.equalsIgnoreCase(other.getLastName())
							&&this.ssn.equalsIgnoreCase(other.getSSN())
							   && this.currency.equalsIgnoreCase(other.getCurrency());
			
		}
		
		
		
	}
	
			
		
	
		
	
	
	
}
