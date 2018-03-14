/*
 * @Class
 * 		PlayList.java
 * @Purpose
 * 		This class creates a play list for queuing song into a list.
 * 		It acts like a Stack but the root is an ArrayList.
 * @Programmer YANG HONG
 * @Programmer Haozhe Xu
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

@SuppressWarnings("serial")
public class PlayList extends Observable implements Serializable, ListModel<String>{

	private ArrayList<Song> playList;
	
	public PlayList(){
		playList = new ArrayList<Song>();
	}
	
	public void add(Song song){
		playList.add(song);
		setChanged();
		notifyObservers();
	}
	
	public Song peek(){
		if(playList.isEmpty())
			return null;
		return playList.get(0);
	}
	
	public void remove(){
		if(playList.isEmpty())
			return;
		playList.remove(0);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Song> getCopyOfPlayList(){
		ArrayList<Song> copy = new ArrayList<Song>();
		for (int i = 0; i < playList.size(); i++)
			copy.add(playList.get(i));
		
		return copy;
	}

	@Override
	public int getSize() {
		return playList.size();
	}

	@Override
	public String getElementAt(int index) {
		String name = playList.get(index).getTitle();
		return name;
	}

	@Override
	public void addListDataListener(ListDataListener l) {}

	@Override
	public void removeListDataListener(ListDataListener l) {}
	
}
