package com.example.studynote.JavaKnowledge;

class Parent {
    public static void main(String[] args) {
        Test test=new Test();
        test.dispatchTouchEvent();
    }


    public void dispatchTouchEvent(){
        onTouchEvent();
    }

    public void onTouchEvent(){
        System.out.println("我是父类");
    }

    static class Test extends Parent{
        @Override
        public void onTouchEvent() {
            System.out.println("我是子类");
        }
    }
}
