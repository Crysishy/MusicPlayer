/*
 * @Class
 * 		AccountCollections.java
 * @Purpose
 * 		This class defines Object AccountCollections. It pre-defines four users
 * 		in the system. It is able to return account by account name. There will
 * 		be further implementations for adding account into the collections.
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AccountCollections implements Serializable{

	private ArrayList<Account> accountCollections;
	private static AccountCollections self;
	
	private AccountCollections(){
		accountCollections = new ArrayList<Account>();
		accountCollections.add(new Account("Chris", "1"));
		accountCollections.add(new Account("Devon", "22"));
		accountCollections.add(new Account("River", "333"));
		accountCollections.add(new Account("Ryan" , "4444"));
	}
	
	public static synchronized AccountCollections getAccountList(){
		if(self == null)
			self = new AccountCollections();
		
		return self;
	}
	
	public ArrayList<Account> getAccountArrayList(){
		return accountCollections;
	}
	
	public int getSize(){
		return accountCollections.size();
	}
	
	public boolean hasAccount(String username){
		for (Account account : accountCollections){
			if (username.equals(account.getUserName()))
				return true;
		}
		return false;
	}
	
	public Account getAccountByName(String username){
		for (Account account : accountCollections){
			if (username.equals(account.getUserName()))
				return account;
		}
		return null;
	}
}
