package org.toktakprogramming.cuetconnect1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentMenu extends Fragment {
    ListView listView;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_menus,container,false);

        listView=(ListView)rootView.findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Search Blood");
        arrayList.add("Truck Cuet Bus");
        arrayList.add("Emergency");
        arrayList.add("Notes");
        arrayList.add("AntiRagging");
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                   case 0:
                   FragmentTransaction ft=getFragmentManager().beginTransaction();
                   ft.replace(R.id.fragment_Container,new FragmentBlood())
                   .addToBackStack(null);
                   ft.commit();

                      break;
                  case 1:
                      break;
                  case 2:
                      break;
                  case 3:
                      break;
              }
            }
        });


    return  rootView;}
}
