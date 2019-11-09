package com.example.myapplication.Pagination;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PaginationScrollListener;
import com.example.myapplication.R;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;

    public LoadingViewHolder( View itemView) {
        super(itemView);
        progressBar=itemView.findViewById(R.id.progressbar);
    }
}
class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView name,length;

    public ItemViewHolder( View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.textname);
        length=(TextView)itemView.findViewById(R.id.textlength);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  final  int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    Loadmore loadmore;
    boolean isloading;
    Activity activity;
    List<Item> items;
    int visiblethreshold=5;
    int lastvisibleitem, totalitemcount;
    public MyAdapter( RecyclerView recyclerView ,Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
         final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalitemcount=linearLayoutManager.getItemCount();
                lastvisibleitem=linearLayoutManager.findLastVisibleItemPosition();

                if(!isloading && totalitemcount<=(lastvisibleitem+visiblethreshold))
                {
                    if(loadmore !=null)
                        loadmore.onloadmore();

                    isloading=true;

                }



            }
        });
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        if(viewType==VIEW_TYPE_ITEM)
        {
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(view);
        }
        else if(viewType==VIEW_TYPE_LOADING)
            {
                View view = LayoutInflater.from(activity)
                        .inflate(R.layout.item_loading, parent, false);
                return new LoadingViewHolder(view);
            }

            return  null;



    }




    @Override
    public int getItemViewType(int position) {
        return items.get(position)==null? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
       /* if (position == items.size() - 1 && isloading)
        {
         return VIEW_TYPE_LOADING;
        }
        else {
            return VIEW_TYPE_ITEM;
        }*/
    }

    public void setLoadmore(Loadmore loadmore) {
        this.loadmore = loadmore;
    }


    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ItemViewHolder){
            Item item=items.get(position);
            ItemViewHolder viewHolder=(ItemViewHolder) holder;
            viewHolder.name.setText(items.get(position).getName());
            viewHolder.length.setText(String.valueOf(items.get(position).getLength()));
        }
        else if (holder instanceof LoadingViewHolder)
        {
            LoadingViewHolder loadingViewHolder=(LoadingViewHolder) holder;
           loadingViewHolder.progressBar.setIndeterminate(true);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        this.isloading = false;
    }
}
