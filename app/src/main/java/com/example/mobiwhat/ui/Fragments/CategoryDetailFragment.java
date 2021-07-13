package com.example.mobiwhat.ui.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.modelsAdapters.TopMobileAdapter;
import com.example.mobiwhat.ui.modelsAdapters.TopMobileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CategoryDetailFragment extends Fragment {
    private static ArrayList<TopMobileModel> data;
    private static RecyclerView.Adapter adapter;
    private RecyclerView categoryRecycle;

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

        categoryRecycle = (RecyclerView) view.findViewById(R.id.categoryRecyclerView);
        if (requireActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            categoryRecycle.setLayoutManager(new GridLayoutManager(getContext(), 1));
        } else {
            categoryRecycle.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        data = new ArrayList<>();
        this.getAPIMobiles();
        return view;
    }

    public void getAPIMobiles() {
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        String url = "https://mobiwhat.000webhostapp.com/api/all_mobiles";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // response
                    try {
                        JSONArray d = new JSONArray(response);
                        JSONObject mobile;
                        for (int i = 0; i < d.length(); i++) {
                            mobile = new JSONObject(d.get(i).toString());
                            String brand = getArguments().getString("brand");
                            Log.d("DATAD", brand);
                            Log.d("DATAD", mobile.getString("brand_name"));
                            if (mobile.getString("brand_name").equalsIgnoreCase(brand)) {
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
                        }
                        adapter = new TopMobileAdapter(data);
                        categoryRecycle.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Log.d("ERROR", "error => " + error.toString())
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer YtLlDhn313P35K5Hkh0oHNIuMjXPQg0i");
                params.put("Auth", "0&Uu^U@%pjSqd,tHof0oI3g^>uTyI~");
                return params;
            }
        };
        queue.add(getRequest);
    }
}