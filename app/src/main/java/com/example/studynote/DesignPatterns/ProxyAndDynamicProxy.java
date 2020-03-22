package com.example.studynote.DesignPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.PortUnreachableException;

/**
 * 代理模式和动态代理模式
 * <p>
 * 代理模式其实就是提供了对目标对象的另外的访问方式，通过代理对象访问目标对象
 * 为啥要这么麻烦呢！！！其实是为了不去修改原有的代码，通过代理也可以访问这个对象而且可以进行扩展
 * 有啥应用呢！比如明星的经纪人，为了找到明星一般是找到他的经纪人。
 * 经纪人就负责一些琐碎的或者运营方面的事情，而具体做事情就是明星本身去做
 */
public class ProxyAndDynamicProxy {

    /*-----------------------静态代理-----------------------
     *
     * */

    /**
     * 首先声明一个接口，用于工作的接口
     */
    public interface IStarDao {
        void dowork();
    }

    /**
     * 明星工作类
     * 主要为演戏
     */
    public class SuperStarDao implements IStarDao {

        @Override
        public void dowork() {
            //演戏工作
        }
    }

    /**
     * 经纪人代理类
     * 主要是负责接活，并且安排明星工作，以及后续宣传工作
     */
    public class StarbrokerDaoProxy implements IStarDao {

        private IStarDao starDao;

        public StarbrokerDaoProxy(IStarDao starDao) {
            this.starDao = starDao;
        }

        @Override
        public void dowork() {
            /*--接活--*/
            starDao.dowork();//明星工作
            /*--宣传工作--*/
        }
    }


    /**
     * 实际操作
     * 就是首先表明要找哪个明星，然后去找对应的经纪人（代理类），完成所有工作
     */
    public class RealClient {
        public void main(String[] args) {
            SuperStarDao starDao = new SuperStarDao();
            StarbrokerDaoProxy proxy = new StarbrokerDaoProxy(starDao);
            proxy.dowork();
        }
    }

    /*-----------------------动态代理-----------------------
     *
     * 动态代理的特点是不需要代理对象实现接口，可以动态实现代理功能
     *
     * */

    /**
     * 实际操作
     * 这里就不需要代理对象实现接口，直接使用Proxy的newProxyInstance方法动态生成明星的经纪人（代理类）
     */
    public class RealClient2 {
        public void main(String[] args) {
            final SuperStarDao starDao = new SuperStarDao();

            SuperStarDao proxy = (SuperStarDao) Proxy.newProxyInstance(
                    starDao.getClass().getClassLoader(),
                    starDao.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            /*--接活--*/
                            Object returnValue = method.invoke(starDao, args);//明星工作
                            /*--宣传工作--*/
                            return returnValue;
                        }
                    });

            proxy.dowork();
        }
    }

}
