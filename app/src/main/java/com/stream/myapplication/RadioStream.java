package com.stream.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.IntentFilter;
import android.content.pm.PackageManager;

import android.net.ConnectivityManager;
import android.net.Uri;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.stream.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RadioStream extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityResultLauncher<String[]>mPermissionResultLauncher;
    private Boolean isReadPermissiongranted=false;
    private Boolean isLocationPermissiongranted=false;
    private Boolean isRecordPermissiongranted=false;
    private Boolean isCallPermissionGranted=false;
    private Boolean isSmsPermissiongranted=false;

    private Button playButton, stopButton;
    private long backPressedTime;
    private Toast backToast;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    FirebaseAuth mAuth;
    private boolean isOnline=false;

    private BottomNavigationView bottomNavigationView;

    private AlarmManagerBroadcastReceiver alarm;

    //Settings
    Settings settings = new Settings();

    private final String EMAILADD = settings.getEmailAddress();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("shows");
    private BottomNavigationView buttomnav;
    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private vpAdapter adapter;

    // Progress dialogue and broadcast receiver variables
    boolean mBufferBroadcastIsRegistered;
    private ProgressDialog pdBuff = null;



    /**
     * Done upon opening the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_stream);


         //checks comnectivityy first
        checkConnectivity();


            mPermissionResultLauncher=registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
                @Override
                public void onActivityResult(Map<String, Boolean> result) {
                    if(result.get(Manifest.permission.READ_EXTERNAL_STORAGE)!=null){
                        isReadPermissiongranted=result.get(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                    if(result.get(Manifest.permission.ACCESS_FINE_LOCATION)!=null){
                        isLocationPermissiongranted=result.get(Manifest.permission.ACCESS_FINE_LOCATION);
                    }
                    if(result.get(Manifest.permission.RECORD_AUDIO)!=null){
                        isRecordPermissiongranted=result.get(Manifest.permission.RECORD_AUDIO);
                    }
                    if(result.get(Manifest.permission.CALL_PHONE)!=null){
                        isCallPermissionGranted=result.get(Manifest.permission.CALL_PHONE);
                    }
                    if(result.get(Manifest.permission.SEND_SMS)!=null){
                        isSmsPermissiongranted=result.get(Manifest.permission.SEND_SMS);
                    }

                }
            });
            requestPermission();




        //ended here
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Radio Kaya 93.1 FM");
        navigationView = (NavigationView) findViewById(R.id.nested);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
        buttomnav =(BottomNavigationView) findViewById(R.id.navigation);

        playButton =(Button) findViewById(R.id.PlayButton);


        // TAB LAYOUT  STARTS HERE
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager2 = (ViewPager2) findViewById(R.id.view_pager2);

        tabLayout.addTab(tabLayout.newTab().setText("On Air"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));

        FragmentManager fm = getSupportFragmentManager();
        adapter = new vpAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        // TAB LAYOUT ENDS HERE


        playButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                checkConnectivity();
                if(isOnline) {
                    Intent intent = new Intent(getApplicationContext(), RadioMediaPlayerService.class);
                    intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
                    getApplicationContext().startService(intent);
                }
                else{

                        AlertDialog alertDialog = new AlertDialog.Builder(RadioStream.this).create();
                        alertDialog.setTitle("Network Not Connected...");
                        alertDialog.setMessage("Please connect to a network and try again");
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // here you can add functions
                            }
                        });

                        alertDialog.show();


                }
            }
        });
        //Stop button
        stopButton = findViewById(R.id.StopButton);
        stopButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Get new MediaPlayerService activity
                Intent intent = new Intent(getApplicationContext(),
                        RadioMediaPlayerService.class);
                stopService(intent);
            }
        });

        buttomnav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.call:
                                String phoneNum = settings.getPhoneNumber();
                                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNum, settings.getPhoneNumber()));
                                startActivity(phoneIntent);
                                break;

                            case R.id.website:

                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getWebsiteURL())); //URL
                                startActivity(browserIntent);
                                break;

                            case R.id.sms:
                                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + settings.getSmsNumber()));
                                smsIntent.putExtra("sms_body", "RADIO KAYA ");
                                startActivity(smsIntent);
                                break;


                            case R.id.email:
                                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                emailIntent.setType("message/rfc822");
                                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAILADD});

                                try {
                                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(RadioStream.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                }
                                break;

                        }
                        return true;
                    }
                });
        if(isOnline) {
            Intent intent = new Intent(getApplicationContext(), RadioMediaPlayerService.class);
            intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
            getApplicationContext().startService(intent);
        }
             else {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Network Not Connected...");
                alertDialog.setMessage("Please connect to a network and try again");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });

                alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawableResource(R.color.blackbright);
            }


        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


    }
    //onresume

    // Handle progress dialogue for buffering...
    private void showPD(Intent bufferIntent) {
        String bufferValue = bufferIntent.getStringExtra("buffering");
        int bufferIntValue = Integer.parseInt(bufferValue);

        // When the broadcasted "buffering" value is 1, show "Buffering"
        // progress dialogue.
        // When the broadcasted "buffering" value is 0, dismiss the progress
        // dialogue.

        switch (bufferIntValue) {
            case 0:
                // Log.v(TAG, "BufferIntValue=0 RemoveBufferDialogue");
                // txtBuffer.setText("");
                if (pdBuff != null) {
                    pdBuff.dismiss();
                }
                break;

            case 1:
                BufferDialogue();
                break;


        }
    };

        // Progress dialogue...
        private void BufferDialogue() {

            pdBuff = ProgressDialog.show(RadioStream.this, "Loading Radio Kaya Stream...",
                    "Acquiring stream...", true);
            pdBuff.getWindow().setBackgroundDrawableResource(R.color.teal_700);
        }

        // Set up broadcast receiver
        private BroadcastReceiver broadcastBufferReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent bufferIntent) {
                showPD(bufferIntent);
            }
        };

    // -- onResume register broadcast receiver. To improve, retrieve saved screen data ---
    @Override
    protected void onResume() {
        // Register broadcast receiver
        if (!mBufferBroadcastIsRegistered) {
            registerReceiver(broadcastBufferReceiver, new IntentFilter(
                    RadioMediaPlayerService.BROADCAST_BUFFER));
            mBufferBroadcastIsRegistered = true;
        }

        super.onResume();
    }
    // -- onPause, unregister broadcast receiver. To improve, also save screen data ---
    @Override
    protected void onPause() {
        // Unregister broadcast receiver
        if (mBufferBroadcastIsRegistered) {
            unregisterReceiver(broadcastBufferReceiver);
            mBufferBroadcastIsRegistered = false;
        }
        super.onPause();
    }
    private void checkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting()
                || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting())
            isOnline = true;
        else
            isOnline = false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()) {

            case R.id.call: {
                String phoneNum = settings.getPhoneNumber();

                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNum, settings.getPhoneNumber()));

                startActivity(phoneIntent);
                break;

            }
            case R.id.website: {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getWebsiteURL())); //URL
                startActivity(browserIntent);
                break;
            }

            //added this
            case R.id.facebook: {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getFacebook())); //URL
                startActivity(browserIntent);
                break;
            }
            case R.id.twitter: {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getTwitter())); //URL
                startActivity(browserIntent);
                break;
            }
            case R.id.instagram: {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getInstagram())); //URL
                startActivity(browserIntent);
                break;
            }

            case R.id.youtube: {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getYoutube())); //URL
                startActivity(browserIntent);
                break;
            }
                //add ended
            case R.id.sms: {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + settings.getSmsNumber()));
                smsIntent.putExtra("sms_body", "RADIO KAYA ");
                startActivity(smsIntent);
                break;
            }

            case R.id.email: {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAILADD});

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RadioStream.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.share: {
                shareApp(this);
            }

        }
        return true;
    }

    private void requestPermission(){
        isReadPermissiongranted=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED;
        isCallPermissionGranted=ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED;
        isSmsPermissiongranted=ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED;

        List<String>permissionGranted=new ArrayList<String>();
        if(!isReadPermissiongranted){
            permissionGranted.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        }

        if(!isCallPermissionGranted){
            permissionGranted.add(Manifest.permission.CALL_PHONE);

        }
        if(!isSmsPermissiongranted){
            permissionGranted.add(Manifest.permission.SEND_SMS);

        }
        if(!permissionGranted.isEmpty()){
            mPermissionResultLauncher.launch(permissionGranted.toArray(new String[0]));

        }
    }
    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public static void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            closeDrawer();
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}

