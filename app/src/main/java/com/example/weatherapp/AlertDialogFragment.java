package com.example.weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Network Error")
                .setMessage("Whoops there is an error MFcker")
                .setPositiveButton("OK", null);

        return builder.create();
    }
}
