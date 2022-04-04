package edu.jsu.mcis.cs408.memopad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import edu.jsu.mcis.cs408.memopad.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        Button adder = binding.btnAdd;
        adder.setOnClickListener(this);

        Button deleter = binding.btnDelete;
        deleter.setOnClickListener(this);
    }
    public void addNewMemo() {
        String memo = binding.newMemo.getText().toString();
        db.addMemo(new Note(memo));
        updateRecyclerView();
    }
    public void deleteExistingMemo(){
        String memo = binding.newMemo.getText().toString();
        db.deleteMemo(new Note(memo));
        updateRecyclerView();
    }
    private void updateRecyclerView(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(db.getAllMemosAsList());
        binding.memoView.setHasFixedSize(true);
        binding.memoView.setLayoutManager(new LinearLayoutManager(this));
        binding.memoView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                addNewMemo();
            case R.id.btnDelete:
                deleteExistingMemo();


        }
    }
}