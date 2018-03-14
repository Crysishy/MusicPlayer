/*
 * @Class
 * 		SongList.java
 * @Purpose
 * 		In this class, we need to test the SongList.java, and make sure that it can converge 100%		
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.SongList;
import model.Song;

public class SongListTest {

	@Test
	public void test() {
		SongList songlist = SongList.getSongList();
		
		Song song = songlist.getSongByFileName("DanseMacabreViolinHook.mp3");
		assertEquals("DanseMacabreViolinHook.mp3", song.getFileName());
		assertEquals("Danse Macabre", song.getTitle());
		assertEquals("Kevin MacLeod", song.getArtist());
		assertEquals(34, song.getSongLength());
		assertEquals(null,songlist.getSongByFileName("Ben"));
		assertEquals("tada.wav",songlist.getSongByTitle("Tada").getFileName());
		assertEquals(null,songlist.getSongByTitle("ben"));
		assertEquals(String.class,songlist.getColumnClass(0));
		assertEquals(Integer.class,songlist.getColumnClass(2));
		assertEquals(3,songlist.getColumnCount());
		assertEquals("Title",songlist.getColumnName(0));
		assertEquals("Artist",songlist.getColumnName(1));
		assertEquals("Seconds",songlist.getColumnName(2));
		assertEquals(9,songlist.getRowCount());
		assertEquals("Tada",songlist.getValueAt(0, 0));
		assertEquals("Microsoft",songlist.getValueAt(0, 1));
		assertEquals(02,songlist.getValueAt(0, 2));
		assertFalse(songlist.isCellEditable(2, 2));
		songlist.setValueAt(song, 0, 0);
		songlist.removeTableModelListener(null);
		songlist.addTableModelListener(null);
		
		song = songlist.getSongByFileName("DeterminedTumbao.mp3");
		assertEquals("DeterminedTumbao.mp3", song.getFileName());
		assertEquals("Determined Tumbao", song.getTitle());
		assertEquals("FreePlay Music", song.getArtist());
		assertEquals(20, song.getSongLength());
		
		song = songlist.getSongByFileName("flute.aif");
		assertEquals("flute.aif", song.getFileName());
		assertEquals("Flute", song.getTitle());
		assertEquals("Sun Microsystems", song.getArtist());
		assertEquals(5, song.getSongLength());
		
		song = songlist.getSongByFileName("LopingSting.mp3");
		assertEquals("LopingSting.mp3", song.getFileName());
		assertEquals("Loping Sting", song.getTitle());
		assertEquals("Kevin MacLeod", song.getArtist());
		assertEquals(4, song.getSongLength());
		
		song = songlist.getSongByFileName("spacemusic.au");
		assertEquals("spacemusic.au", song.getFileName());
		assertEquals("Space Music", song.getTitle());
		assertEquals("Unknown", song.getArtist());
		assertEquals(6, song.getSongLength());
		
		song = songlist.getSongByFileName("SwingCheese.mp3");
		assertEquals("SwingCheese.mp3", song.getFileName());
		assertEquals("Swing Cheese", song.getTitle());
		assertEquals("FreePlay Music", song.getArtist());
		assertEquals(15, song.getSongLength());
		
		song = songlist.getSongByFileName("tada.wav");
		assertEquals("tada.wav", song.getFileName());
		assertEquals("Tada", song.getTitle());
		assertEquals("Microsoft", song.getArtist());
		assertEquals(2, song.getSongLength());
		
		song = songlist.getSongByFileName("TheCurtainRises.mp3");
		assertEquals("TheCurtainRises.mp3", song.getFileName());
		assertEquals("The Curtain Rises", song.getTitle());
		assertEquals("Kevin MacLeod", song.getArtist());
		assertEquals(28, song.getSongLength());
		
		song = songlist.getSongByFileName("UntameableFire.mp3");
		assertEquals("UntameableFire.mp3", song.getFileName());
		assertEquals("Untameable Fire", song.getTitle());
		assertEquals("Pierre Langer", song.getArtist());
		assertEquals(282, song.getSongLength());
	}

}
