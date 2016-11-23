package bruce.com.expresscheck.queryexpress;

import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import bruce.com.expresscheck.utils.App;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    private static final String TAG = "ProgressSubcriber";
//    private SubscriberOnNextListener mSubscriberOnNextListener;





    @Override
    public void onStart() {
//        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(App.getContext(), "获得快递状态信息 ！！！",Toast.LENGTH_SHORT ).show();
        Log.d(TAG, "onCompleted   ");

    }

     public abstract void showProgressDialog();
     public abstract void dismissProgressDialog();

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException){
            Toast.makeText(App.getContext(), "网络中断，请检查您的网络状态" , Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException){
            Toast.makeText(App.getContext(), "网络中断，请检查您的网络状态" , Toast.LENGTH_SHORT).show();
        } else if (e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            int code =  httpException.code();
            String msg = httpException.getMessage();
            Log.d(TAG, " code : "+  msg);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            Toast.makeText(App.getContext(), msg , Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(App.getContext(), "error-->: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        e.printStackTrace();

        dismissProgressDialog();
        Log.d(TAG, " onError : " + e.getMessage());
    }

    @Override
    public void onNext(T t) {

//        mSubscriberOnNextListener.onNext(t);
        Toast.makeText(App.getContext(), TAG + "  onNext() " , Toast.LENGTH_SHORT).show();
        Log.d(TAG, " onNext ");
    }



}
