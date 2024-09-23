package com.mobileapp.mobilelaba2.ui.notifications;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

public class MyContactsHolder extends RecyclerView.ViewHolder {
    TextView contactNameSurnameView, numberView;
    public MyContactsHolder(@NonNull View itemView) {
        super(itemView);
        contactNameSurnameView = itemView.findViewById(R.id.contactNameSurname);
        numberView = itemView.findViewById(R.id.contactNumber);
    }
}