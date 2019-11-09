package com.example.myapplication.ChatApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class inbox_recyclerviewadapter extends RecyclerView.Adapter<inbox_recyclerviewadapter.ViewHolder> {
    private Context context;
    private List<InboxModel> inbox;

    public inbox_recyclerviewadapter(Context context, List<InboxModel> inbox) {
        this.context = context;
        this.inbox = inbox;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.inbox_layout,parent,false);
        return new inbox_recyclerviewadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        final InboxModel user=inbox.get(position);
      holder.user_name.setText(user.getReciever().getName());
        Picasso.with(context).load(user.getReciever().getImgurl()).
                transform(new RoundedCornersTransformation(10,10)).into(holder.profile_img);

      holder.last_message.setText(user.getLastmessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent=new Intent(context,.class);
                Intent intent=new Intent(context,Message_Activity.class);
                intent.putExtra("userid", user.getReciever().getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return inbox.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView user_name,last_message;
        public CircleImageView profile_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.nameofuser);
            profile_img=itemView.findViewById(R.id.user_img);
            last_message=itemView.findViewById(R.id.last_message);
        }
    }



}
