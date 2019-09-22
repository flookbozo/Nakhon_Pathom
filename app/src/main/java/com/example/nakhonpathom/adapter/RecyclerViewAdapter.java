package com.example.nakhonpathom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nakhonpathom.PlaceDetailsActivity;
import com.example.nakhonpathom.R;
import com.example.nakhonpathom.model.Place;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private int mResource;
    private List<Place> mPlaceList;

    public RecyclerViewAdapter(Context mContext, int mResource, List<Place> mPlaceList) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mPlaceList = mPlaceList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResource, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Place place = mPlaceList.get(position);

        holder.place = place;
        holder.nameTextView.setText(place.name);
        holder.districtTextView.setText("อำเภอ".concat(place.district));
        holder.placeImageView.setImageResource(place.imageRes);
    }

    @Override
    public int getItemCount() {
        return mPlaceList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView placeImageView;
        private TextView nameTextView;
        private TextView districtTextView;

        private Place place;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.placeImageView = itemView.findViewById(R.id.logo_image_view);
            this.nameTextView = itemView.findViewById(R.id.place_name_text_view);
            this.districtTextView = itemView.findViewById(R.id.district_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PlaceDetailsActivity.class);
                    intent.putExtra("name", place.name);
                    intent.putExtra("image", place.imageRes);
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
