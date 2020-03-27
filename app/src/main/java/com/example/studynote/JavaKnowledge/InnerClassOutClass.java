package com.example.studynote.JavaKnowledge;


/**
 * 这次说下内部类和外部类
 */
public class InnerClassOutClass {


    /**
     * 内部类顾名思义就是里面的类咯，比如这个innerClass
     * 写在InnerClassOutClass这个类里面，所以就是内部类
     */
    public class innerClass {

    }

    /**
     * 匿名内部类，顾名思义就是没有名字的内部类咯。
     * 比如User类的一个子类,正常使用是需要先声明一个子类，
     * 然后去实例化调用，如下
     */
    public abstract class User {
        public abstract void getName();
    }

    /**
     * 一个子类的正常调用
     */
    public class Student extends User {

        @Override
        public void getName() {

        }
    }

    public void Demo1() {
        Student student = new Student();
        student.getName();
    }

    /**
     * 匿名内部类使用
     * 不需要写出来子类，直接new并实现方法
     * <p>
     * 这就是匿名内部类
     */

    public void Demo2() {
        User user = new User() {
            @Override
            public void getName() {

            }
        };
    }


    /**
     *
     * 关于内部类有个考点，就是大家都知道内部类持有外部类的引用。
     * 那，为什么呢！！！？？？？
     *
     * 我都在你肚子里，怎么可能没关嘛，哈哈哈
     * 其实一句话就可以解释清楚：
     *
     * 内部类虽然和外部类写在同一个文件中，但是编译完成后，还是生成各自的class文件，内部类通过this访问外部类的成员
     *
     * 内部类在编译后会自动在构造方法里面添加一个参数，参数的类型就是外部类的类型
     * 所以在很多内部类导致的内存泄漏问题时，用的就是改成静态内部类，并且加上外部类的弱引用，比如handler处理内存泄漏问题
     * 在com.example.studynote.AndroidKnowledge.HandlerInfoActivity中有讲
     *
     *
     * 下面看看内部类以及编译后的样子
     */

    /**
     * 编译前
     *
     *
     *     class InnerUser {
     *         private int age = 20;
     *     }
     *
     */


    /**
     * 编译后
     *
     * class InnerClassOutClass$InnerUser {
     *         private int age;
     *
     *         InnerClassOutClass$InnerUser(InnerClassOutClass var1) {
     *             this.this$0 = var1;
     *             this.age = 20;
     *         }
     *     }
     */


}
