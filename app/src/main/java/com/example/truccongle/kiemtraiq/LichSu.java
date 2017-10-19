package com.example.truccongle.kiemtraiq;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by truccongle on 02-May-16.
 */
public class LichSu extends AppCompatActivity {
    DatabaseHandler db= new DatabaseHandler(this);

    ArrayList<String> arrList=null;
    ArrayAdapter<String> adapter=null;


ListView lvlichsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsu);
lvlichsu=(ListView)findViewById(R.id.listViewLichSu);
        try {
            lvlichsu = (ListView) findViewById(R.id.listViewLichSu);


            //
            db.copyDB2SDCard();
            //Hiển thị dữ liệu từ CSDL lên ListVIew
            data2ListView();
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public void data2ListView() {

        //Khởi tạo lại ArrayList
        ArrayList arrayList = new ArrayList<String>();
        //Lấy dữ liệu vào ArrayList
        Cursor c = db.getCursor("select * from LichSu");
//        c.moveToFirst();
        while (c.moveToNext()) {
            String row =  c.getString(0) + " IQ của bạn là:" + c.getString(1);
            arrayList.add(row);

        //Set Adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lvlichsu.setAdapter(adapter);
    }
}}


