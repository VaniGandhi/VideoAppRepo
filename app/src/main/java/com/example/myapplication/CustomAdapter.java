package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
    private List<Getpojo.DataBean> famousSpot;
    private Context context;
    public CustomAdapter(Context context,List<Getpojo.DataBean> famousSpot)
    {
        this.context=context;
        this.famousSpot=famousSpot;
    }


    @Override
    public CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.getrecycler_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CustomViewHolder holder, int position) {
        holder.name.setText(famousSpot.get(position).getName());
        holder.address.setText(famousSpot.get(position).getAddress());
        holder.description.setText(famousSpot.get(position).getDescription());





    }

    @Override
    public int getItemCount() {
        return  famousSpot.size();
    }

     public class  CustomViewHolder extends RecyclerView.ViewHolder{
        TextView name, address,description;
        ImageView image;
        public  final View mView;

        public CustomViewHolder( View itemView) {
            super(itemView);
           mView=itemView;
           name=mView.findViewById(R.id.name);
           address=mView.findViewById(R.id.address);
           description=mView.findViewById(R.id.description);
           image=mView.findViewById(R.id.image);


        }

         public void onBind(int position) {
         }
     }
}
