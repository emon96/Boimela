package com.example.emon.myapplication2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllBook extends AppCompatActivity {

    RecyclerView rvJoblistt;
    DatabaseReference databaseList;
    private ImageView imageView;
    private LinearLayout layout;
    private CircleImageView profilePic;
    private ImageButton optionBar,findFriend,messanger;
    String[] types;


    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser user;
    FirebaseAuth mAuth;
    private TextView name;
    private ImageView iv_search;
    String mSearch;
    private EditText et_search;
    private Spinner searchSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);
        rvJoblistt=findViewById(R.id.mypostedjobsId);
        iv_search=findViewById(R.id.search_book);
        rvJoblistt.setHasFixedSize(true);
        et_search=findViewById(R.id.et_book_search);
        rvJoblistt.setLayoutManager(new LinearLayoutManager(this));
        types=getResources().getStringArray(R.array.searchType);
        searchSpinner=findViewById(R.id.searchSpinnerId);
        mAuth=FirebaseAuth.getInstance();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_spinner_view,R.id.typetextviewId,types);
        searchSpinner.setAdapter(adapter);
        databaseList= FirebaseDatabase.getInstance().getReference().child("AllBooks");
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearch=et_search.getText().toString();
                String string=searchSpinner.getSelectedItem().toString();
                searchBook(string);
            }
        });



    }
    public void searchBook(String str){

        Query search_query=FirebaseDatabase.getInstance().getReference().child("AllBooks").
                orderByChild(str).
                startAt(mSearch).
                endAt(mSearch+"\uf8ff");

        FirebaseRecyclerAdapter<AllBooksList, AllBook.UploadImageHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<AllBooksList, AllBook.UploadImageHolder>(
                AllBooksList.class,
                R.layout.all_book_list,
                AllBook.UploadImageHolder.class,
                search_query

        ) {
            @Override
            protected void populateViewHolder(AllBook.UploadImageHolder viewHolder, final AllBooksList model, int position) {
                final String user_key=getRef(position).getKey();


                viewHolder.setTitle(model.getTitle());
                viewHolder.setAuthor(model.getAuthor());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent friend_intent=new Intent(AllBook.this,BookDetails.class);
                        friend_intent.putExtra(getString(R.string.friend_add_key),user_key);
                        startActivity(friend_intent);

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

    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<AllBooksList, AllBook.UploadImageHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<AllBooksList, AllBook.UploadImageHolder>(
                AllBooksList.class,
                R.layout.all_book_list,
                AllBook.UploadImageHolder.class,
                databaseList

        ) {
            @Override
            protected void populateViewHolder(AllBook.UploadImageHolder viewHolder, final AllBooksList model, int position) {
                final String user_key=getRef(position).getKey();


                viewHolder.setTitle(model.getTitle());
                viewHolder.setAuthor(model.getAuthor());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent friend_intent=new Intent(AllBook.this,BookDetails.class);
                        friend_intent.putExtra(getString(R.string.friend_add_key),user_key);
                        startActivity(friend_intent);

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
        public void  setAuthor(String company)
        {
            TextView com=view.findViewById(R.id.authorid);
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
