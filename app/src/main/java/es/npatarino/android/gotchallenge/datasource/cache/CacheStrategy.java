package es.npatarino.android.gotchallenge.datasource.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

/**
 * Created by josedelpozo on 2/5/16.
 */
public class CacheStrategy {

    private static final String SHARED_PREFERENCES_NAME = "GoTData";

    private static final String DATE_NAME = "time_saved";

    private static final int THRESOLD_TIME_MILLIS = 120 * 60 * 1000;

    private SharedPreferences sharedPreferences;

    Context context;

    public CacheStrategy(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveDate(){
        Date date = new Date();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(DATE_NAME, date.getTime());
        editor.commit();
    }

    public boolean isValidData(){
        Date dateSaved = new Date(sharedPreferences.getLong(DATE_NAME, Long.MAX_VALUE));
        Date currentDate = new Date();
        Log.d("dates",""+currentDate.getTime()+" "+dateSaved.getTime());

        if((currentDate.getTime() - dateSaved.getTime()) < 0) return false;


        if((currentDate.getTime() - dateSaved.getTime()) < THRESOLD_TIME_MILLIS) return true;

        return false;
    }
}
