package joyee.findlost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import joyee.findlost.Model.Item;
import joyee.findlost.Util.Constant;

public class ItemDetails extends AppCompatActivity {


    Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        getExtra();

        if(mItem!=null)
        {
            Log.e(" FIND & LOST "," itemName:  "+mItem.getItemName());
        }
        else{
            Log.e(" FIND & LOST "," item is null");
        }



    }
    private void getExtra()
    {
        mItem=(Item) getIntent().getSerializableExtra(Constant.IntentKeys.ITEM_KEY);
    }

}
