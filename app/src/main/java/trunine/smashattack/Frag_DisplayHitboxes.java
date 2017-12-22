package trunine.smashattack;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Frag_DisplayHitboxes extends Fragment {

    DisplayFighterGroup fighterGroupInfo;
    List<String> frameUrls;
    int groupPosition;
    int childPosition;
    int framePosition = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.frag_display_hitboxes, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        final ImageView hitboxes = (ImageView) view.findViewById(R.id.imageView_hitboxes);
        if(fighterGroupInfo.getAttacks().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getAttacks().get(childPosition).getName());
            TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            textView.setText(fighterGroupInfo.getAttacks().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getAttacks().get(childPosition).getAbilityFramePictureUrls();
            preloadFrames(frameUrls, hitboxes);
        }
        else if(fighterGroupInfo.getAerials().size() != 0) {
            // toolbar.setTitle(fighterGroupInfo.getAerials().get(childPosition).getName());
            TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            textView.setText(fighterGroupInfo.getAerials().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getAerials().get(childPosition).getAbilityFramePictureUrls();
            preloadFrames(frameUrls, hitboxes);
        }
        else if(fighterGroupInfo.getSpecials().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getSpecials().get(childPosition).getName());
            TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            textView.setText(fighterGroupInfo.getSpecials().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getSpecials().get(childPosition).getAbilityFramePictureUrls();
            preloadFrames(frameUrls, hitboxes);
        }
        else if(fighterGroupInfo.getGrabs().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getGrabs().get(childPosition).getName());
            TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            textView.setText(fighterGroupInfo.getGrabs().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getGrabs().get(childPosition).getAbilityFramePictureUrls();
            preloadFrames(frameUrls, hitboxes);
        }
        else if(fighterGroupInfo.getThrowsList().size() != 0) {
            //  toolbar.setTitle(fighterGroupInfo.getThrowsList().get(childPosition).getName());
            TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            textView.setText(fighterGroupInfo.getThrowsList().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getThrowsList().get(childPosition).getAbilityFramePictureUrls();
            preloadFrames(frameUrls, hitboxes);
        }
        else if(fighterGroupInfo.getRolls().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getRolls().get(childPosition).getName());
            TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            textView.setText(fighterGroupInfo.getRolls().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getRolls().get(childPosition).getAbilityFramePictureUrls();
            preloadFrames(frameUrls, hitboxes);
        }

        Button nextFrame = (Button) getActivity().findViewById(R.id.button_next);
        nextFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(framePosition != frameUrls.size() -1)
                {
                    framePosition++;
                    File myImageFile = new File(Environment.getExternalStorageDirectory().getPath()+ fighterGroupInfo.getAttacks().get(childPosition).getName() + "_" + framePosition);
                    // Picasso.with(getContext()).load(myImageFile).into(hitboxes);
                    Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
                }



            }
        });

        Button previousFrame = (Button) getActivity().findViewById(R.id.button_previous);
        previousFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(framePosition != 0)
                {
                    framePosition--;
                    File myImageFile = new File(Environment.getExternalStorageDirectory().getPath()+ fighterGroupInfo.getAttacks().get(childPosition).getName() + "_" + framePosition);
                    //Picasso.with(getContext()).load(myImageFile).into(hitboxes);
                    Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
                }



            }
        });
    }


    public void getData(DisplayFighterGroup fighterGroupInfo, int groupPosition, int childPosition)
    {
        this.fighterGroupInfo = fighterGroupInfo;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
    }

    //******************************************************************************************
    // removes the activity's toolbar from the fragment
    //******************************************************************************************
    @Override
    public void onResume() {
        super.onResume();
        //ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressbar);
        // progressBar.setVisibility(View.INVISIBLE);
        Toolbar activityToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        activityToolbar.setVisibility(View.GONE);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return false;
    }

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + framePosition);
                    try
                    {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                        ostream.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            if (placeHolderDrawable != null) {
            }
        }
    };
    public void preloadFrames(List<String> frameUrls, ImageView hitboxes)
    {
        for(framePosition =0; framePosition < frameUrls.size(); framePosition++)
        {
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(target);
            framePosition++;
        }
        framePosition=0;
    }

}