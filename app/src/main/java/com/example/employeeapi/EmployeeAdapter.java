package com.example.employeeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeapi.model.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context mContext;
    List<Employee>employeeList;

    public EmployeeAdapter(Context mContext, List<Employee> employeeList) {
        this.mContext = mContext;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employeeview,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee=employeeList.get(position);

        holder.tvDisplayID.setText(employee.getId());
        holder.tvDisplayName.setText(employee.getEmployee_name());
        holder.tvDisplaySalary.setText(Float.toString(employee.getEmployee_salary()));
        holder.tvDisplayAge.setText(employee.getEmployee_age());
        holder.tvDisplayimage.setText(employee.getProfile_image());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvDisplayID,tvDisplayName,tvDisplaySalary,tvDisplayAge,tvDisplayimage;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDisplayID=itemView.findViewById(R.id.tvDisplayID);
            tvDisplayName=itemView.findViewById(R.id.tvDisplayName);
            tvDisplaySalary=itemView.findViewById(R.id.tvDisplaySalary);
            tvDisplayAge=itemView.findViewById(R.id.tvDisplayAge);
            tvDisplayimage=itemView.findViewById(R.id.tvDisplayImage);
        }
    }
}
