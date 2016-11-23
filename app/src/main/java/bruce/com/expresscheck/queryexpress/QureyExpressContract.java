package bruce.com.expresscheck.queryexpress;

import java.util.ArrayList;

import bruce.com.expresscheck.data.Express;
import bruce.com.expresscheck.BasePresenter;
import bruce.com.expresscheck.BaseView;
import bruce.com.expresscheck.data.ExpressCompany;
import bruce.com.expresscheck.data.ExpressResult;

/**
 * Created by WangChunhe on 2016/5/22.
 */
public interface QureyExpressContract {



    interface View extends BaseView<Presenter> {

        void showQueryExpressResult(ExpressResult expressResult);
        void showQueryExpressCompany(ArrayList<ExpressCompany> list);

        String getNumber();

        void  initProgressDialog();
        void dismissProgressDialog();


    }

    interface Presenter extends BasePresenter {

        void queryExpressInfo(Express express);
        void queryExpressCompany();

    }

}
