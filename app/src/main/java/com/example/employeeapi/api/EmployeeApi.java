package com.example.employeeapi.api;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeApi {

    //get all the employees
    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    //Get employee on the basis of EmpID
    @GET("employee/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID") int empID);

    //register employee
    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

    //update employee
    @PUT("update/{empID}")
    Call<Void>updateEmployee(@Path("empID") int empID,@Body EmployeeCUD emp);
}
