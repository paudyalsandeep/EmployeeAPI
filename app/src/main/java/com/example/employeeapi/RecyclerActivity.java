package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeApi;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerActivity extends AppCompatActivity {

    List<Employee> employeeList;
    RecyclerView recyclerView;
    EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        employeeList=new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        employeeAdapter=new EmployeeAdapter(getApplicationContext(),employeeList);
        recyclerView.setAdapter(employeeAdapter);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeApi employeeApi=retrofit.create(EmployeeApi.class);
        Call<List<Employee>>listCall=employeeApi.getAllEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful())
                {
                    Toast.makeText(RecyclerActivity.this, "Error"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                employeeList=response.body();


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("My message","onFaliure"+t.getLocalizedMessage());
                Toast.makeText(RecyclerActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
