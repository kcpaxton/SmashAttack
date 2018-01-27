package trunine.smashattack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class Frag_Notes_Create extends Fragment {

    EditText editText;
    ImageButton saveButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_notes_create, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButton = (ImageButton) getActivity().findViewById(R.id.saveButton);
        editText = (EditText) getActivity().findViewById(R.id.EditText1);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Save("Note1"); //Replace this name later
            }
        });
    }


    public void Save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(getActivity().openFileOutput(fileName, 0));
            out.write(editText.toString());
            out.close();
            Toast.makeText(getContext(), "Note Saved", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(getContext(), "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}