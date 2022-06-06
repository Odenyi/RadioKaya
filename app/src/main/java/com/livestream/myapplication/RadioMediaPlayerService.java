//package com.example.myapplication;
//
//import java.io.IOException;
//import android.annotation.SuppressLint;
//import android.app.Notification;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnBufferingUpdateListener;
//import android.media.MediaPlayer.OnPreparedListener;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.IBinder;
//import android.util.Log;
//import android.widget.Toast;
//
//
//
//
//public class RadioMediaPlayerService extends Service {
//
//    //Variables
//    private boolean isPlaying = false;
//    private MediaPlayer radioPlayer; //The media player instance
//    private static final int classID =0; // just a number
//    public static String START_PLAY = "START_PLAY";
//
//    //Settings
//    Settings settings = new Settings();
//
//     //logtag
//    private static final String LOG_TAG = "Notification Service";
//
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//
//    /**
//     * Starts the streaming service
//     */
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent.getBooleanExtra(START_PLAY, false)) {
//            play();
//        }
//
//        return Service.START_STICKY;
////
////        if (intent != null) {
////               if (intent.getBooleanExtra(START_PLAY, false)) {
////                Log.i(LOG_TAG, "Received Start Foreground Intent ");
//////                showNotification();
////                play();
////
////            } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
////                Log.i(LOG_TAG, "Clicked Previous");
////            } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
////                Log.i(LOG_TAG, "Clicked Play");
////            } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
////                Log.i(LOG_TAG, "Clicked Next");
////            } else if (intent.getAction().equals(
////                    Constants.ACTION.STOPFOREGROUND_ACTION)) {
////                Log.i(LOG_TAG, "Received Stop Foreground Intent");
////                stopForeground(true);
////                stopSelf();
////            }
////        }
////        return START_STICKY;
//    }
//
//    private void showNotification() {
//        Intent notificationIntent = new Intent(this, RadioStream.class);
//        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplication(),
//                0,
//                notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent previousIntent = new Intent(this, RadioMediaPlayerService.class);
//        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
//        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
//                previousIntent, 0);
//
//        Intent playIntent = new Intent(this, RadioMediaPlayerService.class);
//        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
//        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
//                playIntent, 0);
//
////        Intent nextIntent = new Intent(this, RadioMediaPlayerService.class);
////        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
////        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
////                nextIntent, 0);
////
////        Bitmap icon = BitmapFactory.decodeResource(getResources(),
////                R.drawable.art_background);
//
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle(settings.getRadioName())
//                .setContentText(settings.getMainNotificationMessage())
//                .setSmallIcon(R.drawable.logo)
//                .setContentIntent(pendingIntent)
//                .setOngoing(true)
//                .addAction(android.R.drawable.ic_media_previous,
//                        "Previous", ppreviousIntent)
//                .addAction(android.R.drawable.ic_media_play, "Play",
//                        pplayIntent).build();
////                .addAction(android.R.drawable.ic_media_next, "Next",
////                        pnextIntent).build();
//        startForeground(classID,
//                notification);
//    }
////  end of the new code notification and media stream manager
//
//        /**
//         * Starts radio URL stream
//         */
//        @SuppressLint("NewApi")
//        private void play() {
//
//            //Check connectivity status
//            if (isOnline()) {
//                //Check if player already streaming
//                if (!isPlaying) {
//                    isPlaying = true;
//                    Toast.makeText(getApplicationContext(), "playing", Toast.LENGTH_SHORT).show();
//
//                    //Return to the current activity
////                    Intent intent = new Intent(this, RadioStream.class);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
////                            Intent.FLAG_ACTIVITY_SINGLE_TOP);
////
////                    PendingIntent pi = PendingIntent.getActivity(this, 0, intent,0);
////
////                    //Build and show notification for radio playing
////                    Notification notification = new Notification.Builder(getApplicationContext())
////                            .setContentTitle(settings.getRadioName())
////                            .setContentText(settings.getMainNotificationMessage())
////                            .setSmallIcon(R.drawable.logo)
////                            .setContentIntent(pi)
////                            .build();
//
//
//                    //Get stream URL
//                    radioPlayer = new MediaPlayer();
//                    radioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    try {
//                        radioPlayer.setDataSource(settings.getRadioStreamURL()); //Place URL here
//
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                    }catch (IllegalStateException e){
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
////                    if (settings.getAllowConsole()) {
////                        //Buffering Info
////                        radioPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
////
////                            public void onBufferingUpdate(MediaPlayer mp, int percent) {
////                                Log.i("Buffering", "" + percent);
////                            }
////                        });
////                    }
//                    radioPlayer.prepareAsync();
//                    radioPlayer.setOnPreparedListener(new OnPreparedListener() {
//                     @Override
//                     public void onPrepared(MediaPlayer mediaPlayer) {
//                        mediaPlayer.start();
//
//                     }
//                 });
//
//
//
//
//
////                    startForeground(classID, notification);
//
//
//                    //Display toast notification
////                    Toast.makeText(getApplicationContext(), settings.getPlayNotificationMessage(),
////                            Toast.LENGTH_LONG).show();
//                }
//            } else {
//                //Display no connectivity warning
//                Toast.makeText(getApplicationContext(), "No internet connection",
//                        Toast.LENGTH_LONG).show();
//            }
//
//
//        }
//
//
//
//    /**
//     * Stops the stream if activity destroyed
//     */
//    @Override
//    public void onDestroy() {
//        stop();
//    }
//
//
//    /**
//     * Stops audio from the active service
//     */
//    private void stop() {
//        if (isPlaying) {
//            isPlaying = false;
//            if (radioPlayer != null) {
//                radioPlayer.release();
//                radioPlayer = null;
//            }
//            stopForeground(true);
//        }
//
//        Toast.makeText(getApplicationContext(), "Stream stopped",
//                Toast.LENGTH_LONG).show();
//    }
//
//
//    /**
//     * Checks if there is a data or internet connection before starting the stream.
//     * Displays Toast warning if there is no connection
//     * @return online status boolean
//     */
//    public boolean isOnline() {
//        ConnectivityManager cm =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }
//
//
//}
//
//
 package com.livestream.myapplication;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.livestream.myapplication.R;

public class RadioMediaPlayerService extends Service implements AudioManager.OnAudioFocusChangeListener {

    //Variables

    private boolean isPlaying = false;
    private  boolean isPauseCalling=false;
    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;
    private MediaPlayer radioPlayer; //The media player instance
    private static int classID =1; // just a number
    public static String START_PLAY = "START_PLAY";
    private static final String LOG_TAG = "Notification Service";
    private static final String CHANNEL_ID = "myChannel";
    private static final String CHANNEL_NAME = "myChannelName";
    public static final String BROADCAST_BUFFER = "com.example.myapplication.broadcastbuffer";
    private AudioManager mAudioManager;

    Intent bufferIntent;

    // Declare headsetSwitch variable
    private int headsetSwitch = 1;
    //Settings
    Settings settings = new Settings();
    @Override
    public void onCreate() {
        // ---Set up intent for seekbar broadcast ---

        bufferIntent = new Intent(BROADCAST_BUFFER);
        // Register headset receiver
        registerReceiver(headsetReceiver, new IntentFilter(
                Intent.ACTION_HEADSET_PLUG));
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
         int result =mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

            // Start playback.
            play();
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * Starts the streaming service
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getBooleanExtra(START_PLAY, false)) {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int result =mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                // Start playback.
                play();
                showNotification();
            }



        }

        else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            Toast.makeText(this, "stream started", Toast.LENGTH_SHORT).show();
//            Log.i(LOG_TAG, "Clicked Play");
            fplay();

        }
       else if (intent.getAction().equals(Constants.ACTION.PAUSE_ACTION)) {
            Toast.makeText(this, "stream paused", Toast.LENGTH_SHORT).show();
            Log.i(LOG_TAG, "Clicked PAUSE");
            pause();

        }
       else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            stopForeground(true);
