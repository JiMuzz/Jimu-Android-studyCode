package com.example.studynote.JavaKnowledge;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * 深拷贝和浅拷贝
 */
public class DeepAndShallowCopy {

    /**
     * 定义一个用户，有他的年龄
     */
    public class User {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


    /**
     * java中,如果只是用=赋值是不能进行拷贝的，只是赋值了一个对象的引用
     * 简单点说呢，两个名字对应的其实是同一个人
     * 这里其实就没有拷贝，其实还是一个对象引用地址
     */
    public class equalObject {
        public void main(String[] args) {
            User usera = new User();
            usera.setAge(20);
            User userb = usera;
            //修改usera的年龄
            usera.setAge(22);
            //输出userb的年龄，会发现也是22
            Log.e("", userb.getAge() + "");
        }
    }

    /*---------------浅拷贝----------------*/

    /**
     * 那怎么拷贝呢，要用到clone方法
     * 1、在object里面有个protected clone方法，我们需要覆盖该方法为public
     * 2、实现Cloneable接口
     */
    public class User2 implements Cloneable {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @NonNull
        @Override
        public Object clone() {
            User2 user2 = null;
            try {
                user2 = (User2) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return user2;
        }
    }

    /**
     * 将之前的=usera改成= (User2) usera.clone()
     * 就实现了对象的拷贝，usera的改变也不会影响到userb了
     * 这种拷贝其实就是浅拷贝，拷贝为啥还分深浅呢？接着往下看
     */
    public class copyObject {
        public void main(String[] args) {
            User2 usera = new User2();
            usera.setAge(20);
            User2 userb = (User2) usera.clone();
            //修改usera的年龄
            usera.setAge(22);
            //输出userb的年龄，还是11
            Log.e("", userb.getAge() + "");
        }
    }

    /**
     * 这次我们给user增加一个对象变量，爱好
     */
    public class Favorite {
        private String sport;
        private String color;

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public class User3 implements Cloneable {
        private int age;
        private Favorite favorite;

        public Favorite getFavorite() {
            return favorite;
        }

        public void setFavorite(Favorite favorite) {
            this.favorite = favorite;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @NonNull
        @Override
        public Object clone() {
            User3 user2 = null;
            try {
                user2 = (User3) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return user2;
        }
    }

    /**
     * ok,现在增加了一个变量，favorite
     * 还是改变年龄，没问题，userb没变
     * 但是改变favorite的时候，发现userb的favorite也跟着变了！！
     * 所以，浅拷贝意思就是只拷贝对象中的基本数据类型，而引用数据类型还是引用传递
     * 其实就是值传递+引用传递
     * 那深拷贝呢就是将对象中的所有引用传递继续赋里面的值，一直到所有的基本数据类型都传递完毕
     * 简单点说，深拷贝就是可以copy对象的所有值，使得两个对象完全不影响
     */
    public class copyObject2 {
        public void main(String[] args) {
            User3 usera = new User3();
            Favorite favorite = new Favorite();
            favorite.setColor("蓝色");
            usera.setAge(20);
            usera.setFavorite(favorite);
            User3 userb = (User3) usera.clone();
            //修改usera的年龄和爱好颜色
            usera.setAge(22);
            usera.getFavorite().setColor("红色");
            //输出userb的年龄，还是11
            Log.e("", userb.getAge() + "");
            //输出userb的爱好，变成了红色！！
            Log.e("", userb.getFavorite().getColor() + "");
        }
    }


    /*---------------深拷贝（有点累了额）----------------*/

    public class Favorite2 implements Cloneable {
        private String sport;
        private String color;

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public Object clone() {
            Favorite2 addr = null;
            try {
                addr = (Favorite2) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return addr;
        }
    }

    public class User4 implements Cloneable {
        private int age;
        private Favorite2 favorite;

        public Favorite2 getFavorite() {
            return favorite;
        }

        public void setFavorite(Favorite2 favorite) {
            this.favorite = favorite;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @NonNull
        @Override
        public Object clone() {
            User4 user4 = null;
            try {
                user4 = (User4) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            user4.favorite = (Favorite2) favorite.clone();
            return user4;
        }
    }

    public class copyObject3 {
        public void main(String[] args) {
            User4 usera = new User4();
            Favorite2 favorite = new Favorite2();
            favorite.setColor("蓝色");
            usera.setAge(20);
            usera.setFavorite(favorite);
            User4 userb = (User4) usera.clone();
            //修改usera的年龄和爱好颜色
            usera.setAge(22);
            usera.getFavorite().setColor("红色");
            //输出userb的年龄，还是11
            Log.e("", userb.getAge() + "");
            //输出userb的爱好，还是蓝色
            Log.e("", userb.getFavorite().getColor() + "");
        }
    }


    /**
     * 课代表总结！！！！！！！！
     *
     * 深拷贝对引用数据类型的成员变量的对象图中所有的对象都开辟了内存空间；
     * 而浅拷贝只是传递地址指向，新的对象并没有对引用数据类型创建内存空间。
     *
     */

}
