package com.amrita.dscaseb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikepenz.itemanimators.SlideRightAlphaAnimator;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    RecyclerView recyclerView;
    List<event> eventList;
    FirebaseFirestore fref;
    eventListAdapter listAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frg_home,container,false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView=(RecyclerView)view.findViewById(R.id.recview);
        fref=FirebaseFirestore.getInstance();
        eventList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new SlideRightAlphaAnimator());
        fref=FirebaseFirestore.getInstance();
        fref.collection("events").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent( QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                eventList=new ArrayList<>();
                    for (DocumentSnapshot d : queryDocumentSnapshots) {
                        event evt = new event(String.valueOf(d.get("name")),Uri.parse(String.valueOf(d.get("url"))));
                        eventList.add(evt);
                    }
                listAdapter = new eventListAdapter(getContext(), eventList);
                recyclerView.setAdapter(listAdapter);
                }

        });

    }
}
