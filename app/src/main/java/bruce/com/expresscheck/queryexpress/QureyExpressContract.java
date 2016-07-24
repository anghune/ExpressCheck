package bruce.com.expresscheck.queryexpress;

import bruce.com.expresscheck.data.Express;
import bruce.com.expresscheck.BasePresenter;
import bruce.com.expresscheck.BaseView;

/**
 * Created by WangChunhe on 2016/5/22.
 */
public interface QureyExpressContract {



    interface View extends BaseView<Presenter> {

        void showQueryExpressResult();

        String getNumber();

        void showNetNotConnect();

    }

    interface Presenter extends BasePresenter {

        void queryExpressInfo(Express express);

    }

}
