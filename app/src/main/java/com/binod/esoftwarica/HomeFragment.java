package com.binod.esoftwarica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.adapter.StudentAdapter;

public class HomeFragment extends Fragment  {
    RecyclerView RecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

         RecyclerView = view.findViewById(R.id.RecycleView);

        if(MainActivity.studentLists.isEmpty()){
            Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
        }else {
            StudentAdapter studentAdapter = new StudentAdapter(getContext(), MainActivity.studentLists);
            RecyclerView.setAdapter(studentAdapter);
            RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }
}
