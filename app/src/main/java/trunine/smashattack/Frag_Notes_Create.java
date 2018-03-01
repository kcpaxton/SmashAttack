package trunine.smashattack;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Frag_Notes_Create extends Fragment {

    public static String templateData = "";
    public static String noteTitle = "";
    private String action;
    private static EditText editor;
    private static TextView title;
    private String noteFilter;
    private String oldText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_notes_create, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editor = getActivity().findViewById(R.id.editText);
        title = getActivity().findViewById(R.id.title);
        title.setText(noteTitle);
        checkAndSetTemplateData();
        Bundle bundle = this.getArguments();
        if(bundle != null){
            action = Intent.ACTION_EDIT;
            String uriString = bundle.getString(NotesProvider.CONTENT_ITEM_TYPE);
            retrieveOldText(uriString);
        }
        else
        {
            action = Intent.ACTION_INSERT;
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        if(action.equals(Intent.ACTION_EDIT)) {
            inflater.inflate(R.menu.notes_create,menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_delete:
                deleteNote();
                //force user to go back to Frag_Notes
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }

        return super.onOptionsItemSelected(item);


    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.note_creation));
    }

    @Override
    public void onStop() {
        super.onStop();

        finishEditing();

    }

    public static void getTemplate(String data)
    {
        templateData = data;
    }

    public void finishEditing(){
        String newText = editor.getText().toString().trim();
        Frag_Notes frag_notes = new Frag_Notes();

        switch(action){
            case Intent.ACTION_INSERT:
                if(newText.length() > 0) {
                    insertNote(newText);
                }
                break;
            case Intent.ACTION_EDIT:
                if(newText.length() == 0){
                    deleteNote();
                }else if(oldText.equals(newText)) {
                    //do nothing;
                }else{
                    updateNote(newText);
                }
                break;
        }

    }

    private void updateNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(DB_OpenHelper.NOTE_TEXT, noteText);
        getActivity().getContentResolver().update(NotesProvider.CONTENT_URI, values, noteFilter, null);
        Toast.makeText(getContext(), getString(R.string.note_updated), Toast.LENGTH_SHORT).show();

    }

    public void retrieveOldText(String uriString){

        Uri uri = Uri.parse(uriString);

        if(uri != null){
            noteFilter = DB_OpenHelper.NOTE_ID + "=" + uri.getLastPathSegment();
            Cursor cursor = getActivity().getContentResolver().query(uri, DB_OpenHelper.ALL_COLUMNS, noteFilter, null, null);
            cursor.moveToFirst();
            oldText = cursor.getString(cursor.getColumnIndex(DB_OpenHelper.NOTE_TEXT));
            editor.setText(oldText);
            editor.requestFocus();
        }

    }

    private void checkAndSetTemplateData(){
        if(templateData != null){
            editor.setText(templateData);
            templateData = null;
        }
    }

    private void insertNote(String noteText) {
        ContentValues values = new ContentValues();
        values.put(DB_OpenHelper.NOTE_TEXT, noteText);
        getActivity().getContentResolver().insert(NotesProvider.CONTENT_URI, values);
        //Frag_Notes frag_notes = new Frag_Notes();
        //Notes.insertNewNote(noteText);
    }

    private void deleteNote() {
        getActivity().getContentResolver().delete(NotesProvider.CONTENT_URI, noteFilter,null);
        Toast.makeText(getContext(), getString(R.string.note_deleted), Toast.LENGTH_SHORT).show();
    }

}