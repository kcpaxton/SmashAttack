package trunine.smashattack;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class Notes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    boolean selected =false;
    private Adapter_NotesCursor cursorAdapter;
    private static final int EDITOR_REQUEST_CODE = 1001;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        cursorAdapter = new Adapter_NotesCursor(this, null, 0);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        getSupportLoaderManager().initLoader(0, null, this);

        // final Button createNoteTemplateButton = getActivity().findViewById(R.id.createNoteTemplateButton);
        final FloatingActionButton createNoteButton = findViewById(R.id.createNoteButton);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        final Fragment fragLeft = new Frag_Notes_Create_Select();
        final Fragment fragRight = new Frag_Notes_Create_Select();
        final Fragment fragCreateNote = new Frag_Notes_Create();

        createNoteButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                //remove fragLeft and fragRight and navigate to Frag_Notes_Create
                ft2.remove(fragLeft);
                ft2.remove(fragRight);
                ft2.add(R.id.MyNotesLayout, fragCreateNote);
                ft2.addToBackStack(null);
                ft2.commit();

            }

        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_create_sample:
                insertSampleData();
                break;
            case R.id.action_delete_all:
                deleteAllNotes();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("Notes");

    }

    private void deleteAllNotes() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            getContentResolver().delete(
                                    NotesProvider.CONTENT_URI, null, null);

                            restartLoader();

                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }

    private void insertSampleData() {
        insertNote("Simple note");
        insertNote("Multi-line\nnote");
        insertNote("Very long note with a lot of text that exceeds the width of the screen");

        restartLoader();
    }

    public void insertNewNote(String noteText){
        insertNote(noteText);
        restartLoader();
    }

    public void restartLoader() {
        getSupportLoaderManager().restartLoader(0,null, this);
    }

    public void insertNote(String noteText) {

        ContentValues values = new ContentValues();
        values.put(DB_OpenHelper.NOTE_TEXT, noteText);
        Uri noteUri = getContentResolver().insert(NotesProvider.CONTENT_URI, values);

        //Log.d("Frag_Note", "Inserted Note " + noteUri.getLastPathSegment());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, NotesProvider.CONTENT_URI, null, null, null, null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDITOR_REQUEST_CODE && resultCode == RESULT_OK){
            restartLoader();
        }
    }
}
