package trunine.smashattack;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_FighterSelect extends BaseAdapter {

    private List<Model_Icon> listOfFighterIcons;
    Context context;
    private LayoutInflater layoutInflater;

    public Adapter_FighterSelect(Activity activity, List<Model_Icon> listOfFighterIcons)
    {
        this.listOfFighterIcons = listOfFighterIcons;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return listOfFighterIcons!=null ? listOfFighterIcons.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listOfFighterIcons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        ImageView fighterIconImage;
        TextView fighterIconName;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Context context = parent.getContext();
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.fighter_select_row, null);
            holder = new ViewHolder();
            holder.fighterIconName = (TextView) convertView.findViewById(R.id.fighterSelectText);
            holder.fighterIconImage = (ImageView) convertView.findViewById(R.id.fighterSelectImage);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        final Model_Icon iconItem = listOfFighterIcons.get(position);
        holder.fighterIconName.setText(iconItem.getName());


        if(holder.fighterIconImage != null)
        {
            Picasso.with(context).load(iconItem.getIconUrl()).into(holder.fighterIconImage);
        }
        else
        {
            holder.fighterIconImage.setImageResource(R.drawable.ic_menu_camera);
        }
        return convertView;

    }

}