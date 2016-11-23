package bruce.com.expresscheck.querycompany;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import bruce.com.expresscheck.R;

/**
 * TODO: document your custom view class.
 */
public class SideLetterBarView extends View {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    public static String[] LETTERS = { "↑","A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };

    private  int mSideBarNorBg ;
    private  int mSideBarPresBg ;
    private  float mSideBarWidth ;
    private  int mSideBarTextSize ;
    private  int mSideBarTextNorColor ;
    private  int mSideBarTextPresColor ;
    private  int mSelectPos = -1 ;
    private int oneLetterHeight ;




    private TextPaint mSelectedTextPaint;
    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;



    public SideLetterBarView(Context context) {
        super(context);
        init(null, 0);
    }

    public SideLetterBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SideLetterBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.SideLetterBarView, defStyle, 0);

//        mExampleString = a.getString(
//                R.styleable.SideLetterBarView_exampleString);
//        mExampleColor = a.getColor(
//                R.styleable.SideLetterBarView_exampleColor,
//                mExampleColor);
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        mExampleDimension = a.getDimension(
//                R.styleable.SideLetterBarView_exampleDimension,
//                mExampleDimension);
//
//        if (a.hasValue(R.styleable.SideLetterBarView_exampleDrawable)) {
//            mExampleDrawable = a.getDrawable(
//                    R.styleable.SideLetterBarView_exampleDrawable);
//            mExampleDrawable.setCallback(this);
//        }

        mSideBarNorBg = a.getColor(R.styleable.SideLetterBarView_side_bar_nor_bg,Color.TRANSPARENT);
        mSideBarPresBg = a.getColor(R.styleable.SideLetterBarView_side_bar_pres_bg,Color.parseColor("#BEBEBE"));
        mSideBarWidth = a.getDimension(R.styleable.SideLetterBarView_side_bar_width, 60);
        mSideBarTextNorColor = a.getColor(R.styleable.SideLetterBarView_side_bar_text_nor_bg,Color.parseColor("#565656"));
        mSideBarTextPresColor = a.getColor(R.styleable.SideLetterBarView_side_bar_text_pres_bg,Color.parseColor("#ff000000"));
        mSideBarTextSize = a.getInt(R.styleable.SideLetterBarView_side_bar_text_size,35);







        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        //设置去锯齿属性
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTypeface(Typeface.DEFAULT);
        mTextPaint.setTextSize(mSideBarTextSize);
        mTextPaint.setColor(mSideBarTextNorColor);


        mSelectedTextPaint = new TextPaint();
        mSelectedTextPaint.setTypeface(Typeface.DEFAULT);
        //设置去锯齿属性
        mSelectedTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mSelectedTextPaint.setTextSize(mSideBarTextSize);
        mSelectedTextPaint.setColor(mSideBarTextPresColor);

        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();


    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    /**
     *  使得wrap_content 在xml中生效， 使用wrap_content默认宽/高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(60,450);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(60, heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize, 450);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
         oneLetterHeight = contentHeight / LETTERS.length;

        for (int i = 0; i < LETTERS.length;i++){
            canvas.drawText(LETTERS[i],contentWidth/2 - mTextPaint.measureText(LETTERS[i])/2,oneLetterHeight * (i+1),mTextPaint);

            if (mSelectPos == i){
                canvas.drawText(LETTERS[i],contentWidth/2 - mTextPaint.measureText(LETTERS[i])/2,oneLetterHeight * (i+1),mSelectedTextPaint);
            }
        }

        // Draw the text.
/*        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);*/

        // Draw the example drawable on top of the text.
/*        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }*/
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y = event.getY();
        // 根据纵坐标，得到当前字母的位置
        int position = (int) (y/oneLetterHeight);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                setBackgroundColor(mSideBarPresBg);
                mSelectPos = position;
                if (mSeleteLetterListener != null){
                    mSeleteLetterListener.onSelectedLetter(LETTERS[mSelectPos]);
                }

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (position != mSelectPos){
                    mSelectPos = position;
                    if (mSeleteLetterListener != null){
                        mSeleteLetterListener.onChangedLetter(LETTERS[mSelectPos]);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setBackgroundColor(mSideBarNorBg);
                if (mSeleteLetterListener != null){
                    mSeleteLetterListener.onReleasedLetter(LETTERS[mSelectPos]);
                }

                break;

        }


        return true;
    }

    // 设置监听器
    public interface SeleteLetterListener{
        void onSelectedLetter(String letter);
        void onChangedLetter(String letter);
        void onReleasedLetter(String letter);
    }

    private SeleteLetterListener mSeleteLetterListener;

    public void setSeleteLetterListener(SeleteLetterListener seleteLetterListener) {
        mSeleteLetterListener = seleteLetterListener;
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
