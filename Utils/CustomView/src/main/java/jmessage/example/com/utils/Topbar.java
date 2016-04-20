package jmessage.example.com.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ii on 2016/4/14.
 */
public class Topbar extends RelativeLayout {

    //自定义两个button和一个textview
    private Button btn_left;
    private Button btn_right;
    private TextView txt_title;

    //左边
    private String leftText;
    private int leftTextColor;
    private Drawable leftBackground;

    //中间
    private String titleCenter;
    private int titleCenterColor;
    private float titleCenterTextSize;

    //右边
    private String rightText;
    private int rightTextColor;
    private Drawable rightBackground;

    //布局参数设置
    private LayoutParams leftParams;
    private LayoutParams rightParams;
    private LayoutParams titleParams;

    /**
     * 创建一个接口
     */
    public interface topbarClickListener{
        public void leftClick();
        public void rightClick();
    }

    //映射传递进来的参数
    private topbarClickListener listener;
    /**
     * 给调用者暴露一个可调用的方法
     */
    public void setOnTopbarClickListener(topbarClickListener listener){
        this.listener = listener;
    }

    /**
     * 自定义View,需要重写这样一个构造函数（attrs）
     * 实现自定义属性值和自定义属性之间的映射
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * 将所有获取来的自定义属性的值保存在TypedArray中
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);

        /**
         * 取出自定义属性值
         */
        //左边
        leftText = ta.getString(R.styleable.Topbar_leftText);
        //这里，第二个参数为默认值
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);

        //中间
        titleCenter = ta.getString(R.styleable.Topbar_titleCenter);
        titleCenterColor = ta.getColor(R.styleable.Topbar_titleCenterTextColor, 0);
        titleCenterTextSize = ta.getDimension(R.styleable.Topbar_titleCenterTextSize, 0);

        //右边
        rightText = ta.getString(R.styleable.Topbar_rightText);
        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);

        /**
         * 最后，使用完TypedArray之后，要进行回收
         * 一是避免浪费资源；二是避免缓存满溢
         */
        ta.recycle();

        //实例化我们定义的button和textview
        btn_left = new Button(context);
        btn_right = new Button(context);
        txt_title = new TextView(context);

        /**
         * 将自定义属性值赋给自定义属性
         */
        //左边
        btn_left.setText(leftText);
        btn_left.setTextColor(leftTextColor);
        btn_left.setBackground(leftBackground);

        //中间
        txt_title.setText(titleCenter);
        txt_title.setTextColor(titleCenterColor);
        txt_title.setTextSize(titleCenterTextSize);
        //设置文字居中显示
        txt_title.setGravity(Gravity.CENTER);

        //右边
        btn_right.setText(rightText);
        btn_right.setTextColor(rightTextColor);
        btn_right.setBackground(rightBackground);

        //给viewGroup也设置一个背景颜色
        setBackgroundColor(0xfff59563);

        /**
         * 设置自定义属性的参数
         */

        //左边
        //定义宽和高
        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //居左对齐
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);

        
        //中间
        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //居中对齐
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);


        //右边
        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //居右对齐
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        /**
         *  将自定义属性以Params的形式添加到viewgroup中
         */
        addView(btn_left, leftParams);
        addView(txt_title, titleParams);
        addView(btn_right, rightParams);

        /**
         * 为自定义属性添加响应事件(模板)
         */
        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }

    /**
     * 设置左侧显示图标（因为左侧是button按钮，所以这里的图标也就是指button的background）
     * 右侧同理
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setLeftIcon(Drawable drawable){
        btn_left.setBackground(drawable);
    }

    /**
     * 设置左侧显示文字
     */
    public void setLeftText(String leftText){
        btn_left.setText(leftText);
    }

    /**
     * 设置左侧文字颜色
     */
    public void setLeftTextColor(int color){
        btn_left.setTextColor(color);
    }

    /**
     * 设置中间标题的文字
     */
    public void setTitleCenterText(String titleCenter){
        txt_title.setText(titleCenter);
    }
    /**
     * 设置中间文字的颜色
     */
    public void setTitleCenterColor(int color){
        txt_title.setTextColor(color);
    }
    /**
     * 设置中间文字的大小
     */
    public void setTitleCenterTextSize(int size){
        txt_title.setTextSize(size);
    }

    /**
     * 设置左侧显示图标
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setrightIcon(Drawable drawable){
        btn_right.setBackground(drawable);
    }
    /**
     * 设置左侧显示文字
     */
    public void setrightText(String rightText){
        btn_right.setText(rightText);
    }
    /**
     * 设置右侧文字的颜色
     */
    public void setRightTextColor(int color){
        btn_right.setTextColor(color);
    }
}
