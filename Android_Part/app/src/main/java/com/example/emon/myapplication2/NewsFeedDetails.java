package com.example.emon.myapplication2;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NewsFeedDetails extends AppCompatActivity {

    ImageView imageView;
    TextView title,des;
    String id;
    DatabaseReference databaseReference;
    String part1=null,part2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);

        imageView=(ImageView)findViewById(R.id.imagefullnewsfeedid);
        title=(TextView)findViewById(R.id.titlefullnewsfeedid);
        des=(TextView)findViewById(R.id.detailsfullnewsfeedid);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("");
        id=getIntent().getStringExtra(getString(R.string.friend_add_key));




        databaseReference=FirebaseDatabase.getInstance().getReference().child("Blogs").child(id);


        addValue();



    }
    public void addValue()
    {
        databaseReference.child("title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title.setText(dataSnapshot.getValue().toString());
                //tit=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                des.setText(dataSnapshot.getValue().toString());
                //category.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String  propicUri= dataSnapshot.getValue().toString();
                Picasso.get().load(propicUri).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









    }
}
