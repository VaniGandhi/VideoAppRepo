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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static  final int MSG_TYPE_LEFT=0;
    public static  final int MSG_TYPE_RIGHT=1;

    private Context context;
    private List<Chat_model> chatModelList;
  //  private String imageUrl;
    FirebaseUser firebaseUser;

    public MessageAdapter(Context context, List<Chat_model> chatModelList) {
        this.context = context;
        this.chatModelList = chatModelList;
    //    this.imageUrl=imageUrl;
    }


    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
        else

        {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
            Chat_model chat_model=chatModelList.get(position);
            holder.show_msg.setText(chat_model.getMessage());
         //   Picasso.with(context).load(imageUrl).transform(new RoundedCornersTransformation(10,10)).into();



    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView show_msg;
        public CircleImageView profile_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_msg=itemView.findViewById(R.id.chat_item_left_textview);
            //profile_img=itemView.findViewById(R.id.chat_item_left_profilepic);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(!chatModelList.get(position).getReciever().equals(firebaseUser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return  MSG_TYPE_LEFT;
        }
    }




}

