package com.sununiq.snippet.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 全排列: 1. Johnson Trotter Algorithm
 * 2. 递归求解，固定第一个字符，后面的字符全排列，第一个字符出现的次数n, 后面n-1个字符出现的次数(n-1)!
 * 合计：n * (n-1)!
 * 3. dfs搜索树
 * 4. bfs搜索
 */
public class Permutations {

    /**
     * dfs 搜索
     * @param nums
     * @return
     */
    public List<List<Integer>> permute4(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return permutations;
        }

        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int num : nums) {
            stack.push(Arrays.asList(num));
        }

        while (!stack.isEmpty()) {
            List<Integer> per = stack.pop();
            // 递归终止条件
            if(per.size() == nums.length) {
                permutations.add(per);
                continue;
            }

            for (int num : nums) {
                if(!per.contains(num)) {
                    List<Integer> toSearch = new ArrayList<>(per);
                    toSearch.add(num);

                    stack.push(toSearch);
                }
            }
        }

        return permutations;
    }

    /**
     * bfs 广度优先搜索
     */
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        int l = nums.length;
        if(l == 0) {
            return permutations;
        }

        //bfs 起始搜索路径
        Queue<List<Integer>> paths = new LinkedList<>();
        for (int n : nums) {
            paths.offer(Arrays.asList(n));
        }

        while (!paths.isEmpty()) {
            List<Integer> per = paths.poll();
            // 递归终止条件
            if(per.size() == nums.length) {
                permutations.add(per);
                continue;
            }

            // 开始搜索
            for (int n : nums) {
                if(!per.contains(n)) {
                    List<Integer> toSearch = new ArrayList<>(per);
                    toSearch.add(n);

                    paths.offer(toSearch);
                }
            }
        }

        return permutations;

    }

    List<List<Integer>> result1 = new ArrayList<>();

    /**
     * 递归求解
     */
    public List<List<Integer>> permute1(int[] nums) {
        permute1(nums, 0);
        return result1;
    }

    private void permute1(int[] nums, int pos) {
        // 递归终止条件
        if(pos == nums.length - 1) {
            result1.add(array2List(nums));
        }

        for (int i = pos; i < nums.length; i++) {
            swap(nums, i, pos);
            permute1(nums, pos + 1);
            swap(nums, i, pos); // 很重要，要交换回来，保证是原来的数组
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static List<Integer> array2List(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }


    /**
     * Johnson Trotter Algorithm
     * TODO 未完成
     */
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        boolean[] directions = initDirections(nums);

        while (hashMovableElement(nums, directions)) {

        }

        return null;
    }

    /**
     * 如果元素k指向一个相邻的较小的元素，就说这个在以箭头标记的排列中是移动的
     */
    private boolean hashMovableElement(int[] nums, boolean[] directions) {
        for (int i = 0; i < nums.length; i++) {
            if(directions[i]) {
                if(i < nums.length && nums[i] > nums[i + 1]) {
                    return true;
                }
            } else {
                if(i > 0 && nums[i] > nums[i - 1]) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 初始化方向, false: 指向右边, true: 指向左边
     *
     * @param nums
     * @return
     */
    private boolean[] initDirections(int[] nums) {
        boolean[] directions = new boolean[nums.length];
        for (int i = 0; i < directions.length; i++) {
            directions[i] = false;
        }
        return directions;
    }

    public static void main(String[] args) {
        System.out.println(new Permutations().permute4(new int[]{1, 2, 3}));
    }

}
