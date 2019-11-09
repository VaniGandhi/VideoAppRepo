package com.example.myapplication.Images;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.ChatApp.Message_Activity;
import com.example.myapplication.R;
import com.jackandphantom.blurimage.BlurImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Blur_image extends AppCompatActivity {

    ImageView image,share;
    Button pick_image,Blur_image;
    private int PICK_IMAGE_REQUEST = 1;
    private static  String CHANNEL_ID="null";
    Context context;
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;
    final int Progressmax=100;
    Bitmap bitmap,bm,bitmap2;
    public  static  final String appDirectoryName="XYZ";
    Uri myfileuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_image);
        image=findViewById(R.id.image_view);
        pick_image=findViewById(R.id.pick_image);
        Blur_image=findViewById(R.id.Blur_image);
        share=findViewById(R.id.share_image);

        pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        Blur_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Blurimage();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareimage();
            }
        }
        );
       /* Intent  intent1=getIntent();
        byte[] byteArray1 = intent1.getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0, byteArray1.length);
        image.setImageBitmap(bmp);*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            try {
               bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                image.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void shareimage() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, myfileuri);


        if (isPackageInstalled("com.whatsapp", this)) {
            share.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(share, "Share Image"));


        } else
        {
            Toast.makeText(this, "App not installed", Toast.LENGTH_SHORT).show();
        }
    }

    public void blurimage2()
    {
        createnotification();
        Bitmap bitmap = BlurImage.with(getApplicationContext()).load(bitmap2).intensity(20).Async(true).getImageBlur();
        save(bitmap);
        image.setImageBitmap(bitmap);
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for(int progress=0;progress<=Progressmax;progress+=20)
                {
                    SystemClock.sleep(1000);
                }

            }
        }).start();*/
        builder.setContentText("Completed").
                setProgress(0,0,false).setOngoing(false);
        notificationManager.notify(0,builder.build());

    }
    public void Blurimage()
    {

        if(image.getDrawable()!=null)
        {
            createnotification();
            final Bitmap resultbitmap=BlurBuilder.blur(this,bitmap2);
            save(resultbitmap);
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            //  createnotification();
                            wait(5000);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    //createnotification();
                                    image.setImageBitmap(resultbitmap);
                                    builder.setContentText("Completed").
                                            setProgress(0,0,false).setOngoing(false);
                                    notificationManager.notify(0,builder.build());
                                    Blur_image.setVisibility(View.GONE);

                                 /*   Intent  intent1=getIntent();
                                    byte[] byteArray1 = intent1.getByteArrayExtra("image");
                                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0, byteArray1.length);
                                    image.setImageBitmap(bmp);*/

                                }
                            });

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                };
            };
            thread.start();
        }
        else
        {
            Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show();
        }






    }



    public void createnotification()
    {
         notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            String CHANNEL_ID = "my_channel_01";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {



                CharSequence name = "my_channel";
                String Description = "This is my channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);

                notificationManager.createNotificationChannel(mChannel);
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Intent intent = new Intent(this, Blur_image.class);

            Bundle b = new Bundle();
            b.putByteArray("image",byteArray);

            intent.putExtras(b);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


            Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder = new NotificationCompat.Builder(this,CHANNEL_ID).
                    setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Blurring").
                            setContentText("Blurring in Progress")
                    .setAutoCancel(true)
                    .setSound(defaultsound)
                    .setOngoing(true)
                    .setOnlyAlertOnce(true)
                    .setContentIntent(pendingIntent)
                    .setProgress(Progressmax,0,true)
                    ;

            notificationManager.notify(0, builder.build());
        }

    }


    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void save(Bitmap finalBitmap) {
        String root = Environment.getExternalStorageDirectory() + "/Blur_images/" + appDirectoryName;
        System.out.println(root + " Root value in saveImage Function");
        File myDir = new File(root + "/");

        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        String iname = System.currentTimeMillis() + ".jpg";
        File file = new File(myDir, iname);
        if (file.exists())
            file.delete();

        try {
            FileOutputStream out = new FileOutputStream(file);
            // Bitmap resized = Bitmap.createScaledBitmap(finalBitmap,(int)(finalBitmap.getWidth()*0.5), (int)(finalBitmap.getHeight()*0.5), true);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            myfileuri = Uri.fromFile(file);
            out.flush();
            out.close();

            Toast.makeText(getApplicationContext(),
                    "Bitmap saved",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}





