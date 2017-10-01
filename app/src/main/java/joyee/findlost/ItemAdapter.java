package joyee.findlost;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import joyee.findlost.Model.Item;
import joyee.findlost.Util.Constant;


public class ItemAdapter extends ArrayAdapter<Item> {


    public ItemAdapter(Context context, ArrayList<Item> words) {
        super(context, 0, words);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Item item = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
       // ImageView imageResourceId = (ImageView) listItemView.findViewById(R.id.image);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
       // imageResourceId.setImageResource(item.getImageResourceId());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView ItemName = (TextView) listItemView.findViewById(R.id.itemname);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        ItemName.setText(item.getItemName());

        listItemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(android.view.View v) {

                Item item = getItem(position);

                Intent intent = new Intent(v.getContext(),ItemDetails.class);
                intent.putExtra(Constant.IntentKeys.ITEM_KEY,item);
                v.getContext().startActivity(intent);

            }

        });

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.

        return listItemView;


    }
}

