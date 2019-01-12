package org.toktakprogramming.cuetconnect1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    Button LoginButton;
    EditText email_t,pass_t,id_t;
    TextView forgotPass,createnewAccount;
    AlertDialog.Builder builder;
    SharedPreferences login;
    String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_t=(EditText)findViewById(R.id.email_id);
        pass_t=findViewById(R.id.password_id);
        LoginButton=findViewById(R.id.Loginbutton);
        forgotPass=findViewById(R.id.forgotPassword_id);
        createnewAccount=findViewById(R.id.newaccount);
        id_t=findViewById(R.id.id_1);
        builder = new AlertDialog.Builder(this);


         createnewAccount.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 Intent intent=new Intent(login.this,Registration.class);
                 startActivity(intent);
                 return  false;
             }
         });
         LoginButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(email_t.getText().equals("")||pass_t.getText().equals("")||id_t.getText().equals("")){
                     Toast.makeText(login.this, "Please insert All information", Toast.LENGTH_SHORT).show();
                  }
                 else
                 {
                     query="Select * From Registration where id like '"+id_t.getText().toString()+"',and password like '"+pass_t.getText().toString()+"' and email like'"+email_t.getText().toString()+"';";
                     pushData();
                 }
             }
         });


    }
    private  void pushData(){
        String url = "http://rakib.ourcuet.com/query.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                builder.setTitle("Login info:");
                builder.setMessage("Successfully Loged in");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        login = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = login.edit();
                        editor.putBoolean("loged", true);
                        editor.putString("email",email_t.getText().toString());
                        editor.putString("id",id_t.getText().toString());
                        editor.commit();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Error", Toast.LENGTH_SHORT).show();
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
        MySingleton.getmInstance(login.this).AddToRequestQueue(stringRequest);



    }

}
