package edu.bluejack22_1.GMoneysoLVer.activity.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.bluejack22_1.GMoneysoLVer.HomeActivity;
import edu.bluejack22_1.GMoneysoLVer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddCategoryActivity extends AppCompatActivity {

    EditText et_category_name;
    RadioButton rb_income, rb_expense;
    Button btn_save_category;
    ProgressDialog progressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category2);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Category");
        et_category_name = findViewById(R.id.et_category_name);
        rb_expense = findViewById(R.id.rb_expense);
        rb_income = findViewById(R.id.rb_income);
        btn_save_category = findViewById(R.id.btn_save_category);

        progressDialog = new ProgressDialog(AddCategoryActivity.this);
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setMessage(getString(R.string.saving));

        btn_save_category.setOnClickListener(x -> {
            String categoryName = et_category_name.getText().toString().trim();
            String categoryType;
            if(rb_income.isChecked()) {
                categoryType = "income";
            } else if (rb_expense.isChecked()){
                categoryType = "expense";
            } else {
                return;
            }

            saveData(categoryName, categoryType);
        });
    }

    private void saveData(String categoryName, String categoryType){
        Map<String, Object> category = new HashMap<>();
        category.put("categoryName", categoryName);
        category.put("categoryType", categoryType);

        progressDialog.show();

        db.collection("categories")
                .add(category)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent  = new Intent(AddCategoryActivity.this, HomeActivity.class);
        intent.putExtra("fragmentToGo","category");
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}