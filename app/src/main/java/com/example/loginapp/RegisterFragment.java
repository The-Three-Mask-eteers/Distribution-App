package com.example.loginapp;


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
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Button button = getActivity().findViewById(R.id.btn_register);
        final EditText name  = (EditText) getActivity().findViewById(R.id.et_name);
        final EditText password = (EditText) getActivity().findViewById(R.id.et_password);
        final EditText repassword = (EditText) getActivity().findViewById(R.id.et_repassword);
        final EditText email = (EditText) getActivity().findViewById(R.id.et_mail);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String txt1 = password.getText().toString();
                String txt2 = repassword.getText().toString();
                if (txt1 == txt2) {
                    WebFunctions.addUser(name.getText().toString(), email.getText().toString(), password.getText().toString());
                }
            }
        });


        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
