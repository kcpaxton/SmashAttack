package trunine.smashattack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import static trunine.smashattack.Globals.baseUrl;

public class Frag_FighterDisplay extends Fragment {
    Model_Fighter fighterData = new Model_Fighter();

    private ActionBar actionBar;
    private ViewPager viewPager;
    private Adapter_SectionsPage adapter_sectionsPage;
    Frag_DisplayFighterMoves frag_displayFighterMoves = new Frag_DisplayFighterMoves();
    Frag_DisplayFighterAttributes frag_displayFighterAttributes = new Frag_DisplayFighterAttributes();

    int progressStatus;
    int position;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_fighter_display, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frag_displayFighterMoves.setFighterData(fighterData);
        frag_displayFighterAttributes.setFighterData(fighterData);

        adapter_sectionsPage = new Adapter_SectionsPage(getChildFragmentManager());
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Set the fighter's portrait picture
        ImageView fighterPortrait = (ImageView) view.findViewById(R.id.fighterPortrait_id);
        Picasso.with(getContext()).load(fighterData.getPortraitPictureUrl()).into(fighterPortrait);
    }

    // removes the activity's toolbar from the fragment
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).hideMainActionBar();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.anim_toolbar);
        setToolbar(toolbar);
        toolbar.setTitle(fighterData.getName());
    }

    // Sets the activity's toolbar to visible
    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).setMainActionBar();
        ((MainActivity)getActivity()).showMainActionBar();
    }

    // Retrieves the ID from Frag_FighterSelect
    public void getFighterData(int id){

        final int position = id;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {

                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                   // objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
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

    public void setToolbar(Toolbar toolbar){
        if(toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        Adapter_SectionsPage adapter = new Adapter_SectionsPage(getChildFragmentManager());
        adapter.addFragment(frag_displayFighterMoves, "Hitboxes");
        adapter.addFragment(frag_displayFighterAttributes, "Attributes");
        viewPager.setAdapter(adapter);
    }

}
