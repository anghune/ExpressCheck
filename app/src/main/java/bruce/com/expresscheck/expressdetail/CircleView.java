package bruce.com.expresscheck.expressdetail;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import bruce.com.expresscheck.R;


/**
 * TODO: document your custom view class.
 */
public class CircleView extends View {
//    private String mExampleString; // TODO: use a default from R.string...
//    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
//    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
//    private Drawable mExampleDrawable;
    private final static String TAG = "CircleView";
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;
    private boolean isDraeDouble;

    private float mWidth = 10;
//    private int mOutWith;
//    private int mOutHeight;
    private int mOutColor;
    private Paint mOutPaint;



//    private TextPaint mTextPaint;
//    private float mTextWidth;
//    private float mTextHeight;

    public CircleView(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CircleView);

//        mExampleString = a.getString(
//                R.styleable.CircleView_exampleString);
        mColor = a.getColor(
                R.styleable.CircleView_exampleColor,
                mColor);
        mOutColor = a.getColor(R.styleable.CircleView_mOutColor,mOutColor);
        mWidth = a.getDimension(R.styleable.CircleView_mWidth,mWidth);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
//        mExampleDimension = a.getDimension(
//                R.styleable.CircleView_exampleDimension,
//                mExampleDimension);

//        if (a.hasValue(R.styleable.CircleView_exampleDrawable)) {
//            mExampleDrawable = a.getDrawable(
//                    R.styleable.CircleView_exampleDrawable);
//            mExampleDrawable.setCallback(this);
//        }

        a.recycle();

        // Set up a default TextPaint object
//        mTextPaint = new TextPaint();
//        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setTextAlign(Paint.Align.LEFT);



        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {

        mPaint.setColor(mColor);
        if (isDraeDouble){
            mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mOutPaint.setColor(mOutColor);
        }

    }

    /**
     * 使得wrap_content在xml中生效，使用wrap_content默认宽/高
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
//        if (isDraeDouble){
//            if (widthSpecMode == MeasureSpec.AT_MOST
//                    && heightSpecMode == MeasureSpec.AT_MOST) {
//                setMeasuredDimension(50, 50);
//            } else if (widthSpecMode == MeasureSpec.AT_MOST) {
//                setMeasuredDimension(50, heightSpecSize);
//            } else if (heightSpecMode == MeasureSpec.AT_MOST) {
//                setMeasuredDimension(widthSpecSize, 50);
//            }
//            Log.d(TAG,"  isDraeDouble ");
//        } else {
            if (widthSpecMode == MeasureSpec.AT_MOST
                    && heightSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(15, 15);
            } else if (widthSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(15, heightSpecSize);
            } else if (heightSpecMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSpecSize, 15);
            }
//            Log.d(TAG,"   not isDraeDouble ");
//        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        //使得padding生效
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();


        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(contentWidth,contentHeight) / 2;
//        Log.d(TAG, " radius = " + radius);



//        if (isDraeDouble){
//            mOutPaint.setStyle(Paint.Style.STROKE); //设置空心
//            mOutPaint.setStrokeWidth(15);
//            mOutPaint.setAntiAlias(true);
//            canvas.drawCircle(paddingLeft + contentWidth / 2, paddingTop + contentHeight / 2, 25 -15, mOutPaint);
//            canvas.drawCircle(paddingLeft + contentWidth / 2, paddingTop + contentHeight / 2, 25 -15 , mPaint);

//            canvas.drawCircle(mWidth/ 2, mWidth / 2,mWidth-5 , mOutPaint);
//            canvas.drawCircle(mWidth/ 2, mWidth / 2,mWidth-5 , mPaint);
//        } else {
            canvas.drawCircle(paddingLeft + contentWidth / 2, paddingTop + contentHeight / 2, radius, mPaint);

//            canvas.drawCircle(mWidth/ 2, mWidth / 2,mWidth , mPaint);
//        }


        // Draw the text.
//        canvas.drawText(mExampleString,
//                paddingLeft + (contentWidth - mTextWidth) / 2,
//                paddingTop + (contentHeight + mTextHeight) / 2,
//                mTextPaint);


        // Draw the example drawable on top of the text.
//        if (mExampleDrawable != null) {
//            mExampleDrawable.setBounds(paddingLeft, paddingTop,
//                    paddingLeft + contentWidth, paddingTop + contentHeight);
//            mExampleDrawable.draw(canvas);
//        }
    }


    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
//    public String getExampleString() {
//        return mExampleString;
//    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
//    public void setExampleString(String exampleString) {
//        mExampleString = exampleString;
//        invalidateTextPaintAndMeasurements();
//    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
//    public int getExampleColor() {
//        return mExampleColor;
//    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
//    public void setExampleColor(int exampleColor) {
//        mExampleColor = exampleColor;
//        invalidateTextPaintAndMeasurements();
//    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
//    public float getExampleDimension() {
//        return mExampleDimension;
//    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
//    public void setExampleDimension(float exampleDimension) {
//        mExampleDimension = exampleDimension;
//        invalidateTextPaintAndMeasurements();
//    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
//    public Drawable getExampleDrawable() {
//        return mExampleDrawable;
//    }

//    /**
//     * Sets the view's example drawable attribute value. In the example view, this drawable is
//     * drawn above the text.
//     *
//     * @param exampleDrawable The example drawable attribute value to use.
//     */
//    public void setExampleDrawable(Drawable exampleDrawable) {
//        mExampleDrawable = exampleDrawable;
//    }


    public void setColor(int color) {
        mColor = color;
        invalidateTextPaintAndMeasurements();
    }
//
//    public void setOutWith(int outWith) {
//        this.mOutWith = outWith;
////        invalidateTextPaintAndMeasurements();
//    }
//
//    public void setOutHeight(int outHeight) {
//        this.mOutHeight = outHeight;
////        invalidateTextPaintAndMeasurements();
//    }

    public void setOutColor(int outColor) {

        mOutColor = outColor;
        invalidateTextPaintAndMeasurements();
    }

    public void setDraeDouble(boolean draeDouble) {
        isDraeDouble = draeDouble;
    }


}
