package com.example.truccongle.kiemtraiq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KetQua extends AppCompatActivity {
    double tongDiem;
    Button bangLichSu;
    TextView soDiem,diemcaonhat;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        Bundle TongDiem = getIntent().getExtras();
        tongDiem = TongDiem.getDouble("tongDiem");


        db=new DatabaseHandler(this);
        soDiem = (TextView) findViewById(R.id.txtSoDiem);
        soDiem.setText("IQ của bạn là: " + tongDiem+" ");
        diemcaonhat=(TextView)findViewById(R.id.textViewDiemCaoNhat);
        //
        Cursor c= db.getCursor("SELECT * FROM LichSu ORDER BY TongDiem DESC");
        c.moveToFirst();
        diemcaonhat.setText("IQ cao nhất hiện tạị là:" +(c.getString(1)));
        bangLichSu = (Button) findViewById(R.id.buttonLichSuTest);
        bangLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(KetQua.this,LichSu.class);
                startActivity(in);
            }
        });

    }




    }
