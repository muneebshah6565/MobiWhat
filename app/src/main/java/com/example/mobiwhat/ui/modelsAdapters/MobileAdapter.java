package com.example.mobiwhat.ui.modelsAdapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.Fragments.MobileDetailFragment;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileHolder> {

    private ArrayList<MobileModel> dataSet;

    public MobileAdapter(ArrayList<MobileModel> dataSet) {
        this.dataSet = dataSet;
    }

    private Context con;

    @NonNull
    @Override
    public MobileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        con=parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_mobile_item, parent, false);
        MobileHolder holder=new MobileHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MobileHolder holder, int position) {
        TextView mobileName = holder.mobName;
        TextView mobileDesc = holder.mobDesc;
        TextView mobilePrice = holder.mobPrice;

        ImageView mobileImage=holder.mobImage;

        mobileName.setText(dataSet.get(position).getName());
        mobileDesc.setText(dataSet.get(position).getDesc());
        mobilePrice.setText(String.valueOf(dataSet.get(position).getPrice()));

        Glide.with(con).load("https://mobiwhat.000webhostapp.com/storage" + dataSet.get(position).getImage()).into(mobileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args=new Bundle();
                Log.d("DATAD", String.valueOf(dataSet.get(position).getId()));
                args.putInt("id", dataSet.get(position).getId());
                args.putString("name",dataSet.get(position).getName());
                args.putString("desc",dataSet.get(position).getDesc());
                args.putString("price", String.valueOf(dataSet.get(position).getPrice()));
                args.putString("image",dataSet.get(position).getImage());
                args.putString("ram", String.valueOf(dataSet.get(position).getRam()));
                args.putString("storage", String.valueOf(dataSet.get(position).getStorage()));
                args.putString("battery", String.valueOf(dataSet.get(position).getBattery()));
                args.putString("cameraMain", String.valueOf(dataSet.get(position).getCameraMain()));
                args.putString("cameraFront", String.valueOf(dataSet.get(position).getCameraFront()));
                args.putString("dimension",dataSet.get(position).getDimensions());
                Navigation.findNavController(v).navigate(R.id.nav_mobile_detail,args);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MobileHolder extends RecyclerView.ViewHolder {
        TextView mobName;
        TextView mobDesc;
        ImageView mobImage;
        TextView  mobPrice;

        public MobileHolder(@NonNull View itemView) {
            super(itemView);
            this.mobName = (TextView) itemView.findViewById(R.id.mob_name);
            this.mobDesc = (TextView) itemView.findViewById(R.id.mob_desc);

            this.mobImage = (ImageView)itemView.findViewById(R.id.mob_image);
            this.mobPrice = (TextView) itemView.findViewById(R.id.mob_price);

        }
    }
}
