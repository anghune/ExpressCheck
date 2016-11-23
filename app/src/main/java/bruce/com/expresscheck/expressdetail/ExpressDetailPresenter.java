package bruce.com.expresscheck.expressdetail;

import bruce.com.expresscheck.data.ExpressResult;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/7/31.
 */
public class ExpressDetailPresenter  implements ExpressDetailContract.Presenter{
    private ExpressDetailContract.View mDetailContractView;
    private ExpressResult mExpressResult;



    public ExpressDetailPresenter(ExpressDetailContract.View detailContractView, ExpressResult expressResult) {
        mDetailContractView = checkNotNull(detailContractView," DetailContractView is null");
        mExpressResult = checkNotNull(expressResult,"ExpressResult is null");
        mDetailContractView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void showExpressDetail() {
        mDetailContractView.setExpressResult(mExpressResult);


        Observable.just(mExpressResult.getdeliverystatus())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String result = "";
                        if (s.toString().trim().equals("1"))
                            result = "在途中";
                        else if (s.toString().trim().equals("2"))
                            result = "派件中";
                        else if (s.toString().trim().equals("3"))
                            result = "已签收";
                        else if (s.toString().trim().equals("4"))
                            result = "派送失败";
                        return result;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mDetailContractView.showDeliveryStatus(s);
            }
        });



        mDetailContractView.showExpressCompany(mExpressResult.getType());
        mDetailContractView.showExpressNumber(mExpressResult.getNumber());
    }

    @Override
    public void unsubecribe() {

    }


}
