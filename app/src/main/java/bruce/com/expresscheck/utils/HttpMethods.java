package bruce.com.expresscheck.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import bruce.com.expresscheck.data.Express;
import bruce.com.expresscheck.data.ExpressCompany;
import bruce.com.expresscheck.data.ExpressHttpResult;
import bruce.com.expresscheck.data.ExpressResult;
import bruce.com.expresscheck.data.JiShuService;
import bruce.com.expresscheck.queryexpress.ApiException;
import bruce.com.expresscheck.queryexpress.ProgressSubscriber;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by WangChunhe on 2016/5/29.
 */
public class HttpMethods {

    private static final String TAG = "HttpMethods";
    public static final String appkey = "46bf4b57311d57fc";
    public static final String BASE_URL = "http://api.jisuapi.com/express/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private JiShuService mJiShuApi;
    private Retrofit companyRetrofit;
    private JiShuService mCompanyApi;


    //私有构造函数
    private HttpMethods() {
        // 手动创建一个 OkHttpClient 并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ExpressConverter.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        companyRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();


        mJiShuApi = retrofit.create(JiShuService.class);
        mCompanyApi = companyRetrofit.create(JiShuService.class);
    }

    // 在访问HttpMethod时创建的单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    // 获取单例

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取快递的状态
     * @param subscriber 由调用者传过来的观察者对象
     * @param express  快递对象
     */
    public  void getExpressDetailFromServer(Subscriber<ExpressResult> subscriber, Express express){

        // 方法一
//        mJiShuApi.expres(appkey, express.getType(), express.getNumber())
//                .map(new HttpResultFunc<ExpressResult>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

          //方法二
//          Observable observer = mJiShuApi.expres(appkey, express.getType(), express.getNumber())
//             .map(new HttpResultFunc<ExpressResult>());
//            toSubscribe(observer,subscriber);

           //方法三
        Observable observable = mJiShuApi.getExpressDetail(appkey,express.getType(),express.getNumber())
        /*.compose(new Observable.Transformer<ExpressHttpResult<ExpressResult>,ExpressResult>(){
            @Override
            public Observable<ExpressResult> call(Observable<ExpressHttpResult<ExpressResult>> expressHttpResultObservable) {
                return expressHttpResultObservable.map(new Func1<ExpressHttpResult<ExpressResult>, ExpressResult>() {
                    @Override
                    public ExpressResult call(ExpressHttpResult<ExpressResult> tExpressHttpResult) {

                        if (!tExpressHttpResult.getStatus().toString().trim().equals("0")) {

                            throw new ApiException(Integer.parseInt(tExpressHttpResult.getStatus()));
                        }
                        return tExpressHttpResult.getResult();

                    }
                });
            }
        })*/
                .compose(handleResult());
        toSubscribe(observable,subscriber);

    }

    public void getExpressCompanyFromServer(ProgressSubscriber<ArrayList<ExpressCompany>> subscriber){
        Observable observable = mCompanyApi.getExpressCompany(appkey)
                .map(new HttpResultFunc());
        toSubscribe(observable,subscriber);

    }



    /**
     * 添加线程管理并订阅
     * @param observable
     * @param subscriber
     */
    private void toSubscribe(Observable observable,Subscriber subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用来统一处理Http的resultCode（结果码），并将ExpressHttpResult的Data部分离出来返回给subscriber
     * @param <T>  Subscriber真正需要的数据类型， 也就是Data部分类
     */
    private class HttpResultFunc<T> implements Func1<ExpressHttpResult<T>, T> {
        @Override
        public T call(ExpressHttpResult<T> tExpressHttpResult) {
            Log.d(TAG," code ->=:  " + tExpressHttpResult.toString());
//            Log.d(TAG)

            if (!tExpressHttpResult.getStatus().toString().trim().equals("0")) {

                throw new ApiException(Integer.parseInt(tExpressHttpResult.getStatus()));
            }
            return tExpressHttpResult.getResult();
        }
    }

    /**
     *  对请求的结果进行预处理
     * @param <T>
     * @return
     */
    private <T> Observable.Transformer<ExpressHttpResult<ExpressResult>, ExpressResult> handleResult(){
        return new Observable.Transformer<ExpressHttpResult<ExpressResult>, ExpressResult>(){
            @Override
            public Observable<ExpressResult>  call(Observable<ExpressHttpResult<ExpressResult>> expressHttpResultObservable) {
                return expressHttpResultObservable.map(new Func1<ExpressHttpResult<ExpressResult>, ExpressResult>() {
                    @Override
                    public ExpressResult call(ExpressHttpResult<ExpressResult> tExpressHttpResult) {

                        if (!tExpressHttpResult.getStatus().toString().trim().equals("0")) {

                            throw new ApiException(Integer.parseInt(tExpressHttpResult.getStatus()));
                        }
                        return tExpressHttpResult.getResult();
                    }
                });
            }
        };
    }






}
