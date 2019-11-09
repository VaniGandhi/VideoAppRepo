package com.example.myapplication.Pagination;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Getpojo;
import com.example.myapplication.R;
import com.example.myapplication.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<String> name;
    public CustomAdapter( Context context ,ArrayList<String>name)
    {
        this.context=context;
        this.name=name;
    }



    @Override
    public CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.student, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CustomViewHolder holder, int position) {
       // Student student=students.get(position);
        holder.name.setText(name.get(position));
       // holder.id.setText(student.getRollno());Picasso.get().load(student.getImage()).
               // transform(new RoundedCornersTransformation(10,10)).into(holder.image);
      //  Log.e("image",""+student.getImage());
       //holder.imag,e.setText(famousSpot.get(position).getDescription());





    }

    @Override
    public int getItemCount() {
        return  name.size();
    }

     public  class  CustomViewHolder extends RecyclerView.ViewHolder{
        TextView name;
                /*id;
        ImageView image;*/
        public  final View mView;

        public CustomViewHolder( View itemView) {
            super(itemView);
           mView=itemView;
           name=mView.findViewById(R.id.name);
          /* id=mView.findViewById(R.id.id);

           image=mView.findViewById(R.id.image);
*/


        }


     }
}
