package com.example.mobiwhat.ui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.modelsAdapters.MobileAdapter;
import com.example.mobiwhat.ui.modelsAdapters.MobileModel;
import com.example.mobiwhat.ui.modelsAdapters.TopMobileAdapter;
import com.example.mobiwhat.ui.modelsAdapters.TopMobileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FavouriteFragment extends Fragment {

    private ArrayList<MobileModel> data;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    SharedPreferences sharedPref;

    String favourites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.MobRecyclerView);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }

        sharedPref = getActivity().getSharedPreferences("lists", Context.MODE_PRIVATE);

        data = new ArrayList<MobileModel>();
        progressBar=(ProgressBar) view.findViewById(R.id.mobViewProgress);
        progressBar.setVisibility(View.VISIBLE);

        favourites = getFavourites();

        this.getAPIMobiles();

        return view;
    }

    private String getFavourites() {
        String favList =  sharedPref.getString("favList", null);

        if(favList != null && !favList.equals("") && !favList.equals("[]")){
            return favList;
        }
        return "[]";
    }

    public void getAPIMobiles(){
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = "https://mobiwhat.000webhostapp.com/api/mobile-list?q="+favourites;
        Log.d("DATAD", url);
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // response
                    try {
                        JSONArray d = new JSONArray(response);
                        JSONObject mobile;
                        for (int i = 0; i < d.length(); i++) {
                            mobile = new JSONObject(d.get(i).toString());
                            data.add(new MobileModel(
                                    mobile.getInt("id"),
                                    mobile.getString("cover"),
                                    mobile.getString("name"),
                                    mobile.getString("description"),
                                    mobile.getInt("price"),
                                    mobile.getInt("ram"),
                                    mobile.getInt("storage"),
                                    mobile.getInt("battery_capacity"),
                                    mobile.getInt("camera_main"),
                                    mobile.getInt("camera_front"),
                                    mobile.getString("dimensions")
                            ));
                        }
                        adapter = new MobileAdapter(data);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                    }

                },
                error -> {
                    Log.d("ERROR","error => "+error.toString());
                    progressBar.setVisibility(View.GONE);
                }

        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();
                params.put("Authorization", "Bearer YtLlDhn313P35K5Hkh0oHNIuMjXPQg0i");
                params.put("Auth", "0&Uu^U@%pjSqd,tHof0oI3g^>uTyI~");
                return params;
            }

        };
        queue.add(getRequest);
    }
}