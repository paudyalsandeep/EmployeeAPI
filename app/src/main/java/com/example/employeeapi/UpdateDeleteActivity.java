package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.employeeapi.model.EmployeeCUD;

public class UpdateDeleteActivity extends AppCompatActivity {

    EditText etEmployeeNumber,btnSrch,etEmpNum,etEmpSalary;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        etEmployeeNumber=findViewById(R.id.etEmployeeNumber);
    }
}
