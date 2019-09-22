package com.example.nakhonpathom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nakhonpathom.adapter.PlaceListAdapter;
import com.example.nakhonpathom.adapter.RecyclerViewAdapter;
import com.example.nakhonpathom.model.Place;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*private String[] mPlaceList = new String[]{
            "พระปฐมเจดีย์", "บ้านปายนา", "พิพิธภัณฑ์รถเก่า", "ตลาดท่านา", "วัดกลางบางแก้ว", "ตลาดน้ำลำพญา", "ตลาดน้ำทุ่งบัวแดง"
    };*/

    //private Place[] mPlaceList = new Place[10];
    private List<Place> mPlaceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateData();
        //ListView placeListView = findViewById(R.id.place_list_view);

        /*//สร้าง Adapter
        PlaceListAdapter adapter = new PlaceListAdapter(
                MainActivity.this,
                R.layout.item_place, //ระบุ layout ของแต่ละ item ใน list
                mPlaceList //แหล่งข้อมูลในที่นี้คือ อาร์เรย์
        );
        placeListView.setAdapter(adapter);

        //สร้าง listener เพื่อระบุโค้ดการทำงาน เมื่อแต่ละ Item ถูกคลิก
        placeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //อ่านชื่อสถานที่ของ Item ที่ถูกคลิก จากอาร์เรย์มาเก็บลงตัวแปร
                Place place = mPlaceList.get(i);
                String placeName = place.name;
                //แสดงชื่อสถานที่ออกมาใน Toast
                Toast.makeText(MainActivity.this, placeName, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, PlaceDetailsActivity.class);
                intent.putExtra("name", place.name);
                intent.putExtra("image", place.imageRes);
                startActivity(intent);
            }
        });*/

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                MainActivity.this,
                R.layout.item_place,
                mPlaceList
        );

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    private void populateData() {
        Place place = new Place("พระปฐมเจดีย์", "เมือง", R.drawable.prathomchedi);
        mPlaceList.add(place);
        place = new Place("บ้านปายนา", "นครชัยศรี", R.drawable.banpayna);
        mPlaceList.add(place);
        place = new Place("ตลาดท่านา", "นครชัยศรี", R.drawable.thanamarket);
        mPlaceList.add(place);
        place = new Place("พิพิธภัณฑ์รถเก่า", "นครชัยศรี", R.drawable.jesadatechnikmuseum);
        mPlaceList.add(place);
        place = new Place("วัดกลางบางแก้ว", "นครชัยศรี", R.drawable.watklangbangkaew);
        mPlaceList.add(place);
        place = new Place("ตลาดน้ำลำพญา", "บางเลน", R.drawable.thanamarket);
        mPlaceList.add(place);
        place = new Place("ตลาดน้ำทุ่งบัวแดง", "บางเลน", R.drawable.namtungbuadang);
        mPlaceList.add(place);
        place = new Place("Tree & Tide Riverside", "บางเลน", R.drawable.treetide);
        mPlaceList.add(place);

    }
}
