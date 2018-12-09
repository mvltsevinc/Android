package com.example.mvltsevinc.bottomandtopappbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout,container,false);

        listView = view.findViewById(R.id.listView);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));
        cards.add(new Card("drawable://" + R.drawable.denmark,"Denmark"));
        cards.add(new Card("drawable://" + R.drawable.havasu,"Havasu"));

        CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, cards);
        listView.setAdapter(adapter);


        return view;
    }
}
