package com.example.loginapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inflate the layout for this fragment
        final Button button = getActivity().findViewById(R.id.btn_login);
        final EditText name  = (EditText) getActivity().findViewById(R.id.et_username);
        final EditText password = (EditText) getActivity().findViewById(R.id.et_password);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Thread newThread = new Thread() {
                    @Override
                    public void run() {
                    WebFunctions.verifyUser(name.getText().toString(), password.getText().toString(), new WebFunctions.UserLoginCallback() {
                        @Override
                        public void LoginResult(boolean isVerified) {
                            if (isVerified) {

                                Intent intent = new Intent(getActivity(), HomeActivity.class);

                                startActivity(intent);
                            } else {
                                System.out.println("sorry, the fragment didn't open");
                            }
                        }
                    });
                    }
                };
                newThread.start();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
