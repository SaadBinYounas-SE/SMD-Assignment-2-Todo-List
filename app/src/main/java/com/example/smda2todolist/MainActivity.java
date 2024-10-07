package com.example.smda2todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

// MainActivity.java
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddToDoFragment.OnTaskSaveListener {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private FloatingActionButton fabAdd;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvTodos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabAdd = findViewById(R.id.fabAdd);

        taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", "Description for Task 1"));
        taskList.add(new Task("Task 2", "Description for Task 2"));
        taskList.add(new Task("Task 3", "Description for Task 3"));
        taskList.add(new Task("Task 4", "Description for Task 4"));
        taskList.add(new Task("Task 5", "Description for Task 5"));
        taskList.add(new Task("fanciness", "ALready Done"));

        taskAdapter = new TaskAdapter(this, taskList);
        recyclerView.setAdapter(taskAdapter);

        fabAdd.setOnClickListener(v -> {
            AddToDoFragment addTaskFragment = new AddToDoFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, addTaskFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

    }



    @Override
    public void onTaskSave(String name, String description) {


        Task newTask = new Task(name, description);
        taskList.add(newTask);

        taskAdapter.notifyDataSetChanged();
    }
}
