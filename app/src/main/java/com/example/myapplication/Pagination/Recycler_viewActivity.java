package com.example.myapplication.Pagination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Recycler_viewActivity extends AppCompatActivity implements  View.OnClickListener   {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Boolean isScrolling=false;
    ArrayList<String> names;
    Paginator p = new Paginator();
    private int totalPages = Paginator.TOTAL_NUM_ITEMS / Paginator.ITEMS_PER_PAGE;
    private int currentPage = 0;
    Button prev, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);


       recyclerView =findViewById(R.id.recycler);
       next=findViewById(R.id.next);
       prev=findViewById(R.id.previous);
       next.setOnClickListener(this);
       prev.setOnClickListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), p.generatePage(currentPage)));


    }

    public  ArrayList<String> names()
    {
        /*ArrayList<Student> list=new ArrayList<>();
        list.add(new Student("1","vani",R.drawable.ic_person));
        list.add(new Student("2","simar",R.drawable.ic_person));
        list.add(new Student("3","ritesh",R.drawable.ic_person));
        list.add(new Student("4","sanil",R.drawable.ic_person));
        list.add(new Student("5","arti",R.drawable.ic_person));
        list.add(new Student("6","rohit",R.drawable.ic_person));
        list.add(new Student("7","utkarsh",R.drawable.ic_person));
        list.add(new Student("8","niharika",R.drawable.ic_person));
        list.add(new Student("9","pranjal",R.drawable.ic_person));
        list.add(new Student("10","rachit",R.drawable.ic_person));
        return  list;*/
        ArrayList<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        return  list;




    }

    @Override
    public void onClick(View view) {

    }

 /*   @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.previous:
                prevbutton();
            case R.id.next:
                nextbutton();
        }

    }

    private void nextbutton() {
        currentPage += 1;
        recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), p.generatePage(currentPage)));
        toggleButtons();

    }

    private void prevbutton() {
        currentPage -= 1;

        recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), p.generatePage(currentPage)));

        toggleButtons();
    }

    private void toggleButtons() {
        if (currentPage == totalPages) {
            next.setEnabled(false);
            prev.setEnabled(true);
        } else if (currentPage == 0) {
            prev.setEnabled(false);
            next.setEnabled(true);
        } else if (currentPage >= 1 && currentPage <= totalPages) {
            next.setEnabled(true);
            prev.setEnabled(true);
        }
    }*/
  /*  public void Scrolling()
    {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling=true;
                }

            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems=layoutManager.getChildCount();
                totalitems=layoutManager.getItemCount();

               scrollOutitems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
               
               if(isScrolling && (currentItems+scrollOutitems==totalitems))
                   
               {
                   isScrolling=false;
                   fetchdata();
               }
            }
        });
    }

    private void fetchdata() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++)
                {
                   // students.add(Math.floor(Math.random() * 100));
                    adapter.notifyDataSetChanged();
                }

            }
        },5000);
    }*/
}
