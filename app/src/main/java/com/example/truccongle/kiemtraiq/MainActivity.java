package com.example.truccongle.kiemtraiq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button bttestiq, btlichsu, btthongtiniq, btthongtinungdung, btthoat;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHandler(this);
        try {
            db.copyDB2SDCard();
        } catch (IOException e) {
            e.printStackTrace();
        }



        bttestiq = (Button) findViewById(R.id.btTest);
        bttestiq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btTestIQ = new Intent(MainActivity.this, TestIQ.class);
                startActivity(btTestIQ);
            }
        });

        btlichsu = (Button) findViewById(R.id.btLichSu);
        btlichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btLichSu = new Intent(MainActivity.this, LichSu.class);
                startActivity(btLichSu);
            }
        });
        btthongtiniq = (Button) findViewById(R.id.btThongTinIQ);
        btthongtiniq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btThongTinIQ = new Intent(MainActivity.this, ThongTinIQ.class);
                startActivity(btThongTinIQ);
            }
        });
        btthongtinungdung = (Button) findViewById(R.id.btThongTinUngDung);
        btthongtinungdung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btThongTinUd = new Intent(MainActivity.this, ThongTinUngDung.class);
                startActivity(btThongTinUd);
            }
        });
        btthoat = (Button) findViewById(R.id.btThoat);
        btthoat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Khoi tao lai Activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
    }
}
