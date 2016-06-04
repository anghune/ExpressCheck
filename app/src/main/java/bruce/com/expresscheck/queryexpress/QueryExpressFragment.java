package bruce.com.expresscheck.queryexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.data.Express;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/5/23.
 */
public class QueryExpressFragment extends Fragment  implements QureyExpressContract.View{

    private static final String TAG = "QueryExpressFragment";
    private QureyExpressContract.Presenter mPresenter;
    private EditText mNumberEditxt;
    private Button mQueryButton;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubecribe();
    }

    @Override
    public void setPresenter(QureyExpressContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  root  = inflater.inflate(R.layout.content_query_express, container, false);

        mNumberEditxt = (EditText) root.findViewById(R.id.edit_query_number);
        mQueryButton = (Button) root.findViewById(R.id.btn_query);
        mQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Express express = new Express(getNumber());
                Log.d(TAG, " onClick  number --> " + express.getNumber());
                mPresenter.queryExpressInfo(express);

            }
        });


        return root;

    }

    @Override
    public void showQueryExpressResult() {

    }

    @Override
    public String getNumber() {
        return mNumberEditxt.getText().toString().trim();
    }
}
