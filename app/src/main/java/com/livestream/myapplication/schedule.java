package com.livestream.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livestream.myapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class schedule extends Fragment {
    private RecyclerView recyclerView;
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private final DatabaseReference root = db.getReference().child("shows");
    private MyAdapter adapter;
    private ArrayList<Model> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_showactivity, container, false);

        initrecylerview(v);

        return v;

    }
    private void initrecylerview(View v) {
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), list);

        recyclerView.setAdapter(adapter);



        root.orderByChild("timestart").startAt("00:00").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Model model = dataSnapshot.getValue(Model.class);

                list.add(model);
                adapter.notifyItemInserted(list.size());
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Model changedmodel = dataSnapshot.getValue(Model.class);

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(changedmodel.getId())) {
                        list.set(i, changedmodel);
                        adapter.notifyItemChanged(i);
                        break;
                    }
                }
            }


            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Model removedModel = dataSnapshot.getValue(Model.class);

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(removedModel.getId())) {
                        list.remove(i);
                        adapter.notifyItemRemoved(i);
                        break;
                    }
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}