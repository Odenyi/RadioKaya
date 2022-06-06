package com.livestream.myapplication;

import android.app.Activity;



public class Settings extends Activity {
    /********ALL EDITABLE SETTINGS ARE HERE *****/

    //Name of radio station
    private final String radioName = "RADIOKAYA 93.1";

    //URL of the radio stream
    private String radioStreamURL = "http://usa9-vn.mixstream.net:8084/listen.mp3";

    //URL of webcam (or YouTube link maybe)
    private String radioWebcamURL = "http://ustream.com/";

    //URL for the advertising / message banner. For no banner leave blank, i.e: ""
    private String adBannerURL = "http://www.aironair.co.uk/wp-content/uploads/2013/09/App-Banner.png";

    //Contact button email address
    private String emailAddress = "radiokayafb@gmail.com";


    //Contact button facebook address
    private String facebook = "https://www.facebook.com/RADIOKAYAKENYA/";

    //Contact button twitter address
    private String twitter = "https://twitter.com/radiokaya";

    //Contact button instagram address
    private String instagram = "https://www.instagram.com/radiokaya/?hl=en";



    //Contact button youtube address
    private String youtube = "https://www.youtube.com/channel/UC21fNNZRjBuBERrULe4oSBQ";


    //Contact button phone number
    private String phoneNumber = "+254 703773524";

    //Contact button website URL
    private String websiteURL = "https://radiokaya.co.ke/";

    //Contact button SMS number
    private int smsNumber = 20931;

    //Message to be shown in notification center whilst playing
    private String mainNotificationMessage = "Unasikiliza RadioKaya";

    //TOAST notification when play button is pressed
    private String playNotificationMessage = "Starting RadioKaya";

    //Play store URL (not known until published
    private String playStoreURL = "http://play.google.com/";

    //Enable console output for streaming info (Buffering etc) - Disable = false
    private boolean allowConsole = true;

    /********END OF EDITABLE SETTINGS**********/



    /********DO NOT EDIT BELOW THIS LINE*******/
    public String getRadioName(){
        return radioName;
    }



    public String getRadioWebcamURL(){
        return radioWebcamURL;
    }

    public String getAdBannerURL(){
        return adBannerURL;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        String appendPhoneNumber = phoneNumber;
        return appendPhoneNumber;
    }


    public String getWebsiteURL(){
        return websiteURL;
    }

    public int getSmsNumber(){
        return smsNumber;
    }

    public String getMainNotificationMessage(){
        return mainNotificationMessage;
    }

    public String getPlayNotificationMessage(){
        return playNotificationMessage;
    }

    public String getPlayStoreURL(){
        return playStoreURL;
    }

    public boolean getAllowConsole(){
        return allowConsole;
    }

    public String getRadioStreamURL() {
        return radioStreamURL;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getInstagram() {
        return instagram;
    }
    public String getYoutube() {
        return youtube;
    }

    public void setRadioStreamURL(String radioStreamURL) {
        this.radioStreamURL = radioStreamURL;
    }
}




