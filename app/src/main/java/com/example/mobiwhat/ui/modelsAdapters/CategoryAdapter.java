package com.example.mobiwhat.ui.modelsAdapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiwhat.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    ArrayList categoryNames;

    public CategoryAdapter(ArrayList categoryNames) {
        this.categoryNames = categoryNames;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.single_category_item, parent, false);
        CategoryHolder catHolder = new CategoryHolder(listItem);
        return catHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

         holder.textView.setText(categoryNames.get(position).toString());

         holder.catItem.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 Bundle args=new Bundle();
                 args.putString("brand", categoryNames.get(position).toString());
                 Navigation.findNavController(v).navigate(R.id.nav_brand_detail, args);
             }
         });

    }

    @Override
    public int getItemCount() {
        return categoryNames.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public MaterialCardView catItem;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            this.textView=(TextView) itemView.findViewById(R.id.categoryName);
            catItem= (MaterialCardView) itemView.findViewById(R.id.item);
        }
    }
}
