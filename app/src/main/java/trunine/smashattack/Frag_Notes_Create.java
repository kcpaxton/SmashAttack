package trunine.smashattack;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class Frag_Notes_Create extends Fragment {

    private String action;
    private EditText editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_notes_create, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editor = getActivity().findViewById(R.id.editText);

        Intent intent = getActivity().getIntent();
        Uri uri = intent.getParcelableExtra(NotesProvider.CONTENT_ITEM_TYPE);

        if(uri == null){
            action = Intent.ACTION_INSERT;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.note_creation));
    }

    @Override
    public void onStop() {
        super.onStop();
        //MainActivity.fragmentNoteCreateBool=false;
        finishEditing();
    }

    public void finishEditing(){
        String newText = editor.getText().toString().trim();
        Frag_Notes frag_notes = new Frag_Notes();

        if(newText.length() > 0) {
            insertNote(newText);
        }
        else
        {
            getActivity().setResult(Activity.RESULT_CANCELED);
        }

       // getActivity().finish();

    }

    private void insertNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(DB_OpenHelper.NOTE_TEXT, noteText);
        getActivity().getContentResolver().insert(NotesProvider.CONTENT_URI, values);
        getActivity().setResult(Activity.RESULT_OK);
        //Frag_Notes frag_notes = new Frag_Notes();
        //Notes.insertNewNote(noteText);
    }


}