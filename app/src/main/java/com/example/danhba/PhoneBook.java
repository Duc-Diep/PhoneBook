package com.example.danhba;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danhba.databinding.PhonebookBinding;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook extends Fragment {
    List<Contact> contactList;
    SQLHelper sqlHelper;
    PhonebookBinding binding;

    public static PhoneBook newInstance() {

        Bundle args = new Bundle();

        PhoneBook fragment = new PhoneBook();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.phonebook,container,false);
        sqlHelper = new SQLHelper(getContext());
        contactList = new ArrayList<>();
        contactList = sqlHelper.getAllContact();
        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(contactList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        binding.listPhonebook.setAdapter(phoneBookAdapter);
        binding.listPhonebook.setLayoutManager(layoutManager);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = InfoContact.newInstance("","","");
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_fragment, fragment);
                fragmentTransaction.commit();
            }
        });
        phoneBookAdapter.setIonClickContact(new IonClickContact() {
            @Override
            public void onClickName(Contact contact) {
                Fragment fragment = InfoContact.newInstance(String.valueOf(contact.getId()),contact.getName(),contact.getPhoneNumber());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_fragment, fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onClickPhone(Contact contact) {
                Fragment fragment = InfoContact.newInstance(String.valueOf(contact.getId()),contact.getName(),contact.getPhoneNumber());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_fragment, fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onClickLayout(Contact contact) {
                Fragment fragment = InfoContact.newInstance(String.valueOf(contact.getId()),contact.getName(),contact.getPhoneNumber());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layout_fragment, fragment);
                fragmentTransaction.commit();
            }

            @Override
            public void onClickCall(Contact contact) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+contact.getPhoneNumber()));
                      //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getContext(),"Call failed",Toast.LENGTH_SHORT).show();
                    Log.e("Calling a Phone Number", "Call failed", e);
                }
                //Toast.makeText(getContext(),"Call "+contact.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}
