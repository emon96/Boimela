package com.example.emon.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView rvJoblistt;
    DatabaseReference databaseList;
    private ImageView imageView;
    private LinearLayout layout;
    private CircleImageView profilePic;
    private ImageButton optionBar,findFriend,messanger;


    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;
    FirebaseAuth mAuth;
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rvJoblistt=findViewById(R.id.newsfeedlistrecylerviewId);

        rvJoblistt.setHasFixedSize(true);
        rvJoblistt.setLayoutManager(new LinearLayoutManager(this));
//        authStateListener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(user==null)
//                {
//                    startActivity(new Intent(Welcome.this,MainActivity.class));
//                }
//            }
//        };

        mAuth=FirebaseAuth.getInstance();
        databaseList= FirebaseDatabase.getInstance().getReference().child("Blogs");



        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("All Users");


    }




    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<UserImageList,UploadImageHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<UserImageList, UploadImageHolder>(
                UserImageList.class,
                R.layout.userimagelist,
                UploadImageHolder.class,
                databaseList

        ) {
            @Override
            protected void populateViewHolder(UploadImageHolder viewHolder, final UserImageList model, int position) {
                final String user_key=getRef(position).getKey();


                viewHolder.setTitle(model.getTitle());

                viewHolder.setImage(getApplicationContext(),model.getImage());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent friend_intent=new Intent(Welcome.this,NewsFeedDetails.class);
                        friend_intent.putExtra(getString(R.string.friend_add_key),user_key);
                        startActivity(friend_intent);

                    }
                });





            }
        };
        rvJoblistt.setAdapter(firebaseRecyclerAdapter);


        ///////

    }

    //////

    public static   class UploadImageHolder extends RecyclerView.ViewHolder{
        View view;
        private DatabaseReference ref;

        public UploadImageHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setTitle(String title)
        {
            TextView image_title=view.findViewById(R.id.newsfeedlisttitleid);
            image_title.setText(title);

        }

        public void setImage(Context context, String image)
        {
            ImageView imageView=view.findViewById(R.id.newsfeedlistimage);
            Picasso.get().load(image).into(imageView);
            //imageView1.setImageURI(imageView);

        }


    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.allbooksid)
        {
            startActivity(new Intent(Welcome.this, AllBook.class));
        }

        else if(id==R.id.stallsid)
        {

        }

        else if (id == R.id.settingsId)
        {


        }
        else if(id==R.id.helpid)
        {

        }
        else if(id==R.id.aboutid)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
