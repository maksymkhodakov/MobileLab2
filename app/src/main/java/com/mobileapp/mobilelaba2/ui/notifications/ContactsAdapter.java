package com.mobileapp.mobilelaba2.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<MyContactsHolder> {

    Context context;
    List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyContactsHolder(LayoutInflater.from(context).inflate(R.layout.contact_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactsHolder holder, int position) {
        holder.numberView.setText(contacts.get(position).getNumber());
        holder.contactNameSurnameView.setText(contacts.get(position).getName() + contacts.get(position).getSurname());
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
