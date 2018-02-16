package trunine.smashattack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import static trunine.smashattack.Globals.baseUrl;

public class Frag_FighterDisplay extends Fragment {
    Model_Fighter fighterData = new Model_Fighter();
    private ArrayList<DisplayFighterGroup> fighterGroupList = new ArrayList<DisplayFighterGroup>();
    private Adapter_FighterDisplay listAdapter;
    private ExpandableListView displayFighterExpandableListView;
    int progressStatus;
    int position;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // ((AppCompatActivity) getActivity()).getSupportActionBar().hide(); //hides the original actionBar
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.frag_fighter_display, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //********************************************************************************************
        //Sets the collapsing toolbar as the toolbar
        //********************************************************************************************
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.anim_toolbar);
       // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //********************************************************************************************


        displayAttributes(); //sets the attributes for the selected character
        //expandAll();
        // initialize display data into the expandable list
        fighterGroupList.clear();
        initializeData();
        displayFighterExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.displayFighterExpandableListView);
        displayFighterExpandableListView.setFocusable(false);
        listAdapter = new Adapter_FighterDisplay(getActivity(), fighterGroupList);
        displayFighterExpandableListView.setAdapter(listAdapter);
        displayFighterExpandableListView.setPaddingRelative(0, 50, 20, 0);


        //****************************************************************************************************************
        // setOnChildClickListener listener for child row click
        //****************************************************************************************************************
        displayFighterExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Frag_DisplayHitboxes fragDisplayHitboxes = new Frag_DisplayHitboxes();

                fragDisplayHitboxes.getData(/*fighterData, */fighterGroupList.get(groupPosition), groupPosition,childPosition);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.addToBackStack("DisplayFighterFragment");
                ft.replace(R.id.MyFrameLayout, fragDisplayHitboxes, "DisplayHitboxesFragment");
                ft.addToBackStack(null);
                ft.commit();
                return false;

            }
        });
        //***************************************************************************************************************

        //******************************************************************************************************
        // setOnGroupClickListener listener for group heading click
        //******************************************************************************************************
        displayFighterExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                DisplayFighterGroup headerInfo = fighterGroupList.get(groupPosition);
                return false;
            }
        });
        //******************************************************************************************************

        //******************************************************************************************
        // Set the fighter's portrait picture
        //******************************************************************************************
        ImageView fighterPortrait = (ImageView) view.findViewById(R.id.fighterPortrait_id);
        Picasso.with(getContext()).load(fighterData.getPortraitPictureUrl()).into(fighterPortrait);
        //******************************************************************************************
    }

    //******************************************************************************************
    // removes the activity's toolbar from the fragment
    //******************************************************************************************
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(fighterData.getName());
        //ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressbar);
        // progressBar.setVisibility(View.INVISIBLE);
        //Toolbar activityToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
       // activityToolbar.setVisibility(View.GONE);
    }
    //******************************************************************************************


    ///******************************************************************************************
    // Sets the activity's toolbar to visible
    //*******************************************************************************************
    @Override
    public void onStop() {
        super.onStop();
        Toolbar activityToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        activityToolbar.setVisibility(View.VISIBLE);

    }

    //******************************************************************************************
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();

            return true;
        }
        return false;
    }*/


    //*****************************************************************************************************************************************************
    //Sets the attributes
    //*****************************************************************************************************************************************************
    private void displayAttributes(){
        TextView attributesText = (TextView) getActivity().findViewById(R.id.textview_attributes_id);
        attributesText.setText("Weight: " + fighterData.Attributes.getWeight() + " (Rank: " + fighterData.Attributes.getWeightRank() + ")" +
                "\n\nRun Speed: " + fighterData.Attributes.getRunSpeed() + " (Rank: " + fighterData.Attributes.getRunSpeedRank() + ")" +
                "\n\nWalk Speed: " + fighterData.Attributes.getWalkSpeed() + " (Rank: " + fighterData.Attributes.getWalkSpeedRank() + ")" +
                "\n\nAir Speed: " + fighterData.Attributes.getAirSpeed() + " (Rank: " + fighterData.Attributes.getAirSpeedRank() + ")" +
                "\n\nFall Speed: " + fighterData.Attributes.getFallSpeed() + " (Rank: " + fighterData.Attributes.getFallSpeedRank() + ")" +
                "\n\nFast Fall Speed: " + fighterData.Attributes.getFastFallSpeed() + " (Rank: " + fighterData.Attributes.getFastFallSpeedRank() + ")" +
                "\n\nMaximum Jumps: " + fighterData.Attributes.getMaximumJumps() +
                "\n\nWall Jump: " + fighterData.Attributes.getWallJump() +
                "\n\nWall Cling: " + fighterData.Attributes.getWallCling() +
                "\n\nCrawl: " + fighterData.Attributes.getCrawl() +
                "\n\nTether: " + fighterData.Attributes.getTether());
        attributesText.setTextSize(22);
    }
    //****************************************************************************************************************************************************


    //******************************************************************************************
    // Expands all the groups
    //******************************************************************************************
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            displayFighterExpandableListView.expandGroup(i);
        }
    }
    //******************************************************************************************
    //******************************************************************************************
    // Collapses all groups
    //******************************************************************************************
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            displayFighterExpandableListView.collapseGroup(i);
        }
    }
    //******************************************************************************************


    //******************************************************************************************
    // Initializes data in the expandable list view
    //******************************************************************************************
    private int initializeData(){
        int groupPosition = 0;

        DisplayFighterGroup headerInfo = new DisplayFighterGroup();
        headerInfo.setName("Attacks");
        headerInfo.setAttackProductList(fighterData.Attacks);
        fighterGroupList.add(headerInfo);

        headerInfo = new DisplayFighterGroup();
        headerInfo.setName("Aerials");
        headerInfo.setAerialsProductList(fighterData.Aerials);
        fighterGroupList.add(headerInfo);

        headerInfo = new DisplayFighterGroup();
        headerInfo.setName("Specials");
        headerInfo.setSpecialsProductList(fighterData.Specials);
        fighterGroupList.add(headerInfo);

        headerInfo = new DisplayFighterGroup();
        headerInfo.setName("Grabs");
        headerInfo.setGrabsProductList(fighterData.Grabs);
        fighterGroupList.add(headerInfo);

        headerInfo = new DisplayFighterGroup();
        headerInfo.setName("Throws");
        headerInfo.setThrowsProductList(fighterData.Throws);
        fighterGroupList.add(headerInfo);

        headerInfo = new DisplayFighterGroup();
        headerInfo.setName("Rolls");
        headerInfo.setRollsProductList(fighterData.Rolls);
        fighterGroupList.add(headerInfo);

        groupPosition = fighterGroupList.indexOf(headerInfo);
        return groupPosition;
    }


    //******************************************************************************************


    //******************************************************************************************
    // Retrieves the ID from Frag_FighterSelect
    //******************************************************************************************
    public void getFighterData(int id){
        final int position = id;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    //Gathers the fighters data and puts it into fighterData
                    fighterData = objectMapper.readValue(new URL(baseUrl + "fighter/" + position), Model_Fighter.class);
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
