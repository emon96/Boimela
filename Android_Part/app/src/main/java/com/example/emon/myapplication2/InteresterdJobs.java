package com.example.emon.myapplication2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class InteresterdJobs extends AppCompatActivity {
    RecyclerView rvJoblistt;
    DatabaseReference databaseList;
    private ImageView imageView;
    private LinearLayout layout;
    private CircleImageView profilePic;
    private ImageButton optionBar,findFriend,messanger;
    String jobname=null;


    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;
    FirebaseAuth mAuth;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesterd_jobs);
        rvJoblistt=findViewById(R.id.myinterestedjobsId);

        rvJoblistt.setHasFixedSize(true);
        rvJoblistt.setLayoutManager(new LinearLayoutManager(this));
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(user==null)
                {
                    startActivity(new Intent(InteresterdJobs.this,MainActivity.class));
                }
            }
        };
        jobname=getIntent().getStringExtra("jobcategory");
        if(jobname!=null)
        {
            mAuth=FirebaseAuth.getInstance();
            databaseList= FirebaseDatabase.getInstance().getReference().child(getString(R.string.jobcategory)).child(jobname);
        }
        else
        {
            mAuth=FirebaseAuth.getInstance();
            databaseList= FirebaseDatabase.getInstance().getReference().child("All Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interested");
        }




    }
    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<UserImageList,Welcome.UploadImageHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<UserImageList, Welcome.UploadImageHolder>(
                UserImageList.class,
                R.layout.userimagelist,
                Welcome.UploadImageHolder.class,
                databaseList

        ) {
            @Override
            protected void populateViewHolder(Welcome.UploadImageHolder viewHolder, final UserImageList model, int position) {
                final String user_key=getRef(position).getKey();



                viewHolder.setTitle(model.getTitle());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(jobname!=null)
                        {
                            String string;
                            string=user_key+":"+jobname;
                            Intent friend_intent=new Intent(InteresterdJobs.this,JobDetails.class);
                            friend_intent.putExtra(getString(R.string.friend_add_key),string);
                           // friend_intent.putExtra("jobcategory",jobname);
                            startActivity(friend_intent);
                        }
                        else
                        {
                            Intent friend_intent=new Intent(InteresterdJobs.this,InterestedJobDetails.class);
                            friend_intent.putExtra(getString(R.string.friend_add_key),user_key);
                            startActivity(friend_intent);
                        }


                    }
                });

          /*      viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!model.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                        {
                            Intent friend_intent=new Intent(Welcome.this,Friend_Profile_Activity.class);
                            friend_intent.putExtra(getString(R.string.friend_add_key),model.getId());
                            startActivity(friend_intent);
                        }
                        else
                        {
                            startActivity(new Intent(Welcome.this,UserProfile.class));
                        }

                    }
                });*/




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
            TextView image_title=view.findViewById(R.id.titleId);
            image_title.setText(title);

        }
        public void  setCompany(String company)
        {
            TextView com=view.findViewById(R.id.companyId);
            com.setText(company);
        }



        public void setQuality(String quality) {
            TextView req=view.findViewById(R.id.requirementId);
            req.setText(quality);
        }
        public void setExperience(String experience) {
            TextView exp=view.findViewById(R.id.experiencetId);
            exp.setText(experience);
        }
        public void setDeadline(String deadline) {
            TextView dead=view.findViewById(R.id.deadlineId);
            dead.setText(deadline);
        }

    }



}
