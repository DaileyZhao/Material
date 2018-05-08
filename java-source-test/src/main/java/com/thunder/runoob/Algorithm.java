package com.thunder.runoob;

/**
 * Create by zcm on 2018/5/8 下午2:55
 */
public class Algorithm {
    /*
     * 规律：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束：
     * 如果该数字大于要查找的数字，剔除这个数字所在的列：如果该数字小于要查找的数字，剔除这个数字所在的行。
     * 也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除）行或者一列，这样每一步都可以缩小
     * 查找的范围，直到找到要查找的数字，或者查找范围为空。
     */
    public static boolean findFromArray(int number,int array[][]){
        int rows=array.length;
        int cols=array[0].length;
        if (array.length<1||array[0].length<1){
            return false;
        }
        int row=0;
        int col=cols-1;
        while (row>=0&&row<rows&&col>=0&&col<cols){
            if (number==array[row][col]){
                return true;
            }else if (array[row][col]>number){
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    public static String replaceSpace(StringBuffer str){
        // 空格的个数
        int spaceNum = 0;
        // 遍历查找空格的个数
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == ' ') {
                spaceNum ++;
            }
        }
        int indexOld = str.length() - 1;  // 原字符串下标
        int indexNew =  str.length() + 2 * spaceNum - 1; // 新字符串下标
        str.setLength(indexNew+1);   // 设置新的str长度
        while (indexOld >= 0 ) {
            if(str.charAt(indexOld) == ' ') {
                str.setCharAt(indexNew--, '0');
                str.setCharAt(indexNew--, '2');
                str.setCharAt(indexNew--, '%');
            } else {
                str.setCharAt(indexNew--, str.charAt(indexOld));
            }
            indexOld --;
        }
        return str.toString();
    }

    public static void main(String args[]){
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(findFromArray(7,matrix));
        StringBuffer str=new StringBuffer("   ");
        System.out.println(""+replaceSpace(str)+"");
    }
}
