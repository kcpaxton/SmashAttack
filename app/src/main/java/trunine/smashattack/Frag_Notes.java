package trunine.smashattack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Frag_Notes extends Fragment {
    boolean selected =false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_notes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button createNoteButton = getActivity().findViewById(R.id.createNoteButton);

        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        final FragmentTransaction ft2 = getActivity().getSupportFragmentManager().beginTransaction();
        final Fragment fragLeft = new Frag_Notes_Create_Select();
        final Fragment fragRight = new Frag_Notes_Create_Select();
        final Fragment fragCreateNote = new Frag_Notes_Create();

        createNoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(selected)
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
                }


            }
        });
    }
}