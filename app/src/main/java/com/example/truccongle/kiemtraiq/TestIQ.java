package com.example.truccongle.kiemtraiq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by truccongle on 02-May-16.
 */
public class TestIQ extends AppCompatActivity {
    // RelativeLayout manHinh;
    TextView cauHoi, daA, daB, daC, daD , cauHoiSo,tvdongho;
    Button btnA, btnB, btnC, btnD;
    String dapAn;
    double diem;
    double tongDiem = 0;
    DatabaseHandler db;
    int count = 0;

    ArrayList<Integer> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_iq);
        db = new DatabaseHandler(this);

        cauHoi = (TextView) findViewById(R.id.textViewCauHoi);
//          manHinh = (RelativeLayout) findViewById(R.id.ManHinh);
        //  manHinh.setBackgroundResource(R.drawable.iq);
        btnA = (Button) findViewById(R.id.buttonDaA);
        btnB = (Button) findViewById(R.id.buttonDaB);
        btnC = (Button) findViewById(R.id.buttonDaC);
        btnD = (Button) findViewById(R.id.buttonDaD);
        cauHoi = (TextView) findViewById(R.id.textViewCauHoi);

        daA = (TextView) findViewById(R.id.textViewDaA);
        daB = (TextView) findViewById(R.id.textViewDaB);
        daC = (TextView) findViewById(R.id.textViewDaC);
        daD = (TextView) findViewById(R.id.textViewDaD);
        cauHoiSo = (TextView) findViewById(R.id.textCauHoiSo);
        tvdongho= (TextView)findViewById(R.id.tvDongho);
        final int[] countS = {1500000 / 1000};

        final CountDownTimer countDownTimer =new CountDownTimer(1500000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvdongho.setText("Time:" +countS[0] +"s");
                countS[0]--;
            }

            @Override
            public void onFinish() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date=new Date();
                String ngay=sdf.format(date);
                db.excuteSQL("INSERT INTO LichSu VALUES('"+ngay+"',"+tongDiem+")");
                Intent it = new Intent(getApplicationContext(), KetQua.class);
                it.putExtra("tongDiem", tongDiem);
                startActivity(it);

            }
        };
        countDownTimer.start();

        loadData();
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "A";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "B";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "C";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chon = "D";
                if (chon.equalsIgnoreCase(dapAn)) {
                    tongDiem = tongDiem + diem;
                }
                loadData();
            }
        });

    }

    //
    public void loadData() {
        int length = db.getCursor("SELECT * FROM CauHoi").getCount();
        Random rd = new Random();
        int random = rd.nextInt(length) + 1;

        if (!testRandom(random)) {
            arr.add(random);
            if (count < 40) {

                Cursor c = db.getCursor("SELECT* FROM CauHoi WHERE MaCauHoi=" + random + "");
                while (c.moveToNext()) {
                    String cauHoi = c.getString(1);
                    this.cauHoi.setText(cauHoi);

                    String daA = c.getString(2);
                    this.daA.setText(daA);
                    String daB = c.getString(3);
                    this.daB.setText(daB);
                    String daC = c.getString(4);
                    this.daC.setText(daC);
                    String daD = c.getString(5);
                    this.daD.setText(daD);
                    diem = c.getDouble(7);
                    dapAn = c.getString(6);
                }
                count++;
                cauHoiSo.setText("Câu hỏi:"+count + "/40");
            } else {
//                Calendar cal = GregorianCalendar.getInstance();
//                cal.setTime(new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date=new Date();
                String ngay=sdf.format(date);
//                cal.add(Calendar.DAY_OF_YEAR, 0);//so ngay truoc. 0 la ngay hien tai
//                Date day = cal.getTime();
//                String ngay=sdf.format(day);

                db.excuteSQL("INSERT INTO LichSu VALUES('"+ngay+"',"+tongDiem+")");

                Intent it = new Intent(getApplicationContext(), KetQua.class);
                it.putExtra("tongDiem", tongDiem);
                startActivity(it);

            }
            } else {
                loadData();
            }
        }


    public boolean testRandom(int a) {
        for (int i : arr) {
            if (a == i)
                return true;
        }
        return false;
    }
}
