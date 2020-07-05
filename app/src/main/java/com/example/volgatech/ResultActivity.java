package com.example.volgatech;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private List<Tree> treeList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Расчёт");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Area area = (Area) getIntent().getSerializableExtra("data");

        recyclerView = findViewById(R.id.recyclerView);

        treeList = new ArrayList<>();

        assert area != null;
        treeList = new DatabaseHelper(this).getTreeDates(String.valueOf(area.getID()));


        fillRecyclerView();
    }

    public void fillRecyclerView(){
        ResultAdapter adapter = new ResultAdapter(this, treeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ResultActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        ResultActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        ResultActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        return true;
    }
}