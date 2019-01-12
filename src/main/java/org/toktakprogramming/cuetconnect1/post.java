package org.toktakprogramming.cuetconnect1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class post extends AppCompatActivity {
      EditText post ;
      Button btn;
      AlertDialog.Builder builder;
      SharedPreferences sharedPreferences;
      private  String query,id,name,Post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        post=findViewById(R.id.editpost);
        btn=findViewById(R.id.postButton);
        builder=new AlertDialog.Builder(post.this);
        sharedPreferences=getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        id=sharedPreferences.getString("id","Nullid");
        name=sharedPreferences.getString("name","nullName");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushData();

            }
        });


    }

    private void pushData(){
        String url = "http://rakib.ourcuet.com/query.php";
         query= "Insert into post_table (poster_id,poster_name,post)values('"+id+"','" +name+"','"+post.getText().toString()+"')";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                builder.setTitle("Post info:");
                builder.setMessage("Post Successful\n");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent = new Intent(post.this, MainActivity.class);
                         finish();
                         startActivity(intent);

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(post.this, MainActivity.class);

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
                Toast.makeText(post.this, "Error", Toast.LENGTH_SHORT).show();
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
        MySingleton.getmInstance(post.this).AddToRequestQueue(stringRequest);


    }
}
