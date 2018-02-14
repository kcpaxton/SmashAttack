package trunine.smashattack;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Frag_Notes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    boolean selected =false;
    private Adapter_NotesCursor cursorAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        inflater.inflate(R.menu.notes,menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_notes, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cursorAdapter = new Adapter_NotesCursor(getContext(), null, 0);

        ListView list = (ListView) getActivity().findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        getLoaderManager().initLoader(0, null, this);

        final Button createNoteTemplateButton = getActivity().findViewById(R.id.createNoteTemplateButton);
        final Button createNoteButton = getActivity().findViewById(R.id.createNoteButton);

        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        final FragmentTransaction ft2 = getActivity().getSupportFragmentManager().beginTransaction();
        final Fragment fragLeft = new Frag_Notes_Create_Select();
        final Fragment fragRight = new Frag_Notes_Create_Select();
        final Fragment fragCreateNote = new Frag_Notes_Create();

        createNoteTemplateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*if(selected)
                {
                    //remove fragLeft and fragRight and navigate to Frag_Notes_Create
                    ft2.remove(fragLeft);
                    ft2.remove(fragRight);
                    ft2.replace(R.id.MyFrameLayout, fragCreateNote);
                    ft2.commit();
                    selected = false;
                }
                else
                {
                    ft.add(R.id.frag_left, fragLeft);
                    ft.add(R.id.frag_right, fragRight);
                    ft.commit();
                    createNoteButton.setText("Next");
                    selected = true;
                }*/
            }
        });

        createNoteButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                //remove fragLeft and fragRight and navigate to Frag_Notes_Create
                ft2.remove(fragLeft);
                ft2.remove(fragRight);
                ft2.replace(R.id.MyFrameLayout, fragCreateNote);
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

    private void deleteAllNotes() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {

                            getActivity().getContentResolver().delete(
                                    NotesProvider.CONTENT_URI, null, null);

                            restartLoader();

                            Toast.makeText(getActivity(),
                                    getString(R.string.all_deleted),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    private void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
    }

    private void insertNote(String noteText) {

        ContentValues values = new ContentValues();
        values.put(DB_OpenHelper.NOTE_TEXT, noteText);
        Uri noteUri = getActivity().getContentResolver().insert(NotesProvider.CONTENT_URI, values);

        Log.d("Frag_Note", "Inserted Note " + noteUri.getLastPathSegment());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), NotesProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

}

