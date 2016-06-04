package bruce.com.expresscheck.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    //创建App的单例
    private static class SingleHolder{
        private static final App INSTANCE = new App();
    }

    //获取单例

    public static App getInstance(){
        return SingleHolder.INSTANCE;
    }

}
