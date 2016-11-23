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
 * Created by WangChunhe on 2016/9/30.
 */

public class DoubleCircleView extends View {
    private Paint mInPaint ;
    private Paint mOutPaint ;
    private int mInColor;
    private int mOutColor;
    private int mWidth;

    public DoubleCircleView(Context context) {
        this(context,null);
    }

    public DoubleCircleView(Context context, AttributeSet attrs) {
       this(context,attrs, 0);

    }

    public DoubleCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

          mInPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
          mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

       final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DoubleCircleView );
        mInColor = typedArray.getColor(R.styleable.DoubleCircleView_double_circle_mInColor, Color.GREEN);
        mOutColor = typedArray.getColor(R.styleable.DoubleCircleView_double_circle_mOutColor,Color.RED);
        mWidth = typedArray.getColor(R.styleable.DoubleCircleView_double_circle_mWidth,15);


        typedArray.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(50, 50);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(50, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 50);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //使得padding生效
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();


        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(contentWidth,contentHeight) / 2;
        mOutPaint.setStyle(Paint.Style.STROKE); //设置空心
        mOutPaint.setStrokeWidth(mWidth); //设置外圆宽度
        mOutPaint.setAntiAlias(true); //抗锯齿
        mOutPaint.setColor(mOutColor);
        mInPaint.setColor(mInColor);


        canvas.drawCircle(paddingLeft + contentWidth / 2, paddingTop + contentHeight / 2, radius - mWidth, mOutPaint);
        canvas.drawCircle(paddingLeft + contentWidth / 2, paddingTop + contentHeight / 2, radius - mWidth , mInPaint);

    }
}
