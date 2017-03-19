import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by ZCM on 17-2-22 下午4:07.
 */

public class XApplication extends Application {
    private static Context context;
    public static Context getAppContext(){
        return context;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
