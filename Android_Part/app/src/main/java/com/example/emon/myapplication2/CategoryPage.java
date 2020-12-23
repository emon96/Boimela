package com.example.emon.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CategoryPage extends AppCompatActivity {
    private String[] joblist;
    Spinner joblistspinner;
    String value;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        joblist=getResources().getStringArray(R.array.jobcategorylist);
        joblistspinner=findViewById(R.id.searchbycategorySpinner);
        ArrayAdapter<String> jobnames=new ArrayAdapter<String>(this,R.layout.jobspinner,R.id.jobId,joblist);
        joblistspinner.setAdapter(jobnames);
        value=joblistspinner.getSelectedItem().toString();

        button=findViewById(R.id.searchButtonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent friend_intent=new Intent(CategoryPage.this,InteresterdJobs.class);
                friend_intent.putExtra("jobcategory",value);
                startActivity(friend_intent);
            }
        });

    }
}
