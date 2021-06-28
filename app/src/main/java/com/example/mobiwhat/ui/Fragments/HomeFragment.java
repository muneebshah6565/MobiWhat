package com.example.mobiwhat.ui.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mobiwhat.R;
import com.example.mobiwhat.ui.modelsAdapters.CategoryAdapter;
import com.example.mobiwhat.ui.modelsAdapters.MobileAdapter;
import com.example.mobiwhat.ui.modelsAdapters.MobileModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    ViewFlipper slider;
    private static ArrayList<MobileModel> data;
    private static RecyclerView.Adapter adapter;
    private Integer[] drawableArray = {
            R.drawable.iphone12, R.drawable.itel, R.drawable.lenovo,
            R.drawable.lg, R.drawable.motorola, R.drawable.xiaomiredmi,
            R.drawable.nokia7plus, R.drawable.onepluspro, R.drawable.oppoa15,
            R.drawable.samsungs20, R.drawable.sonyxperia2, R.drawable.vivopro
    };

    private RecyclerView mobilesRecycle;

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
        String[] nameArray = {"IPhone 12 Pro", "Itel S15 Pro", "Lenovo K8 Note", "LG G8S", "Motorola Moto", "Xiaomi Redmi Note 9", "Nokia 7 Plus","One Plus Pro", "Oppo A15", "Samsung S20", "Sony Xperia2","Vivo X60 Pro"};
        String[] descArray = {
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
        };

        Integer[] priceArray = {
                100,300,200,400,600,700,100,300,200,400,600,700
        };

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
                            data.add(new MobileModel(
                                    mobile.getString("cover"),
                                    mobile.getString("name"),
                                    mobile.getString("description"),
                                    mobile.getInt("price")
                            ));
                        }
                        adapter = new MobileAdapter(data);
                        mobilesRecycle.setAdapter(adapter);
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