package com.abnamro.apps.pockettester.UI;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.abnamro.apps.pockettester.DataManager;
import com.abnamro.apps.pockettester.NoteInfo;
import com.abnamro.apps.pockettester.PocketTesterActivity;
import com.abnamro.apps.pockettester.R;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class NoteUITest {

    @Rule
    public ActivityTestRule<PocketTesterActivity> mActivityRule =
            new ActivityTestRule<>(PocketTesterActivity.class);

    private List<Runnable> mAfterActions = new ArrayList<Runnable>();

    @After
    public void ExecuteAfterActions() {
        mAfterActions.forEach(x -> x.run());
    }

    /*
    This is an example of an UI test that does tests if an item can be added through the main screen and is
    saved after pressing the close button. That the item is saved is checked by the DataManager object.
    UI testing is not the same as doing everything through the UI
     */
    @Test
    public void givenTheApplication_whenAddingAnItem_thenTheNewItemIsAdded() {

        // Arrange
        final String title = "New Bug Found";
        final String content = "This is the content of the bug";

        // Act
        onView(allOf(withId(R.id.fab), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.text_note_title), isDisplayed())).perform(replaceText(title), closeSoftKeyboard());
        onView(allOf(withId(R.id.text_note_text), isDisplayed())).perform(replaceText(content), closeSoftKeyboard());

        pressBack();

        DataManager dataManager = DataManager.getInstance();
        List<NoteInfo> notes = dataManager.getNotes();
        NoteInfo note = notes.get(notes.size() - 1);

        // Add Cleanup step that will run even if the assert fails
        mAfterActions.add(() -> dataManager.removeNote(notes.size() - 1));

        // Assert
        assertThat(note.getTitle(), is(title));
        assertThat(note.getText(), is(content));
        assertThat(note.getTopic().getTopicId(), is("Bug"));
    }

    /*
    In this example we do the opposite: we retrieve the first item from the DataManager in the arrange phase and click on the
    same item in the list. Then we check if the text on the screen is the same as in the DataManager
     */
    @Test
    public void givenANote_whenSelectingTheNote_thenCanSeeTheDetails() {

        // Arrange
        DataManager dataManager = DataManager.getInstance();
        NoteInfo note = dataManager.getNotes().get(0);

        // Act
        onData(anything()).inAdapterView(allOf(withId(R.id.list_notes), isCompletelyDisplayed())).atPosition(0).perform(click());

        // Assert
        onView(allOf(withId(R.id.text_note_title), isDisplayed())).check(matches(withText(note.getTitle())));
        onView(allOf(withId(R.id.text_note_text), isDisplayed())).check(matches(withText(note.getText())));

        pressBack();
    }
}
