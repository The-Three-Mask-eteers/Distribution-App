package com.example.loginapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class ExampleDialog extends AppCompatDialogFragment {
    public static String display_text = "";
    @NonNull

    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String red = Objects.place_name.toLowerCase();
        HashMap<String, Entity> map;
        if (Objects.parameter.equals("placesThatGiveDonations")) {
            map = Objects.placesThatGiveDonations;
        } else {
            map = Objects.placesThatAcceptDonations;
        }

        Entity selectedPlace = map.get(red);
        String name = selectedPlace.name;
        String address = selectedPlace.address;
        String description = selectedPlace.description;
        String acceptedItems = selectedPlace.acceptedItems;
        String phone = selectedPlace.phone;
        String email = selectedPlace.email;

        display_text = name + "\n " + address + "\n" + description + "\n" + acceptedItems + "\n" + phone + "\n" + email;


        final SpannableString s = new SpannableString(display_text);
        final TextView tx1 = new TextView(getContext());
        tx1.setText(s);
        tx1.setAutoLinkMask(RESULT_OK);
        tx1.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(s, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(s, Linkify.WEB_URLS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setView(tx1)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });


//added a TextView


        AlertDialog alter1 = builder.create();

        return alter1;
    }
}
