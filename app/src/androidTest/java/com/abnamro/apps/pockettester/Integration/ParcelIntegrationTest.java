package com.abnamro.apps.pockettester.Integration;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import com.abnamro.apps.pockettester.ItemTypeInfo;
import com.abnamro.apps.pockettester.ModuleInfo;
import com.abnamro.apps.pockettester.NoteInfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This is an example of an integration test with a mocked dependency.
 * The Parcel object can be mocked through the AndroidJUnit4 runner so we do not have to use
 * a mocking framework like Mockito
 */
@RunWith(AndroidJUnit4.class)
public class ParcelIntegrationTest {

    @Test
    public void givenAnItemTypeInfo_whenParcelling_thenCorrect() {

        // Arrange
        // create one source of testdata used in this method
        final String topicId = "topicParcelTest";
        final String title = "This is the parcel integration test";

        ItemTypeInfo itemTypeInfo = new ItemTypeInfo(topicId, title, null);
        Parcel parcel = Parcel.obtain();
        itemTypeInfo.writeToParcel(parcel, itemTypeInfo.describeContents());
        parcel.setDataPosition(0);

        // Act
        ItemTypeInfo createdFromParcel = itemTypeInfo.CREATOR.createFromParcel(parcel);

        // Assert
        assertThat(createdFromParcel.getTopicId(), is(topicId));
        assertThat(createdFromParcel.getTitle(), is(title));
    }

    @Test
    public void givenANoteInfo_whenParcelling_thenCorrect() {

        // Arrange
        final String title = "noteParcelTest";
        final String text = "This is the note integration test";

        ItemTypeInfo itemTypeInfo = new ItemTypeInfo("noteParcelTopicId", "Title for the note parcel topic item", new ArrayList<ModuleInfo>());

        NoteInfo noteInfo = new NoteInfo(itemTypeInfo, title, text);
        Parcel parcel = Parcel.obtain();
        noteInfo.writeToParcel(parcel, noteInfo.describeContents());
        parcel.setDataPosition(0);

        // Act
        NoteInfo createdFromParcel = noteInfo.CREATOR.createFromParcel(parcel);

        // Assert
        assertThat(createdFromParcel.getTitle(), is(title));
        assertThat(createdFromParcel.getText(), is(text));
        assertThat(createdFromParcel.getTopic(), is(itemTypeInfo));
    }
}