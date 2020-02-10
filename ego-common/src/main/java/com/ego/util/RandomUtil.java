package com.ego.util;

/**
 * @see( 功能介绍 ) :  验证码生成工具类
 * @version( 版本号 ) : 1.0
 * @author( 创建人 ) : Dylan
 * @since : JDK 1.8
 */
public class RandomUtil {
    //  获取 n 位随机验证码
    public static String getRandom1() {
//  还有一种简单的办法就是把所有大小写字母和数字都写到一起 直接一个随机数就生成了
        String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String code = "";
        for (int i = 0; i < 4; i++) {
            if (Math.random() * 2 > 1) {
                if (Math.random() * 2 > 1) {
                    code = code + letters[(int) Math.floor(Math.random() * 26 + 1)];
                } else {
                    String s = letters[(int) Math.floor(Math.random() * 26 + 1)];
                    code = code + s.toUpperCase();
                }
            } else {
                code = code + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
            }
        }
        return code;
    }

    //  获取 n 位随机验证码
    public static String getRandom2(Integer n) {
        String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String code = "";
        for (int i = 0; i < n; i++) {
            code = code + letters[(int) Math.floor(Math.random() * letters.length + 1)];
        }
        return code;
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.getRandom1());
    }
}