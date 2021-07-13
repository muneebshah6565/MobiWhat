package com.example.mobiwhat.ui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobiwhat.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MobileDetailFragment extends Fragment {
    ImageView mobImage;
    TextView mobName;
    TextView mobPrice;
    TextView mobDesc;
    Button favourite;
    Button compare;

    TextView mobRam,mobStorage,mobBattery,backCamera,frontCamera,dimmension;


    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3;
    Button descExpandBtn,specExpandBtn,buylinkExpandBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mobile_detail, container, false);
        descExpandBtn=(Button) view.findViewById(R.id.expandableButton1);
        specExpandBtn=(Button) view.findViewById(R.id.expandableButton2);
        buylinkExpandBtn=(Button) view.findViewById(R.id.expandableButton3);

        favourite=(Button) view.findViewById(R.id.fav_btn);
        compare=(Button) view.findViewById(R.id.compare_btn);
        mobImage=(ImageView) view.findViewById(R.id.mob_image);
        mobName=(TextView) view.findViewById(R.id.mob_name);
        mobDesc=(TextView) view.findViewById(R.id.mobDesc);
        mobPrice=(TextView) view.findViewById(R.id.mob_price);

        mobRam=(TextView) view.findViewById(R.id.ram);
        mobStorage=(TextView) view.findViewById(R.id.storage);
        mobBattery=(TextView) view.findViewById(R.id.battery);
        backCamera=(TextView) view.findViewById(R.id.backCamera);
        frontCamera=(TextView) view.findViewById(R.id.frontCamera);
        dimmension=(TextView) view.findViewById(R.id.dimension);


        mobName.setText(getArguments().getString("name"));
        mobDesc.setText(getArguments().getString("desc"));
        mobPrice.setText(getArguments().getString("price"));

        Glide.with(getContext()).load("https://mobiwhat.000webhostapp.com/storage" + getArguments().getString("image")).into(mobImage);

        mobRam.setText(getArguments().getString("ram"));
        mobStorage.setText(getArguments().getString("storage"));
        mobBattery.setText(getArguments().getString("battery"));
        backCamera.setText(getArguments().getString("cameraMain"));
        frontCamera.setText(getArguments().getString("cameraFront"));
        dimmension.setText(getArguments().getString("dimension"));
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        descExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout1 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout1);
                expandableLayout1.toggle();
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> favArray=new ArrayList<>();
                favArray.add(getArguments().getInt("id"));
                Toast.makeText(getContext(),getArguments().getInt("id"),Toast.LENGTH_LONG).show();
                editor.putString("favList",favArray.toString());
                editor.apply();
            }
        });

        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> compArray=new ArrayList<>();
                compArray.add(getArguments().getInt("id"));
                Toast.makeText(getContext(),getArguments().getInt("id"),Toast.LENGTH_LONG).show();
                editor.putString("compList",compArray.toString());
                editor.apply();
            }
        });

        specExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout2);
                expandableLayout2.toggle();
            }
        });

        buylinkExpandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout3 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout3);
                expandableLayout3.toggle();
            }
        });
        return view;
    }
}