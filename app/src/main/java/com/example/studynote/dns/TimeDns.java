package com.example.studynote.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Dns;

public class TimeDns implements Dns {
    private long timeout;

    public TimeDns(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public List<InetAddress> lookup(final String hostname) throws UnknownHostException {
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        } else {
            try {
                FutureTask<List<InetAddress>> task = new FutureTask<>(
                        new Callable<List<InetAddress>>() {
                            @Override
                            public List<InetAddress> call() throws Exception {
                                return Arrays.asList(InetAddress.getAllByName(hostname));
                            }
                        });
                new Thread(task).start();
                return task.get(timeout, TimeUnit.MILLISECONDS);
            } catch (Exception var4) {
                UnknownHostException unknownHostException =
                        new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                unknownHostException.initCause(var4);
                throw unknownHostException;
            }
        }
    }
}
