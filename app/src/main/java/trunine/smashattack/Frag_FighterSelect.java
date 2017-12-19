package trunine.smashattack;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frag_FighterSelect extends ListFragment {
    List<Model_Icon> listOfFighterIcons = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getFighterIcons();
        return inflater.inflate(R.layout.frag_fighter_select, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new Adapter_FighterSelect(getActivity(), listOfFighterIcons));
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
    }

    //******************************************************************************************
    // Retrieve the Icon data from http://matthewgollaher.azurewebsites.net/wiiusmash4/card
    //******************************************************************************************
    public void getFighterIcons() {


        Thread thread = new Thread(new Runnable() {
            public void run() {

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                   // objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
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
