package com.sununiq.snippet.leetcode;

import java.util.Stack;

/**
 * @program: java-snippet
 *
 * @description:
 *
 * @author: sununiq
 *
 * @create: 2018-10-28 17:12
 **/
public class WordSearch79 {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(new WordSearch79().exist(board, "SEE"));
        System.out.println(new WordSearch79().exist(board, "ABCCED"));
        System.out.println(new WordSearch79().exist(board, "ABCB"));
    }

    public boolean exist(char[][] board, String word) {
        if (word == null || word.trim().equals("")) {
            return true;
        }

        char[] chs = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, chs, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] words, int index, int x, int y) {
        if (index == words.length) {
            return true;
        }
        if (x < 0 || x == board.length || y < 0 || y == board[0].length) {
            return false;
        }
        if (board[x][y] != words[index]) {
            return false;
        }

        char source = board[x][y];
        board[x][y] = '*'; // mark the point first

        boolean exist = dfs(board, words, index + 1, x, y + 1)
            || dfs(board, words, index + 1, x, y - 1)
            || dfs(board, words, index + 1, x + 1, y)
            || dfs(board, words, index + 1, x - 1, y);

        board[x][y] = source;
        return exist;
    }
}
