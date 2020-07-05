package com.example.volgatech;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.muddzdev.styleabletoast.StyleableToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Tree tree = new Tree(0, "", 0, 0, 0,0, 0, 0);
    private Tree tree2;
    private List<Tree> treeList = new ArrayList<>();
    private Area area;
    private AlertDialog dialogBuilder;
    private View dialogView;

    private EditText forestry;
    private EditText local_forestry;
    private EditText kvartal;
    private EditText vydel;
    private EditText nomer_pp;
    private EditText square;


    private Spinner name;
    private Spinner rh;
    private Spinner stupen;
    private EditText diametr;
    private EditText del;
    private EditText pol_del;
    private EditText drova;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Лесной калькулятор");
        setSupportActionBar(toolbar);

        tree2 = new Tree( 0, "", 0, 0, 0,0, 0, 0);

        area = new Area( 0, "", "", 0, 0,0, 0, "");

        forestry = findViewById(R.id.forestry);
        local_forestry = findViewById(R.id.local_forestry);
        kvartal = findViewById(R.id.kvartal);
        vydel = findViewById(R.id.vydel);
        nomer_pp = findViewById(R.id.nomer_pp);
        square = findViewById(R.id.square);

        name = findViewById(R.id.name);
        rh = findViewById(R.id.rh);
        stupen = findViewById(R.id.stupen);
        diametr = findViewById(R.id.diametr);
        del = findViewById(R.id.del);
        pol_del = findViewById(R.id.pol_del);
        drova = findViewById(R.id.drova);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.nav_notes) {
            Intent record = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(record);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onDialogButtonClick(View v){
        final View.OnClickListener listener;
        if (v.getId() == R.id.addButton) {
            listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tree = new Tree(0, "", 0, 0, 0,0, 0, 0);

                    Spinner poroda1 = dialogView.findViewById(R.id.name);
                    tree.setPoroda(poroda1.getSelectedItem().toString());
                    Spinner rh = dialogView.findViewById(R.id.rh);
                    tree.setRh(Integer.parseInt(rh.getSelectedItem().toString()));
                    Spinner stupen1 = dialogView.findViewById(R.id.stupen);
                    tree.setStupen(Integer.parseInt(stupen1.getSelectedItem().toString()));

                    EditText diametr1 = dialogView.findViewById(R.id.diametr);

                    EditText del_kol = dialogView.findViewById(R.id.del);

                    EditText pol_del_kol = dialogView.findViewById(R.id.pol_del);

                    EditText drova_kol = dialogView.findViewById(R.id.drova);


                    if(TextUtils.isEmpty(diametr1.getText().toString())) {
                        diametr1.setError("Не заполнено");
                    } else if(TextUtils.isEmpty(del_kol.getText().toString())) {
                        del_kol.setError("Не заполнено");
                    } else if(TextUtils.isEmpty(pol_del_kol.getText().toString())) {
                        pol_del_kol.setError("Не заполнено");
                    } else if(TextUtils.isEmpty(drova_kol.getText().toString())) {
                        drova_kol.setError("Не заполнено");
                    } else {
                        tree.setDiametr(Integer.parseInt(diametr1.getText().toString()));
                        tree.setDel_kol(Integer.parseInt(del_kol.getText().toString()));
                        tree.setPol_del_kol(Integer.parseInt(pol_del_kol.getText().toString()));
                        tree.setDrova_kol(Integer.parseInt(drova_kol.getText().toString()));

                        treeList.add(tree);

                        StyleableToast.makeText(getApplicationContext(),"Добавлено",R.style.AddedToastMessage).show();
                        dialogBuilder.dismiss();
                    }

                }
            };
            run_dialog(listener);
        }

    }

    private void run_dialog(View.OnClickListener listener){
        dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_vedomast, null);

        Button button1 = dialogView.findViewById(R.id.buttonSubmit);
        Button button2 = dialogView.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuilder.dismiss();
            }
        });
        button1.setOnClickListener(listener);

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void onStartClick(View v){

        List<EditText> editTextList = new ArrayList<>();
        editTextList.add(forestry);
        editTextList.add(local_forestry);
        editTextList.add(kvartal);
        editTextList.add(vydel);
        editTextList.add(nomer_pp);
        editTextList.add(square);
        editTextList.add(diametr);
        editTextList.add(del);
        editTextList.add(pol_del);
        editTextList.add(drova);

        String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        for(EditText edit : editTextList){
            if(TextUtils.isEmpty(edit.getText())){
                edit.setError("Не заполнено");
                StyleableToast.makeText(this,"Заполните все поля",R.style.ErrorToast).show();
                return;
            }
        }

        area.setForestry(forestry.getText().toString());
        area.setLocalForestry(local_forestry.getText().toString());
        area.setKvartal(Integer.parseInt(kvartal.getText().toString()));
        area.setVidel(Integer.parseInt(vydel.getText().toString()));
        area.setNumber_pp(Integer.parseInt(nomer_pp.getText().toString()));
        area.setArea(Float.parseFloat(square.getText().toString()));
        area.setDate(currentDateandTime);


        tree2.setPoroda(String.valueOf(name.getSelectedItem()));
        tree2.setDiametr(Integer.parseInt(diametr.getText().toString()));
        tree2.setDel_kol(Integer.parseInt(del.getText().toString()));
        tree2.setPol_del_kol(Integer.parseInt(pol_del.getText().toString()));
        tree2.setDrova_kol(Integer.parseInt(drova.getText().toString()));
        tree2.setRh(Integer.parseInt(rh.getSelectedItem().toString()));
        tree2.setStupen(Integer.parseInt(stupen.getSelectedItem().toString()));
        treeList.add(tree2);

        new DatabaseHelper(getBaseContext()).addDatesArea(new Area(
                area.getForestry(),
                area.getLocalForestry(),
                area.getKvartal(),
                area.getVidel(),
                area.getNumber_pp(),
                area.getArea(),
                area.getDate()
        ));

        int lastId = new DatabaseHelper(getBaseContext()).getLastId();


        for (int i = 0; i < treeList.size(); i++) {
            new DatabaseHelper(getBaseContext()).addDatesTree(new Tree(
                    treeList.get(i).getPoroda(),
                    treeList.get(i).getDiametr(),
                    treeList.get(i).getDel_kol(),
                    treeList.get(i).getPol_del_kol(),
                    treeList.get(i).getDrova_kol(),
                    treeList.get(i).getRh(),
                    treeList.get(i).getStupen()
            ), String.valueOf(lastId));
        }

        area.setID(lastId);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("data", area);
        finish();
        startActivity(intent);
        MainActivity.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
