package com.ericxiao.leetcode;

import java.util.HashMap;
import java.util.logging.Logger;

/*
* Given an array of integers, return indices of the two numbers such that they add up to a specific target.
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
* Example:
* Given nums = [2, 7, 11, 15], target = 9,
* Because nums[0] + nums[1] = 2 + 7 = 9,
* return [0, 1].
* */
public class TwoSum {
    final Logger log = Logger.getGlobal();

    public int[] TwoSum(int[] nums, int target) {
        log.info("start...");
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            log.info(target + "|" + nums[i] + "|" + i);
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        log.info("end...");
        return null;
    }
}
