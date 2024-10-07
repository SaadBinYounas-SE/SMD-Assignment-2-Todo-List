package com.example.smda2todolist;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddToDoFragment extends Fragment {

    private EditText etTaskName, etTaskDescription;
    private Button btnSave;
    private OnTaskSaveListener taskSaveListener;

    public interface OnTaskSaveListener {
        void onTaskSave(String name, String description);
    }

    public AddToDoFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskSaveListener) {
            taskSaveListener = (OnTaskSaveListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTaskSaveListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_do, container, false);

        etTaskName = view.findViewById(R.id.etTaskName);
        etTaskDescription = view.findViewById(R.id.etTaskDescription);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = etTaskName.getText().toString();
            String description = etTaskDescription.getText().toString();

            if (taskSaveListener != null) {
                taskSaveListener.onTaskSave(name, description);
            }
            getActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        taskSaveListener = null;
    }
}
