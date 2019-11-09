package com.example.myapplication.ChatApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ChatApp.Notifications.Client;
import com.example.myapplication.ChatApp.Notifications.Data_model;
import com.example.myapplication.ChatApp.Notifications.Myresponse;
import com.example.myapplication.ChatApp.Notifications.Sender;
import com.example.myapplication.ChatApp.Notifications.Token_model;
import com.example.myapplication.R;
import com.example.myapplication.RoundedCornersTransformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Activity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    CircleImageView image;
    TextView name;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference, databaseReference1, databaseReference2,
            reference, databaseReference3, databaseReference4;
    Intent intent;
    ImageView send_button;
    EditText message;
    String userid;
    private String chatid, newchatid;
    String key;
    String textmessage;
    MessageAdapter messageAdapter;
    List<Chat_model> chat_modelList = new ArrayList<>();
    RecyclerView recyclerView;
    HashMap<String, Object> hashMap;
    APIservice apIservice;
    boolean notify = false, hasKey = false;
    String last_message;
    String token1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_);
        Toolbar toolbar = findViewById(R.id.tbar);
        setSupportActionBar(toolbar);
        image = findViewById(R.id.userimg);
        name = findViewById(R.id.username);
        send_button = findViewById(R.id.send_button);
        message = findViewById(R.id.type_msg);


        apIservice = Client.getClient("https://fcm.googleapis.com/fcm/send/").create(APIservice.class);


        recyclerView = findViewById(R.id.recycler_msg);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        intent = getIntent();
        send_button.setOnClickListener(this);
        userid = intent.getStringExtra("userid");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference1 = databaseReference.getDatabase().getReference("chats/" + firebaseUser.getUid()).child(userid);


        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Message_Activity.onDataChange---->" + dataSnapshot.getValue());
                if (dataSnapshot.getValue() == null && !hasKey) {
                    key = databaseReference.push().getKey();
                    databaseReference1 = databaseReference.getDatabase().getReference("chats/" + firebaseUser.getUid());
                    HashMap<String, Object> map = new HashMap<>();

                    map.put(userid, key);
                    chatid = key;
                    System.out.println("Chat Key---->" + key);
                    assert key != null;
                    databaseReference1.updateChildren(map);
                    databaseReference1 = databaseReference.getDatabase().getReference("chats/" + userid);
                    map = new HashMap<>();

                    map.put(firebaseUser.getUid(), key);

                    databaseReference1.updateChildren(map);

                    hasKey = true;


                    databaseReference2 = FirebaseDatabase.getInstance().getReference("messages").child(chatid);
                    databaseReference2.addValueEventListener(this);
//
                  //  createinbox(key);


                } else if (!hasKey) {
                    hasKey = true;
                    DatabaseReference db = databaseReference.getDatabase().getReference("chats/" + firebaseUser.getUid() + "/" + userid);
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            chatid = String.valueOf(dataSnapshot.getValue());
                            System.out.println("existing chat ID = " + chatid);

                            databaseReference2 = FirebaseDatabase.getInstance().getReference("messages").child(chatid);
                            databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Chat_model chat_model = snapshot.getValue(Chat_model.class);
                                        chat_modelList.add(chat_model);
                                    }
                                    messageAdapter = new MessageAdapter(getApplicationContext(), chat_modelList);
                                    recyclerView.setAdapter(messageAdapter);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                          //  createinbox(chatid);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ShowUserDetails();
        // createRoom();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_button:
                notify = true;
                sendButtonListener();
        }
    }

    private void ShowUserDetails() {
        System.out.println(" chat ID = " + chatid);
        Log.e("id", "" + chatid);

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserChatApp user = dataSnapshot.getValue(UserChatApp.class);
                Log.e("name", "" + firebaseUser.getDisplayName());

                name.setText(user.getName());
                Picasso.with(getApplicationContext()).load(user.getImgurl()).
                        transform(new RoundedCornersTransformation(10, 10)).into(image);
                // readmessages(firebaseUser.getUid(),userid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void sendButtonListener() {
        textmessage = message.getText().toString();
        if (!textmessage.equals("")) {

            sendMessage(userid, textmessage, chatid);
        } else {
            Toast.makeText(getApplicationContext(), "You can't send empty message", Toast.LENGTH_LONG).show();
        }
        message.setText("");
    }

    public void sendMessage(final String reciver, final String message, String id) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("reciever", userid);
        map.put("message", textmessage);
        databaseReference2 = databaseReference.getDatabase().getReference("messages");
        databaseReference2.child(chatid).push().updateChildren(map);
        readmessages(firebaseUser.getUid(), userid, chatid);

        final String msg=message;
         reference=databaseReference.getDatabase().getReference("users").child(firebaseUser.getUid());
         reference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 UserChatApp userChatApp=dataSnapshot.getValue(UserChatApp.class);
                 if(notify) {
                     final DatabaseReference token=FirebaseDatabase.getInstance().getReference("Tokens");
                     Query query=token.orderByKey().equalTo(reciver);
                     query.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             for(DataSnapshot snapshot:dataSnapshot.getChildren()) {

                                 Token_model token_model = snapshot.getValue(Token_model.class);
                                  token1=token_model.getToken();
                             }
                             sendNotification(String.valueOf(token1), msg,reciver);
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     }
                     );



                   /*  String token="dt9M9_d8cj8:APA91bF2b_xdtSdmP0uBxuwI-bP-_dyUxi3X-tce0MOtXyTfjEgp5_Tw6OGdpaWTB_" +
                             "tL1JaJvRYNEBvQH6ftdSfHED0gLsS63vOjiDeScqMlYeMXT1BaJL_nWQXmxX5u3XU2OlxT8QRq";*/

                 }
                 notify=false;

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

    }
            private  void sendnotification(String reciver, final String name, final String message)
            {
                final DatabaseReference token=FirebaseDatabase.getInstance().getReference("Tokens");
                Query query=token.orderByKey().equalTo(reciver);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                            Token_model token_model = snapshot.getValue(Token_model.class);
                            Data_model data_model = new Data_model(firebaseUser.getUid(), R.mipmap.ic_launcher, name + ": " + message,
                                    "New message", userid);

                            Sender sender = new Sender(data_model, token_model.getToken());
                            System.out.println("token=------------------->"+token_model.getToken());

                            apIservice.sendnotification(sender).enqueue(new Callback<Myresponse>() {
                                @Override
                                public void onResponse(Call<Myresponse> call, Response<Myresponse> response) {
                                    if (response.code() == 200) {
                                        if (response.body().sucess != 1) {
                                            Toast.makeText(Message_Activity.this, "failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(Message_Activity.this, "sucess", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Myresponse> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

    @SuppressLint("StaticFieldLeak")
    private void sendNotification(final String regToken, final String message, final String chatwith) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Gson gson = new Gson();
                    Data_model data = new Data_model();
                    data.setBody(message);
                    data.setTitle("New Message");
                    data.setName(chatwith);
                    data.setSent(firebaseUser.getUid());
                   // data.setNotifyKey("chat_id");
                    //data.setSent(SharedPreferenceHelper.getInstance().getUserId());
                   // data.setChatID(chatID);
                   // data.setFirebaseToken(SharedPreferenceHelper.getInstance().getFirebaseToken());
                    Sender sender = new Sender();
                    sender.setTo(regToken);
                    sender.setData(data);
                    String jsonString = gson.toJson(sender);
                    OkHttpClient client = new OkHttpClient();
                    final MediaType JSON
                            = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, jsonString);
                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .header("Authorization", "key=AIzaSyAftgxPcHlEwP5gC_Ji1coD5rzFPoOd-Uc")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    okhttp3.Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();
                    Log.d("token1", "doInBackground: response=>" + finalResponse + " " + regToken);
                } catch (Exception e) {
                    //Log.d(TAG,e+"");
                }
                return null;
            }
        }.execute();

    }



    public void readmessages(final String myid, final String userid, String id) {
        chat_modelList = new ArrayList<>();
        createinbox(chatid);
        databaseReference2 = FirebaseDatabase.getInstance().getReference("messages").child(chatid);
        databaseReference2.addValueEventListener(this);

    }
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        chat_modelList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Chat_model chat_model = snapshot.getValue(Chat_model.class);

            chat_modelList.add(chat_model);
            //createinbox(chatid);


        }
        messageAdapter = new MessageAdapter(getApplicationContext(), chat_modelList);
        recyclerView.setAdapter(messageAdapter);


    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }


    public void createinbox(final String chatid) {

        reference = databaseReference.getDatabase().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserChatApp userChatApp = snapshot.getValue(UserChatApp.class);
                    if (Objects.requireNonNull(userChatApp).getUid().equals(userid)) {
                        databaseReference3 = FirebaseDatabase.getInstance().getReference("inbox")
                                .child(firebaseUser.getUid()).child(chatid).child(userid);
                        databaseReference3.setValue(userChatApp);

                    }

                    if (firebaseUser.getUid().equals(userChatApp.getUid())) {
                        databaseReference3 = FirebaseDatabase.getInstance().getReference("inbox")
                                .child(userid).child(chatid).child(firebaseUser.getUid());
                        databaseReference3.setValue(userChatApp);
                    }


                }
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Chat_model chat_model = snapshot.getValue(Chat_model.class);
                            last_message = chat_model.getMessage();
                            databaseReference3 = FirebaseDatabase.getInstance().getReference("inbox").
                                    child(firebaseUser.getUid()).child(chatid);
                            databaseReference3.child("lastmessage").setValue(last_message);
                   databaseReference3=FirebaseDatabase.getInstance().getReference("inbox").
                            child(userid).child(chatid);
                    databaseReference3.child("lastmessage").setValue(last_message);


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
