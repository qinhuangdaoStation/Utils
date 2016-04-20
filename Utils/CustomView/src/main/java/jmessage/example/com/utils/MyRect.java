package jmessage.example.com.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ii on 2016/4/14.
 */
public class MyRect extends View {

    //引用资源
    public MyRect(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        int color = ta.getColor(R.styleable.MyView_rect_color, 0x000000);
        setBackgroundColor(color);

        ta.recycle();
    }

    //代码调用
    public MyRect(Context context) {
        super(context);
    }
}
