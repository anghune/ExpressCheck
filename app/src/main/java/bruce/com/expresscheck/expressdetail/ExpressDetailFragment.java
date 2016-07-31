package bruce.com.expresscheck.expressdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.data.ExpressResult;
import bruce.com.expresscheck.queryexpress.QueryExpressFragment;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public class ExpressDetailFragment extends Fragment implements ExpressDetailContract.View{

    private static final String TAG = "ExpressDetailFragment";
    private RecyclerView mRecyclerView;
    private DetailAdapter mDetailAdapter;


    public static ExpressDetailFragment newInstance() {
        return new ExpressDetailFragment();
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        ExpressResult result = (ExpressResult) intent.getSerializableExtra(QueryExpressFragment.EXTRA);
        Log.d(TAG," result : " + result.toString());
        mDetailAdapter = new DetailAdapter(result);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_express_detail,container,false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_express_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mRecyclerView.setAdapter(mDetailAdapter);

        return root;
    }

    @Override
    public void showExpressDetail() {

    }

    @Override
    public void setPresenter(ExpressDetailContract.Presenter presenter) {

    }

    private static class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

        private ExpressResult mExpressResult;

        public DetailAdapter(ExpressResult expressResult) {
            mExpressResult = expressResult;
        }

        @Override
        public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.item_recycler_express_detail,parent,false);

            return new DetailViewHolder(v);
        }

        @Override
        public void onBindViewHolder(DetailViewHolder holder, int position) {

            holder.getTextView().setText(mExpressResult.getList().get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mExpressResult.getList().size();
        }

        static class DetailViewHolder extends RecyclerView.ViewHolder{
            private final TextView mTextView;

            public DetailViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView)itemView.findViewById(R.id.txt_recyview_express_detail);
            }

            public TextView getTextView() {
                return mTextView;
            }
        }
    }

}
