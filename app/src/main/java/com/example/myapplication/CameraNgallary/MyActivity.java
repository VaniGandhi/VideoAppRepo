package com.example.myapplication.CameraNgallary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.pm.PackageManager.*;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class MyActivity extends AppCompatActivity implements  View.OnClickListener{
    CircleImageView imageView;
    ImageView chooseimage;
    String pathtofile;
    File filepath;
    Uri file;
    public static  final int Pick_Images=100;
    Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
        imageView=findViewById(R.id.circleimageview);
        chooseimage=findViewById(R.id.chooseimage);

        chooseimage.setOnClickListener( this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chooseimage:
               opengallary();
break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==Pick_Images)
            {
                imageuri=data.getData();

               try {

                   Bitmap bm=MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                   imageView.setImageBitmap(bm);
               }catch(IOException e)
               {
                   e.printStackTrace();
               }

            }
        }


    }
   /* public void TakePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }*/


    private void TakePicture() {
        Intent takepic=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepic.resolveActivity(getPackageManager()) != null) {
            File photofile = null;
            photofile=createPhotofile();
            if(photofile!=null)
            {
               pathtofile=photofile.getAbsolutePath();
                Uri photouri= FileProvider.getUriForFile(MyActivity.this,"com.example.myapplication",photofile);
                takepic.putExtra(MediaStore.EXTRA_OUTPUT,photouri);
                startActivityForResult(takepic,1);
            }

        }
    }

  /*  public void open_camera() {
        Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        File output = new File(dir, SystemClock.currentThreadTimeMillis() + ".jpg");
        if (!output.exists()) {
            try {
                output.createNewFile();
                takepic.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
                path(output);
                startActivityForResult(takepic, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void path(File file)
    {
        this.filepath=file;
    }
*/

    private File createPhotofile() {
        String name=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File Storage=getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try {
             image=File.createTempFile(name,".jpg",Storage);
        } catch (IOException e) {
            Log.e("mylog","Exception:"+e.toString());
        }
        return  image;
    }

    private void opengallary()
    {
        Intent gallary=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallary,Pick_Images);
    }
}
