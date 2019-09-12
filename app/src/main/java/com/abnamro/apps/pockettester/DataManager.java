package com.abnamro.apps.pockettester;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<ItemTypeInfo> mCourses = new ArrayList<>();
    private List<NoteInfo> mNotes = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeCourses();
            ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Bas Dam";
    }

    public String getCurrentUserEmail() {
        return "bas@pa7.nl";
    }

    public List<NoteInfo> getNotes() {
        return mNotes;
    }

    public int createNewNote() {
        NoteInfo note = new NoteInfo(null, null, null);
        mNotes.add(note);
        return mNotes.size() - 1;
    }

    public int findNote(NoteInfo note) {
        for(int index = 0; index < mNotes.size(); index++) {
            if(note.equals(mNotes.get(index)))
                return index;
        }

        return -1;
    }

    public void removeNote(int index) {
        mNotes.remove(index);
    }

    public List<ItemTypeInfo> getCourses() {
        return mCourses;
    }

    public ItemTypeInfo getCourse(String id) {
        for (ItemTypeInfo course : mCourses) {
            if (id.equals(course.getCourseId()))
                return course;
        }
        return null;
    }

    public List<NoteInfo> getNotes(ItemTypeInfo course) {
        ArrayList<NoteInfo> notes = new ArrayList<>();
        for(NoteInfo note:mNotes) {
            if(course.equals(note.getCourse()))
                notes.add(note);
        }
        return notes;
    }

    public int getNoteCount(ItemTypeInfo course) {
        int count = 0;
        for(NoteInfo note:mNotes) {
            if(course.equals(note.getCourse()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    //region Initialization code

    private void initializeCourses() {
        mCourses.add(new ItemTypeInfo("Bug", "Application Bug", new ArrayList<ModuleInfo>()));
        mCourses.add(new ItemTypeInfo("Idea", "Test Idea", new ArrayList<ModuleInfo>()));
        mCourses.add(new ItemTypeInfo("Check", "Remember to Check", new ArrayList<ModuleInfo>()));
    }

    public void initializeExampleNotes() {
        final DataManager dm = getInstance();

        ItemTypeInfo course = dm.getCourse("Bug");
        mNotes.add(new NoteInfo(course, "Double click an item",
                "Two notes will open"));
        mNotes.add(new NoteInfo(course, "Remove Item",
                "There is no way to remove an item"));

        course = dm.getCourse("Idea");
        mNotes.add(new NoteInfo(course, "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?"));
        mNotes.add(new NoteInfo(course, "Long running operations",
                "Foreground Services can be tied to a notification icon"));

        course = dm.getCourse("Check");
        mNotes.add(new NoteInfo(course, "Adding duplicate notes",
                "What would happen if you add two exactly the same notes?"));
        mNotes.add(new NoteInfo(course, "Changing to duplicate note",
                "What happens when you change a note that it becomes a duplicate one?"));
        mNotes.add(new NoteInfo(course, "Emojis",
                "Can I add emoji's in my notes???"));
    }
    //endregion
}
