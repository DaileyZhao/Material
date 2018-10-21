package com.algorithm.java;

import java.util.Arrays;

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

    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr){
        int[] temp=new int[arr.length];//在排序前，先定义好一个临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if (left<right){
            int mid=(left+right)/2;
            sort(arr, left, mid, temp);
            sort(arr, mid+1, right, temp);
            merge(arr,left,mid,right,temp);
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
    public static void main(String args[]){
        int []arr = {9,8,7,6,5,4,3,2,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
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
