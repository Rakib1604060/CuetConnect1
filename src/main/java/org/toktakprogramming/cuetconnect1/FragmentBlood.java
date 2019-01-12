package org.toktakprogramming.cuetconnect1;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class FragmentBlood extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<BloodItem>bloodItems= new ArrayList<>();
    String url="http://rakib.ourcuet.com/query.php";
    private JSONArray array;
     ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View V= inflater.inflate(R.layout.fragment_blood,container,false);
       recyclerView=V.findViewById(R.id.bloodrecyclerview);
       progressBar=V.findViewById(R.id.progress_blood);
       progressBar.setVisibility(View.GONE);

       Map<String ,String>map=new HashMap<>();
       map.put("query","select * From blood_table;");
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       PushOrParse.getDataFromServer(url,map,getActivity());
       fetchData();


       return  V;
    }

    void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                array=PushOrParse.deliverData();
                Toast.makeText(getActivity(),array.toString(),Toast.LENGTH_LONG).show();
                  for (int i=0;i<array.length();i++){

                    try {
                        JSONObject j=array.getJSONObject(i);
                        BloodItem b=new BloodItem(j.getString("user_name"),j.getString("blood_group"),j.getString("user_name"));
                        bloodItems.add(b);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                progressBar.setVisibility(View.GONE);
                adapter=new BloodRecyclerAdapter(bloodItems,getActivity());
                recyclerView.setAdapter(adapter);
            }
        },3000);





    }
}
