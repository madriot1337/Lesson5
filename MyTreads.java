package lesson_5;

import java.util.Arrays;

public class MyTreads {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) {
        oneThread();
        twoThreads();

    }
    public static void oneThread() {

        float[] arr = new float[SIZE];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;


        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }

        System.out.println("Время которое заняла работа цикла: " + (System.currentTimeMillis() - a) + " мс");


    }

    public MyTreads() {
    }

    public static void twoThreads() {

        float[] arr = new float[SIZE];
        long a = System.currentTimeMillis();

        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];

        System.arraycopy(arr, 0, leftHalf, 0, HALF);
        System.arraycopy(arr, HALF, rightHalf, 0, HALF);

        


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < leftHalf.length; i++) {
                    leftHalf[i] = 1.0f;


                }

                for (int i = 0; i < leftHalf.length ; i++) {
                    leftHalf[i] = (float)(leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                            Math.cos(0.4f + i / 2));
                }



            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < rightHalf.length; i++) {
                    rightHalf[i] = 1.0f;


                }

                for (int i = 0; i < rightHalf.length ; i++) {
                    rightHalf[i] = (float)(rightHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                            Math.cos(0.4f + i / 2));
                }


            }
        });

        thread1.start();
        thread2.start();

        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, HALF);
        System.arraycopy(rightHalf, 0, mergedArray, HALF, HALF);

        System.out.println("Время которое заняла работа цикла в двух потоках " +
                (System.currentTimeMillis() - a) + " мс");



    }
}
