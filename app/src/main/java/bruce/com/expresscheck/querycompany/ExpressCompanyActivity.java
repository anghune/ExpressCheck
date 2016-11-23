package bruce.com.expresscheck.querycompany;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.data.ExpressCompany;
import bruce.com.expresscheck.queryexpress.QueryExpressFragment;
import bruce.com.expresscheck.utils.ActivityUtils;

public class ExpressCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_company);

        ExpressCompanyFragment expressCompanyFragment = (ExpressCompanyFragment) getSupportFragmentManager().findFragmentById(R.id.frame_express_company);
        ArrayList<ExpressCompany> list = getIntent().getParcelableArrayListExtra(QueryExpressFragment.COMPANY_LIST_EXTRA);

        if (expressCompanyFragment == null){
            expressCompanyFragment = ExpressCompanyFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),expressCompanyFragment,R.id.frame_express_company);
        }
        ExpressCompanyPresenter presenter = new ExpressCompanyPresenter(expressCompanyFragment,list);
    }
}
