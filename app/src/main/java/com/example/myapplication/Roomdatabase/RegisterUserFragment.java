
package com.example.myapplication.Roomdatabase;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class RegisterUserFragment extends Fragment implements View.OnClickListener {
    EditText useremail, userpassword, username, userconfirmpassword, userid;
    TextView alreadyregistered, loginnow;
    Button login, facebooklogin, logout, fb, google, instagram;
    SignInButton googlelogin;
    ArrayList<String> emails;
    String email, name, password;
    User user;

    public static RegisterUserFragment getInstance() {
        RegisterUserFragment registerUserFragment=new RegisterUserFragment();
        return  registerUserFragment;
    }

    public RegisterUserFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        intilaizer(view);
        buttonlisteners();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                onsignup();
                //getFragmentManager().popBackStack();
               getFragmentManager().beginTransaction().replace(R.id.frame1, new VeiwUserFragment())
                        .addToBackStack(null).commit();
                //isUserExist();

        }

    }

    public void intilaizer(View view) {


        useremail = view.findViewById(R.id.edit_email);
        userpassword = view.findViewById(R.id.edit_password);
        userconfirmpassword = view.findViewById(R.id.edit_confirmpassword);
        username = view.findViewById(R.id.edit_name);

        alreadyregistered = view.findViewById(R.id.Text_view1);
        login = view.findViewById(R.id.login_button);
        facebooklogin = view.findViewById(R.id.facebooklogin_button);
        googlelogin = view.findViewById(R.id.google_button);
        loginnow = view.findViewById(R.id.Text_view3);
        logout = view.findViewById(R.id.signout);
        fb = view.findViewById(R.id.fb);
        google = view.findViewById(R.id.google);
        instagram = view.findViewById(R.id.instagram);
      //  userid = view.findViewById(R.id.edit_id);
        FrameLayout frameLayout = view.findViewById(R.id.framelayout2);
        TextInputLayout textInputLayout = view.findViewById(R.id.textinputlayout2);
        textInputLayout.setVisibility(View.GONE);
        // frameLayout.setVisibility(View.GONE);
        userid.setVisibility(View.GONE);
        userconfirmpassword.setVisibility(View.GONE);


    }

    public void buttonlisteners() {
        login.setOnClickListener(this);
    }

    public void onsignup() {


        name = username.getText().toString().trim();
        email = useremail.getText().toString().trim();

        password = userpassword.getText().toString().trim();
        //String id=userid.getText().toString().trim();

        user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setUseremail(email);

        String response = validations(name, email, password);
        if (!TextUtils.isEmpty(response)) {

            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();


        } else {
            if (!isUserExist()) {
              Main2Activity.myAppDatabase.myDao().adduser(user);

            }else{
                Toast.makeText(getActivity(), "This email is already exists", Toast.LENGTH_SHORT).show();
            }

        }


    }




    public String validations(String name, String email, String password) {
        if (TextUtils.isEmpty(name)) {
            return "enter name";

        }
        if (TextUtils.isEmpty(email)) {
            return "enter email";
        }
        if (TextUtils.isEmpty(password)) {
            return "enter password";
        }
        return null;

    }

    private boolean isUserExist() {
        List<User> users = Main2Activity.myAppDatabase.myDao().getUsers();

        for (User usr : users) {
            if (usr.getUseremail().equalsIgnoreCase(email)) {
                return true;

            }
        }
        return false;
    }
}


