package bruce.com.expresscheck.utils;

import bruce.com.expresscheck.data.Express;
import bruce.com.expresscheck.presenter.BasePresenter;
import bruce.com.expresscheck.view.BaseView;

/**
 * Created by WangChunhe on 2016/5/22.
 */
public interface TasksContract {



    interface View extends BaseView<Presenter> {

        void showQueryExpressResult();

        String getNumber();

    }

    interface Presenter extends BasePresenter {

        void queryExpressInfo(Express express);

    }

}
