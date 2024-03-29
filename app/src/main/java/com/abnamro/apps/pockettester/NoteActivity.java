package com.abnamro.apps.pockettester;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
        public static final String NOTE_POSITION = "com.abnamro.apps.pockettester.NOTE_POSITION";
        public static final int POSITION_NOT_SET = -1;
        private NoteInfo mNote;
        private boolean mIsNewNote;
        private Spinner mSpinnerTopics;
        private EditText mTextNoteTitle;
        private EditText mTextNoteText;
        private int mNotePosition;
        private boolean mIsCancelling;
        private NoteActivityViewModel mViewModel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_note);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                    ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
            mViewModel = viewModelProvider.get(NoteActivityViewModel.class);

            if(mViewModel.mIsNewlyCreated && savedInstanceState != null)
                mViewModel.restoreState(savedInstanceState);

            mViewModel.mIsNewlyCreated = false;

            mSpinnerTopics = findViewById(R.id.spinner_topic);

            List<ItemTypeInfo> courses = DataManager.getInstance().getTopics();
            ArrayAdapter<ItemTypeInfo> adapterTopic =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
            adapterTopic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerTopics.setAdapter(adapterTopic);

            readDisplayStateValues();
            saveOriginalNoteValues();

            mTextNoteTitle = findViewById(R.id.text_note_title);
            mTextNoteText = findViewById(R.id.text_note_text);

            if(!mIsNewNote)
                displayNote(mSpinnerTopics, mTextNoteTitle, mTextNoteText);
        }

        private void saveOriginalNoteValues() {
            if(mIsNewNote)
                return;
            mViewModel.mOriginalNoteTopicId = mNote.getTopic().getTopicId();
            mViewModel.mOriginalNoteTitle = mNote.getTitle();
            mViewModel.mOriginalNoteText = mNote.getText();

        }

        @Override
        protected void onPause() {
            super.onPause();
            if(mIsCancelling) {
                if(mIsNewNote) {
                    DataManager.getInstance().removeNote(mNotePosition);
                } else {
                    storePreviousNoteValues();
                }
            } else {
                saveNote();
            }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            if(outState != null)
                mViewModel.saveState(outState);
        }

        private void storePreviousNoteValues() {
            ItemTypeInfo topic = DataManager.getInstance().getTopic(mViewModel.mOriginalNoteTopicId);
            mNote.setTopic(topic);
            mNote.setTitle(mViewModel.mOriginalNoteTitle);
            mNote.setText(mViewModel.mOriginalNoteText);
        }

        private void saveNote() {
            mNote.setTopic((ItemTypeInfo) mSpinnerTopics.getSelectedItem());
            mNote.setTitle(mTextNoteTitle.getText().toString());
            mNote.setText(mTextNoteText.getText().toString());
        }

        private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
            List<ItemTypeInfo> courses = DataManager.getInstance().getTopics();
            int courseIndex = courses.indexOf(mNote.getTopic());
            spinnerCourses.setSelection(courseIndex);
            textNoteTitle.setText(mNote.getTitle());
            textNoteText.setText(mNote.getText());
        }

        private void readDisplayStateValues() {
            Intent intent = getIntent();
            int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
            mIsNewNote = position == POSITION_NOT_SET;
            if(mIsNewNote){
                createNewNote();
            } else {
                mNote = DataManager.getInstance().getNotes().get(position);
            }
        }

        private void createNewNote() {
            DataManager dm = DataManager.getInstance();
            mNotePosition = dm.createNewNote();
            mNote = dm.getNotes().get(mNotePosition);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_note, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_send_mail) {
                sendEmail();
                return true;
            } else if (id == R.id.action_cancel) {
                mIsCancelling = true;
                finish();
            }

            return super.onOptionsItemSelected(item);
        }

        private void sendEmail() {
            ItemTypeInfo topic = (ItemTypeInfo) mSpinnerTopics.getSelectedItem();
            String subject = mTextNoteTitle.getText().toString();
            String text = "PocketTester says: this is important \"" +
                    topic.getTitle() + "\"\n" + mTextNoteText.getText();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc2822");
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(intent);
        }
}
