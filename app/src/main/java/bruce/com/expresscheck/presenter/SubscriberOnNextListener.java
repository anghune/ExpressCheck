package bruce.com.expresscheck.presenter;

/**
 * Created by WangChunhe on 2016/6/2.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
