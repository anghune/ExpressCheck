package bruce.com.expresscheck.queryexpress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.utils.ActivityUtils;

public class QueryExpressActivity extends AppCompatActivity {

    private QueryExpressPresenter mQueryExpressPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_express);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        QueryExpressFragment queryFragment = new QueryExpressFragment();
        mQueryExpressPresenter = new QueryExpressPresenter(queryFragment);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),queryFragment,R.id.contentFrame);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}