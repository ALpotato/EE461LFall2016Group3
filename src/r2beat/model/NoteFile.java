package r2beat.model;

import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NoteFile {
    private final long trueTime = System.currentTimeMillis() + 325;
    private ArrayList<Note> notes = new ArrayList<Note>(); //list of notes in the song

    public NoteFile(File file)//file is a .SM file created using external programs
    {
        ArrayList<Note> notes = new ArrayList<Note>();
        //parses the .SM file to produce a NoteFile
        double bpm;
        double currentTime;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while (!(line = reader.readLine()).substring(0, 7).equals("#OFFSET")) {
            } //do nothing, this iterates through extraneous lines

            currentTime = -1 * Double.parseDouble(line.split("[:;]")[1]);//sets song start time

            while (!(line = reader.readLine()).substring(0, 5).equals("#BPMS")) {
            }    //iterates through more extraneous lines

            bpm = Double.parseDouble(line.split("[=;]")[1]); //sets song beats per minute

            while ((line = reader.readLine()).length() != 4) {
            }//iterate through more extraneous lines

            ArrayList<String> currentMeasure = new ArrayList<String>();
            currentMeasure.add(line);
            while ((line = reader.readLine()) != null) //while there are still notes
            {
                if (line.indexOf(',') == -1 && line.indexOf(';') == -1) //add notes to temporary array
                    currentMeasure.add(line);
                else //end of measure, process measure
                {
                    int numBeatsInMeasure = currentMeasure.size();
                    double timePerBeat = 240.0 / (bpm * numBeatsInMeasure);
                    for (String s : currentMeasure) {
                        for (int i = 0; i < 4; i++) {
                            if (s.charAt(i) == '1') //1 means there is a note on this column at this time
                            {
                                notes.add(new Note(i + 1, (int) (currentTime * 1000))); //this is where the actual notes are generated
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
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public long getTrueTime() {
        return trueTime;
    }

    public String getNotesJSON() {
        Gson gson = new Gson();
        return gson.toJson(notes);
    }
}

class Note {
    private int column; //which column the note is on e.g. left, down, up, right
    private int time; //time since start of song the note is located

    public Note(int column, int time) {
        this.column = column;
        this.time = time;
    }

    public int getColumn() {
        return column;
    }

    public int getTime() {
        return time;
    }
}