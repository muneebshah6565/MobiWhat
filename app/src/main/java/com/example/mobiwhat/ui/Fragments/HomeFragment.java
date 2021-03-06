package com.example.mobiwhat.ui.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.modelsAdapters.CategoryAdapter;
import com.example.mobiwhat.ui.modelsAdapters.MobileAdapter;
import com.example.mobiwhat.ui.modelsAdapters.MobileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    ViewFlipper slider;
    private static ArrayList<MobileModel> data;
    private static RecyclerView.Adapter adapter;

    private RecyclerView mobilesRecycle;
    private ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList personNames = new ArrayList<>(Arrays.asList("Samsung", "Huawei", "Oppo", "Apple", "Xiaomi","Vivo"));

        int sliderImages[]={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};
 // Categories
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.catRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CategoryAdapter customAdapter = new CategoryAdapter(personNames);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//Slider
        slider=root.findViewById(R.id.slider);

        for(int image:sliderImages){
            FlipperImages(image);
        }
//Mobile Recycle View

        mobilesRecycle = (RecyclerView) root.findViewById(R.id.mobilesRecyclerView);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mobilesRecycle.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        else{
           mobilesRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
            CardView sliderLayout=(CardView) root.findViewById(R.id.slider_card);
            ViewGroup.LayoutParams params=sliderLayout.getLayoutParams();
            params.height=600;
            sliderLayout.setLayoutParams(params);
        }
        data = new ArrayList<MobileModel>();
        this.getAPIMobiles();
        progressBar=(ProgressBar) root.findViewById(R.id.mobViewProgress);
        progressBar.setVisibility(View.VISIBLE);
        return root;
    }

    public void FlipperImages(int image){
        ImageView imageview=new ImageView(getContext());
        imageview.setBackgroundResource(image);

        slider.addView(imageview);
        slider.setFlipInterval(2000);
        slider.setAutoStart(true);

        slider.setInAnimation(getContext(), android.R.anim.slide_in_left);
        slider.setOutAnimation(getContext(), android.R.anim.slide_out_right);

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
                            Log.d("DATAD", String.valueOf(mobile.getInt("id")));
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
                        mobilesRecycle.setAdapter(adapter);
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