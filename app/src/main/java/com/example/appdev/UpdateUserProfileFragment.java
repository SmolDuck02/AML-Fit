package com.example.appdev;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.util.ArrayList;

public class UpdateUserProfileFragment extends Fragment {

    View rootView;
    private EditText editTextName, editTextEmail, editTextNumber, editTextLocation;
    private Button buttonSave;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    User extractedCurrentUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_update_account, container, false);


        editTextName = rootView.findViewById(R.id.editTextName);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextNumber = rootView.findViewById(R.id.editTextNumber);
        editTextLocation = rootView.findViewById(R.id.editTextLocation);

        buttonSave = rootView.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new SaveProfileClickListener());



        /////////////////


        sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Gson gson = new Gson();

        //extracting for cart items display
        String userString = sharedPreferences.getString("currentUser", "Default");
        extractedCurrentUser = gson.fromJson(userString, User.class);

//        Toast.



        ///////////




        return rootView;


    }

    private class SaveProfileClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {


            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String number = editTextNumber.getText().toString();
            String location = editTextLocation.getText().toString();

            System.out.println(name+ "iiiiiiiiiiiiiiiiiiiiiii");

            if(!name.equals(""))
                extractedCurrentUser.setName(name);
            if(!email.equals(""))
                extractedCurrentUser.setEmail(email);
            if(!number.equals(""))
                extractedCurrentUser.setNumber(number);
            if(!location.equals(""))
                extractedCurrentUser.setLocation(location);


            /////////////////



            Gson gson = new Gson();


            //setting the current user to string
            String currentUserString = gson.toJson(extractedCurrentUser);
            editor.putString("currentUser", currentUserString);
            editor.apply();




            //updating the currentUser in the arraylist of accounts

            //extracting for list of sccounts
            String accountsString = sharedPreferences.getString("accounts", "Default");
            Account accountList = gson.fromJson(accountsString, Account.class);

            ArrayList<User> accountUserList = accountList.getUserList();

            for(int i = 0; i < accountUserList.size(); i++){
                System.out.println(accountUserList.get(i).getUsername() + "!!!!!!!");
            }


            int pos = sharedPreferences.getInt("currentUserPosition", -1);

            System.out.println(pos + " " + accountUserList.get(pos).getUsername() + "yyyyyyyyyyyyyyyyyyyyyyyyyyy");

            accountUserList.set(pos, extractedCurrentUser);


            accountList.setUserList(accountUserList);


            String accountsListString = gson.toJson(accountList);
            editor.putString("accounts", accountsListString);
            editor.apply();





            //////////////////////





//            Bundle bundle = new Bundle();
//            //bundle.putString("key","abc"); // Put anything what you want
//            bundle.putString("name", name);
//            bundle.putString("email", email);
//            bundle.putString("number", number);
//            bundle.putString("location", location);

            Fragment fragment = new AccountFragment();
//            fragment.setArguments(bundle);


            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_containerInside, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();




        }
    }
}
