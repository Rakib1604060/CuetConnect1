package org.toktakprogramming.cuetconnect1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    Button submitbutton;
    Spinner bloodSpin;
    CheckBox checkBox;
    EditText email, password, password2, id,name;
    SharedPreferences login;
    String Email, Password, Password2, Blood, Id;
    ArrayAdapter<CharSequence> arrayAdapter;
    String url, query;
    AlertDialog.Builder builder;
    SharedPreferences details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submitbutton = findViewById(R.id.submitButton);
        bloodSpin = findViewById(R.id.spinner);
        checkBox = findViewById(R.id.checkBox);
        email = findViewById(R.id.email);
        name=findViewById(R.id.name);
        password = findViewById(R.id.password);
        id = findViewById(R.id.id_id);
        builder = new AlertDialog.Builder(Registration.this);
        password2 = findViewById(R.id.password2);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.bloodGroup, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodSpin.setAdapter(arrayAdapter);
        bloodSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Blood = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Blood = "";
            }
        });


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().equals("") || password.getText().equals("") || password2.getText().equals("") || id.getText().equals("")||name.getText().equals("")) {
                    Toast.makeText(Registration.this, "Please Insert All information", Toast.LENGTH_SHORT).show();
                } else {

                    if (!checkBox.isChecked()) {
                        Toast.makeText(Registration.this, "Check the tick box", Toast.LENGTH_SHORT).show();
                    } else {
                        Email = email.getText().toString();
                        Password = password.getText().toString();
                        Id = id.getText().toString();
                        url = "http://rakib.ourcuet.com/query.php";
                        query = "Insert into Registration (id,email,Name,password,bloodGroup)values('" + Id + "','" + Email + "','" +name.getText().toString()+"','"+ Password + "','" + Blood + "')";
                        pushData();

                    }


                }


            }
        });


    }

    private void pushData(){
        String url = "http://rakib.ourcuet.com/query.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                builder.setTitle("Registration info:");
                builder.setMessage(response);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        details = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = details.edit();
                        editor.putString("name",name.getText().toString());
                        editor.commit();

                        Intent intent = new Intent(Registration.this, login.class);
                        finish();
                        startActivity(intent);

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registration.this, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> regPack = new HashMap<String, String>();
                regPack.put("query", query);
                return regPack;
            }

        };
        MySingleton.getmInstance(Registration.this).AddToRequestQueue(stringRequest);


    }



}