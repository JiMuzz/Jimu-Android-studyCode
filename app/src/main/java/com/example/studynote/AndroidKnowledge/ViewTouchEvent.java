package com.example.studynote.AndroidKnowledge;

import android.view.MotionEvent;

import java.net.PortUnreachableException;

/**
 * 这次说下Android中的事件分发机制
 * <p>
 * 从开始点击屏幕开始，就会产生从最外层viewgroup开始一直到最里层的view一连串事件传递。
 * 每一层view或者viewgroup都会首先调用它的dispatchTouchEvent方法，然后判断是否就在当前一层消费掉事件
 */
public class ViewTouchEvent {

    /**
     * 首先上一段伪代码，是在书上看到的，也是我觉得总结的最好的
     * <p>
     * 如果当前是viewgroup层级，就会判断onInterceptTouchEvent是否为true，
     * 如果为true，则代表事件要消费在这一层级，不再往下传递。
     * 接着便执行当前viewgroup的onTouchEvent方法。如果onInterceptTouchEvent为false，
     * 则代表事件继续传递到下一层级的 dispatchTouchEvent方法，接着一样的代码逻辑，一直到最里面一层的view。
     */
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean isConsume = false;
        if (isViewGroup) {
            if (onInterceptTouchEvent(event)) {
                isConsume = onTouchEvent(event);
            } else {
                isConsume = child.dispatchTouchEvent(event);
            }

        } else {
            //isView
            isConsume = onTouchEvent(event);
        }
        return isConsume;
    }


    /**
     * 最里面一层的view**则会直接执行onTouchEvent方法，
     * 这时候，view有没有权利拒绝消费事件呢？
     * 按道理view作为最底层的，应该是没有发言权才对。
     * 但是呢，秉着公平公正原则，view也是可以拒绝的，
     * 可以在onTouchEvent方法返回false，表示他不想消费这个事件。
     * 那么这个事件又会怎么处理呢？见下面一段伪代码：
     * <p>
     * 如果view的onTouchEvent方法返回false，那么它的父容器的onTouchEvent又会被调用，
     * 如果父容器的onTouchEvent又返回false，则又交给上一级。
     * 一直到最上层，也就是Activity的onTouchEvent被调用。
     */
    public void handleTouchEvent(MotionEvent event) {
        if (!onTouchEvent(event)) {
            getParent.onTouchEvent(event);
        }
    }

    /**
     * 另外，关于onTouch，onTouchEvent和onClick又是怎么样的调用关系呢？
     * 当某一层view的onInterceptTouchEvent被调用，则代表当前层级要消费事件。
     * 如果它的onTouchListener被设置了的话，则onTouch会被调用，如果onTouch的返回值返回true，则onTouchEvent不会被调用。
     * 如果返回false或者没有设置onTouchListener，则会继续调用onTouchEvent。
     * 而onClick方法则是设置了onClickListener则会被正常调用。为了方便理解，继续来段伪代码
     */
    public void consumeEvent(MotionEvent event) {
        if (setOnTouchListener) {
            onTouch();
            if (!onTouch()) {
                onTouchEvent(event);
            }
        } else {
            onTouchEvent(event);
        }

        if (setOnClickListener) {
            onClick();
        }
    }


    /**
     * 以下只是方便伪代码不报错，不代表任何意思
     */
    public void onClick(){};
    public boolean setOnClickListener;
    public boolean setOnTouchListener;
    public boolean isViewGroup;
    public ViewTouchEvent getParent;
    public ViewTouchEvent child;
    public boolean testboolean;
    public boolean onTouch(){return testboolean;}
    public boolean onInterceptTouchEvent(MotionEvent event){return testboolean;}
    public boolean onTouchEvent(MotionEvent event){return testboolean;}
}
