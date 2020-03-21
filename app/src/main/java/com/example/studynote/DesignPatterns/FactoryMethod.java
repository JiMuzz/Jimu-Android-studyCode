package com.example.studynote.DesignPatterns;

/**
 * 工厂方法模式
 * 封装类中变化的部分，提取其中个性化善变的部分为独立类，通过依赖注入以达到解耦、复用和方便后期维护拓展的目的
 */
public class FactoryMethod {

    /**
     * 抽象产品类，所有产品的抽象父类，描述公共的方法部分
     */
    public abstract class Product {
        public abstract void dosomething();
    }

    /**
     * 具体产品类，所有产品的抽象父类，描述公共的方法部分
     * 这里比如想生产 手机产品，电视产品
     */
    public class PhoneProduct extends Product {

        @Override
        public void dosomething() {
            //具体事情实现，比如这里生产cpu，屏幕，摄像头等制作手机
        }
    }

    public class TvProduct extends Product {

        @Override
        public void dosomething() {
            //具体事情实现，比如这里生产电视屏幕，遥控器等
        }
    }

    /**
     * 抽象工厂类，生产一个泛型的产品对象
     */
    public abstract class Factory {
        public abstract Product createProduct(Class<? extends Product> c);
    }


    /**
     * 具体工厂类，所有产品从这里生产，可以返回具体的产品对象
     */
    public class RealFactory extends Factory {

        public Product createProduct(Class<? extends Product> c) {
            Product product = null;
            try {
                product = (Product) Class.forName(c.getName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return product;
        }
    }


    /**
     * 具体实现
     * 将工厂实例化，并且开始生产不同产品
     * 工厂方法模式实现的精髓就在于，
     * 这一套系统建立好后，再要生产新的产品只需实现新产品的具体实现方法，实现解耦。
     */
    public class RealUser {
        public void main(String[] args) {
            Factory factory = new RealFactory();
            Product product=factory.createProduct(PhoneProduct.class);
            product.dosomething();

        }
    }
}
