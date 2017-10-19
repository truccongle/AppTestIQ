package com.example.truccongle.kiemtraiq;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by truccongle on 03-May-16.
 */
public class Data extends ActionBarActivity {


    // Khai báo biến
    DatabaseHandler db = new DatabaseHandler(this);
    //Khai báo biến toàn cục
    ArrayList<String> MangCauHoi = null;
    ArrayAdapter<String> adap = null;
    String sMCH =null;
    String strCauHoi = null;
    String strDaA=null;
    String strDaB=null;
    String strDaC=null;
    String strDaD=null;
    String strDa=null;
    String strDiem=null;
    EditText edtMaCH;
    EditText edtCauHoi;
    EditText edtDaA;
    EditText edtDaB;
    EditText edtDaC;
    EditText edtDaD;
    EditText edtDa;
    EditText edtDiem;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        //
        try {
            lv = (ListView) findViewById(R.id.listViewCauHoi);
            edtMaCH = (EditText) findViewById(R.id.editTextMCH);
            edtCauHoi = (EditText) findViewById(R.id.editTextCH);
            edtDaA=(EditText)findViewById(R.id.editTextDaA);
            edtDaB=(EditText)findViewById(R.id.editTextDaB);
            edtDaC=(EditText)findViewById(R.id.editTextDaC);
            edtDaD=(EditText)findViewById(R.id.editTextDaD);
            edtDa=(EditText)findViewById(R.id.editTextDa);
            edtDiem=(EditText)findViewById(R.id.editTextDiem);
            //
            db.copyDB2SDCard();
            //Hiển thị dữ liệu từ CSDL lên ListVIew
            data2ListView();
            //
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String row=MangCauHoi.get(position);
                    String Mang[]=row.split("-");
                    edtMaCH.setText(Mang[0]);
                    edtCauHoi.setText(Mang[1]);
                    edtDaA.setText(Mang[2]);
                    edtDaB.setText(Mang[3]);
                    edtDaC.setText(Mang[4]);
                    edtDaD.setText(Mang[5]);
                    edtDa.setText(Mang[6]);
                    edtDiem.setText(Mang[7]);

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Phương thức 2: Load dữ liệu lên ListVIew
     public void data2ListView() {

        //Khởi tạo lại ArrayList
        MangCauHoi = new ArrayList<String>();
        //Lấy dữ liệu vào ArrayList
        Cursor c = db.getCursor("select * from CauHoi");
//        c.moveToFirst();
        while (c.moveToNext()) {
            String row = c.getString(0) + "-" + c.getString(1)+"-"+ c.getString(2)+"-"+ c.getString(3)+"-"+ c.getString(4)+"-"+ c.getString(5)+"-"+c.getString(6)+"-"+ c.getString(7);
            MangCauHoi.add(row);
        }
        //Set Adapter
        adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MangCauHoi);
        lv.setAdapter(adap);
    }

    //Phương thức 3: Thêm CSDL
    public void Them(View v) {
        Cursor c = db.getCursor("select * from CauHoi where MaCauHoi ='" + edtMaCH.getText() + "' ");
        if (c.getCount() == 1) {
            Toast.makeText(Data.this, "Mã câu hỏi đã tồn tại,Vui long nhập mã khác", Toast.LENGTH_LONG).show();
        } else {
            db.excuteSQL("Insert into CauHoi values('"+edtMaCH.getText()+"','"+edtCauHoi.getText()+"','"+edtDaA.getText()+"','"+edtDaB.getText()+"','"+edtDaC.getText()+"','"+edtDaD.getText()+"','"+edtDa.getText()+"','"+edtDiem.getText()+"')");
            Toast.makeText(Data.this, "Đã thêm thành công!", Toast.LENGTH_LONG).show();
            data2ListView();
            //
            edtMaCH.setText("");
            edtCauHoi.setText("");
            edtDaA.setText("");
            edtDaB.setText("");
            edtDaC.setText("");
            edtDaD.setText("");
            edtDa.setText("");
            edtDiem.setText("");

        }
    }

    //Phương thức 4: Sửa CSDL
    public void Sua(View v) {
        db.excuteSQL("update CauHoi set CauHoi='"+edtCauHoi.getText()+"',DapAnA='"+edtDaA.getText()+"',DapAnB='"+edtDaB.getText()+"',DapAnC='"+edtDaC.getText()+"',DapAnD='"+edtDaD.getText()+"',DapAn='"+edtDa.getText()+"',Diem='"+edtDiem.getText()+"' where MaCauHoi='"+edtMaCH.getText()+"' ");
        data2ListView();
        edtMaCH.setText("");
        edtCauHoi.setText("");
        edtDaA.setText("");
        edtDaB.setText("");
        edtDaC.setText("");
        edtDaD.setText("");
        edtDa.setText("");
        edtDiem.setText("");
    }

    //Phương thức 5: Xóa CSDL
    public void Xoa(View v) {
        db.excuteSQL("delete from CauHoi where MaCauHoi='"+edtMaCH.getText()+"' ");
        data2ListView();
        edtMaCH.setText("");
        edtCauHoi.setText("");
        edtDaA.setText("");
        edtDaB.setText("");
        edtDaC.setText("");
        edtDaD.setText("");
        edtDa.setText("");
        edtDiem.setText("");
    }
}


