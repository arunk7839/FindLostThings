package joyee.findlost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import joyee.findlost.Model.Item;
import joyee.findlost.Util.Constant;

public class DashboardActivity extends AppCompatActivity {


    FirebaseDatabase mFireBaseDatabase;
    DatabaseReference mDataBaseItemReference;

    Button mAdditemButton;
    ListView mItemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordlist);
        initiliseFirebaseDatabase();
        initFirebaseListener();
        initWidget();
        initListener();
    }

    private void initiliseFirebaseDatabase() {
        mFireBaseDatabase = FirebaseDatabase.getInstance();
       // mFireBaseDatabase.setPersistenceEnabled(true);

        mDataBaseItemReference = mFireBaseDatabase.getReference(Constant.FireBaseConstants.FIND_ND_LOST)
                .child(Constant.FireBaseConstants.ITEM);
    }


    private void initFirebaseListener() {

        mDataBaseItemReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Item> itemList=new ArrayList<Item>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                   Item item= postSnapshot.getValue(Item.class);
                   itemList.add(item);
                }

                initItemData(itemList);
                Log.e("&&&&& ","$$$$$"+itemList.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDataBaseItemReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               Item item = dataSnapshot.getValue(Item.class);
                Log.d("", "Value is: " + item.getItemName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* mDataBaseItemReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Item item = dataSnapshot.getValue(Item.class);
                Log.d("", "Value is: " + item.getItemName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });*/
    }

    private void initWidget() {
        mItemListView = (ListView) findViewById(R.id.list);
        mAdditemButton = (Button) findViewById(R.id.btn_additem);
    }

    private void initItemData(ArrayList<Item> itemList) {
/*        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("purse", R.drawable.purses));
        words.add(new Word("watch", R.drawable.watchs));
        words.add(new Word("Ring", R.drawable.ring));*/
        ItemAdapter adapter = new ItemAdapter(this, itemList);
        mItemListView.setAdapter(adapter);
    }

    private void initListener() {
        mAdditemButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(DashboardActivity.this, AddItemActivity.class);
                startActivity(intent);

            }

        });
    }


}