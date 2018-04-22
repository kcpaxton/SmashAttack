package trunine.smashattack;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
    TextView frameStepperText;
    Button previousFrame;
    Button nextFrame;
    FloatingActionButton playFrames;
    FloatingActionButton stopPlayFrames;
    ImageView hitboxes;
    private Handler handler = new Handler();
    int height = 350;
    int width = 500;
    int groupPosition;
    int childPosition;
    int framePosition = 0;
    boolean endHandler = false;

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
        frameStepperText = (TextView) getActivity().findViewById(R.id.frameStepper);
        previousFrame = (Button) getActivity().findViewById(R.id.button_previous);
        nextFrame = (Button) getActivity().findViewById(R.id.button_next);
        playFrames = (FloatingActionButton) getActivity().findViewById(R.id.button_play);
        stopPlayFrames = (FloatingActionButton) getActivity().findViewById(R.id.button_stop);
        hitboxes = (ImageView) view.findViewById(R.id.imageView_hitboxes);

        stopPlayFrames.setVisibility(View.GONE);

        if(fighterGroupInfo.getAttacks().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getAttacks().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getAttacks().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getAttacks().get(childPosition).getAbilityFramePictureUrls();
            setFirstFrame(hitboxes);

        }
        else if(fighterGroupInfo.getAerials().size() != 0) {
            // toolbar.setTitle(fighterGroupInfo.getAerials().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getAerials().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getAerials().get(childPosition).getAbilityFramePictureUrls();
            setFirstFrame(hitboxes);
        }
        else if(fighterGroupInfo.getSpecials().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getSpecials().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
           // textView.setText(fighterGroupInfo.getSpecials().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getSpecials().get(childPosition).getAbilityFramePictureUrls();
            setFirstFrame(hitboxes);
        }
        else if(fighterGroupInfo.getGrabs().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getGrabs().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getGrabs().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getGrabs().get(childPosition).getAbilityFramePictureUrls();
            setFirstFrame(hitboxes);
        }
        else if(fighterGroupInfo.getThrowsList().size() != 0) {
            //  toolbar.setTitle(fighterGroupInfo.getThrowsList().get(childPosition).getName());
            //TextView textView = (TextView) getActivity().findViewById(R.id.textview);
            //textView.setText(fighterGroupInfo.getThrowsList().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getThrowsList().get(childPosition).getAbilityFramePictureUrls();
            setFirstFrame(hitboxes);
        }
        else if(fighterGroupInfo.getRolls().size() != 0) {
            //toolbar.setTitle(fighterGroupInfo.getRolls().get(childPosition).getName());

            //textView.setText(fighterGroupInfo.getRolls().get(childPosition).getName());

            frameUrls = fighterGroupInfo.getRolls().get(childPosition).getAbilityFramePictureUrls();
            setFirstFrame(hitboxes);
        }

        setFirstFrameStepper();


        stopPlayFrames.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endHandler = true;
            }
        });

        playFrames.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endHandler = false;
                framePosition = 0;
                playFrames.setVisibility(View.GONE);
                stopPlayFrames.setVisibility(View.VISIBLE);
                handler.postDelayed(playFrameRunnable, 100);
            }
        });


        nextFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endHandler = true;
                if(framePosition != frameUrls.size() -1) {
                    framePosition++;
                    frameStepperText.setText("" + (framePosition + 1) + '/' + frameUrls.size());
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


        previousFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endHandler =true;
                if(framePosition != 0)
                {
                    framePosition--;
                    frameStepperText.setText("" + (framePosition+1) + '/' + frameUrls.size());
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

    private void setFirstFrame(ImageView hitboxes){
        if(frameUrls.size() > 0){
            Picasso.with(getContext()).load(frameUrls.get(framePosition)).into(hitboxes);
        }
        else{
            Picasso.with(getContext()).load(R.drawable.ic_add_24dp).into(hitboxes);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setFirstFrameStepper() {
        if (frameUrls.size() > 0) {
            frameStepperText.setText("" + (framePosition + 1) + '/' + frameUrls.size());
            nextFrame.setVisibility(View.VISIBLE);
            previousFrame.setVisibility(View.VISIBLE);
            playFrames.setVisibility(View.VISIBLE);
        } else {
            frameStepperText.setText("Sorry! Frame data will be coming soon for this move!");
            nextFrame.setVisibility(View.GONE);
            previousFrame.setVisibility(View.GONE);
            playFrames.setVisibility(View.GONE);
        }
    }

    private Runnable playFrameRunnable = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            frameStepperText.setText("" + (framePosition + 1) + '/' + frameUrls.size());

            Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().networkPolicy(NetworkPolicy.OFFLINE, NetworkPolicy.NO_CACHE).into(hitboxes, new Callback() {
                @Override
                public void onSuccess() {
                    Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().into(hitboxes);
                }

                @Override
                public void onError() {
                    Picasso.with(getContext()).load(frameUrls.get(framePosition)).resize(width, height).centerCrop().into(hitboxes);
                }

            });

            if(framePosition < frameUrls.size()-1 && !endHandler) {
                handler.postDelayed(this, 100);
                framePosition++;
            }
            else{
                playFrames.setVisibility(View.VISIBLE);
                stopPlayFrames.setVisibility(View.GONE);
                endHandler = false;
            }


        }
    };

}
