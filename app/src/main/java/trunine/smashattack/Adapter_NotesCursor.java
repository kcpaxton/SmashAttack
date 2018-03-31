package trunine.smashattack;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Adapter_NotesCursor extends CursorAdapter{

    public Adapter_NotesCursor(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.note_item_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String noteText = cursor.getString(cursor.getColumnIndex(DB_OpenHelper.NOTE_TEXT)); //Change this to Note_Title when ready

        int position = noteText.indexOf(10);//ascii value of linefeed character
        if(position != -1){
            noteText = noteText.substring(0, position) + "...";
        }

        TextView textView = view.findViewById(R.id.tvNote);
        textView.setText(noteText);

    }
}
