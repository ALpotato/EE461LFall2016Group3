package jjr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Song {
	//music object for the song mp3 goes here
	Notefile notes;
	
	public Song (File f)
	{
		notes = new Notefile(f);
	}
	
	public Song(Notefile n)
	{
		notes = n;
	}
}

//TODO: make this a Singleton
class Notefile {
	ArrayList<Note> notes = new ArrayList<Note>(); //list of notes in the song
	
	public Notefile(File f) //f is a .SM file created using external programs
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
								n.add(new Note(i+1, currentTime)); //this is where the actual notes are generated
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
}

class Note {
	int track; //which column the note is on e.g. left, down, up, right
	double time; //time since start of song the note is located
	
	public Note(int tr, double ti){
		track = tr;
		time = ti;
	}
}