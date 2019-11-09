package com.example.myapplication.VideoPlayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.ChatApp.InboxModel;
import com.example.myapplication.ChatApp.inbox_recyclerviewadapter;
import com.example.myapplication.R;
import com.example.myapplication.Roomdatabase.Main2Activity;
import com.example.myapplication.Roomdatabase.MyAppDatabase;
import com.example.myapplication.Roomdatabase.User;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class SongListScreen extends AppCompatActivity   {

   RecyclerView recyclerView;
    FloatingActionButton camera, gallary, playwithurl;
    VideoView videoView;
    private static final int VIDEO_CAPTURE = 101;
    static final String appDirectoryName = "XYZ";
    private static final int Pick_videos = 100;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 102;
    private List<Video_model> video_model;
    ImageView thumbnail_image,delete;
    Bitmap bmThumbnail;
    String thumbnail;
    public  static MyAppVideoDatabase myAppVideoDatabase;
    private Songlistadpater songlistadpater;

    private ShimmerFrameLayout mShimmerViewContainer;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_screen);
        recyclerView = findViewById(R.id.listofsongs);
        camera = findViewById(R.id.camera);
        gallary = findViewById(R.id.gallary);
        playwithurl = findViewById(R.id.play_with_url);
        delete=findViewById(R.id.deletelist);
        videoView = findViewById(R.id.videoview);
        thumbnail_image=findViewById(R.id.thumbnail_image);
        videoView.setVisibility(View.GONE);
        myAppVideoDatabase= Room.databaseBuilder(getApplicationContext(), MyAppVideoDatabase.class,"videodb")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        video_model=new ArrayList<>();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedata();
            }
        });
        Intent   intent2=getIntent();
        final String videoname=  intent2.getStringExtra("id");
        if(videoname!=null)
        { Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            wait(500);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    myAppVideoDatabase.videoDao().delete(videoname);
                                    showdata();

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
            showdata();
        }
        showdata();

        camera.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          opencamera();
                                      }
                                  }
        );
        gallary.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           opengallary();

                                       }
                                   }
        );
        playwithurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SongListScreen.this, Play_with_url.class);
                startActivity(intent);
            }
        });


    }

    private void opencamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_CAPTURE);

    }

    private void opengallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, Pick_videos);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    Uri uri = data.getData();
                    String videopath = getPath(uri);
                    StringTokenizer tokens = new StringTokenizer(videopath, ".");
                    String name = tokens.nextToken();
                    String videoid=tokens.nextToken();

                    int writeExternalStoragePermission = ContextCompat.checkSelfPermission
                            (SongListScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(SongListScreen.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                    }
                    saveVideoToInternalStorage(videopath);
                    bmThumbnail =ThumbnailUtils.createVideoThumbnail(getPath(uri), MediaStore.Video.Thumbnails.MICRO_KIND);
                    saveImage(bmThumbnail);

                    VideoModel videoModel = new VideoModel();
                    videoModel.setThumbnailpath(thumbnail);
                    videoModel.setVideopath(videopath);
                    videoModel.setName(videoid);




                    if (!ifvideoexits(videopath)) {
                        myAppVideoDatabase.videoDao().addvideo(videoModel);
                        showdata();

                    } else
                    {
                        Toast.makeText(this, "video already exits", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        } else if (resultCode == RESULT_OK) {
            if (requestCode == Pick_videos) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    String selectedVideoPath = getPath(contentURI);

                    StringTokenizer tokens = new StringTokenizer(selectedVideoPath, "V");
                    String name = tokens.nextToken();
                    String videoid=tokens.nextToken();

                    System.out.println("name------------>"+videoid);
                    Log.d("path", selectedVideoPath);
                    int writeExternalStoragePermission = ContextCompat.checkSelfPermission
                            (SongListScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(SongListScreen.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                    }
                    saveVideoToInternalStorage(selectedVideoPath);
                 bmThumbnail =ThumbnailUtils.createVideoThumbnail(getPath(contentURI), MediaStore.Video.Thumbnails.MICRO_KIND);
                    saveImage(bmThumbnail);

                    VideoModel videoModel = new VideoModel();
                    videoModel.setThumbnailpath(thumbnail);
                    videoModel.setVideopath(selectedVideoPath);
                    videoModel.setName(videoid);
                    System.out.println("videopath------------->" + videoModel.getVideopath());
                    System.out.println("thumbnailpath---------->" + videoModel.getThumbnailpath());

                    if (!ifvideoexits(selectedVideoPath)) {
                        myAppVideoDatabase.videoDao().addvideo(videoModel);
                        showdata();

                    } else
                    {
                        Toast.makeText(this, "video already exits", Toast.LENGTH_SHORT).show();

                    }
                }
            }


            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {

            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public void saveVideoToInternalStorage(String filePath) {

        File newfile;

        try {

            File currentFile = new File(filePath);
       File  appfile = new File(Environment.getExternalStorageDirectory() + "/VideoCompress/" + appDirectoryName);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            newfile = new File(appfile,  "Thumbnail"+ timeStamp + ".mp4");
           // createThumbnailFromPath(appfile.getPath(),MediaStore.Images.Thumbnails.MINI_KIND);

            if (!appfile.exists()) {
                appfile.mkdirs();

            }

            if (currentFile.exists()) {

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);
                System.out.println("filepath-------->" + newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Log.e("vii", "Video file saved successfully.");
            } else {
                Log.e("vii", "Video saving failed. Source file missing.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION) {
            int grantResultsLength = grantResults.length;
            if (grantResultsLength > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "You granted write  to external storage permission.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "You denied write  to external storage permission.", Toast.LENGTH_LONG).show();
            }
        }


    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root  + "/VideoCompress/" + appDirectoryName);
        myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Thumbnail"+ timeStamp +".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showdata()

    {
         video_model=new ArrayList<>();

        List<VideoModel> videoModel= myAppVideoDatabase.videoDao().getVideos();

        if(videoModel.size()==0)
        {
            songlistadpater = new Songlistadpater(getApplicationContext(), video_model);
            recyclerView.setAdapter(songlistadpater);
            return ;

        }
       for(VideoModel videoModel1:videoModel)
       {



                String video = videoModel1.getVideopath();
                String thumbnail = videoModel1.getThumbnailpath();
                int id = videoModel1.getId();

                String name = videoModel1.getName();
                System.out.println("id---------------->" + id);

                bmThumbnail = ThumbnailUtils.createVideoThumbnail(video, MediaStore.Video.Thumbnails.MICRO_KIND);

                Video_model video_model1 = new Video_model(video, bmThumbnail);
                video_model1.setVideo(video);
                video_model1.setThumbnail(bmThumbnail);
                video_model1.setId(id);
                video_model1.setName(name);
                video_model.add(video_model1);
                songlistadpater = new Songlistadpater(getApplicationContext(), video_model);
                recyclerView.setAdapter(songlistadpater);
                //mShimmerViewContainer.stopShimmerAnimation();

            }


    }


    private Boolean ifvideoexits( String videopath)
    {
        List<VideoModel> videoModel = myAppVideoDatabase.videoDao().getVideos();

        for (VideoModel vv : videoModel) {
            if (vv.getVideopath().equalsIgnoreCase(videopath)) {
                return true;

            }
        }
        return false;
    }


    private void deletedata()

    {
        myAppVideoDatabase.videoDao().delete();
            video_model.clear();
            songlistadpater.notifyDataSetChanged();
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
    }

}


