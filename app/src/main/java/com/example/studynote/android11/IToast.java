package com.example.studynote.android11;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studynote.MainApplication;
import com.example.studynote.R;

public class IToast {
    /**
     * 展示toast==LENGTH_SHORT
     *
     * @param msg
     */
    public static void show(String msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 展示toast==LENGTH_LONG
     *
     * @param msg
     */
    public static void showLong(String msg) {
        show(msg, Toast.LENGTH_LONG);
    }


    private static void show(String massage, int show_length) {
        Context context = MainApplication.getInstance();
        //使用布局加载器，将编写的toast_layout布局加载进来
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        //获取ImageView
        ImageView image = (ImageView) view.findViewById(R.id.toast_iv);
        //设置图片
        image.setImageResource(R.mipmap.ic_launcher);
        //获取TextView
        TextView title = (TextView) view.findViewById(R.id.toast_tv);
        //设置显示的内容
        title.setText(massage);
        Toast toast = new Toast(context);
        //设置Toast要显示的位置，水平居中并在底部，X轴偏移0个单位，Y轴偏移70个单位，
//        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 70);
        //设置显示时间
        toast.setDuration(show_length);

        toast.setView(view);
        toast.show();
    }


}