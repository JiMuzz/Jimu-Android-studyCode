package com.example.studynote.DesignPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式（发布-订阅模式）
 * 顾名思义，这个模式就是用来观察的，形成一种观察的关系后，当这个对象的状态改变，其他所有的观察者都会有所反馈
 * 比如：最近很火的抢口罩哈哈，当所有人都订阅观察一款口罩的存货信息，
 * 口罩有存货后会给每个用户发消息，然后用户就可以来抢购了
 */
public class ObserverModel {

    /**
     * 定义观察者的接口
     */
    public interface Observer {
        public void notifyMsg(String msg);
    }

    /**
     * 定义买口罩的用户类
     */
    public class BuyUser implements Observer {

        @Override
        public void notifyMsg(String msg) {
            //收到口罩有货的消息就去抢购
        }
    }

    /**
     * 定义口罩的接口
     */
    public interface observable {
        public void addBuyUser(BuyUser user);

        public void deleteBuyUser(BuyUser user);

        public void notify(String msg);
    }

    /**
     * 定义被观察类，口罩
     */
    public class Mask implements observable {

        List<BuyUser> mUserList = new ArrayList();

        @Override
        public void addBuyUser(BuyUser user) {
            mUserList.add(user);
        }

        @Override
        public void deleteBuyUser(BuyUser user) {
            mUserList.remove(user);
        }

        @Override
        public void notify(String msg) {
            for (BuyUser user : mUserList) {
                user.notifyMsg(msg);
            }
        }
    }

    /**
     * 实际应用情况如下，定义一个口罩，增加几个对口罩观察的对象，也就是购买用户，
     * 当口罩有货时，会告诉每一个订阅了这个口罩库存消息的用户，然后用户就可以去购买了。
     */
    public class RealClient{
        public  void main(String[] args) {
            Mask mask=new Mask();
            mask.addBuyUser(new BuyUser());
            mask.addBuyUser(new BuyUser());
            mask.addBuyUser(new BuyUser());
            mask.notify("口罩有货啦");
        }
    }


}
