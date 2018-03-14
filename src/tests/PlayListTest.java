/*
 * @Class
 * 		PlayList.java
 * @Purpose
 * 		In this class, we need to test the PlayList.java, and make sure that it can converge 100%		
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.PlayList;
import model.Song;

public class PlayListTest {

	@Test
	public void test() {
		PlayList playlist = new PlayList();
		assertEquals(null, playlist.peek());
		
		playlist.add(new Song("song1.mp3", "song1", "singer1", 10));
		playlist.add(new Song("song2.mp3", "song2", "singer2", 20));
		Song song = playlist.peek();
		assertEquals(2,playlist.getSize());
		assertEquals("song1",playlist.getElementAt(0));
		playlist.addListDataListener(null);
		playlist.removeListDataListener(null);
		assertEquals("song1.mp3", song.getFileName());
		assertEquals("song1", song.getTitle());
		assertEquals("singer1", song.getArtist());
		assertEquals(10, song.getSongLength());
		ArrayList<Song> copy = new ArrayList();
	    copy =  playlist.getCopyOfPlayList();
		
		playlist.remove();
		song = playlist.peek();
		assertEquals("song2.mp3", song.getFileName());
		assertEquals("song2", song.getTitle());
		assertEquals("singer2", song.getArtist());
		assertEquals(20, song.getSongLength());
		
		playlist.remove();
		assertEquals(null, playlist.peek());
		
		playlist.remove();
		assertEquals(null, playlist.peek());
	}

}
