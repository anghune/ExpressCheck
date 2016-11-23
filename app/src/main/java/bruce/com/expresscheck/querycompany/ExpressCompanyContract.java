package bruce.com.expresscheck.querycompany;

import java.util.ArrayList;

import bruce.com.expresscheck.BasePresenter;
import bruce.com.expresscheck.BaseView;
import bruce.com.expresscheck.data.ExpressCompany;

/**
 * Created by WangChunhe on 2016/10/21.
 */

public interface ExpressCompanyContract {

    interface View extends BaseView<Presenter>{

        void showCompanyList(ArrayList<ExpressCompany> list);
    }

    interface Presenter extends BasePresenter{

        void queryExpressCompanyList();
    }
}