//            stopSelf();
            stop();
        }


        return Service.START_STICKY;
    }


    private void fplay() {
     radioPlayer.start();
    }

    private void pause() {
        if (isPlaying) {

            if (radioPlayer != null) {
                radioPlayer.pause();


            }}
    }

    private void showNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);

        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

//        //added this
//            //Return to the current activity
            Intent intent = new Intent(this, RadioStream.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

            //click the the button
            Intent playintent= new Intent(this,RadioMediaPlayerService.class);
            playintent.setAction(Constants.ACTION.PLAY_ACTION);
            PendingIntent pIntent=PendingIntent.getService(this,0,playintent,PendingIntent.FLAG_IMMUTABLE);


            Intent pauseintent= new Intent(this,RadioMediaPlayerService.class);
            pauseintent.setAction(Constants.ACTION.PAUSE_ACTION);
            PendingIntent paIntent=PendingIntent.getService(this,0,pauseintent,PendingIntent.FLAG_IMMUTABLE);

            Intent stopintent= new Intent(this,RadioMediaPlayerService.class);
            stopintent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            PendingIntent stoppintent= PendingIntent.getService(this,0,stopintent,PendingIntent.FLAG_IMMUTABLE);

          //add ended here
   Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(settings.getMainNotificationMessage())
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setCategory(Notification.CATEGORY_SERVICE)
                .addAction(new NotificationCompat.Action(R.drawable.play_icon,"PLAY",pIntent))
                .addAction( new NotificationCompat.Action(R.drawable.pause_icon,"PAUSE",paIntent))
                .addAction( new NotificationCompat.Action(R.drawable.stop_icon,"STOP",stoppintent))
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo))


                .setContentIntent(pi)
                .build();

        startForeground(2, notification);}
        else
            startForeground(1, new Notification());

    }

    /**
     * Starts radio URL stream
     */
    @SuppressLint("NewApi")
    private void play() {

        //Check connectivity status
        if (isOnline()){
            //Check if player already streaming
            if (!isPlaying) {
                isPlaying = true;

                //Return to the current activity

                //Get stream URL
                radioPlayer = new MediaPlayer();
                try {
                    radioPlayer.setDataSource(settings.getRadioStreamURL()); //Place URL here
//                    radioPlayer.prepare();
                    radioPlayer.prepareAsync();
                    // Send message to Activity to display progress dialogue

                    // Send message to Activity to display progress dialogue
                    sendBufferingBroadcast();

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Stream found",
                            Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
//
//                if (settings.getAllowConsole()) {
//                    //Buffering Info
//                    radioPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
//                        public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                            Log.i("Buffering", "" + percent);
//                        }
//                    });
//                }
//                radioPlayer.prepareAsync();
                radioPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        mp.start(); //Start radio stream
                        sendBufferCompleteBroadcast();

                    }
                });

////Display toast notification
                Toast.makeText(getApplicationContext(), settings.getPlayNotificationMessage(),
                        Toast.LENGTH_LONG).show();
            }
    }
        else {
//            //Display no connectivity warning
            Toast.makeText(getApplicationContext(), "No internet connection",
                    Toast.LENGTH_SHORT).show();
        }


    }
    //methods
    private void sendBufferingBroadcast() {
        // Log.v(TAG, "BufferStartedSent");
        bufferIntent.putExtra("buffering", "1");
        sendBroadcast(bufferIntent);
    }
    // Send a message to Activity that audio is prepared and ready to start
    // playing.
    private void sendBufferCompleteBroadcast() {
        // Log.v(TAG, "BufferCompleteSent");
        bufferIntent.putExtra("buffering", "0");
        sendBroadcast(bufferIntent);
    }

    /**
     * Stops the stream if activity destroyed
     */
    @Override
    public void onDestroy() {
        stop();

        mAudioManager.abandonAudioFocus(this);



    }
    // If headset gets unplugged, stop music and service.
    private BroadcastReceiver headsetReceiver = new BroadcastReceiver() {
        private boolean headsetConnected = false;

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            // Log.v(TAG, "ACTION_HEADSET_PLUG Intent received");
            if (intent.hasExtra("state")) {
                if (headsetConnected && intent.getIntExtra("state", 0) == 0) {
                    headsetConnected = false;
                    headsetSwitch = 0;
                    // Log.v(TAG, "State =  Headset disconnected");
                    // headsetDisconnected();
                } else if (!headsetConnected
                        && intent.getIntExtra("state", 0) == 1) {
                    headsetConnected = true;
                    headsetSwitch = 1;
                    // Log.v(TAG, "State =  Headset connected");
                }

            }

            switch (headsetSwitch) {
                case (0):
                    headsetDisconnected();
                    break;
                case (1):
                    break;
            }
        }

    };

    private void headsetDisconnected() {
        stop();
        stopSelf();

    }

    /**
     * Stops audio from the active service
     */
    private void stop() {
        if (isPlaying) {
            isPlaying = false;
            if (radioPlayer != null) {
                radioPlayer.release();
                radioPlayer = null;
            }
            stopForeground(true);
        }


    }

    /**
     * Checks if there is a data or internet connection before starting the stream.
     * Displays Toast warning if there is no connection
     * @return online status boolean
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting()
                || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public void onAudioFocusChange(int i) {

        if(i<=0) {
            pause();
        } else {
            fplay();
        }

    }



}

