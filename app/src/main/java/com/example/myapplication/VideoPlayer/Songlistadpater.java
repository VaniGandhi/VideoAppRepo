package com.example.myapplication.VideoPlayer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ChatApp.Recycler_viewAdapter;
import com.example.myapplication.ChatApp.UserChatApp;
import com.example.myapplication.R;
import com.example.myapplication.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Songlistadpater  extends  RecyclerView.Adapter<Songlistadpater.ViewHolder> {

    private Context context;
    private List<Video_model> video_model;
    private Recyclerviewclicklistener recyclerviewclicklistener;

    public Songlistadpater(Context context, List<Video_model> video_model) {
        this.context = context;
        this.video_model = video_model;
        //this.recyclerviewclicklistener=recyclerviewclicklistener;
    }

    @NonNull
    @Override
    public Songlistadpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.songlistitem,parent,false);

        return new Songlistadpater.ViewHolder(view,recyclerviewclicklistener);


    }

    @Override
    public void onBindViewHolder(final Songlistadpater.ViewHolder holder, final int position) {

        final Video_model vv=video_model.get(position);
        System.out.println(vv.getName());
        holder.video_name.setText(vv.getName()+"");
      holder.video_img.setImageBitmap(vv.getThumbnail());
      holder.delete_button.setVisibility(View.VISIBLE);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Intent intent=new Intent(context,VideoPlayerScreen.class);
              intent.putExtra("video",vv.getVideo());
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(intent);



          }
      });
      holder.delete_button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              video_model.remove(position);
              notifyItemRemoved(position);
              Intent intent1=new Intent(context,SongListScreen.class);
              intent1.putExtra("id",vv.getName());
              intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(intent1);
          }
      });





    }



    @Override
    public int getItemCount() {
        return video_model.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public TextView video_name;
        public ImageView video_img,delete_button;
        Recyclerviewclicklistener recyclerviewclicklistener;




        public ViewHolder(@NonNull View itemView ,Recyclerviewclicklistener recyclerviewclicklistener) {
            super(itemView);
            video_img=itemView.findViewById(R.id.video_img);
            video_name=itemView.findViewById(R.id.nameofvideo);
            delete_button=itemView.findViewById(R.id.delete_icon);
      //      delete_button.setVisibility(View.GONE);
            this.recyclerviewclicklistener=recyclerviewclicklistener;
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
         recyclerviewclicklistener.onClick(getAdapterPosition());

        }
    }
    public interface Recyclerviewclicklistener {

        void onClick(int position);
    }

}
