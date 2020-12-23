package com.example.emon.myapplication2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class postedJobsDetails extends AppCompatActivity {
    private String friend_id,name,image,my_id;
    private TextView title,category,company,requ,exp,respon,about,salary,location,contact,deadline;
    String tit,cat,com,req,ex,res,abo,sal,loc,con,dea;

    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference,myreference;
    private FirebaseAuth mAuth;
    private RecyclerView rvImageLIst;
    private RadioGroup radioGroup;
    RadioButton radioButton;
    int selected;
    String interest;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_jobs_details);
        friend_id=getIntent().getStringExtra(getString(R.string.friend_add_key));
        databaseReference=FirebaseDatabase.getInstance().getReference().child("All Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Upload Jobs").child(friend_id);
        myreference=FirebaseDatabase.getInstance().getReference().child("All Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interested");

        title=findViewById(R.id.titleId);
        category=findViewById(R.id.categoryId);
        company=findViewById(R.id.companyId);
        requ=findViewById(R.id.requirementId);
        exp=findViewById(R.id.experiencetId);
        respon=findViewById(R.id.responsibilityId);
        about=findViewById(R.id.aboutId);
        salary=findViewById(R.id.salaryId);
        location=findViewById(R.id.locationId);
        contact=findViewById(R.id.contactId);
        deadline=findViewById(R.id.deadlineId);




        addValue();

    }
    public void addValue()
    {
        databaseReference.child("title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title.setText(dataSnapshot.getValue().toString());
                tit=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cat=dataSnapshot.getValue().toString();
                category.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("company").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                company.setText(dataSnapshot.getValue().toString());
                com =dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("quality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                requ.setText(dataSnapshot.getValue().toString());
                req =dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("experience").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exp.setText(dataSnapshot.getValue().toString());
                ex=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("responsibility").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                respon.setText(dataSnapshot.getValue().toString());
                res=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                about.setText(dataSnapshot.getValue().toString());
                abo=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("salary").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                salary.setText(dataSnapshot.getValue().toString());
                sal=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("location").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                location.setText(dataSnapshot.getValue().toString());
                loc=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contact.setText(dataSnapshot.getValue().toString());
                con=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("deadline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                deadline.setText(dataSnapshot.getValue().toString());
                dea=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });












    }
}
