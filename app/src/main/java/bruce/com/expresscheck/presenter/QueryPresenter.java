package bruce.com.expresscheck.presenter;

import android.util.Log;

import bruce.com.expresscheck.data.Express;
import bruce.com.expresscheck.data.ExpressResult;
import bruce.com.expresscheck.utils.HttpMethods;
import bruce.com.expresscheck.utils.TasksContract;
import rx.Subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/5/23.
 */
public class QueryPresenter implements TasksContract.Presenter {

    private static final String TAG = "QueryPresenter";
    private final TasksContract.View mTasksView;
    private Subscriber<ExpressResult> mSubscriber;

    public QueryPresenter(TasksContract.View tasksView) {
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");
        mTasksView.setPresenter(this);
    }

    @Override
    public void queryExpressInfo(Express express) {

        mSubscriber = new Subscriber<ExpressResult>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "queryExpressInfo -->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "queryExpressInfo -->onError");
            }

            @Override
            public void onNext(ExpressResult expressResult) {
                Log.d(TAG, "queryExpressInfo --> " + expressResult.toString());
            }


        };

        HttpMethods.getInstance().getExpressFromServer(mSubscriber, express);
    }

    @Override
    public void subscribe() {



    }

    @Override
    public void unsubecribe() {

    }


}
