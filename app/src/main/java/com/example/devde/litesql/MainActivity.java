package com.example.devde.litesql;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText firstname,lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        textView = findViewById(R.id.textview);

        controller = new DB_Controller(this,"",null,1);
    }

    public void btn_click(View view){
        switch (view.getId()){
            case R.id.add_student:
                try {
                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.delete_student:
                try {
                    controller.delete_student(firstname.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.update_student:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER NEW FIRST NAME");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.update_student(firstname.getText().toString(),new_firstname.getText().toString());
                    }
                });
                dialog.show();

                break;
            case R.id.list_show:
                controller.list_all_students(textView);
                break;

        }

    }
}
