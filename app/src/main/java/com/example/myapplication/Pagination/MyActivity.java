package com.example.myapplication.Pagination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyActivity extends AppCompatActivity {

    List<Item > items=new ArrayList<>();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_my);

        random10Data();

        RecyclerView recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(recyclerView,this,items);
        recyclerView.setAdapter(adapter);

        adapter.setLoadmore(new Loadmore() {
            @Override
            public void onloadmore() {
                if(items.size()<=50)
                {
                   items.add(null);
                   adapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        items.remove(items.size()-1);
                           adapter.notifyItemRemoved(items.size());
                            int index=items.size();
                            int end=index+10;
                            for(int i=index;i<end;i++)
                            {
                                String name= UUID.randomUUID().toString();
                                //Item item=new Item(name,name.length());
                                Item item=new Item(name,i);
                                items.add(item);

                            }
                          adapter.notifyDataSetChanged();
                            adapter.setLoaded();

                        }
                    },5000);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"end",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void random10Data() {

        for(int i=10;i<=10;i++)
        {
            String name= UUID.randomUUID().toString();
            Item item=new Item(name,i);
            items.add(item);

        }
    }
}
