package com.example.myapplication.Images;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;

public class Draw_image extends AppCompatActivity {

    private static final int FILE_ATTACHMENT = 11;
    DrawerView drawerView;
    ImageView image;
    Uri myfileuri;
    Button button1, undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_view);
        drawerView = findViewById(R.id.draw);
        button1 = (Button) findViewById(R.id.button1);
        undo = findViewById(R.id.undo);
        image = findViewById(R.id.img);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (drawerView.havebitmap()) {
                    Bitmap bitmap = drawerView.getBitmap();
                    save(bitmap);
                } /*else{
                    Toast.makeText(DrawerViewActivity.this,"please draw something",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        /*undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerView.onClickUndo();
            }
        });*/

    }

    private void save(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        System.out.println(root + " Root value in saveImage Function");
        File myDir = new File(root + "/Canavsdemo");

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
            // drawerView.clear();
            Toast.makeText(getApplicationContext(),
                    "Saved Sucessfully",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareimage()
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM,myfileuri);
        startActivity(Intent.createChooser(share, "Share Image"));

     /*   if(isPackageInstalled("com.whatsapp",this)){
            share.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(share, "Share Image"));
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Install Whatsapp", Toast.LENGTH_LONG).show();
        }
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });*/

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

    private boolean hasStoragePermission(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                Toast.makeText(Draw_image.this,"permission not granted",Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        } else {
            return true;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }
}
