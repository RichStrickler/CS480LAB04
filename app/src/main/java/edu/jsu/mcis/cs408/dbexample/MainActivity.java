package edu.jsu.mcis.cs408.dbexample;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.jsu.mcis.cs408.dbexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        db = new DatabaseHandler(this, null, null, 1);
        updateRecyclerView();

        binding.addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMemo();
            }
        });

        binding.delBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMemo();
            }
        });

    }

    public void addNewMemo() {
        String memo = binding.addmemoInput.getText().toString();
        db.addMemo(new Memo(memo));
        updateRecyclerView();
    }
    public void deleteMemo() {
        String id = "0";
        if (!binding.delmemoInput.getText().toString().isEmpty()){
            id = binding.delmemoInput.getText().toString();
        }

        db.removeMemo(id);
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(db.getAllMemosAsStringList());

        binding.output.setHasFixedSize(true);
        binding.output.setLayoutManager(new LinearLayoutManager(this));
        binding.output.setAdapter(adapter);
    }

}