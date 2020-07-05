package com.example.volgatech;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    private Area area;

    private int index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Введенные данные");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Bundle args = getIntent().getExtras();

        // Получение обьекта и его индекса, если его нет - создание
        if (args != null){
            area = (Area) args.getSerializable(Area.class.getSimpleName());
            index = args.getInt("index");
        }if (index == -1){
            area = new Area(0, "", "", 0, 0, 0, 0, "");

        }

        TextView forestry = findViewById(R.id.forestry);
        TextView local_forestry = findViewById(R.id.local_forestry);
        TextView kvartal = findViewById(R.id.kvartal);
        TextView vydel = findViewById(R.id.vydel);
        TextView nomer_pp = findViewById(R.id.nomer_pp);
        TextView square = findViewById(R.id.square);
        TextView date = findViewById(R.id.date);

        date.setText(String.valueOf(area.getDate()));
        forestry.setText(String.valueOf(area.getForestry()));
        local_forestry.setText(String.valueOf(area.getLocalForestry()));
        kvartal.setText(String.valueOf(area.getKvartal()));
        vydel.setText(String.valueOf(area.getVidel()));
        nomer_pp.setText(String.valueOf(area.getNumber_pp()));
        square.setText(String.valueOf(area.getArea()));

    }

    public void onBackPressed() {
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}