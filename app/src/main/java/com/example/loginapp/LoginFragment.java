package com.example.loginapp;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Button button = getActivity().findViewById(R.id.btn_login);
        final EditText name  = (EditText) getActivity().findViewById(R.id.et_username);
        final EditText password = (EditText) getActivity().findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebFunctions.verifyUser(name.getText().toString(), password.getText().toString(), new WebFunctions.UserLoginCallback() {
                    @Override
                    public void LoginResult(boolean isVerified) {
                        //
                    }
                });
            }
        });


        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
