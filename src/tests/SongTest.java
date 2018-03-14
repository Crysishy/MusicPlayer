/*
 * @Class
 * 		Song.java
 * @Purpose
 * 		In this class, we need to test the Song.java, and make sure that it can converge 100%		
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;

public class SongTest {

	@Test
	public void test() {
		Song song = new Song("song.mp3", "This Is A Song", "This Is An Artist", 300);
		
		assertEquals("song.mp3", song.getFileName());
		assertEquals("This Is A Song", song.getTitle());
		assertEquals("This Is An Artist", song.getArtist());
		assertEquals(300, song.getSongLength());
		assertEquals(0, song.getTimesPlayed());
		assertTrue(song.isPlayable());
		assertEquals("5:00",song.getSongLengthInFormat());
		
		song.increaseTimesPLayed();
		assertEquals(1, song.getTimesPlayed());
		assertTrue(song.isPlayable());

		song.increaseTimesPLayed();
		assertEquals(2, song.getTimesPlayed());
		assertTrue(song.isPlayable());
		
		song.increaseTimesPLayed();
		assertEquals(3, song.getTimesPlayed());
		assertFalse(song.isPlayable());
		
		song.minusDateBy(1);
		assertEquals(3, song.getTimesPlayed());
		assertTrue(song.isPlayable());
		assertEquals(0, song.getTimesPlayed());
	}

}
