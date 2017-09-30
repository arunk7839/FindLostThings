package joyee.findlost;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import joyee.findlost.Model.Item;
import joyee.findlost.Util.Constant;


public class AddItemActivity extends AppCompatActivity {
    private static final int RC_IMAGE = 2;
    private ImageView imageView;
    EditText ItemName;
    EditText ItemLocation;
    EditText ItemDiscription;
    EditText Date;
    EditText Time;
    ImageView ImageUrl;
    Button btn_save;
    Uri downloadUrl;
    String imageEncoded;
    Button btn_camera;


    FirebaseDatabase mFireBaseDatabase;
    DatabaseReference mDataBaseReference;
    DatabaseReference mDataBaseItemReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        initiliseFirebaseDatabase();
        initWidgets();
        initListeners();

    }

    private void initiliseFirebaseDatabase() {
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mDataBaseReference = mFireBaseDatabase.getReference(Constant.FireBaseConstants.FIND_ND_LOST);
        mDataBaseItemReference = mDataBaseReference.child(Constant.FireBaseConstants.ITEM);
    }

    private void initWidgets() {
        imageView = (ImageView) findViewById(R.id.image);
        ItemName = (EditText) findViewById(R.id.et_itemname);
        ItemLocation = (EditText) findViewById(R.id.et_location);
        ItemDiscription = (EditText) findViewById(R.id.et_itemdisc);
        Date = (EditText) findViewById(R.id.et_date);
        Time = (EditText) findViewById(R.id.et_time);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_camera = (Button) findViewById(R.id.btn_camera);
    }

    private void initListeners() {
        btn_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RC_IMAGE);
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Item item = new Item();
                item.setItemName("Radio");
                item.setItemId(System.currentTimeMillis());
                mDataBaseItemReference.push().setValue(item);
            }


        });
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }

}






