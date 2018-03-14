/*
 * @Class
 * 		Account.java
 * @Purpose
 * 		In this class, we need to test the Account.java, and make sure that it can converge 100%		
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Account;
import model.Song;

public class AccountTest {

	@Test
	public void testAccount() {
		Account user01 = new Account("user01", "pass01");
		Song song01 = new Song("song.mp3", "Song", "Zero One", 80000);
		Song song02 = new Song("song.mp3", "Song", "Zero One", 8000);
		
		assertTrue(user01.isValidUser("user01", "pass01"));
		assertFalse(user01.isValidUser("user", "pass01"));
		assertFalse(user01.isValidUser("user01", "pass"));
		assertFalse(user01.isValidUser("user", "pass"));
		assertEquals("user01",user01.getUserName());
		
		assertEquals(0, user01.getSongsPLayed());
		assertEquals(90000, user01.getSecondsRemaining());
		assertEquals("25:00:00", user01.getTimeRemaining());
		assertTrue(user01.canPlay(song01));
		assertTrue(user01.canPlay(song02));
		
		user01.increaseSongsPlayed();
		user01.decreaseListeningTime(10000);
		assertEquals(1, user01.getSongsPLayed());
		assertEquals(80000, user01.getSecondsRemaining());
		assertEquals("22:13:20", user01.getTimeRemaining());
		assertTrue(user01.canPlay(song01));
		assertTrue(user01.canPlay(song02));
		
		user01.increaseSongsPlayed();
		user01.decreaseListeningTime(10000);
		assertEquals(2, user01.getSongsPLayed());
		assertEquals(70000, user01.getSecondsRemaining());
		assertEquals("19:26:40", user01.getTimeRemaining());
		assertFalse(user01.canPlay(song01));
		assertTrue(user01.canPlay(song02));
		
		user01.increaseSongsPlayed();
		user01.decreaseListeningTime(10000);
		assertEquals(3, user01.getSongsPLayed());
		assertEquals(60000, user01.getSecondsRemaining());
		assertEquals("16:40:00", user01.getTimeRemaining());
		assertFalse(user01.canPlay(song01));
		assertFalse(user01.canPlay(song02));
		
		user01.plusCurrentDateBy(1);
		assertEquals(3, user01.getSongsPLayed());
		assertEquals(60000, user01.getSecondsRemaining());
		assertEquals("16:40:00", user01.getTimeRemaining());
		assertTrue(user01.canPlay(song01));
		assertTrue(user01.canPlay(song02));
		assertEquals(0, user01.getSongsPLayed());
		assertEquals(90000, user01.getSecondsRemaining());
		assertEquals("25:00:00", user01.getTimeRemaining());
	}

}
