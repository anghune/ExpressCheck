package bruce.com.expresscheck.queryexpress;

import android.widget.Toast;

import bruce.com.expresscheck.utils.App;
import rx.Subscriber;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public class ProgressSubscriber<T> extends Subscriber<T>{

    private SubscriberOnNextListener mSubscriberOnNextListener;

    public ProgressSubscriber(SubscriberOnNextListener subscriberOnNextListener) {
        mSubscriberOnNextListener = subscriberOnNextListener;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

        Toast.makeText(App.getContext(), "获得快递状态信息",Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(App.getContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {

        mSubscriberOnNextListener.onNext(t);
    }
}
