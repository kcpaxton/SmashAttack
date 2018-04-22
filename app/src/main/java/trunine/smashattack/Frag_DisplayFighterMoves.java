package trunine.smashattack;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class Frag_DisplayFighterMoves extends Fragment {

    Model_Fighter fighterData = new Model_Fighter();
    private Adapter_FighterDisplay listAdapter;
    private ExpandableListView displayFighterExpandableListView;
    private ArrayList<DisplayFighterGroup> fighterGroupList = new ArrayList<DisplayFighterGroup>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_display_fighter_moves, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayFighterExpandableListView = (ExpandableListView) getActivity().findViewById(R.id.displayFighterExpandableListView);
        displayFighterExpandableListView.setFocusable(false);
        listAdapter = new Adapter_FighterDisplay(getActivity(), fighterGroupList);
        displayFighterExpandableListView.setAdapter(listAdapter);
        displayFighterExpandableListView.setPaddingRelative(0, 10, 20, 0);


        // initialize display data into the expandable list
        fighterGroupList.clear();
        initializeData();

        // setOnChildClickListener listener for child row click
        displayFighterExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Frag_DisplayHitboxes fragDisplayHitboxes = new Frag_DisplayHitboxes();

                fragDisplayHitboxes.getData(/*fighterData, */fighterGroupList.get(groupPosition), groupPosition,childPosition);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                //ft.addToBackStack("DisplayFighterFragment");
                ft.replace(R.id.MyFrameLayout, fragDisplayHitboxes, "DisplayHitboxesFragment");
                ft.addToBackStack(null);
                ft.commit();
                return false;

            }
        });

        // setOnGroupClickListener listener for group heading click
        displayFighterExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                DisplayFighterGroup headerInfo = fighterGroupList.get(groupPosition);
                return false;
            }
        });
    }

    // Initializes data in the expandable list view
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

    public void setFighterData(Model_Fighter fighterData){
        this.fighterData = fighterData;
    }

    // Expands all the groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            displayFighterExpandableListView.expandGroup(i);
        }
    }

    // Collapses all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            displayFighterExpandableListView.collapseGroup(i);
        }
    }
}
