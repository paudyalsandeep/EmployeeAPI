package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeApi;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.model.EmployeeCUD;
import com.example.employeeapi.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteActivity extends AppCompatActivity {

    EditText etEmployeeNumber,etEmpName,etEmpSalary,etEmpAge;
    Button btnSrch,btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        etEmployeeNumber=findViewById(R.id.etEmployeeNumber);
        etEmpName=findViewById(R.id.etEmpName);
        etEmpSalary=findViewById(R.id.etEmpSalary);
        etEmpAge=findViewById(R.id.etEmpAge);
        btnSrch=findViewById(R.id.btnSrch);
        btnUpdate=findViewById(R.id.btnUpdate);

        btnSrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

    }

    private void updateEmployee()
    {
        String name=etEmpName.getText().toString();
        Float salary=Float.parseFloat(etEmpSalary.getText().toString());
        int age =Integer.parseInt(etEmpAge.getText().toString());

        EmployeeCUD employee=new EmployeeCUD(name,salary,age);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);

        Call<Void> voidCall=employeeApi.updateEmployee(Integer.parseInt(etEmployeeNumber.getText().toString()),employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDeleteActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void loadData()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);

        Call<Employee>listCall=employeeApi.getEmployeeByID(Integer.parseInt(etEmployeeNumber.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(UpdateDeleteActivity.this,response.body().toString(), Toast.LENGTH_SHORT).show();
                String content="";
                content+=" ID : "+response.body().getId() + "\n";
                content+=" Name : "+response.body().getEmployee_name() + "\n";
                content+=" Age : "+response.body().getEmployee_age() + "\n";
                content+=" Salary : "+response.body().getEmployee_salary() + "\n";

                //tvSearchData.setText(content);
                etEmpName.setText(response.body().getEmployee_name());
                //etEmpSalary.setText(Float.parseFloat(response.body().getEmployee_salary()));
                etEmpAge.setText(response.body().getEmployee_age());

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
