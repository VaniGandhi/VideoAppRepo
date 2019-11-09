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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Recycler_viewAdapter  extends RecyclerView.Adapter<Recycler_viewAdapter.ViewHolder> {
    private Context context;
    private List<UserChatApp> userChatApp;

    public Recycler_viewAdapter(Context context, List<UserChatApp> userChatApp) {
        this.context = context;
        this.userChatApp = userChatApp;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chatapp_recyclerview_layout,parent,false);
        return new Recycler_viewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        final UserChatApp user=userChatApp.get(position);
        holder.user_name.setText(user.getName());
        Picasso.with(context).load(user.getImgurl()).
                transform(new RoundedCornersTransformation(10,10)).into(holder.profile_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Message_Activity.class);
              //  Intent intent1=new Intent(context,ChattingActivity.class);
            //    intent1.putExtra("userid1",user.getUid());
               intent.putExtra("userid", user.getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
             //   context.startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userChatApp.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView  user_name,last_message;
        public CircleImageView profile_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.nameofuser);
            profile_img=itemView.findViewById(R.id.user_img);
            last_message=itemView.findViewById(R.id.last_message);
        }
    }
}
