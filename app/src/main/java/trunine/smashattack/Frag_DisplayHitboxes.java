package trunine.smashattack;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Cache;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Frag_DisplayHitboxes extends Fragment {

    Model_Fighter fighterData = new Model_Fighter();
    DisplayFighterGroup fighterGroupInfo;
    List<String> frameUrls;
    int height = 350;
    int width = 500;
    int groupPosition;
    int childPosition;
    int framePosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        return inflater.inflate(R.layout.frag_display_hitboxes, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageView hitboxes = (ImageView) view.findViewById(R.id.imageView_hitboxes);
        if(fighterGroupInfo.getAttacks().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getAttacks().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
           // textView.setText(fighterGroupInfo.getAttacks().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getAttacks().get(childPosition).getAbilityFramePictureUrls();
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }
        else if(fighterGroupInfo.getAerials().size() != 0) {
            // toolbar.setTitle(fighterGroupInfo.getAerials().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getAerials().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getAerials().get(childPosition).getAbilityFramePictureUrls();
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }
        else if(fighterGroupInfo.getSpecials().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getSpecials().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
           // textView.setText(fighterGroupInfo.getSpecials().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getSpecials().get(childPosition).getAbilityFramePictureUrls();
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }
        else if(fighterGroupInfo.getGrabs().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getGrabs().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getGrabs().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getGrabs().get(childPosition).getAbilityFramePictureUrls();
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }
        else if(fighterGroupInfo.getThrowsList().size() != 0) {
            //  toolbar.setTitle(fighterGroupInfo.getThrowsList().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getThrowsList().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getThrowsList().get(childPosition).getAbilityFramePictureUrls();
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }
        else if(fighterGroupInfo.getRolls().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getRolls().get(childPosition).getName());

            //textView.setText(fighterGroupInfo.getRolls().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getRolls().get(childPosition).getAbilityFramePictureUrls();
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }

        Button nextFrame = (Button) getActivity().findViewById(R.id.button_next);
        final TextView frameStepperText = (TextView) getActivity().findViewById(R.id.frameStepper);
        frameStepperText.setText("" + (framePosition+1) + '/' + frameUrls.size());
        nextFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(framePosition != frameUrls.size() -1) {
                    framePosition++;
                    frameStepperText.setText("" + (framePosition + 1) + '/' + frameUrls.size());
                    //startProgress(); // handle showing progress view somehow
                    Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().networkPolicy(NetworkPolicy.OFFLINE, NetworkPolicy.NO_CACHE).into(hitboxes, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().into(hitboxes);
                        }

                        @Override
                        public void onError() {
                            Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().into(hitboxes);
                        }
                        //stopProgress(); // handle stopping progress view somehow
                    });
                }
            }
        });

        Button previousFrame = (Button) getActivity().findViewById(R.id.button_previous);
        previousFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(framePosition != 0)
                {
                    framePosition--;
                    frameStepperText.setText("" + (framePosition+1) + '/' + frameUrls.size());
                    //startProgress(); // handle showing progress view somehow
                    Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().networkPolicy(NetworkPolicy.OFFLINE, NetworkPolicy.NO_CACHE).into(hitboxes, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().into(hitboxes);
                        }

                        @Override
                        public void onError() {
                            Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().into(hitboxes);
                        }
                        //stopProgress(); // handle stopping progress view somehow
                    });
                }
            }
        });
        preloadFrames(frameUrls, hitboxes);
    }

    private void preloadFrames(final List<String> frameUrls, final ImageView hitboxes) {
        for (int i = 0; i < frameUrls.size(); i++){
            framePosition = i;
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().fetch();
        }
        framePosition = 0;
    }


    public void getData(/*Model_Fighter fighterData,*/ DisplayFighterGroup fighterGroupInfo, int groupPosition, int childPosition)
    {
        this.fighterGroupInfo = fighterGroupInfo;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        //this.fighterData = fighterData;
    }

    // removes the activity's toolbar from the fragment
    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //toolbar.setTitle( fighterGroupInfo.getAttacks().get(childPosition).getName());
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return false;
    }
}
