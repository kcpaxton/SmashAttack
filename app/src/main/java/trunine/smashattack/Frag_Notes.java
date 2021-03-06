package trunine.smashattack;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class Frag_Notes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    boolean selected =false;
    private Adapter_NotesCursor cursorAdapter;
    private static final int EDITOR_REQUEST_CODE = 1001;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Fragment frg = null;
        frg = getFragmentManager().findFragmentByTag("Frag_Notes");
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(frg);
        fragmentTransaction.attach(frg);
        fragmentTransaction.commit();
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

        final FloatingActionButton createNoteTemplateButton = getActivity().findViewById(R.id.createNoteTemplateButton);
        final FloatingActionButton createNoteButton = getActivity().findViewById(R.id.createNoteButton);

        //final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        final FragmentTransaction ft2 = getActivity().getSupportFragmentManager().beginTransaction();
        final Fragment fragCreateNote = new Frag_Notes_Create();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ft2.replace(R.id.MyFrameLayout, fragCreateNote);
                ft2.addToBackStack(null);
                ft2.commit();
                Uri uri = Uri.parse(NotesProvider.CONTENT_URI + "/" + id);
                Bundle bundle = new Bundle();
                bundle.putString(NotesProvider.CONTENT_ITEM_TYPE, uri.toString());
                fragCreateNote.setArguments(bundle);
            }
        });


        createNoteTemplateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String editTextTemplate =
                        "- Neutral\n" +
                        "\n" +
                        "\n" +
                        "- Stages\n" +
                        "   - Go to\n" +
                        "\n" +
                        "   - Ban\n" +
                        "\n" +
                        "\n" +
                        "- Test\n";

                Frag_Notes_Create frag_notes_create = new Frag_Notes_Create();
                Frag_Notes_Create.getTemplate(editTextTemplate);
                ft2.replace(R.id.MyFrameLayout, fragCreateNote);
                ft2.addToBackStack(null);
                ft2.commit();
            }
        });

        createNoteButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
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
            case R.id.action_delete_all:
                deleteAllNotes();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle( "Notes");

        //add if here
        //if(NotesProvider.CONTENT_URI != NULL{
            restartLoader();
        //}

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

    public void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
    }

    public void insertNote(String noteText, String noteTitle) {
        ContentValues values = new ContentValues();
        values.put(DB_OpenHelper.NOTE_TITLE, noteTitle);
        values.put(DB_OpenHelper.NOTE_TEXT, noteText);
        Uri noteUri = getContext().getContentResolver().insert(NotesProvider.CONTENT_URI, values);
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

