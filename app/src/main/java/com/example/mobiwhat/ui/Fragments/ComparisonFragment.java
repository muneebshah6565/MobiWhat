package com.example.mobiwhat.ui.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.modelsAdapters.TopMobileAdapter;
import com.example.mobiwhat.ui.modelsAdapters.TopMobileModel;

import java.util.ArrayList;

public class ComparisonFragment extends Fragment {
    private static ArrayList<TopMobileModel> data;
    private static RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_comparison, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.MobRecyclerView);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        data = new ArrayList<TopMobileModel>();
        progressBar=(ProgressBar) view.findViewById(R.id.mobViewProgress);
//        progressBar.setVisibility(View.VISIBLE);
        getComparisonItems();
        return view;
    }

    private void getComparisonItems() {
        String image,name,desc,price,ram,storage,battery,cm,cf,dim;
        int id;

        try{
            id = getArguments().getInt("id");
            image=getArguments().getString("image");
            name=getArguments().getString("name");
            desc=getArguments().getString("desc");
            price=getArguments().getString("price");
            ram=getArguments().getString("ram");
            storage=getArguments().getString("storage");
            battery=getArguments().getString("battery");
            cm=getArguments().getString("mainCamera");
            cf=getArguments().getString("frontCamera");
            dim=getArguments().getString("dimension");

            data.add(new TopMobileModel(id, image,name,desc,Integer.parseInt(price),Integer.parseInt(ram),Integer.parseInt(storage),Integer.parseInt(battery),Integer.parseInt(cm),Integer.parseInt(cf),dim));

            adapter = new TopMobileAdapter(data);
            recyclerView.setAdapter(adapter);
//            progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}