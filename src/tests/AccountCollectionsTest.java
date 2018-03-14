/*
 * @Class
 * 		AccountCollectionTest.java
 * @Purpose
 * 		In this class, we need to test the AccountCollection.java, and make sure that it can converge 100%		
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Account;
import model.AccountCollections;

public class AccountCollectionsTest {

	@Test
	public void test() {
		AccountCollections list = AccountCollections.getAccountList();
		
		assertTrue(list.getSize() == 4);
		assertTrue(list.hasAccount("Chris"));
		assertFalse(list.hasAccount("Ben"));
		assertEquals(null,list.getAccountByName("ben"));
		assertTrue(list.getAccountByName("Chris").isValidUser("Chris", "1"));
		assertTrue(list.getAccountByName("Devon").isValidUser("Devon", "22"));
		assertTrue(list.getAccountByName("River").isValidUser("River", "333"));
		assertTrue(list.getAccountByName("Ryan").isValidUser("Ryan" , "4444"));
		
		ArrayList<Account> list1 = list.getAccountArrayList();
		assertTrue(list1.size() == 4);
		assertTrue(list1.get(0).isValidUser("Chris", "1"));
		assertTrue(list1.get(1).isValidUser("Devon", "22"));
		assertTrue(list1.get(2).isValidUser("River", "333"));
		assertTrue(list1.get(3).isValidUser("Ryan" , "4444"));
	}

}
