package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnShowAll,btnRegisterPage,btnSearchPage,btnUpdateDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowAll=findViewById(R.id.btnRegisterPage);
        btnRegisterPage=findViewById(R.id.btnRegisterPage);
        btnSearchPage=findViewById(R.id.btnSearchPage);
        btnUpdateDelete=findViewById(R.id.btnUpdateDelete);

        btnRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterEmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnSearchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchEmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UpdateDeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
