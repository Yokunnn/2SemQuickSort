package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();

        System.out.println("Simple test: ");
        int[] test = {7 ,5 ,32, 6, 2, 0}; //last cell for count of swaps
        coolQuickSort(test, 0);
        for(int i = 0; i< test.length-1; i++){
            System.out.print(test[i] + " ");
        }
        System.out.println();

        int[][] rndMatrix = new int[4][1001];
        for(int i = 0; i < rndMatrix[0].length-1; i++){
            rndMatrix[0][i] = rnd.nextInt(100);
        }
        for(int i = 1; i < rndMatrix.length; i++){
            rndMatrix[i] = Arrays.copyOf(rndMatrix[i-1], 1001);
        }
        System.out.println("Random array (" + (rndMatrix[0].length-1) + "): ");
        for(int i = 0; i<rndMatrix.length; i++){
            coolQuickSort(rndMatrix[i], i);
            System.out.println("<" + i + "> way swaps: " + rndMatrix[i][rndMatrix[i].length-1]);
        }

        int[][] sortedMatrix = new int[4][1001];
        sortedMatrix[0] = Arrays.copyOf(rndMatrix[0], 1001);
        for(int i = 1; i < sortedMatrix.length; i++){
            sortedMatrix[i] = Arrays.copyOf(sortedMatrix[i-1], 1001);
        }
        System.out.println("Sorted array (" + (sortedMatrix[0].length-1) + "): ");
        for(int i = 0; i<sortedMatrix.length; i++){
            coolQuickSort(sortedMatrix[i], i);
            System.out.println("<" + i + "> way swaps: " + sortedMatrix[i][sortedMatrix[i].length-1]);
        }

        int[][] backSortedMatrix = new int[4][1001];
        backSortedMatrix[0] = Arrays.copyOf(sortedMatrix[0], 1001);
        backSortedMatrix[0] = reverseSort(backSortedMatrix[0], 0, backSortedMatrix[0].length-1);
        for(int i = 1; i < backSortedMatrix.length; i++){
            backSortedMatrix[i] = Arrays.copyOf(backSortedMatrix[i-1], 1001);
        }
        System.out.println("BackSorted array (" + (backSortedMatrix[0].length-1) + "): ");
        for(int i = 0; i<backSortedMatrix.length; i++){
            coolQuickSort(backSortedMatrix[i], i);
            System.out.println("<" + i + "> way swaps: " + backSortedMatrix[i][backSortedMatrix[i].length-1]);
        }

        //Arrays are the same
    }

    private static void coolQuickSort(int[] data, int left, int right, int borderSelectWay, int swaps){
        if(left>=right){
            return;
        }
        int border;
        switch(borderSelectWay){
            case 0:
                border = left;
                break;
            case 1:
                border = right;
                break;
            case 2:
                border = medianOfThreeRnd(data, left, right);
                break;
            case 3:
                Random rnd = new Random();
                border = left + rnd.nextInt(right-left);
                break;
            default:
                System.out.println("No way!");
                return;
        }
        int element = data[border];
        int i = left, j = right;
        while(i <= j){
            while(i!=data.length-1 && data[i]<element){
                i++;
            }
            while(j!=0 && data[j]>element){
                j--;
            }
            if(i <= j){
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                i++;
                j--;
                swaps++;
            }
        }
        data[data.length-1] = swaps;
        if(left < j){
            coolQuickSort(data, left, j, borderSelectWay, swaps);
        }
        if(i < right) {
            coolQuickSort(data, i, right, borderSelectWay, swaps);
        }
    }

    public static void coolQuickSort(int[] data, int borderSelectWay){
        coolQuickSort(data, 0, data.length-2, borderSelectWay, 0);
    }

    private static int medianOfThreeRnd(int[] data, int left, int right){
        int n = right - left;
        if(n<3){
            return left;
        }
        Random rnd = new Random();
        int first = rnd.nextInt(n/3);
        int second = rnd.nextInt(2*n/3 - n/3) + n/3;
        int third = rnd.nextInt(n - 2*n/3) + 2*n/3;
        int[] arr = {data[left + first], data[left + second], data[left + third]};
        Arrays.sort(arr);
        if(arr[1]==data[left+first]){
            return left+first;
        } else if(arr[1]==data[left+second]){
            return left+second;
        } else return left + third;
    }

    public static int[] reverseSort(int[] arr, int left, int right){
        for(int i = 0; i<(right-left+1)/2; i++){
            int tmp = arr[left+i];
            arr[left+i] = arr[right-i];
            arr[right-i] = tmp;
        }
        return arr;
    }
}
