package com.example.emon.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookPost extends AppCompatActivity {
    EditText title,type,author,page,price;
    Button bt;
    String ti,ty,au,pa,pr;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_post);

        title=(EditText)findViewById(R.id.titleId);
        type=(EditText)findViewById(R.id.typeid);
        author=(EditText)findViewById(R.id.authorid);
        page=(EditText)findViewById(R.id.priceid);
        price=(EditText)findViewById(R.id.priceid);
        bt=(Button )findViewById(R.id.postbuttonid);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("AllBooks").push();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ti=title.getText().toString();
                ty=type.getText().toString();
                au=author.getText().toString();
                pa=page.getText().toString();
                pr=price.getText().toString();
                databaseReference.child("title").setValue(ti);
                databaseReference.child("type").setValue(ty);
                databaseReference.child("author").setValue(au);
                databaseReference.child("page").setValue(pa);
                databaseReference.child("price").setValue(pr);
                startActivity(new Intent(BookPost.this,Welcome.class));
            }
        });

    }
}
