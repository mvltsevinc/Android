package com.example.mvltsevinc.cardviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ListView cardListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardListView = findViewById(R.id.cardListView);

        ArrayList<Cards> cardList = new ArrayList<Cards>();
        cardList.add(new Cards("drawable://" + R.drawable.a,"Resim1"));
        cardList.add(new Cards("drawable://" + R.drawable.b,"Resim2"));
        cardList.add(new Cards("drawable://" + R.drawable.a,"Resim1"));
        cardList.add(new Cards("drawable://" + R.drawable.b,"Resim2"));
        cardList.add(new Cards("drawable://" + R.drawable.b,"Resim1"));
        cardList.add(new Cards("drawable://" + R.drawable.a,"Resim2"));

        CustomListAdapter adapter = new CustomListAdapter(this,R.layout.listview_layout,cardList);
        cardListView.setAdapter(adapter);

    }
}
