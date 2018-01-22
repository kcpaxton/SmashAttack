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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_notes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        FloatingActionButton addNoteButton = (FloatingActionButton) getActivity().findViewById(R.id.createNoteFab);

        addNoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Fragment frag = new Frag_Notes_Create();
                ft.replace(R.id.MyFrameLayout, frag);
                ft.commit();
            }
        });
    }
}