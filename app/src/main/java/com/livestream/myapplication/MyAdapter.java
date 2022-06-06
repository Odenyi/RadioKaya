package com.livestream.myapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.livestream.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    private String mstart;
    private String mshow = "";
    private String post_key = "";
    private Calendar calendar;
    private AlarmManager alarmManager;

    public MyAdapter(Context context, ArrayList<Model> mList) {

        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.schedule, parent, false);

        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.presenters.setText(model.getPresenters());
        holder.show.setText(model.getShow());
        holder.start.setText(model.getTimestart());
        holder.stop.setText(model.getTimestop());
        holder.day.setText(model.getDay());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_key = model.getId();
                mshow = model.getShow();
                mstart = model.getTimestart();

                updateData();
            }
        });
        //add ended

    }
//added this
    private void updateData() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.setnotification, null);
        myDialog.setView(mView);
        final AlertDialog dialog = myDialog.create();


        final TextView showtv = mView.findViewById(R.id.shownotify);
        final TextView timenotify = mView.findViewById(R.id.timenotify);
        timenotify.setText(mstart);
        showtv.setText(mshow);
        ImageView notify = mView.findViewById(R.id.notify);

        Button cancel = mView.findViewById(R.id.cancel);
        Button set = mView.findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int hours = Integer.parseInt(mstart.substring(0, mstart.indexOf(":")));
                int minutes = Integer.parseInt(mstart.substring(1, mstart.indexOf(":")));
                calendar.set(Calendar.HOUR_OF_DAY, hours);
                calendar.set(Calendar.MINUTE, minutes);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                setalarm();


                dialog.dismiss();
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                if (alarmManager == null) {
                    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                }
                alarmManager.cancel(pendingIntent);
                Toast.makeText(context, "alarm canceled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void setalarm() {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(context, "Reminder is set ", Toast.LENGTH_SHORT).show();
    }
    //add ended here

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView presenters, show, start, stop, day;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            presenters = itemView.findViewById(R.id.presentersTv1);
            show = itemView.findViewById(R.id.showTv1);
            start = itemView.findViewById(R.id.fromtv1);
            stop = itemView.findViewById(R.id.totv1);
            day = itemView.findViewById(R.id.daytv1);
        }
    }

}