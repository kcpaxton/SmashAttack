package trunine.smashattack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter_FighterDisplay extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<DisplayFighterGroup> groupList;


    public Adapter_FighterDisplay(Context context, ArrayList<DisplayFighterGroup> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        if(groupList.get(groupPosition).getAttacks().size() != 0)
        {
            return groupList.get(groupPosition).getAttacks().get(childPosition);
        }
        else if(groupList.get(groupPosition).getAerials().size() != 0)
        {
            return groupList.get(groupPosition).getAerials().get(childPosition);
        }
        else if(groupList.get(groupPosition).getSpecials().size() != 0)
        {
            return groupList.get(groupPosition).getSpecials().get(childPosition);
        }
        else if(groupList.get(groupPosition).getGrabs().size() != 0)
        {
            return groupList.get(groupPosition).getGrabs().get(childPosition);
        }
        else if(groupList.get(groupPosition).getThrowsList().size() != 0)
        {
            return groupList.get(groupPosition).getThrowsList().get(childPosition);
        }
        else if(groupList.get(groupPosition).getRolls().size() != 0)
        {
            return groupList.get(groupPosition).getRolls().get(childPosition);
        }

        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.display_fighter_child_items, null);
        }
        TextView childItem = (TextView) view.findViewById(R.id.childItem);


        if(groupList.get(groupPosition).getAttacks().size() != 0)
        {
            Model_Attack detailInfo = (Model_Attack) this.getChild(groupPosition, childPosition);
            childItem.setText(detailInfo.getName());
        }
        else if(groupList.get(groupPosition).getAerials().size() != 0)
        {
            Model_Aerial detailInfo = (Model_Aerial) this.getChild(groupPosition, childPosition);
            childItem.setText(detailInfo.getName().trim());
        }
        else if(groupList.get(groupPosition).getSpecials().size() != 0)
        {
            Model_Special detailInfo = (Model_Special) this.getChild(groupPosition, childPosition);
            childItem.setText(detailInfo.getName().trim());
        }
        else if(groupList.get(groupPosition).getGrabs().size() != 0)
        {
            Model_Grab detailInfo = (Model_Grab) this.getChild(groupPosition, childPosition);
            childItem.setText(detailInfo.getName().trim());
        }
        else if(groupList.get(groupPosition).getThrowsList().size() != 0)
        {
            Model_Throw detailInfo = (Model_Throw) this.getChild(groupPosition, childPosition);
            childItem.setText(detailInfo.getName().trim());
        }
        else if(groupList.get(groupPosition).getRolls().size() != 0)
        {
            Model_Roll detailInfo = (Model_Roll) this.getChild(groupPosition, childPosition);
            childItem.setText(detailInfo.getName().trim());
        }

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if(groupList.get(groupPosition).getAttacks().size() != 0)
        {
            return groupList.get(groupPosition).getAttacks().size();
        }
        else if(groupList.get(groupPosition).getAerials().size() != 0)
        {
            return groupList.get(groupPosition).getAerials().size();
        }
        else if(groupList.get(groupPosition).getSpecials().size() != 0)
        {
            return groupList.get(groupPosition).getSpecials().size();
        }
        else if(groupList.get(groupPosition).getGrabs().size() != 0)
        {
            return groupList.get(groupPosition).getGrabs().size();
        }
        else if(groupList.get(groupPosition).getThrowsList().size() != 0)
        {
            return groupList.get(groupPosition).getThrowsList().size();
        }
        else
        {
            return groupList.get(groupPosition).getRolls().size();
        }

    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        DisplayFighterGroup headerInfo = (DisplayFighterGroup) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.display_fighter_group_items, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

