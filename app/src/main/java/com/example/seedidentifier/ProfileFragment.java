package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment{

    FirebaseUser User;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    String UserId;

    EditText EmailEditText;
    EditText PasswordEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_profile, container, false);

        EmailEditText = contentView.findViewById(R.id.EmailEditText);
        PasswordEditText = contentView.findViewById(R.id.PasswordEditText);

        User = FirebaseAuth.getInstance().getCurrentUser();
//        Toast.makeText(getActivity(), "" + User.getUid(), Toast.LENGTH_LONG).show();
//        Toast.makeText(getActivity(), "" + User.getEmail(), Toast.LENGTH_LONG).show();

        //mDatabaseReference.child("Users").child("userEmail").setValue(User.getUid());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
        UserId = User.getUid();

        mDatabaseReference.child(UserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User UserProfile = snapshot.getValue(User.class);

                if(UserProfile != null) {

                    String EmailField = UserProfile.getUserEmail();
                    String PasswordField = UserProfile.getUserPassword();

                    EmailEditText.setText(EmailField);
                    PasswordEditText.setText(PasswordField);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        Button Logout;
        Logout = contentView.findViewById(R.id.LogoutButton);
        Logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), Login.class));
        });

        return contentView;
    }
}