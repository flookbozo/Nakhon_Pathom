package com.example.nakhonpathom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nakhonpathom.adapter.PlaceListAdapter;
import com.example.nakhonpathom.adapter.RecyclerViewAdapter;
import com.example.nakhonpathom.db.DatabaseHelper;
import com.example.nakhonpathom.model.Place;

import java.util.ArrayList;
import java.util.List;

import static com.example.nakhonpathom.db.DatabaseHelper.COL_DISTRICT;
import static com.example.nakhonpathom.db.DatabaseHelper.COL_ID;
import static com.example.nakhonpathom.db.DatabaseHelper.COL_IMAGE;
import static com.example.nakhonpathom.db.DatabaseHelper.COL_NAME;
import static com.example.nakhonpathom.db.DatabaseHelper.TABLE_PLACE;

public class MainActivity extends AppCompatActivity {

    /*private String[] mPlaceList = new String[]{
            "พระปฐมเจดีย์", "บ้านปายนา", "พิพิธภัณฑ์รถเก่า", "ตลาดท่านา", "วัดกลางบางแก้ว", "ตลาดน้ำลำพญา", "ตลาดน้ำทุ่งบัวแดง"
    };*/

    //private Place[] mPlaceList = new Place[10];
    private List<Place> mPlaceList = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populateData();

        mDbHelper = new DatabaseHelper(MainActivity.this);
        mDatabase = mDbHelper.getWritableDatabase();

        readFromDb();

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

        mAdapter = new RecyclerViewAdapter(
                MainActivity.this,
                R.layout.item_place,
                mPlaceList
        );

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(mAdapter);

        Button addPlaceButton = findViewById(R.id.add_place_button);
        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: อ่านข้อมูลจาก place_name_edit_text แล้ว insert ลง database

                //อ่านข้อมูลจาก place_name_edit_text มาพักเก็บลงตัวแปร name
                EditText placeNameEditText = findViewById(R.id.place_name_edit_text);
                String name = placeNameEditText.getText().toString();

                //เอาตัวแปร name insert ไปใน database
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, name);
                cv.put(COL_DISTRICT, "");
                cv.put(COL_IMAGE, R.drawable.ic_launcher_foreground);
                mDatabase.insert(TABLE_PLACE, null, cv);

                readFromDb();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void readFromDb() {
        mPlaceList.clear();

        Cursor cursor = mDatabase.query(TABLE_PLACE, null, null, null, null,null, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            String district = cursor.getString(cursor.getColumnIndex(COL_DISTRICT));
            int image = cursor.getInt(cursor.getColumnIndex(COL_IMAGE));

            Place place = new Place(id, name, district, image);
            mPlaceList.add(place);

        }
    }

    private void populateData() {
        /*Place place = new Place("พระปฐมเจดีย์", "เมือง", R.drawable.prathomchedi);
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
        mPlaceList.add(place);*/

    }
}
