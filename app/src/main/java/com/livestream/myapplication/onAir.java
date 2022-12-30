package com.livestream.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livestream.myapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class onAir extends Fragment {

    private RecyclerView recyclerView;
    DatabaseReference root ;
    private MyAdapter adapter;
    private ArrayList<Model> list;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 300000;
    SimpleDateFormat simpleDateFomart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_on_air, container, false);

        onAirrecylerview(v);
        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                //do something
                onAirrecylerview(v);

                handler.postDelayed(runnable, delay);
            }
        }, delay);


        return v;

    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        handler.postDelayed( runnable = new Runnable() {
//            public void run() {
//                //do something
//                onAirrecylerview();
//
//                handler.postDelayed(runnable, delay);
//            }
//        }, delay);
//    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        super.onPause();
    }


    private void onAirrecylerview(View v) {
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        GregorianCalendar cal = new GregorianCalendar();
        Calendar calendar = Calendar.getInstance();
        String currentTime = new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
        int nowtime =Integer.parseInt(currentTime);
        int day=cal.get(Calendar.DAY_OF_WEEK);

            //MONDAY TO FRIDAY SHOWS
        if (day==2&& nowtime>=5 && nowtime<8||day==3&& nowtime>=5 && nowtime<8||day==4&& nowtime>=5 && nowtime<8||day==5&& nowtime>=5 && nowtime<8||day==6&& nowtime>=5 && nowtime<8){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-MtD_nxUQ2IRIxxxgVyS");
            // hapa kuna hii id nadhani ni wrong haioneshi ata leo nimenote alafu ukipress back sai inazima background notification
            // lets check hio id, back haifai kuzima background?
            //nimenote ilikuwa saw ni vile 8-9 hakuna show


            query.addChildEventListener(childEventListener);
        }

        if (day==2&& nowtime>=9 && nowtime<13||day==3&& nowtime>=9 && nowtime<13||day==4&& nowtime>=9 && nowtime<13||day==5&& nowtime>=9 && nowtime<13||day==6&& nowtime>=9 && nowtime<13){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3HYCFlJ_0uZj2daeJ");

            query.addChildEventListener(childEventListener);
        }

        if (day==2&& nowtime>=13 && nowtime<16||day==3&& nowtime>=13 && nowtime<16||day==4&& nowtime>=13 && nowtime<16||day==5&& nowtime>=13 && nowtime<16||day==6&& nowtime>=13 && nowtime<16){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3Hlp58VI2IJs3Ecz4");

            query.addChildEventListener(childEventListener);
        }

        if (day==2&& nowtime>=16 && nowtime<19||day==3&& nowtime>=16 && nowtime<19||day==4&& nowtime>=16 && nowtime<19||day==5&& nowtime>=16 && nowtime<19||day==6&& nowtime>=16 && nowtime<19){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3I5WfLaDwCYgXRr0V");

            query.addChildEventListener(childEventListener);
        }

        if (nowtime>=19 && nowtime<20){
            if(day==7){
                Query query = FirebaseDatabase.getInstance().getReference("shows")
                        .orderByChild("id")
                        .equalTo("-Mt3LgUztYSOXBSWEISx");
                // show yenye iko na hii id ita appear

                query.addChildEventListener(childEventListener);

            }
            if(day==1){

                Query query = FirebaseDatabase.getInstance().getReference("shows")
                        .orderByChild("id")
                        .equalTo("-Mt3MvTxekOI4zIxygu0");

                query.addChildEventListener(childEventListener);

            }
            else{
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3IIx4Yprwv7yfyifq");

            query.addChildEventListener(childEventListener);}
        }


        if (day==2&& nowtime>=20 ||day==3&& nowtime>=20 ||day==4&& nowtime>=20 ||day==5&& nowtime>=20){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3Ji4LcUaQD5-uZDD2");

            query.addChildEventListener(childEventListener);
        }
        if (day==2&& nowtime>=0 && nowtime<4||day==3&& nowtime>=0 && nowtime<4||day==4&& nowtime>=0 && nowtime<4||day==5&& nowtime>=0 && nowtime<4||day==6&& nowtime>=0 && nowtime<4){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3JuABOFqhxXRLzKsU");
            // show yenye iko na hii id ita appear

            query.addChildEventListener(childEventListener);
        }

        if (day==6&& nowtime>=0 && nowtime<4){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3JuABOFqhxXRLzKsU");

            query.addChildEventListener(childEventListener);
        }

        if (day==6&& nowtime>=20 ){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3KYuWeVS7-o5DIFnU");


            query.addChildEventListener(childEventListener);
        }

     //WEEKEND / SARTURDAY
        if (day==7&& nowtime>=5 && nowtime<10){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3Kqeknkg7Q_nzsvQ4");

            query.addChildEventListener(childEventListener);
        }
        if (day==7&& nowtime>=10 && nowtime<13){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3L1X-OLJo74nWR6yk");

            query.addChildEventListener(childEventListener);
        }
        if (day==7&& nowtime>=13 && nowtime<16){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3LHhM_rFEnEBpEYoW");

            query.addChildEventListener(childEventListener);
        }
        if (day==7&& nowtime>=16 && nowtime<19){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3LViN4oWx2_Kfkdyx");

            query.addChildEventListener(childEventListener);
        }

        if (day==7&& nowtime>=20) {
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3LrkOaTsuf6ZjQe8e");

            query.addChildEventListener(childEventListener);
        }
        //SUNDAY SHOWS
        if (day==1&& nowtime>=5 && nowtime<9){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3M1wiD0TFvzzmm-tr");

            query.addChildEventListener(childEventListener);
        }
        if (day==1&& nowtime>=9 && nowtime<12){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3MJkXKJ4YZGrjrska");

            query.addChildEventListener(childEventListener);
        }
        if (day==1&& nowtime>=12 && nowtime<16){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3MW0M6fttew5e8Bbu");

            query.addChildEventListener(childEventListener);
        }
        if (day==1&& nowtime>=16 && nowtime<19){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3MjAWpYXcscf1Uvn8");

            query.addChildEventListener(childEventListener);
        }

        if (day==1&& nowtime>=20){
            Query query = FirebaseDatabase.getInstance().getReference("shows")
                    .orderByChild("id")
                    .equalTo("-Mt3N80SV9Ut8g4QZjei");

            query.addChildEventListener(childEventListener);
        }

    }

    ChildEventListener childEventListener=new ChildEventListener() {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            if (dataSnapshot.exists()) {
                Model model = dataSnapshot.getValue(Model.class);

                list.add(model);
            }
            adapter.notifyDataSetChanged();
        }
        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Model changedmodel = dataSnapshot.getValue(Model.class);

            int i=0;

                    list.set(i, changedmodel);
                    adapter.notifyItemChanged(i);

                }


        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Model removedModel = dataSnapshot.getValue(Model.class);
            int i=0;


                    list.remove(i);
                    adapter.notifyItemRemoved(i);


        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}