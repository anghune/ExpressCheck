package bruce.com.expresscheck.expressdetail;

import bruce.com.expresscheck.BasePresenter;
import bruce.com.expresscheck.BaseView;
import bruce.com.expresscheck.data.ExpressResult;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public interface ExpressDetailContract {

    interface Presenter extends BasePresenter{
        void showExpressDetail();
    }

    interface View extends BaseView<Presenter> {
        void setExpressResult(ExpressResult result);

        void showDeliveryStatus(String delivery);

        void showExpressCompany(String company);

        void showExpressNumber(String number);

    }

}
