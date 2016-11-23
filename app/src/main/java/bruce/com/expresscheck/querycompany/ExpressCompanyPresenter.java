package bruce.com.expresscheck.querycompany;

import java.util.ArrayList;

import bruce.com.expresscheck.data.ExpressCompany;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/10/21.
 */

public class ExpressCompanyPresenter implements ExpressCompanyContract.Presenter {


    private ExpressCompanyContract.View mView;
    private ArrayList<ExpressCompany> mExpressCompanyArrayList;

    public ExpressCompanyPresenter(ExpressCompanyContract.View view, ArrayList<ExpressCompany> list) {
        mView = checkNotNull(view, " ExpressCompanyFragment cannot be null!");
        mView.setPresenter(this);
        mExpressCompanyArrayList = checkNotNull(list," ExpressCompany cannot be null");

    }

    @Override
    public void queryExpressCompanyList() {
        mView.showCompanyList(mExpressCompanyArrayList);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubecribe() {

    }
}
