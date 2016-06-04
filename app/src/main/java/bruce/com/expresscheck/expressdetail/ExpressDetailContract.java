package bruce.com.expresscheck.expressdetail;

import bruce.com.expresscheck.BasePresenter;
import bruce.com.expresscheck.BaseView;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public interface ExpressDetailContract {

    interface Presenter extends BasePresenter{

    }

    interface View extends BaseView<Presenter> {

        void showExpressDetail();
    }

}
