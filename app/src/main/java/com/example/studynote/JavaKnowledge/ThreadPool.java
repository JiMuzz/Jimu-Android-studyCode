package com.example.studynote.JavaKnowledge;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.UserManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.studynote.DesignPatterns.Singleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池来咯
 * <p>
 * 线程池，顾名思义，装了很多线程的池子，
 * 当池子中有空闲的线程就可以直接复用，当没有空闲的线程就需要在池子外等待
 */
public class ThreadPool {

    /**
     * 有三种简单的创建线程的方法
     */
    public class SimpleThread {
        //固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //不限制线程上限的线程池
        ExecutorService pool2 = Executors.newCachedThreadPool();
        //只有一个线程的线程池
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
    }


    /**
     * 完成构造函数创建线程池
     * <p>
     * 固定大小的线程池,相当于上述的newFixedThreadPool，这里是完整构造方法
     * <p>
     * 线程池工作流程：
     * <p>
     * If fewer than corePoolSize threads are running, the Executor always prefers adding a new thread rather than queuing.
     * If corePoolSize or more threads are running, the Executor always prefers queuing a request rather than adding a new thread.
     * If a request cannot be queued, a new thread is created unless this would exceed maximumPoolSize, in which case, the task will be rejected.
     * <p>
     * 如果运行的线程数小于corePoolSize，则直接创建核心新线程执行
     * 如果运行线程数大于corePoolSize，则进入排队队列
     * 如果队列满了并且运行线程数小于maximumPoolSize,创建非核心线程执行
     * 如果队列满了并且运行线程数大于maximumPoolSize，则调用拒绝策略拒绝任务
     * （corePoolSize>排队队列>maximumPoolSize>拒绝策略）
     *
     * <p>
     * 线程队列：
     * 1、ArrayBlockingQueue
     * 是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
     * <p>
     * 2、LinkedBlockingQueue
     * 一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，
     * 吞吐量通常要高于ArrayBlockingQueue。
     * Executors.newFixedThreadPool()和Executors.newSingleThreadExecutor使用了这个队列。
     * <p>
     * 3、SynchronousQueue
     * 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，
     * 否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，
     * Executors.newCachedThreadPool使用了这个队列。
     * <p>
     * 4、PriorityBlockingQueue
     * 一个具有优先级的无限阻塞队列。
     * <p>
     * 无界队列与有界队列区别：
     * 无界队列不存在队伍入队失败情况，如果没有空闲的线程，则任务进入队列等待。
     * 若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。
     * 无界队列就不会受到maximumPoolSize的影响了。
     * <p>
     * 拒绝策略：
     * AbortPolicy	抛出RejectedExecutionException
     * DiscardPolicy	什么也不做，直接忽略
     * DiscardOldestPolicy	丢弃执行队列中最老的任务，尝试为当前提交的任务腾出位置
     * CallerRunsPolicy	直接由提交任务者执行这个任务
     */
    public class newThreadPool {
        ExecutorService pool = new ThreadPoolExecutor(10, //长期维持的线程数
                10,//线程数上限
                0L,
                TimeUnit.MILLISECONDS,//超过这个时间，多余的线程就会被回收
                new LinkedBlockingQueue<Runnable>(),//任务排队队列
                new ThreadFactory() {//线程产生方式
                    @Override
                    public Thread newThread(Runnable runnable) {
                        return null;
                    }
                }, new RejectedExecutionHandler() {//线程拒绝策略，可以不写这个参数
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

            }
        });
    }


    /**
     * 看了这么多概念，现在来实践下
     * <p>
     * <p>
     * 当前任务是输出name，并且等待一秒
     * 现在来了6个任务，
     * 第一个任务来了，有空闲线程，就直接执行了，
     * 第二个任务来了，没有空闲线程，但是队列没满，进入队列等待
     * 第三个任务来了，没有空闲线程，但是队列没满，进入队列等待
     * 第四个任务来了，没有空闲线程，但是队列没满，进入队列等待
     * 第五个任务来了，没有空闲线程，队列已满（队列大小为3），
     * 但是现在正在运行的线程数为1，没超过最大线程数，所以进入创建线程执行任务
     * 第六个任务来了，没有空闲线程，队列已满，最大线程数已经达到，所以直接根据拒绝策略拒绝
     * <p>
     * <p>
     * 所以最后执行结果是：
     * <p>
     * 任务1
     * 任务5
     * 拒绝
     * 任务2
     * 任务3
     * 任务4
     */
    public class ThreadPoolExcutorTest implements Runnable {
        public String name;

        public ThreadPoolExcutorTest(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void main(String[] args) {
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(3);
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                    1, //corePoolSize
                    2,    //maximumPoolSize
                    1L,
                    TimeUnit.SECONDS,
                    workQueue
            );
            threadPool.execute(new ThreadPoolExcutorTest("任务1"));
            threadPool.execute(new ThreadPoolExcutorTest("任务2"));
            threadPool.execute(new ThreadPoolExcutorTest("任务3"));
            threadPool.execute(new ThreadPoolExcutorTest("任务4"));
            threadPool.execute(new ThreadPoolExcutorTest("任务5"));
            threadPool.execute(new ThreadPoolExcutorTest("任务6"));
            threadPool.shutdown();

        }

    }



    public void createThread(){
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                //请求接口之前，初始化操作
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... parameters) {
                //请求接口
                return "";
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                //在主线程显示线程任务执行的进度
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String responseString) {
                //接收线程任务执行结果
            }
        }.execute();
    }


    public class MyService extends IntentService {


        private int count = 0;

        public MyService() {
            super("");
        }

        @Override
        protected void onHandleIntent(Intent intent) {
        }
    }



}
