package com.example.crimson.crimson;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_profile extends Fragment {

    public View parentHolder;

    public FirebaseAuth mAuth;
    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();

    public UserDetails userDetails_object;

    private EditText nameOfUser;
    private EditText ageOfUser;
    private EditText occupationOfUser;
    private EditText annualIncomeOfUser;

    private CheckBox subscriptionCheckbox;
    private CheckBox isSilver;
    private CheckBox isGolden;
    private CheckBox isDiamond;

    private Button submitUserDetails;

    private String nameOfUserString;
    private String ageOfUserString;
    private String occupationOfUserString;
    private String annualIncomeOfUserString;

    private boolean userType;
    private boolean isSilverUser;
    private boolean isGoldenUser;
    private boolean isDiamondUser;

    public User_profile() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       parentHolder = inflater.inflate(R.layout.fragment_user_profile, container, false);

       nameOfUser = (EditText)parentHolder.findViewById(R.id.editTextName);
       ageOfUser = (EditText)parentHolder.findViewById(R.id.editTextAge);
       occupationOfUser = (EditText)parentHolder.findViewById(R.id.editTextOccupation);
       annualIncomeOfUser = (EditText)parentHolder.findViewById(R.id.editTextIncome);
       subscriptionCheckbox = (CheckBox)parentHolder.findViewById(R.id.checkBoxSubscription);
       isSilver = (CheckBox)parentHolder.findViewById(R.id.checkBoxSilver);
       isGolden = (CheckBox)parentHolder.findViewById(R.id.checkBoxGolden);
       isDiamond = (CheckBox)parentHolder.findViewById(R.id.checkBoxDiamond);
       submitUserDetails = (Button)parentHolder.findViewById(R.id.buttonSubmitUserDetails);

       submitUserDetails.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               nameOfUserString = nameOfUser.getText().toString();
               ageOfUserString = ageOfUser.getText().toString();
               occupationOfUserString = occupationOfUser.getText().toString();
               annualIncomeOfUserString = annualIncomeOfUser.getText().toString();
               userType = subscriptionCheckbox.isChecked();
               isSilverUser = isSilver.isChecked();
               isGoldenUser = isGolden.isChecked();
               isDiamondUser = isDiamond.isChecked();


               if ((TextUtils.isEmpty(nameOfUserString)) || (TextUtils.isEmpty(ageOfUserString)|| !TextUtils.isDigitsOnly(ageOfUserString)) || (TextUtils.isEmpty(annualIncomeOfUserString)|| !TextUtils.isDigitsOnly(annualIncomeOfUserString) )|| (TextUtils.isEmpty(occupationOfUserString))) {
                   Toast.makeText(parentHolder.getContext(), "Enter All Details", Toast.LENGTH_LONG).show();
               }
               else if(isSilverUser){

                   userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                           .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType)
                           .setUserTypeSilver(isSilverUser).create();
                   mDbRef.child("User Details").push().setValue(userDetails_object);
               }
               else if(isGoldenUser){

                   userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                           .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType)
                           .setUserTypeGolden(isGoldenUser).create();
                   mDbRef.child("User Details").push().setValue(userDetails_object);
               }
               else if(isDiamondUser){

                   userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                           .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType)
                           .setUserTypeDiamond(isDiamondUser).create();
                   mDbRef.child("User Details").push().setValue(userDetails_object);
               }
               else {
                   userDetails_object = new UserDetails.Builder().setNameOfUser(nameOfUserString).setAgeOfUser(ageOfUserString)
                           .setOccupationOfUser(occupationOfUserString).setAnnualIncomeOfUser(annualIncomeOfUserString).setUserType(userType).create();
                   mDbRef.child("User Details").push().setValue(userDetails_object);
               }


               if(userType){

                   User user = new WithOffers(new WithFreeCredit(new BasicUser()));
                   Toast.makeText(parentHolder.getContext(), " "+ user.createRegisteredUser(), Toast.LENGTH_LONG).show();
               }
           }
       });

       return parentHolder;
    }

}