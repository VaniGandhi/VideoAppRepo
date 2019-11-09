package com.example.myapplication.ChatApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.RoundedCornersTransformation;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChattingActivity extends AppCompatActivity implements ValueEventListener {


    private RecyclerView recyclerView;
    private Recycler_viewAdapter recycler_viewAdapter;
    private inbox_recyclerviewadapter inbox_recyclerviewadapter;
    private List<UserChatApp > users;
    FirebaseUser firebaseUser;
     DatabaseReference databaseReference,databaseReference1,databaseReference2,databaseReference3;
     private  List<String> userlist;
     Intent intent;
    String userid1;
    String chatid;
    ImageView send_button;
    EditText message;

    CircleImageView image;
    TextView name;
    MessageAdapter messageAdapter;
    List<Chat_model> chat_modelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        Toolbar toolbar=findViewById(R.id.tbar);
        image = findViewById(R.id.userimg);
        name = findViewById(R.id.username);
        send_button = findViewById(R.id.send_button);
        message = findViewById(R.id.type_msg);
        /*recyclerView=findViewById(R.id.recycler_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        recyclerView=findViewById(R.id.recycler_msg1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        userlist=new ArrayList<>();
        intent=getIntent();
         userid1=intent.getStringExtra("userid");
        System.out.println("userid--------------->"+userid1);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference1=FirebaseDatabase.getInstance().getReference("messages");
        databaseReference3=databaseReference.getDatabase().getReference("chats/"+firebaseUser.getUid()).child(userid1);
     databaseReference3.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             chatid= (String) dataSnapshot.getValue();
             System.out.println("chatis------->"+chatid);


         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });

            getchatid();
      ShowUserDetails();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    private void ShowUserDetails() {
        System.out.println(" chat ID = " + chatid);
        Log.e("id", "" + chatid);

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userid1);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserChatApp user = dataSnapshot.getValue(UserChatApp.class);
                Log.e("name", "" + firebaseUser.getDisplayName());

                name.setText(user.getName());
                Picasso.with(getApplicationContext()).load(user.getImgurl()).
                        transform(new RoundedCornersTransformation(10, 10)).into(image);
                // readmessages(firebaseUser.getUid(),userid);
               readMessages(chatid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void readMessages(String id)
    {
        chat_modelList = new ArrayList<>();


        databaseReference2 = FirebaseDatabase.getInstance().getReference("messages").child(id);
        databaseReference2.addValueEventListener(this);

    }
    @Override
    public void onDataChange( DataSnapshot dataSnapshot) {
        chat_modelList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Chat_model chat_model = snapshot.getValue(Chat_model.class);
            chat_modelList.add(chat_model);

        }
        messageAdapter = new MessageAdapter(getApplicationContext(), chat_modelList);
        recyclerView.setAdapter(messageAdapter);


    }

    @Override
    public void onCancelled( DatabaseError databaseError) {

    }


    private String getchatid()
    {
        databaseReference3=databaseReference.getDatabase().getReference("chats/"+firebaseUser.getUid()).child(userid1);
        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                     chatid= (String) dataSnapshot.getValue();
                    System.out.println("chatis------->"+chatid);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return  chatid;
    }


}