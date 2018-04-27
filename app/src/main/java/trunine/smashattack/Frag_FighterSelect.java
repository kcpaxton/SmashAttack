package trunine.smashattack;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frag_FighterSelect extends ListFragment {
    List<Model_Icon> listOfFighterIcons = new ArrayList<>();
    ProgressBar progressBar;
    private AdView adView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getFighterIcons();

        return inflater.inflate(R.layout.frag_fighter_select, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new Adapter_FighterSelect(getActivity(), listOfFighterIcons));
        getListView().setDivider(null);
        getListView().setDividerHeight(0);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                        getListView().setCacheColorHint(getResources().getColor(R.color.colorBlack));

                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            protected Void doInBackground(Void... params) {
                                Frag_FighterDisplay fragFighterDisplay = new Frag_FighterDisplay();

                                Model_Icon testFighter = (Model_Icon) parent.getItemAtPosition(position); // Gets the id of the selected fighter

                                fragFighterDisplay.getFighterData(testFighter.getFighterId());
                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.MyFrameLayout, fragFighterDisplay, "DisplayFighterFragment");
                                ft.addToBackStack(null);
                                ft.commit();
                                return null;
                            }
                        }.execute();




                    }
                }
        );

        MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-2028376543213618~7792138691");
        adView = (AdView) getActivity().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle( "Fighter Select");
    }

    // Retrieve the Icon data from http://matthewgollaher.azurewebsites.net/wiiusmash4/card
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
