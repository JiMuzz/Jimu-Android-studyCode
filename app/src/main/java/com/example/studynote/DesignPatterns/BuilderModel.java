package com.example.studynote.DesignPatterns;

/**
 * 建造者模式
 * 将一个复杂对象的构建和表示分离的模型
 * <什么意思呢，就是比如我要创建一个对象，我只需要把最重要的一些信息告诉他，他就可以帮我完成所有的事情，
 * 简单构建，不需要了解具体的怎么表示过程>
 */
public class BuilderModel {

    /**
     * 手机产品实体类，定义手机的一些属性（颜色，系统）
     */
    public class Phone {
        private String color;
        private String os;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }
    }

    /**
     * 抽象的构建类
     */
    public abstract class Builder {
        public abstract void buildColor(String color);

        public abstract void buildOs(String os);
    }

    /**
     * 具体的构建类
     */
    public class HuaweiBuilder extends Builder {
        private Phone huaweiPhone = new Phone();

        @Override
        public void buildColor(String color) {
            huaweiPhone.setColor(color);
        }

        @Override
        public void buildOs(String os) {
            huaweiPhone.setOs(os);
        }

        public Phone getHuaweiPhone() {
            return huaweiPhone;
        }
    }

    /**
     * 导演类，导演所有的一切，构建对象的各个部分
     * 其实这就有点像什么，像做了一些简单的封装，
     * 让外面的人可以轻易的创建具体需要的对象，
     * 而不用知道里面做了啥
     */
    public class Director {
        Builder mBuilder;

        public Director(Builder builder) {
            this.mBuilder = builder;
        }

        public void constract(String color, String os) {
            mBuilder.buildColor(color);
            mBuilder.buildOs(os);
        }
    }

    /**
     * 实现类
     * 通过简单的传参，创建生产对象
     * 但是其实一般不这么做，而是通过内部类方法通过builder的一种链式表达构建对象，见下面类
     */
    public class mainClient {
        public void main(String[] args) {
            Builder builder = new HuaweiBuilder();
            Director director = new Director(builder);
            director.constract("黄色", "Android");
        }
    }


    /**
     * 一般构建类这样写，构建过程写在静态内部类里面，也比较方便明了
     */
    public static class XiaomiPhone {
        private String color;
        private String os;

        private XiaomiPhone(Builder builder) {
            this.color = builder.color;
            this.os = builder.os;
        }

        public String getColor() {
            return color;
        }

        public String getOs() {
            return os;
        }

        static class Builder {
            private String color;
            private String os;

            public Builder setColor(String color) {
                this.color = color;
                return this;
            }

            public Builder setOs(String os) {
                this.os = os;
                return this;
            }

            public XiaomiPhone builder() {
                return new XiaomiPhone(this);
            }
        }
    }

    /**
     * 实现类2
     * 这样是不是就很熟悉了，很多第三方sdk都是这样方便用户调用，
     * 对了，还有dialog构建，都是这样
     */
    public class mainClient2 {
        public void main(String[] args) {
            XiaomiPhone xiaomiPhone = new XiaomiPhone.Builder()
                    .setColor("")
                    .setOs("")
                    .builder();
        }
    }

}
