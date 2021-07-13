package com.example.mobiwhat.ui.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.modelsAdapters.BrandsModel;
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

public class TopMobilesFragment extends Fragment {

    private static ArrayList<TopMobileModel> data;
    private static RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mobiles, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.MobRecyclerView);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        data = new ArrayList<TopMobileModel>();
        progressBar=(ProgressBar) root.findViewById(R.id.mobViewProgress);
        progressBar.setVisibility(View.VISIBLE);
        this.getAPIMobiles();
        return root;
    }
    public void getAPIMobiles(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://mobiwhat.000webhostapp.com/api/all_mobiles";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // response
                    try {
                        JSONArray d = new JSONArray(response);
                        JSONObject mobile;
                        for (int i = 0; i < d.length(); i++) {
                            mobile = new JSONObject(d.get(i).toString());
                            data.add(new TopMobileModel(
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
                        adapter = new TopMobileAdapter(data);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Log.d("ERROR","error => "+error.toString())
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