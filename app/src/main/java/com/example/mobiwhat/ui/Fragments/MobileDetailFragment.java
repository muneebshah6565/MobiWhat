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

        String image,name,desc,price,ram,storage,battery,cm,cf,dim;

        image=getArguments().getString("image");
        name=getArguments().getString("name");
        desc=getArguments().getString("desc");
        price=getArguments().getString("price");
        ram=getArguments().getString("ram");
        storage=getArguments().getString("storage");
        battery=getArguments().getString("battery");
        cm=getArguments().getString("cameraMain");
        cf=getArguments().getString("cameraFront");
        dim=getArguments().getString("dimension");


        mobName.setText(name);
        mobDesc.setText(desc);
        mobPrice.setText(price);

        Glide.with(getContext()).load("https://mobiwhat.000webhostapp.com/storage" + getArguments().getString("image")).into(mobImage);

        mobRam.setText(ram);
        mobStorage.setText(storage);
        mobBattery.setText(battery);
        backCamera.setText(cm);
        frontCamera.setText(cf);
        dimmension.setText(dim);

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
                FavouriteFragment fav=new FavouriteFragment();
                Bundle args=new Bundle();
                args.putString("image",image);
                args.putString("name",name);
                args.putString("desc",desc);
                args.putString("price",price);
                args.putString("ram",ram);
                args.putString("storage",storage);
                args.putString("battery",battery);
                args.putString("mainCamera",cm);
                args.putString("frontCamera",cf);
                args.putString("dimension",dim);
                fav.setArguments(args);
                Toast.makeText(getContext(),"Added to Favourites",Toast.LENGTH_LONG).show();
            }
        });

        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComparisonFragment comp=new ComparisonFragment();
                Bundle args=new Bundle();
                args.putString("image",image);
                args.putString("name",name);
                args.putString("desc",desc);
                args.putString("price",price);
                args.putString("ram",ram);
                args.putString("storage",storage);
                args.putString("battery",battery);
                args.putString("mainCamera",cm);
                args.putString("frontCamera",cf);
                args.putString("dimension",dim);
                comp.setArguments(args);
                Toast.makeText(getContext(),"Added to Compare",Toast.LENGTH_LONG).show();

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