package bruce.com.expresscheck.querycompany;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import bruce.com.expresscheck.data.ExpressCompany;

/**
 * Created by WangChunhe on 2016/11/21.
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "TitleItemDecoration";
    private ArrayList<ExpressCompany> mDatas;
    private Paint mPaint;
    private Rect mBounds;//用于存放测量文字的Rect

    private LayoutInflater mInflater;

    private int mTitleHeight ;// title的高
    private int mTitleSize; //title字体大小
    private static int COLOR_TITLE_BG = Color.parseColor("#FFDFDFDF");
    private static int COLOR_TITLE_FONT = Color.parseColor("#FF000000");

    public TitleItemDecoration(Context context, ArrayList<ExpressCompany> list){
        super();
        this.mDatas = list;
        mPaint = new Paint();
        mBounds = new Rect();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30,context.getResources().getDisplayMetrics());
        mTitleSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTitleSize);
        mPaint.setAntiAlias(true);//抗锯齿
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0;i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child
                    .getLayoutParams();
            int position = params.getViewAdapterPosition();
            //RecyclerView的item position 在重置时可能为-1，
            if (position > -1){
                if (position == 0){//等于0时有title
                    drawTitleArea(c,left,right,child,params,position);
                }else {//其他通过判断
                    if (mDatas.get(position).getLetter() != null
                            && !mDatas.get(position).getLetter().toString().equals(mDatas.get(position - 1).getLetter())){
                        //如果不为空，而且和前一个Letter不一样，说明是新的分类，此时需要title
                        drawTitleArea(c,left,right,child,params,position);

                    }

                }
            }


        }

    }

    /**
     *  绘制Title区域背景及文字
     * @param c
     * @param left
     * @param right
     * @param child
     * @param params
     * @param position
     */
    private void drawTitleArea(Canvas c,int left, int right, View child, RecyclerView.LayoutParams params, int position){
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(left,child.getTop() - params.topMargin - mTitleHeight,
                right,child.getTop() - params.topMargin,mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);

        mPaint.getTextBounds(mDatas.get(position).getLetter(), 0, mDatas.get(position).getLetter().length(), mBounds);
        c.drawText(mDatas.get(position).getLetter(),child.getPaddingLeft(),
                child.getTop() - params.topMargin - (mTitleHeight / 2 - mBounds.height() / 2),mPaint);

    }

    /**
     * 最后调用 绘制在最上层
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager)(parent.getLayoutManager())).findFirstVisibleItemPosition();

        String tag = mDatas.get(position).getLetter();
        View child = parent.findViewHolderForLayoutPosition(position).itemView;
        // 定义一个isMove ，判断Canvas是否位移过
        boolean isMove = false;
        //防止数组越界
        if ((position + 1) < mDatas.size()){//当前第一个可见的Item的tag,不等于以后一个Item的tag,说明悬浮的View需要切换了
            if (null != tag && ! tag.equals(mDatas.get(position + 1).getLetter())){
                Log.d(TAG, " onDrawOver() called with : c = " +child.getTop() );//当getTop开始变负，它的绝对值，是第一个可见的Item移除屏幕
                if (child.getHeight() + child.getTop() < mTitleHeight){//当第一个可见的item在屏幕中还剩的高度小于title区域的高度时，我们开始做悬浮Titlede的“交换动画”
                    c.save(); //每次绘制前，保存当前的Canvas状态
                    isMove = true;

                    //一种头部折叠起来的效果，
                    //与c.drawRect比较，只有bottom参数不一样，由于child.getHeight() + child.getTop() < mTitleHeight,所以绘制区域是在不断减小，有种折叠起来的感觉
//                    c.clipRect(parent.getPaddingLeft(),parent.getPaddingTop(),parent.getRight() - parent.getPaddingRight(),parent.getPaddingTop() + child.getHeight() + child.getTop());


                    //类似饿了么点餐时，商品列表悬停头部切换“动画效果”
                    //上滑时，将canvas上移（y为负数），所以后面的canvas画出来Rect和Text都上移了，有种切换“动画”视觉效果
                    c.translate(0,child.getHeight() + child.getTop() - mTitleHeight);


                }

            }
        }

        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.getTextBounds(tag,0,tag.length(),mBounds);
        c.drawText(tag, child.getPaddingLeft(),
                parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mBounds.height() / 2),mPaint);
        if (isMove)
            c.restore();//恢复画布到之前的保存的状态


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {// RecycleView的item position在重置时可能为-1，保险起见加判断
            if(position == 0){//等于0肯定存在title
                outRect.set(0,mTitleHeight,0,0);
            } else {//其他的通过判断
                if(mDatas.get(position).getLetter() != null && !mDatas.get(position).getLetter().toString().equals(mDatas.get(position - 1).getLetter())){
                    outRect.set(0,mTitleHeight, 0,0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
    }
}
