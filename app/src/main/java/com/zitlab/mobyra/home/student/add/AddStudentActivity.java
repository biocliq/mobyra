package com.zitlab.mobyra.home.student.add;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zitlab.mobyra.MobyraInstance;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.student.Student;
import com.zitlab.mobyra.library.MobyraClient;

public class AddStudentActivity extends AppCompatActivity {
    MobyraInstance mobyraInstance;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mobyraInstance = MobyraInstance.getInstance();
        pd = new ProgressDialog(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        EditText studentCode = findViewById(R.id.studentCode);
        EditText studentName = findViewById(R.id.studentName);
        EditText studentClass = findViewById(R.id.studentClass);
        EditText studentDOB = findViewById(R.id.studentDOB);

//        Date date = new Date();
//        long timeInMillis = date.getTime();
//        studentCode.setText("SD" + timeInMillis);

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(v -> {
            pd.show();
            MobyraClient client = mobyraInstance.client();
            String studentNameStr = studentName.getText().toString();
            String studentCodeStr = studentCode.getText().toString();
            String studentClassStr = studentClass.getText().toString();
            String studentDOBStr = studentDOB.getText().toString();
            if (null != studentNameStr && null != studentCodeStr && null != studentClassStr && null != studentDOBStr) {
                Student student = new Student();
                student.setStudentCode(studentCodeStr);
                student.setStudentName(studentNameStr);
                student.setStudentClass(studentClassStr);
                student.setDob(studentDOBStr);
                client.save(student, Student.class, (status, response, exception) -> {
                    pd.dismiss();
                    Intent intent = getIntent();
                    if (status.isStatus()) {
                        intent.putExtra("status", true);
                        setResult(RESULT_OK, intent);
                        Toast.makeText(this, "Student saved Successfully.", Toast.LENGTH_SHORT).show();
                    } else {
                        intent.putExtra("status", false);
                        setResult(RESULT_CANCELED, intent);
                        Toast.makeText(this, "Error in saving student information: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    finish();
                });
            } else {
                Toast.makeText(this, "Invalid Input data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}