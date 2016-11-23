package bruce.com.expresscheck.querycompany;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bruce.com.expresscheck.R;
import bruce.com.expresscheck.data.ExpressCompany;

import static com.google.common.base.Preconditions.checkNotNull;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ExpressCompanyFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ExpressCompanyFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ExpressCompanyFragment extends Fragment implements ExpressCompanyContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    private ExpressCompanyContract.Presenter mPresenter;
    private SideLetterBarView mSideLetterBarView;
    private RecyclerView mRecyclerViewCompany;
    private CompanyRecycleAdapter mAdapter;
    private TextView mTextViewTip;

    private TitleItemDecoration mTitleItemDecoration;
    private DividerItemDecoration mDividerItemDecoration;

    public ExpressCompanyFragment() {
        // Required empty public constructor

    }

    public static ExpressCompanyFragment newInstance(){
        return new ExpressCompanyFragment();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpressCompanyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpressCompanyFragment newInstance(String param1, String param2) {
        ExpressCompanyFragment fragment = new ExpressCompanyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_express_company, container, false);
        mSideLetterBarView = (SideLetterBarView) root.findViewById(R.id.side_bar_view);
        mRecyclerViewCompany = (RecyclerView)root.findViewById(R.id.recycler_express_company);
        mTextViewTip = (TextView)root.findViewById(R.id.text_tip);
        final LinearLayoutManager manager = new LinearLayoutManager(container.getContext());
        mDividerItemDecoration = new  DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST);
        mRecyclerViewCompany.setLayoutManager(manager);

        mPresenter.queryExpressCompanyList();

        mSideLetterBarView.setSeleteLetterListener(new SideLetterBarView.SeleteLetterListener() {
            @Override
            public void onSelectedLetter(String letter) {

                int pos =  mAdapter.getLetterPosition(letter);
                manager.scrollToPositionWithOffset(pos,0);
                manager.setStackFromEnd(true);
                mTextViewTip.setText(letter);
                mTextViewTip.setVisibility(View.VISIBLE);
            }

            @Override
            public void onChangedLetter(String letter) {

                int pos =  mAdapter.getLetterPosition(letter);
                manager.scrollToPositionWithOffset(pos,0);
                manager.setStackFromEnd(true);
                mTextViewTip.setText(letter);
            }

            @Override
            public void onReleasedLetter(String letter) {
                mTextViewTip.setVisibility(View.GONE);
            }
        });
        return root;
    }

//    private

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public void setPresenter(ExpressCompanyContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showCompanyList(ArrayList<ExpressCompany> list) {
        mTitleItemDecoration = new TitleItemDecoration(getActivity(),list);

        mAdapter = new CompanyRecycleAdapter(list);
        mRecyclerViewCompany.setAdapter(mAdapter);

        mRecyclerViewCompany.addItemDecoration(mTitleItemDecoration);
        mRecyclerViewCompany.addItemDecoration(mDividerItemDecoration);
        mRecyclerViewCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.setBackgroundResource(R.drawable.recycler_bg);
//                TextView textView = (TextView)v.findViewById(R.id.text_company_name);
//
                Toast.makeText(getActivity()," mRecyclerViewCompany 点击事件 ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


    private static class CompanyRecycleAdapter extends RecyclerView.Adapter<CompanyRecycleAdapter.CompanyViewHolder>{
        private ArrayList<ExpressCompany> mExpressCompanyArrayList;


        public CompanyRecycleAdapter(ArrayList<ExpressCompany> expressCompanyArrayList) {
            mExpressCompanyArrayList = expressCompanyArrayList;
        }

        @Override
        public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.item_recycler_express_company,parent,false);
            v.setBackgroundResource(R.drawable.recycler_bg);
            return new CompanyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CompanyViewHolder holder, int position) {

//            if (mExpressCompanyArrayList.get(position).getName().toString().length()==1){
//
//                holder.mCompanyTitle.setText(mExpressCompanyArrayList.get(position).getName().toString());
//                holder.mCompanyTitle.setVisibility(View.VISIBLE);
//                holder.mCompanyName.setVisibility(View.GONE);
////                holder.mCompanyIcon.setVisibility(View.GONE);
//                holder.mCompanyTel.setVisibility(View.GONE);
//            }else {
//                holder.mCompanyTitle.setVisibility(View.GONE);
//                holder.mCompanyName.setVisibility(View.VISIBLE);
//                holder.itemView.setBackgroundResource(R.drawable.recycler_bg);
                holder.mCompanyName.setText(mExpressCompanyArrayList.get(position).getName());
//                holder.mCompanyIcon.setVisibility(View.GONE);
//                holder.mCompanyTel.setVisibility(View.VISIBLE);
                holder.mCompanyTel.setText(mExpressCompanyArrayList.get(position).getTel());
//            }
            holder.itemView.setClickable(true);








        }

        public int getLetterPosition(String letter){

            for (int i = 0;i<mExpressCompanyArrayList.size();i++){
                if (mExpressCompanyArrayList.get(i).getLetter().toString().equals(letter)){
                    return i;
                }
            }
            return -1;
        };





        @Override
        public int getItemCount() {
            return mExpressCompanyArrayList.size();
        }

        static class CompanyViewHolder extends RecyclerView.ViewHolder{
//            private final ImageView mCompanyIcon;
            private final TextView mCompanyName;
//            private final TextView mCompanyTitle;
            private final TextView mCompanyTel;
            public CompanyViewHolder(View itemView) {
                super(itemView);
//                mCompanyTitle = (TextView)itemView.findViewById(R.id.text_letter_title);
//                mCompanyIcon = (ImageView)itemView.findViewById(R.id.image_company_icon);
                mCompanyName = (TextView)itemView.findViewById(R.id.text_company_name);
                mCompanyTel = (TextView)itemView.findViewById(R.id.text_company_tel);

            }

        }
    }
}
