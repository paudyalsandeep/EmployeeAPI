package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeApi;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {

    private EditText etEmpNo;
    private Button btnSearch;
    private TextView tvSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etEmpNo=findViewById(R.id.etEmployeeNo);
        btnSearch=findViewById(R.id.btnSearch);
        tvSearchData=findViewById(R.id.tvSearchData);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    loadData();

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

        Call<Employee>listCall=employeeApi.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmployeeActivity.this,response.body().toString(), Toast.LENGTH_SHORT).show();
                String content="";
                content+=" ID : "+response.body().getId() + "\n";
                content+=" Name : "+response.body().getEmployee_name() + "\n";
                content+=" Age : "+response.body().getEmployee_age() + "\n";
                content+=" Salary : "+response.body().getEmployee_salary() + "\n";

                tvSearchData.setText(content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmployeeActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
