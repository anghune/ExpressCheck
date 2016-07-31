package bruce.com.expresscheck.expressdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.utils.ActivityUtils;

public class ExpressDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_detail);

        ExpressDetailFragment expressDetailFragment = (ExpressDetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_express_detail);
        if (expressDetailFragment == null) {
            expressDetailFragment = ExpressDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),expressDetailFragment,R.id.frame_express_detail);;

        }


    }
}
