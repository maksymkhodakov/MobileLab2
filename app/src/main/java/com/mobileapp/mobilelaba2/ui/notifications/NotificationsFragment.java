package com.mobileapp.mobilelaba2.ui.notifications;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private static final int REQUEST_READ_CONTACTS_PERMISSION = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Якщо дозвіл не наданий, викличе запит на дозвіл
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS_PERMISSION);
        } else {
            // Якщо дозвіл вже наданий, відображення контактів
            List<Contact> ivanContacts = getContactsWithFatherName();

            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewContact);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            ContactsAdapter contactsAdapter = new ContactsAdapter(requireContext(), ivanContacts);
            recyclerView.setAdapter(contactsAdapter);
        }
    }

    public List<Contact> getContactsWithFatherName() {
        List<Contact> contacts = new ArrayList<>();

        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        String selection = "UPPER(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + ") LIKE ?";
        String[] selectionArgs = {"% % %"};

        Cursor cursor = requireContext().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null
        );

        if (cursor != null) {
            try {
                int displayNameIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                 int phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);

                while (cursor.moveToNext()) {
                    String contactName = cursor.getString(displayNameIndex);
                    String contactNumber = cursor.getString(phoneNumberIndex);
                    contacts.add(new Contact(contactName, " ", contactNumber));
                }
            } catch (IllegalArgumentException e) {
                // Обробка помилки, якщо вказано невірний стовпець
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }

        return contacts;
    }
}