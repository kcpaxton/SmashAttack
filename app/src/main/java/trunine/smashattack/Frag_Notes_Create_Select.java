package trunine.smashattack;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frag_Notes_Create_Select extends ListFragment {
    List<Model_Icon> listOfFighterIcons = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getFighterIcons();

        return inflater.inflate(R.layout.frag_note_creation_select, container, false);
    }

    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(new Adapter_FighterNotesCreateSelect(getActivity(), listOfFighterIcons));
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //******************************************************************************************
        // Set the OnItemClickListener
        //******************************************************************************************
        getListView().setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        getListView().setCacheColorHint(getResources().getColor(R.color.colorBlack));

                        Frag_FighterDisplay fragFighterDisplay = new Frag_FighterDisplay();

                        Model_Icon testFighter = (Model_Icon) parent.getItemAtPosition(position); // Gets the id of the selected fighter

                        fragFighterDisplay.getFighterData(testFighter.getFighterId());
                        FragmentTransaction ft = getFragmentManager().beginTransaction();

                        ft.addToBackStack("FighterSelectFragment");
                        ft.replace(R.id.MyFrameLayout, fragFighterDisplay, "DisplayFighterFragment");
                        ft.commit();

                    }
                }
        );
        //******************************************************************************************
    }

    //******************************************************************************************
    // Retrieve the Icon data from http://matthewgollaher.azurewebsites.net/wiiusmash4/card
    //******************************************************************************************
    public void getFighterIcons() {


        Thread thread = new Thread(new Runnable() {
            public void run() {

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    URL jsonUrl = new URL(Globals.iconUrl);
                    listOfFighterIcons = Arrays.asList(objectMapper.readValue(jsonUrl, Model_Icon[].class));//Gathers the icons from json to a list of Model_Icon objects
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
