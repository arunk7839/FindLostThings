package joyee.findlost;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;

import joyee.findlost.Model.Item;
import joyee.findlost.Util.Constant;


public class AddItemActivity extends AppCompatActivity {
    private static final int RC_IMAGE = 2;
    private ImageView imageView;
    EditText mItemNameEt;
    EditText ItemLocation;
    EditText mItemDetailEt;
    EditText Date;
    EditText Time;
    ImageView ImageUrl;
    Button btn_save;
    Uri downloadUrl;
    String imageEncoded;
    Button btn_camera;
    Spinner mSpinnerItemFor;


    String mItemImageDownloadURL;
    FirebaseDatabase mFireBaseDatabase;
    DatabaseReference mDataBaseReference;
    DatabaseReference mDataBaseItemReference;

    FirebaseStorage mFirebaseStorage;
    StorageReference mItemPhotosStorageReference;


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

        mFirebaseStorage=FirebaseStorage.getInstance();
        mItemPhotosStorageReference=mFirebaseStorage.getReference()
                .child(Constant.FireBaseConstants.FIND_ND_LOST_STORAGE)
                .child(Constant.FireBaseConstants.ITEM_STORAGE);
    }

    private void initWidgets() {
        imageView = (ImageView) findViewById(R.id.image);
        mItemNameEt = (EditText) findViewById(R.id.et_itemname);
        ItemLocation = (EditText) findViewById(R.id.et_location);
        mItemDetailEt = (EditText) findViewById(R.id.et_itemdisc);
        Date = (EditText) findViewById(R.id.et_date);
        Time = (EditText) findViewById(R.id.et_time);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        mSpinnerItemFor=(Spinner) findViewById(R.id.sp_itemfor);

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
                item.setItemName(""+mItemNameEt.getText());
                item.setItemId(System.currentTimeMillis());
                item.setItemFor(""+mSpinnerItemFor.getSelectedItem());
                item.setItemDetail(""+mItemDetailEt.getText());
                item.setItemCreatedDate(System.currentTimeMillis());
                item.setItemFoundLostDate(System.currentTimeMillis());
                item.setItemType("unkonwn");
                item.setLocation(""+ItemLocation.getText());
                item.setItemImageUri(mItemImageDownloadURL);
                mDataBaseItemReference.push().setValue(item);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
       // Bitmap bitmap = (Bitmap) data.getExtras().get("data");

       try {
           String filename = "itemPhoto" + System.currentTimeMillis() + ".png";
           File sd = Environment.getExternalStorageDirectory();
           File file = new File(sd, filename);
           if (!file.exists())
               file.createNewFile();

           Bitmap bitmap = (Bitmap) data.getExtras().get("data");
           try {
               FileOutputStream out = new FileOutputStream(file);
               bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
               out.flush();
               out.close();
           } catch (Exception e) {
               e.printStackTrace();
           }


           Uri selectedPhotoUri = Uri.parse(file.getAbsolutePath());

           Log.e("FIND & LOST", " selectedPhotoUri " + selectedPhotoUri);

           StorageReference storageReference = mItemPhotosStorageReference.child(selectedPhotoUri.getLastPathSegment());
           storageReference.putFile(selectedPhotoUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   Uri photoDownloadURI = taskSnapshot.getDownloadUrl();
                   mItemImageDownloadURL = photoDownloadURI.toString();
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(AddItemActivity.this,"Fail",Toast.LENGTH_LONG);
                   e.printStackTrace();
               }
           });


           imageView.setImageBitmap(bitmap);
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }

}






