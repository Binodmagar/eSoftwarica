package com.binod.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.esoftwarica.EditStudent;
import com.binod.esoftwarica.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    Context context;
    List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_layout, parent, false);
        return new StudentViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, final int position) {

        Student student = studentList.get(position);
        holder.tvName.setText(student.getName());
        holder.tvAddress.setText(student.getAddress());
        holder.tvGender.setText(student.getGender());
        holder.tvAge.setText(Integer.toString(student.getAge()));
        holder.imgEdit.setImageDrawable(context.getResources().getDrawable(R.drawable.edit));
        holder.imgDelete.setImageDrawable(context.getResources().getDrawable(R.drawable.delete));

        String gender = student.getGender();
        if(gender == "Male"){
            holder.imgProfile.setImageResource(R.drawable.male);
        }else if(gender == "Female"){
            holder.imgProfile.setImageResource(R.drawable.female);
        }else if(gender == "Other"){
            holder.imgProfile.setImageResource(R.drawable.other);
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student studentdel = studentList.get(position);
                studentList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, studentdel + " is removed", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student studentEdit = studentList.get(position);
                int index = studentList.indexOf(studentEdit);
                EditStudent.index = index;
                Intent intent = new Intent(context, EditStudent.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvAge, tvAddress, tvGender;
        ImageView imgProfile, imgEdit, imgDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvGender = itemView.findViewById(R.id.tvGender);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
    }

}
