package com.example.myapplication.ChatApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ChatApp.Notifications.Token_model;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class All_users_with_chats extends AppCompatActivity {

    ImageView add_img;
    private RecyclerView recyclerView;
    private Recycler_viewAdapter recycler_viewAdapter;
    private inbox_recyclerviewadapter inbox_recyclerviewadapter;
    private List<InboxModel> users;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference, databaseReference1, databaseReference2, databaseReference3, databaseReference4, reference;
    private List<String> userlist;
    Intent intent;
    String userid;
    String chatid;
    String last_message;
    Boolean hashkey=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_user_with_chats);
        Toolbar toolbar = findViewById(R.id.tbar);
        recyclerView = findViewById(R.id.recycler_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        userlist = new ArrayList<>();

        getdata();

        //     getchatid();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat App");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        add_img = findViewById(R.id.add_circle);
        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(All_users_with_chats.this, All_users_Activity.class);
                startActivity(intent);

            }
        });
        updateToken(FirebaseInstanceId.getInstance().getToken());


    }

    private void getdata() {


        databaseReference1 = databaseReference.getDatabase().getReference("inbox").child(firebaseUser.getUid());
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 {
                     users = new ArrayList<>();
                     
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        InboxModel inboxModel = new InboxModel();
                        Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();
                        String chatid = snapshot.getKey();
                        inboxModel.setChatid(chatid);
                        if (snapshot.hasChild("lastmessage")) {
                            String message = snapshot.child("lastmessage").getValue().toString();
                            inboxModel.setLastmessage(message);
                        }
                        System.out.println("dataSnapshot = " + newPost.entrySet());

                        for (Map.Entry<String, Object> data :
                                newPost.entrySet()) {
                            System.out.println("data Key= " + data.getKey());
                            System.out.println("data Value= " + data.getValue());

                            if (!data.getKey().equals("lastmessage")) {

                                Map<String, Object> userDetails = (Map<String, Object>) data.getValue();


                                String email = String.valueOf(userDetails.get("email"));
                                String name = String.valueOf(userDetails.get("name"));
                                String imgurl = String.valueOf(userDetails.get("imgurl"));
                                String uid = String.valueOf(userDetails.get("uid"));

                                InboxModel.ReceiverDetails receiverDetails = new InboxModel.ReceiverDetails();
                                receiverDetails.setName(name);
                                receiverDetails.setEmail(email);
                                receiverDetails.setImgurl(imgurl);
                                receiverDetails.setUid(uid);
                                inboxModel.setReciever(receiverDetails);


                            }


                        }
                        users.add(inboxModel);


                    }
                }

            inbox_recyclerviewadapter=new inbox_recyclerviewadapter(getApplicationContext(),users);
              recyclerView.setAdapter(inbox_recyclerviewadapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



   /* private void  allusers()
    {
        users=new ArrayList<>();
        databaseReference2=databaseReference.getDatabase().getReference("users");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                { UserChatApp userChatApp=snapshot.getValue(UserChatApp.class);
                    if(!firebaseUser.getUid().equals(userChatApp.getUid()))
                    {
                        userid1=userChatApp.getUid();



                        users.add(userChatApp);


                    }


                }

                inbox_recyclerviewadapter=new inbox_recyclerviewadapter(getApplicationContext(),users);
                recyclerView.setAdapter(inbox_recyclerviewadapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    // getchatid();





   private void updateToken( String Token)
    {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Tokens");
        Token_model token=new Token_model(Token);
        databaseReference.child(firebaseUser.getUid()).setValue(token);

    }

/*

    private void getlastmessage()
    {
        databaseReference1=databaseReference.getDatabase().getReference("messages/"+chatid);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Chat_model chat_model=snapshot.getValue(Chat_model.class);
                    last_message=chat_model.getMessage();

                    System.out.println("lastmessage------------->"+last_message);
                    Toast.makeText(getApplicationContext()," "+last_message,Toast.LENGTH_LONG).show();
                    databaseReference4=databaseReference.getDatabase().getReference();
                    databaseReference.child("inbox").child(chatid).setValue(last_message);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        inbox_recyclerviewadapter=new inbox_recyclerviewadapter(getApplicationContext(),users);
        recyclerView.setAdapter(inbox_recyclerviewadapter);
    }
*/


}
