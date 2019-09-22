package com.example.nakhonpathom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nakhonpathom.R;
import com.example.nakhonpathom.model.Place;

import java.util.List;

public class PlaceListAdapter extends ArrayAdapter<Place> {

    private Context mContext;
    private int mResource;
    private List<Place> mPlaceList;

    public PlaceListAdapter(@NonNull Context context, int resource, @NonNull List<Place> placeList) {
        super(context, resource, placeList);
        this.mContext = context;
        this.mResource = resource;
        this.mPlaceList = placeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ทำการ Inflate layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //ใช้ในกรณีที่ List มีข้อมูลเยอะมาก เป็นการรียูสหน้า Layout ที่ inflate แล้วนำมาใช้ใหม่
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(mResource, parent, false);
        }


        //เข้าถึงออบเจ็ค Place ตาม position ที่ ListView ขอมา
        Place place = mPlaceList.get(position);

        //กำหนดชื่อสถานที่ลงใน TextView
        TextView placeNameTextView = v.findViewById(R.id.place_name_text_view);
        placeNameTextView.setText(place.name);

        //กำหนดชื่ออำเถอลงใน TextView
        TextView districtTextView = v.findViewById(R.id.district_text_view);
        districtTextView.setText("อำเภอ".concat(place.district));

        ImageView logoImageView = v.findViewById(R.id.logo_image_view);
        logoImageView.setImageResource(place.imageRes);

        return v;
    }
}
