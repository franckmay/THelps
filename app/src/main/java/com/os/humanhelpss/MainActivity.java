package com.os.humanhelpss;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.os.humanhelpss.Adapter.AdapterSujet;
import com.os.humanhelpss.Model.Sujet;
import com.os.humanhelpss.data.HelpDBHelper;
import com.os.humanhelpss.data.SujetContrat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Sujet> userList;
    AdapterSujet adapter;
    FloatingActionButton floatingActionButton;
    private Cursor cursor;
    private HelpDBHelper emdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emdb = new HelpDBHelper(this);
        cursor = emdb.getAllSujets();

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ine = new Intent(getApplicationContext(), AddSujetActivity.class);
                startActivity(ine);
            }
        });
        initData();
        initRecylerView();
    }

    private void initData() {
        userList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String titre, description;
                byte[] img = cursor.getBlob(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_IMAGE));
                titre = cursor.getString(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_TITRE));
                description = cursor.getString(cursor.getColumnIndex(SujetContrat.SujetEntry.COLUMN_SUJET_DESCRIPTION));


                userList.add(new Sujet(titre, description, img));

            } while (cursor.moveToNext());
        }
        cursor.close();



    }

    private void initRecylerView() {
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterSujet(this, userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}