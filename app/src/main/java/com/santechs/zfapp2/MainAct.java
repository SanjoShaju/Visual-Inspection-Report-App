package com.santechs.zfapp2;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity {


    EditText _in_part_name, _in_part_no, _in_emp_no, _in_cmm_no;
    Button _bt_submit;

    ArrayList<String> ar = new ArrayList<>();
    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


        _in_part_name = findViewById(R.id.in_part_name);
        _in_part_no = findViewById(R.id.in_part_no);
        _in_emp_no = findViewById(R.id.in_emp_no);
        _in_cmm_no = findViewById(R.id.in_cmm_no);
        _bt_submit = findViewById(R.id.bt_submit);

        AddData();
        deleteData();
    }


    public void AddData() {
        _bt_submit.setOnClickListener(
                new View.OnClickListener(){
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View view) {

                        ar.add("Part Name : ");
                        ar.add(_in_part_name.getText().toString());
                        ar.add("\nPart No. : ");
                        ar.add(_in_part_no.getText().toString());
                        ar.add("\nEMP No. : ");
                        ar.add(_in_emp_no.getText().toString());
                        ar.add("\nCMM No. : ");
                        ar.add(_in_cmm_no.getText().toString());
                        ar.add("\n\n");
                        str = String.join("", ar);
                        FileHelper.saveToFile(str);

                        str = null;
                        ar.clear();
                        Intent intent = new Intent(MainAct.this, CheckPoints.class);
                        startActivity(intent);

                    }
                }
        );
    }


    public void deleteData() {
        FileHelper.empty_txt_file();
    }

}
