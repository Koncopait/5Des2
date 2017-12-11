package com.example.user.a5des;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et_bruto;
    private TextView nama,gaji_tx,pph,netto;
    RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;
    double gaji=0,gaji_bersih=0,tunjangan=0;
    CheckBox menikah;
    Button hitung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rg = (RadioGroup) findViewById(R.id.radioGroup2);
        et_bruto = (EditText) findViewById(R.id.et_tinggi);
        rb1 = (RadioButton) findViewById(R.id.satusetengahpersen);
        rb2 = (RadioButton) findViewById(R.id.duapersen);
        final TextInputLayout lay_tinggi = (TextInputLayout) findViewById(R.id.input_layout_tinggi);
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        menikah = (CheckBox) findViewById(R.id.status);
        hitung = (Button) findViewById(R.id.hitung);
        nama = (TextView) findViewById(R.id.nama);
        gaji_tx = (TextView) findViewById(R.id.gaji);
        et_bruto.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(et_bruto.getText().toString())){
                    lay_tinggi.setError("Masukkan Bruto");
                    rb1.setEnabled(false);
                    rb2.setEnabled(false);
                }else {
                    lay_tinggi.setError(null);
                    rb1.setEnabled(true);
                    rb2.setEnabled(true);
                }
            }
        });

        menikah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(menikah.isChecked()){
                    tunjangan = 0.2;
                }else if (!menikah.isChecked()){
                    tunjangan = 0;
                }else {
                    tunjangan =0;
                }

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.satusetengahpersen){
                    gaji =1500000;
                }else if (checkedId==R.id.duapersen){
                    gaji =2000000;
                }


            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nama_karyawan = et_bruto.getText().toString();
                gaji_bersih = gaji+(tunjangan*gaji);
                int int_gaji = (int) gaji_bersih;
                gaji_tx.setText("Rp. "+String .valueOf(int_gaji));
                nama.setText(nama_karyawan);

            }
        });


    }


}
