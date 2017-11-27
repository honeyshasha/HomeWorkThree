package com.bwie.three.viewCricle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import com.bwie.three.R;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public class MyCricleHua extends View{
    //定义几个必要的变量
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    private int color;
    private Paint paint;
    private Paint mPaint;

    public MyCricleHua(Context context) {
        super(context);
        init(context);

    }

    public MyCricleHua(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MyCricleHua(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCircle, defStyleAttr, 0);

        int indexCount = typedArray.getIndexCount();


        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {

                case R.styleable.MyCircle_viewColor:
                    // 默认颜色设置,黑色
                    color = typedArray.getColor(attr, Color.RED);
                    break;
            }
        }
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setColor(color);
    }
    // 初始化这些变量(在构造函数中调用这个方法):
    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 6;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.asdf, options);
        mMatrix = new Matrix();
    }
    //具体绘制:
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(color);
        canvas.translate(95, 100);      // 平移坐标系
        Path path = new Path();                                 // 创建 Path
        path.addCircle(200, 200, 200, Path.Direction.CW);           // 添加一个圆形
        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure
        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }
        //这个方法是用于得到路径上某一长度的位置以及该位置的正切值：
        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片

        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, paint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, paint);                     // 绘制箭头
        invalidate();                                                           // 重绘页面
    }

    //改变颜色
    public void setColor(int red) {
        color = red;
    }

}
