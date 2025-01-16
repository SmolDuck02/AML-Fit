package com.example.appdev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

public class AccountFragment extends Fragment {


    View rootView;

    TextView textViewName, textViewEmail, textViewNumber, textViewLocation;
    Button settingsButton, logoutButton;

    private static final int REQUEST_PROFILE_UPDATE = 1;

    String name = "", email = "", number = "", location = "";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    User extractedCurrentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_account, container, false);

//        Bundle bundle = this.getArguments();
//
//        if(bundle != null && bundle.getString("name") != null){
//            // handle your code here.
//            name = bundle.getString("name");
//            email = bundle.getString("email");
//            number = bundle.getString("number");
//            location = bundle.getString("location");
//        }




        /////////////////


        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

        Toast.makeText(getActivity(), extractedCurrentUser.getUsername() + "  " , Toast.LENGTH_SHORT).show();




        ///////////




        logoutButton = rootView.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new LogoutClickListner());

        textViewName = rootView.findViewById(R.id.textViewName);
        textViewEmail = rootView.findViewById(R.id.textViewEmail);
        textViewNumber = rootView.findViewById(R.id.textViewNumber);
        textViewLocation = rootView.findViewById(R.id.textViewLocation);



        textViewName.setText(extractedCurrentUser.getName());
        textViewEmail.setText(extractedCurrentUser.getEmail());
        textViewNumber.setText(extractedCurrentUser.getNumber());
        textViewLocation.setText(extractedCurrentUser.getLocation());

       //!name.equals("")
//        if(!name.equals(""))
//
//        if(!email.equals(""))
//
//        if(!number.equals(""))
//
//        if(!location.equals(""))
//



        settingsButton = rootView.findViewById(R.id.settingsbutton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_containerInside, new UpdateUserProfileFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

               // getChildFragmentManager().beginTransaction().replace(R.id.main_content, new insertFragmentNameHere())
            }
        });

        return rootView;


    }

    private class LogoutClickListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Toast.makeText(requireActivity(), "Logging you out", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(requireActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }


//
//    private void launchProfileUpdateActivity() {
//        Intent intent = new Intent(this, UpdateUserProfileFragment.class);
//        startActivityForResult(intent, REQUEST_PROFILE_UPDATE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_PROFILE_UPDATE && resultCode == Activity.RESULT_OK && data != null) {
//            // Retrieve the updated profile information from the intent
//            String name = data.getStringExtra("name");
//            String email = data.getStringExtra("email");
//            String number = data.getStringExtra("number");
//            String location = data.getStringExtra("location");
//
//            // Update the profile UI with the updated information
//            textViewName.setText(name);
//            textViewEmail.setText(email);
//            textViewNumber.setText(number);
//            textViewLocation.setText(location);
//        }
//    }
}
