package org.toktakprogramming.cuetconnect1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentHome extends Fragment {
    RecyclerView recyclerView;
    TextView textView;
    Context context;
    RecyclerView.Adapter adapter;
    Map<String,String>pack=new HashMap<>();
    ProgressBar progressBar;
    String url="http://rakib.ourcuet.com/query.php";

    JSONArray response;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecyclerItem> item=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView= inflater.inflate(R.layout.fragment_home,container,false);
     recyclerView=rootView.findViewById(R.id.recyclerview);
     textView=rootView.findViewById(R.id.text);
     recyclerView.setHasFixedSize(true);
     progressBar=rootView.findViewById(R.id.progressbar);
     progressBar.setVisibility(View.GONE);
     layoutManager=new LinearLayoutManager(context);
     recyclerView.setLayoutManager(layoutManager);
     pack.put("query","select * From post_table;");


     textView.setOnTouchListener(new View.OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
             Intent intent =new Intent( getActivity(),post.class);
             startActivity(intent);

            return  true;
         }
     });



        PushOrParse.getDataFromServer(url,pack,getActivity());

            fetchdata();










         return rootView;


    }

    private void fetchdata(){
          progressBar.setVisibility(View.VISIBLE);



        final  Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                response=PushOrParse.deliverData();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject j=response.getJSONObject(i);
                        RecyclerItem it=new RecyclerItem(j.getString("post"),j.getString("poster_id"),j.getString("poster_name"),j.getString("like_count"));

                        item.add(it);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter=new RecycleAdapter(item,getActivity());
                recyclerView.setAdapter(adapter);
            }
        },3000);
    }
}
