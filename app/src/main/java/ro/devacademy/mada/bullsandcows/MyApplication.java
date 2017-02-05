package ro.devacademy.mada.bullsandcows;

import android.app.Application;
import net.alexandroid.shpref.ShPref;

/**
 * Created by Mada on 05-Feb-17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShPref.init(this, ShPref.APPLY);
    }
}
