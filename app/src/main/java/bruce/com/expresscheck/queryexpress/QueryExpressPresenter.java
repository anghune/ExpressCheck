package bruce.com.expresscheck.queryexpress;

import android.util.Log;

import java.util.ArrayList;

import bruce.com.expresscheck.data.Express;
import bruce.com.expresscheck.data.ExpressCompany;
import bruce.com.expresscheck.data.ExpressResult;
import bruce.com.expresscheck.utils.HttpMethods;
import rx.Subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/5/23.
 */
public class QueryExpressPresenter implements QureyExpressContract.Presenter {

    private static final String TAG = "QueryExpressPresenter";
    private final QureyExpressContract.View mTasksView;
    private Subscriber<ExpressResult> mSubscriber;
//    private SubscriberOnNextListener mSubscriberOnNextListener;

    public QueryExpressPresenter(QureyExpressContract.View tasksView) {
        mTasksView = checkNotNull(tasksView, "tasksView cannot be null!");
        mTasksView.setPresenter(this);
//        mSubscriberOnNextListener = new SubscriberOnNextListener<ExpressResult>() {
//            @Override
//            public void onNext(ExpressResult expressResult) {
//                mTasksView.showQueryExpressResult(expressResult);
//            }
//        };

    }

    @Override
    public void queryExpressInfo(Express express) {

//        mSubscriber = new Subscriber<ExpressResult>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "queryExpressInfo -->onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "queryExpressInfo -->onError");
//            }
//
//            @Override
//            public void onNext(ExpressResult expressResult) {
//                Log.d(TAG, "queryExpressInfo --> " + expressResult.toString());
//            }
//
//
//        };

        HttpMethods.getInstance().getExpressDetailFromServer(new ProgressSubscriber<ExpressResult>(){

            @Override
            public void showProgressDialog() {
                mTasksView.initProgressDialog();
            }


            @Override
            public void dismissProgressDialog() {
                mTasksView.dismissProgressDialog();
            }

            @Override
            public void onNext(ExpressResult expressResult) {

                mTasksView.showQueryExpressResult(expressResult);
                Log.d(TAG, " result = :" + expressResult.toString());
            }
        }, express);

//        HttpMethods.getInstance().ge


//        HttpMethods.getInstance().getExpressFromSer(new ProgressSubscriber<ExpressHttpResult>() {
//            @Override
//            public void showProgressDialog() {
//                mTasksView.initProgressDialog();
//            }
//
//            @Override
//            public void dismissProgressDialog() {
//                mTasksView.dismissProgressDialog();
//            }
//
//            @Override
//            public void onNext(ExpressHttpResult expressHttpResult) {
////                super.onNext(expressHttpResult);
//                mTasksView.showQueryExpressResult((ExpressResult) expressHttpResult.getResult());
//            }
//        },express);
    }

    @Override
    public void queryExpressCompany() {

        HttpMethods.getInstance().getExpressCompanyFromServer(new ProgressSubscriber<ArrayList<ExpressCompany>>() {
            @Override
            public void showProgressDialog() {
                mTasksView.initProgressDialog();
            }

            @Override
            public void dismissProgressDialog() {
                mTasksView.dismissProgressDialog();
            }

            @Override
            public void onNext(ArrayList<ExpressCompany> list) {
//              for (int i = 0;i<list.size();i++){
                  Log.d(TAG," company = " + list.size());

//                mTasksView.showQueryExpressCompany(handleListTitle(list));

                mTasksView.showQueryExpressCompany(list);
//                for (int i = 0;i<list.size();i++){
//                    System.out.println(list.get(i).toString());
//                }


//                for (int i = 0;i<mCompanyWithTitleList.size();i++){
//                    System.out.println(mCompanyWithTitleList.get(i).toString());
//                }
//              }
            }
        });
    }

    private ArrayList<ExpressCompany>  handleListTitle(ArrayList<ExpressCompany> list){
        ArrayList<ExpressCompany> mCompanyWithTitleList = new ArrayList<ExpressCompany>();
        String titleTemp = "";
        for (int i = 0; i<list.size();i++){

            if (!list.get(i).getLetter().toString().equals(titleTemp)){
                ExpressCompany titleCompany = new ExpressCompany(list.get(i).getLetter(),list.get(i).getLetter());
                mCompanyWithTitleList.add(titleCompany);
                mCompanyWithTitleList.add(list.get(i));
            }else {
                mCompanyWithTitleList.add(list.get(i));
            }
            titleTemp = list.get(i).getLetter();

        }
        return mCompanyWithTitleList;
    }

    @Override
    public void subscribe() {



    }

    @Override
    public void unsubecribe() {

    }


}
