/*
 * @Class
 * 		SongList.java
 * @Purpose
 * 		This class defines object SongList. It adds all songs needed into the list.
 * 		It is responsible for getting songs out of the song list by song name.
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class SongList implements Serializable, TableModel{

	private ArrayList<Song> songList;
	private static SongList self;
	
	private SongList(){
		songList = new ArrayList<Song>();
		songList.add(new Song("tada.wav",                   "Tada",              "Microsoft",        02));
		songList.add(new Song("flute.aif",                  "Flute",             "Sun Microsystems", 05));
		songList.add(new Song("spacemusic.au",              "Space Music",       "Unknown",          06));
		songList.add(new Song("SwingCheese.mp3",            "Swing Cheese",      "FreePlay Music",   15));
		songList.add(new Song("DeterminedTumbao.mp3",       "Determined Tumbao", "FreePlay Music",   20));
		songList.add(new Song("LopingSting.mp3",            "Loping Sting",      "Kevin MacLeod",    04));
		songList.add(new Song("TheCurtainRises.mp3",        "The Curtain Rises", "Kevin MacLeod",    28));
		songList.add(new Song("DanseMacabreViolinHook.mp3", "Danse Macabre",     "Kevin MacLeod",    34));
		songList.add(new Song("UntameableFire.mp3",         "Untameable Fire",   "Pierre Langer",    282));
	}
	
	public static synchronized SongList getSongList(){
		if (self == null)
			self = new SongList();
		
		return self;
	}
	
	public Song getSongByFileName(String fileName){
		for(Song song : songList){
			if (fileName.equals(song.getFileName()))
				return song;
		}
		return null;
	}
	
	public Song getSongByTitle(String title){
		for(Song song : songList){
			if (title.equals(song.getTitle()))
				return song;
		}
		return null;
	}

	// String | String | int
	// Title  | Artist | Seconds
	//   0    |    1   |   2
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0 || columnIndex == 1)
			return String.class;
		else
			return Integer.class;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "Title";
		else if (columnIndex == 1)
			return "Artist";
		else
			return "Seconds";
	}
	
	@Override
	public int getRowCount() {
		return songList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Song song = songList.get(rowIndex);
		if (columnIndex == 0)
			return song.getTitle();
		else if (columnIndex == 1)
			return song.getArtist();
		else
			return song.getSongLength();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

	@Override
	public void addTableModelListener(TableModelListener l) {}

	@Override
	public void removeTableModelListener(TableModelListener l) {}
}
