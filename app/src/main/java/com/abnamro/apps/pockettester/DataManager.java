package com.abnamro.apps.pockettester;

import java.util.ArrayList;
import java.util.List;

public class DataManager implements IDataManager {
    private static DataManager ourInstance = null;

    private List<ItemTypeInfo> mTopics = new ArrayList<>();
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

    @Override
    public List<ItemTypeInfo> getTopics() {
        return mTopics;
    }

    public ItemTypeInfo getTopic(String id) {
        for (ItemTypeInfo topic : mTopics) {
            if (id.equals(topic.getTopicId()))
                return topic;
        }
        return null;
    }

    public List<NoteInfo> getNotes(ItemTypeInfo topic) {
        ArrayList<NoteInfo> notes = new ArrayList<>();
        for(NoteInfo note:mNotes) {
            if(topic.equals(note.getTopic()))
                notes.add(note);
        }
        return notes;
    }

    @Override
    public int getNoteCount(ItemTypeInfo topic) {
        int count = 0;
        for(NoteInfo note:mNotes) {
            if(topic.equals(note.getTopic()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    //region Initialization code

    private void initializeCourses() {
        mTopics.add(new ItemTypeInfo("Bug", "Application Bug", new ArrayList<ModuleInfo>()));
        mTopics.add(new ItemTypeInfo("Idea", "Test Idea", new ArrayList<ModuleInfo>()));
        mTopics.add(new ItemTypeInfo("Check", "Remember to Check", new ArrayList<ModuleInfo>()));
    }

    public void initializeExampleNotes() {
        final DataManager dm = getInstance();

        ItemTypeInfo topic = dm.getTopic("Bug");
        mNotes.add(new NoteInfo(topic, "Double click an item",
                "Two notes will open"));
        mNotes.add(new NoteInfo(topic, "Remove Item",
                "There is no way to remove an item"));

        topic = dm.getTopic("Idea");
        mNotes.add(new NoteInfo(topic, "Import from API",
                "When we can enter new notes from an API, we can remotely manage our testdata!!!"));
        mNotes.add(new NoteInfo(topic, "Delete All interface",
                "When we import from API, we want to delete everything too in a convenient way!"));

        topic = dm.getTopic("Check");
        mNotes.add(new NoteInfo(topic, "Adding duplicate notes",
                "What would happen if you add two exactly the same notes?"));
        mNotes.add(new NoteInfo(topic, "Changing to duplicate note",
                "What happens when you change a note that it becomes a duplicate one?"));
        mNotes.add(new NoteInfo(topic, "Emojis",
                "Can I add emoji's in my notes???"));
    }
    //endregion
}
