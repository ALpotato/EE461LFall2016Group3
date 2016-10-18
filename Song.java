package jjr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Song {
	//music object for the song mp3 goes here
	private Notefile notes;
	private int songStartTime;
	private long trueTime;
	
	public Song (File f)
	{
		notes = Notefile.getInstance();
		notes.createFile(f);
	}
	
	public Song(Notefile n)
	{
		notes = n;
	}
	
	public void play()
	{
		trueTime = System.currentTimeMillis();
		songStartTime = notes.getNotes().get(0).getTime() - 5000;
	}
	
	public Notefile getNotes()
	{
		return notes;
	}
	
	public int getSongStartTime()
	{
		return songStartTime;
	}
	
	public long getTrueTime()
	{
		return trueTime;
	}
}

class Notefile {
	private static Notefile notefile = new Notefile();
	private ArrayList<Note> notes = new ArrayList<Note>(); //list of notes in the song
	
	private Notefile() {}
	
	public void createFile(File f)//f is a .SM file created using external programs
	{
		ArrayList<Note> n = new ArrayList<Note>();
		//parses the .SM file to produce a Notefile
		double bpm;
		double currentTime;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			while((line = reader.readLine()).substring(0,7).equals("#OFFSET") == false)
				{} //do nothing, this iterates through extraneous lines
			
			currentTime = Double.parseDouble(line.split("[:;]")[1]); //sets song start time
			
			while((line = reader.readLine()).substring(0,5).equals("#BPMS") == false)
				{}	//iterates through more extraneous lines
			
			bpm = Double.parseDouble(line.split("[=;]")[1]); //sets song beats per minute
			
			while((line = reader.readLine()).length() != 4)
				{}//iterate through more extraneous lines
			
			ArrayList<String> currentMeasure = new ArrayList<String>();
			currentMeasure.add(line);
			while((line = reader.readLine()) != null) //while there are still notes
			{
				if (line.indexOf(',') == -1 && line.indexOf(';') == -1) //add notes to temporary array
					currentMeasure.add(line);
				else //end of measure, process measure
				{
					int numBeatsInMeasure = currentMeasure.size();
					double timePerBeat = 240.0/(bpm * numBeatsInMeasure);
					for (String s : currentMeasure)
					{
						for (int i = 0; i < 4; i++)
						{
							if (s.charAt(i) == '1') //1 means there is a note on this track at this time
							{
								n.add(new Note(i+1, (int)(currentTime*1000))); //this is where the actual notes are generated
							}
						}
						currentTime += timePerBeat;
					}
					currentMeasure.clear();
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		notes = n;
	}
	
	public ArrayList<Note> getNotes()
	{
		return notes;
	}
	
	public static Notefile getInstance()
	{
		return notefile;
	}
}

class Note {
	private int track; //which column the note is on e.g. left, down, up, right
	private int time; //time since start of song the note is located
	
	public Note(int tr, int ti){
		track = tr;
		time = ti;
	}
	
	public int getTrack()
	{
		return track;
	}
	
	public int getTime()
	{
		return time;
	}
}