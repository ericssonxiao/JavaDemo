package com.ericxiao.leetcode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class TwoSumTest {
    final Logger log = Logger.getGlobal();

    @Test
    public void shouldArrayEquals() {
        TwoSum obj = new TwoSum();
        int[] val = new int[]{2,3,6,8};
        int[] res = obj.TwoSum(val,9);
        log.info(Arrays.toString(res));
//        Assert.assertArrayEquals(new int[]{0,1},res);
        Assert.assertArrayEquals(new int[]{1,2},res);
        log.info("start process...");
        log.warning("memory is running out...");
        log.fine("ignored.");
        log.severe("process will be terminated...");
    }
}
