package com.bilibili.juc.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class RemoveDuplicateCharUtil {

    public static final int MIN_DUPLICATE_CHAR_LENGTH = 3;
    public static final int CHAR_A_TO_INT = 97;
    public static final char  CHAR_A = 'a';
    public static final String LOWER_CHAR_REGEX = "[a-z]+";
    public static final String ERROR_TIP = "请输入合法的字符串，只能包含a~z";
    public static List<String> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        // 案例1  aabcccbbad
        String case1 = "aabcc8cbbad";
        List<Integer> charCountList1 = getDuplicateCharCount(case1, new ArrayList<>());
        removeDuplicateChar(case1, charCountList1);
        System.out.println("案例1:"+ case1 + "结果1:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
        arrayList.clear();
//
//        // 案例2  aabcccbbad
//        String case2 = "abcccbad";
//        List<Integer> charCountList2 = getDuplicateCharCount(case2, new ArrayList<>());
//        removeDuplicateChar(case2, charCountList2);
//        System.out.println("案例2:"+ case2 + "结果2:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
//
//        // 案例3  bbbbbbbbbbbb
//        String case3 = "bbbbbbbbbbbb";
//        List<Integer> charCountList3 = getDuplicateCharCount(case3, new ArrayList<>());
//        removeDuplicateChar(case3, charCountList3);
//        System.out.println("案例3:"+ case3 + "结果3:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
//
//        // 案例4  aabbccddeee
//        String case4 = "aabbccddeee";
//        List<Integer> charCountList4 = getDuplicateCharCount(case4, new ArrayList<>());
//        removeDuplicateChar(case4, charCountList4);
//        System.out.println("案例4:"+ case2 + "结果4:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
//
//        // 案例5  eeeaaabbdbbadbaadsajeyytk
//        String case5 = "eeeaaabbdbbadbaadsajeyytk";
//        List<Integer> charCountList5 = getDuplicateCharCount(case5, new ArrayList<>());
//        removeDuplicateChar(case5, charCountList5);
//        System.out.println("案例5:"+ case5 + "结果5:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
//
//        // 案例6  kajdhafehfueueuuuuuuuyeqeywqeyyyy
//        String case6 = "kajdhafehfueueuuuuuuuyeqeywqeyyyy";
//        List<Integer> charCountList6 = getDuplicateCharCount(case6, new ArrayList<>());
//        removeDuplicateChar(case6, charCountList6);
//        System.out.println("案例6:"+ case6 + "结果6:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
//
//        // 案例7  ""
//        String case7 = "";
//        List<Integer> charCountList7 = getDuplicateCharCount(case7, new ArrayList<>());
//        removeDuplicateChar(case7, charCountList7);
//        System.out.println("案例7:"+ case7 + "结果7:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
//
//        // 案例8  null
//        String case8 = null;
//        List<Integer> charCountList8 = getDuplicateCharCount(case7, new ArrayList<>());
//        removeDuplicateChar(case8, charCountList8);
//        System.out.println("案例8:"+ case7 + "结果8:" + (arrayList.size() == 0? "": arrayList.get(arrayList.size() - 1)));
//        arrayList.clear();
    }

    /**
     * 删除连续重复的字符
     * @param str 字符串
     * @param charCountList 统计列表
     */
    public static void removeDuplicateChar (String str, List<Integer> charCountList){
        if(!Optional.ofNullable(str).isPresent()){
            return;
        }
        if(!str.matches(LOWER_CHAR_REGEX)){
            System.out.println(ERROR_TIP);
            return;
        }
        boolean continueFlag = false;
        StringBuffer stringBuffer = new StringBuffer();
        int charIndex = 0;
        for (int i = 0; i < charCountList.size(); i++) {
            charIndex += charCountList.get(i);
            if( MIN_DUPLICATE_CHAR_LENGTH > charCountList.get(i)){
                for (int j = 0; j < charCountList.get(i); j++) {
                    stringBuffer.append(str.charAt(charIndex - 1));
                }
            }
            if(MIN_DUPLICATE_CHAR_LENGTH <= charCountList.get(i) && !continueFlag){
                if(CHAR_A != str.charAt(charIndex - 1)){
                    stringBuffer.append(beforeChar(str.charAt(charIndex - 1)));
                }
                continueFlag = true;
            }
        }
        arrayList.add(stringBuffer.toString());
        if(continueFlag){
            removeDuplicateChar(stringBuffer.toString(), getDuplicateCharCount(stringBuffer.toString(), new ArrayList<>()));
        }
    }

    /**
     * 统计字符串字符的个数
     * @param str
     * @param charCountList
     * @return
     */
    public static List<Integer> getDuplicateCharCount(String str, List<Integer> charCountList){
        if(!Optional.ofNullable(str).isPresent()){
            return charCountList;
        }
        if(!str.matches(LOWER_CHAR_REGEX)){
            System.out.println(ERROR_TIP);
        }
        char[] charArray  = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            // 统计连续相同的字符次数
            int j = 1;
            while(i != charArray.length - 1 && charArray[i] == charArray[i+1]){
                i ++ ;
                j++;
            }
            charCountList.add(j);
        }
        return charCountList;
    }

    /**
     * 获取前一个字符
     * @param targetChar 目标字符
     * @return
     */
    public static char beforeChar(char targetChar){
        int charInt = targetChar;
        if(charInt == CHAR_A_TO_INT){
            return targetChar;
        }
        return (char) (charInt - 1);
    }
}
