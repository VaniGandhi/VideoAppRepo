package com.example.myapplication.Roomdatabase;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class VeiwUserFragment extends Fragment {
    TextView textinfo;

        public static  VeiwUserFragment getInstance()
        {
            VeiwUserFragment veiwUserFragment=new VeiwUserFragment();
            return  veiwUserFragment;
        }


    public VeiwUserFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     View view= inflater.inflate(R.layout.fragment_veiw_user, container, false);
     intializers(view);
     showdata();


     return  view;
    }
    public void intializers(View view)

    {
        textinfo=view.findViewById(R.id.textinfo);
    }
    public void showdata()
    {
        List<User> users=Main2Activity.myAppDatabase.myDao().getUsers();
        String info=" ";
        for(User usr:users)
        {
            int id=usr.getId();
            String name=usr.getUsername();
            String email=usr.getUseremail();

            info=info+"\n\n"+"id:"+id+"\n"+"name:"+name+"\n"+"email:"+email;
            Log.e("nn",""+usr.getUseremail());
            textinfo.setText(info);




        }
    }

    public void showall()
    {
        List<User> users=Main2Activity.myAppDatabase.myDao().getUsers();

    }


}
