package com.example.emon.myapplication2;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PostNewsfeed extends AppCompatActivity {
    private ImageButton imageButton;
    private RecyclerView search_list;
    private Uri imageUri;
    private EditText title,description;
    private StorageReference storageReference;
    private Uri resultUri;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,mdata,dataRef1,dataRef2;
    private static final int code=10;
    private Button postButton;
    FirebaseUser firebaseUser;

    private String imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news_feed);
        imageButton=(ImageButton)findViewById(R.id.postNewsFeedButtonId);
        title=(EditText)findViewById(R.id.newsfeedtitleid);
        description=(EditText)findViewById(R.id.newsfeeddetailsid);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("NewsFeed");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addPost();
                selectImage();
            }
        });
        storageReference= FirebaseStorage.getInstance().getReference();

        firebaseAuth=FirebaseAuth.getInstance();
        postButton=findViewById(R.id.postButtonId);
        firebaseUser=firebaseAuth.getCurrentUser();
        mdata=FirebaseDatabase.getInstance().getReference().child("All Users").child(firebaseUser.getUid());
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Post();
            }
        });

       // progressBar=findViewById(R.id.imagePostProgressbarId);
    }
    public void Post()
    {

        final String tit,des;
        tit=title.getText().toString();
        des=description.getText().toString();
        if(TextUtils.isEmpty(resultUri.toString()))
        {
            Toast.makeText(PostNewsfeed.this,"Please,Select an image",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(tit))
        {
            Toast.makeText(PostNewsfeed.this,"title can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(des))
        {
            Toast.makeText(PostNewsfeed.this,"description can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }


        //progressDialog.show();
        final StorageReference filepath=storageReference.child(resultUri.getLastPathSegment());
        filepath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(PostNewsfeed.this,"Registerd successful",Toast.LENGTH_SHORT).show();
                            // startActivity(new Intent(Register.this,MainActivity.class));
                            String userId=firebaseAuth.getCurrentUser().getUid();
                            final DatabaseReference current_user=databaseReference.push();
                            current_user.child("title").setValue(tit);
                            current_user.child("description").setValue(des);


                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imgUri=uri.toString();
                                    current_user.child("image").setValue(imgUri);
                                }
                            });



            }

        });
        filepath.getDownloadUrl();

        startActivity(new Intent(PostNewsfeed.this,Welcome.class));

    }
  /*
*/

    public void selectImage()
    {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select picture"),code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==code && resultCode==RESULT_OK )
        {
            imageUri=data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(13,9).start(this);

        }
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK)
            {
//Toast.makeText(Register.this,"in result",Toast.LENGTH_SHORT).show();
                resultUri=result.getUri();
                imageButton.setImageURI(resultUri);

            }
            else if(code==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception exception=result.getError();
            }
        }
    }




}