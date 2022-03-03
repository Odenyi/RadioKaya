package com.stream.myapplication;

public class Constants {

    public static final String MUSIC_SERVICE_ACTION_START = " com.example.myapplication.start";
    public static final String MUSIC_SERVICE_ACTION_PLAY = " com.example.myapplication.play";
    public static final String MUSIC_SERVICE_ACTION_PAUSE = " com.example.myapplication.pause";
    public static final String MUSIC_SERVICE_ACTION_STOP = " com.example.myapplication.stop";

    public interface ACTION {
        public static String MAIN_ACTION = "action.main";
        public static String PREV_ACTION = "action.prev";
        public static String PLAY_ACTION = "action.play";
        public static String PAUSE_ACTION = "action.pause";
        public static String NEXT_ACTION = "action.next";
        public static String CLOSE_ACTION = "action.close";
        public static String STARTFOREGROUND_ACTION = "action.startforeground";
        public static String STOPFOREGROUND_ACTION = "action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}

