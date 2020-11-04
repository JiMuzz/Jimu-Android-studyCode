package com.example.studynote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.os.Trace;
import android.util.Log;
import android.util.LogPrinter;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.Domain;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Dns;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] a = new int[]{0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        findRepeatNumber(a);

    }



    public int findRepeatNumber(int[] nums) {
        Set<Integer> test = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!test.add(nums[i])) {
                return nums[i];
            }
        }
        return 0;
    }

    class Solution {
        public int FindRepeatNumber(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                nums[nums[i]] += nums.length;
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= 2 * nums.length) {
                    return i;
                }
            }
            return -1;
        }
    }


}
