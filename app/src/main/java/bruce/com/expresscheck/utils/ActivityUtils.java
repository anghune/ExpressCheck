package bruce.com.expresscheck.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/5/16.
 */
public class ActivityUtils {
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment frament,int frameId){

        checkNotNull(fragmentManager);
        checkNotNull(frament);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, frament);
        transaction.commit();

    }
}
