package bruce.com.expresscheck.expressdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.data.ExpressResult;
import bruce.com.expresscheck.data.ExpressStatus;
import bruce.com.expresscheck.queryexpress.QueryExpressFragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by WangChunhe on 2016/6/4.
 */
public class ExpressDetailFragment extends Fragment implements ExpressDetailContract.View{


    private static final String TAG = "ExpressDetailFragment";
    private RecyclerView mRecyclerView;
    private DetailAdapter mDetailAdapter;
    private ExpressDetailContract.Presenter mDetailPresenter;
    private AppCompatTextView mNumberTextView;
    private AppCompatTextView mDeliveryTextView;
    private AppCompatTextView mCompanyTextView;



    public static ExpressDetailFragment newInstance() {
        return new ExpressDetailFragment();
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        ExpressResult result = (ExpressResult) intent.getSerializableExtra(QueryExpressFragment.EXTRA);
        Log.d(TAG," result : " + result.toString());
//        mDetailAdapter = new DetailAdapter(result);
//        setExpressResult();

    }

    @Override
    public void setExpressResult(ExpressResult result) {
        mDetailAdapter = new DetailAdapter(result);
        mRecyclerView.setAdapter(mDetailAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_express_detail,container,false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_express_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mCompanyTextView = (AppCompatTextView)root.findViewById(R.id.express_detail_company);
        mNumberTextView = (AppCompatTextView)root.findViewById(R.id.express_detail_number_txt);
        mDeliveryTextView = (AppCompatTextView)root.findViewById(R.id.express_detail_deliverystatus_txt);
        mDetailPresenter.showExpressDetail();

        return root;
    }

    @Override
    public void showDeliveryStatus(String delivery) {
        mDeliveryTextView.setText(delivery);
    }

    @Override
    public void showExpressCompany(String company) {
        mCompanyTextView.setText(company);
    }

    @Override
    public void showExpressNumber(String number) {
        mNumberTextView.setText(number);
    }

    @Override
    public void setPresenter(ExpressDetailContract.Presenter presenter) {
        mDetailPresenter = checkNotNull(presenter);
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

            ExpressStatus  result = mExpressResult.getList().get(position);
            holder.getTxtStatus().setText(result.getStatus().toString());
            holder.getTxtTime().setText(result.getTime().toString());

            if (position == 0){
                holder.mTopLine.setVisibility(View.INVISIBLE);
                holder.mCircleView.setVisibility(View.GONE);
                holder.mDoubleCircleView.setVisibility(View.VISIBLE);
                holder.mBottomLine.setVisibility(View.VISIBLE);
            }else if (position == mExpressResult.getList().size()-1){
                holder.mCircleView.setVisibility(View.VISIBLE);
                holder.mDoubleCircleView.setVisibility(View.GONE);
                holder.mTopLine.setVisibility(View.VISIBLE);
                holder.mBottomLine.setVisibility(View.INVISIBLE);
                holder.mContentBottomLine.setVisibility(View.INVISIBLE);


            }else {
                holder.mCircleView.setVisibility(View.VISIBLE);
                holder.mDoubleCircleView.setVisibility(View.GONE);
                holder.mContentBottomLine.setVisibility(View.VISIBLE);
                holder.mTopLine.setVisibility(View.VISIBLE);
                holder.mBottomLine.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return mExpressResult.getList().size();
        }

        static class DetailViewHolder extends RecyclerView.ViewHolder{
            private final TextView mTxtStatus;
            private final TextView mTxtTime;
            private final View mTopLine;
            private final View mBottomLine;
            private final CircleView mCircleView;
            private final DoubleCircleView mDoubleCircleView;
            private final View mContentBottomLine;

            public DetailViewHolder(View itemView) {
                super(itemView);
                mTxtStatus = (TextView)itemView.findViewById(R.id.txt_express_detail_status);
                mTxtTime = (TextView)itemView.findViewById(R.id.txt_express_detail_time);
                mTopLine = itemView.findViewById(R.id.line_top);
                mBottomLine = itemView.findViewById(R.id.line_bottom);
                mCircleView = (CircleView)itemView.findViewById(R.id.cirle);
                mContentBottomLine = itemView.findViewById(R.id.content_bottom_line);
                mDoubleCircleView = (DoubleCircleView)itemView.findViewById(R.id.double_circle);

            }

            public TextView getTxtStatus() {
                return mTxtStatus;
            }

            public TextView getTxtTime() {
                return mTxtTime;
            }

            public View getTopLine() {
                return mTopLine;
            }

            public View getBottomLine() {
                return mBottomLine;
            }

            public CircleView getCircleView() {
                return mCircleView;
            }

            public DoubleCircleView getDoubleCircleView() {
                return mDoubleCircleView;
            }

            public View getContentBottomLine() {
                return mContentBottomLine;
            }
        }
    }

}
