package com.phanngocnhutranh.android_delete;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.phanngocnhutranh.Adapter.Adapter;
import com.phanngocnhutranh.Database.Database;
import com.phanngocnhutranh.Giangvien.Giangvien;
import com.phanngocnhutranh.android_delete.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Adapter adapter;
    Database database;
    ArrayList<Giangvien> giangviens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareDB();
        render();
        addEvent();
    }
    public void prepareDB(){
        database = new Database(MainActivity.this);
        database.createData();
    }
    public void render(){
        String sql = " SELECT * FROM " + Database.TBL_NAME;
        Cursor cursor = database.queryData(sql);
        giangviens.clear();
        while (cursor.moveToNext()){
            Giangvien gv = new Giangvien(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            giangviens.add(gv);
        }
        adapter = new Adapter( this, R.layout.custom_tem, giangviens);
        binding.lvList.setAdapter(adapter);
    }

    public void addEvent(){
        binding.lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.delete);
                dialog.show();

                TextView code = dialog.findViewById(R.id.edtcode);
                TextView name = dialog.findViewById(R.id.edtname);
                TextView classroom = dialog.findViewById(R.id.edtclass);


                Giangvien gv = giangviens.get(position);

                code.setText(String.valueOf(gv.getCode()));
                name.setText(String.valueOf(gv.getName()));
                classroom.setText(String.valueOf(gv.getClassroom()));

                Button delete = dialog.findViewById(R.id.btnDelete);
                Button cancle = dialog.findViewById(R.id.btnCancle);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sql = " DELETE FROM " + Database.TBL_NAME + " WHERE Magiangvien = " + gv.getCode();

                        database.execsql(sql);

                        render();

                        dialog.dismiss();
                    }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                return false;
            }
        });
    }
}