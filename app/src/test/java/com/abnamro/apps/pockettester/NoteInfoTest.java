package com.abnamro.apps.pockettester;

import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;

import static org.junit.Assert.*;

/*
 * Example of an Unit test in the Java language
 * The Notes are tested in isolation, we do not refer or store data in the DataManager (that would
 * be an external dependency).
 */

public class NoteInfoTest {

    private NoteInfo mNote;
    private String mDescription;
    private String mTitle;
    private ItemTypeInfo mItemTypeInfo;

    @Before
    public void setupNote() {
        mItemTypeInfo = new ItemTypeInfo("TopicId", "Some Topic Title", new LinkedList<ModuleInfo>());
        mTitle = "new item";
        mDescription = "new description";
        mNote = new NoteInfo(mItemTypeInfo, mTitle, mDescription);
    }

    @Test
    public void givenANote_whenGetTheTopic_thenCorrect() {
        assertEquals(mItemTypeInfo, mNote.getTopic());
    }

    @Test
    public void givenANote_whenChangeTheTopic_thenTopicIsChanged() {
        ItemTypeInfo itemTypeInfo = new ItemTypeInfo("OtherId", "Other Topic Title", new LinkedList<ModuleInfo>());
        mNote.setTopic(itemTypeInfo);
        assertEquals(itemTypeInfo, mNote.getTopic());
    }

    @Test
    public void givenANote_whenGetTheTitle_thenCorrect() {
        assertEquals(mTitle, mNote.getTitle());
    }

    @Test
    public void givenANote_whenChangeTheTitle_thenTitleIsChanged() {
        String title = "just follow the instructions";
        mNote.setTitle(title);
        assertEquals(title, mNote.getTitle());
    }

    @Test
    public void givenANote_whenGetTheText_thenCorrect() {
        assertEquals(mDescription, mNote.getText());
    }

    @Test
    public void givenANote_whenChangeTheText_thenTextIsChanged() {
        String text = "I love deadlines. I love the whooshing noise they make as they go by.";
        mNote.setText(text);
        assertEquals(text, mNote.getText());
    }
}