package trunine.smashattack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Frag_DisplayFighterAttributes extends Fragment {

    Model_Fighter fighterData = new Model_Fighter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_display_fighter_attributes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
;
        displayAttributes();
    }

    public void setFighterData(Model_Fighter fighterData){
        this.fighterData = fighterData;
    }

    //Sets the attributes
    @SuppressLint("SetTextI18n")
    private void displayAttributes(){
        TextView attributesText = (TextView) getActivity().findViewById(R.id.attributes);
        attributesText.setText("Weight: " + fighterData.Attributes.getWeight() + "\n     (Rank: " + fighterData.Attributes.getWeightRank() + ")" +
                "\n\nRun Speed: " + fighterData.Attributes.getRunSpeed() + "\n     (Rank: " + fighterData.Attributes.getRunSpeedRank() + ")" +
                "\n\nWalk Speed: " + fighterData.Attributes.getWalkSpeed() + "\n     (Rank: " + fighterData.Attributes.getWalkSpeedRank() + ")" +
                "\n\nAir Speed: " + fighterData.Attributes.getAirSpeed() + "\n     (Rank: " + fighterData.Attributes.getAirSpeedRank() + ")" +
                "\n\nFall Speed: " + fighterData.Attributes.getFallSpeed() + "\n     (Rank: " + fighterData.Attributes.getFallSpeedRank() + ")" +
                "\n\nFast Fall Speed: " + fighterData.Attributes.getFastFallSpeed() + "\n     (Rank: " + fighterData.Attributes.getFastFallSpeedRank() + ")" +
                "\n\nMaximum Jumps: " + fighterData.Attributes.getMaximumJumps() +
                "\n\nWall Jump: " + fighterData.Attributes.getWallJump() +
                "\n\nWall Cling: " + fighterData.Attributes.getWallCling() +
                "\n\nCrawl: " + fighterData.Attributes.getCrawl() +
                "\n\nTether: " + fighterData.Attributes.getTether());
        attributesText.setTextSize(22);
        attributesText.setTextColor(getResources().getColor(R.color.colorWhite));
    }
}
