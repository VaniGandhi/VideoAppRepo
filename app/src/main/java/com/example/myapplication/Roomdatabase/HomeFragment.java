package com.example.myapplication.Roomdatabase;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;


public class HomeFragment extends Fragment implements  View.OnClickListener {
    Button signup,login;



    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_lauch_screen, container, false);
        signup=view.findViewById(R.id.lsignup_button);
        login=view.findViewById(R.id.llogin_button);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case  R.id.lsignup_button:
             getFragmentManager().beginTransaction().
                        replace(R.id.frame1,RegisterUserFragment.getInstance()).addToBackStack(null).commit();
                break;
            case  R.id.llogin_button:
                getFragmentManager().beginTransaction().
                        replace(R.id.frame1,VeiwUserFragment.getInstance()).addToBackStack(null).commit();

        }

    }
}
