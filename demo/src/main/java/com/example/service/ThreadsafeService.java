package com.example.service;

import java.util.LinkedList;

public class ThreadsafeService {

    private LinkedList<Integer> list = new LinkedList<>();

    public synchronized void increment() {
        int nextNumber = 0;
        int size = list.size();
        if (size > 0) {
            nextNumber = list.getLast() + 1;
        }
        Integer nextInteger = new Integer(nextNumber);
        list.addLast(nextInteger);
    }
   /*
Hilo 1                                             Hilo2  
int nextNumber = 0;                           int nextNumber = 0;


int size = list.size(); {0})
                                            int size = list.size(); (0)
if (size > 0) {
    nextNumber=
        list.getLast() + 1;
}
Integer nextInteger =
    new_Integer(nextNumber);
list.addLast(nextInteger);
{Insertar 0}
                                             if (size > 0) {
                                                 nextNumber =
                                                     list.getLast() + 1;
                                                }                                        
                                                Integer nextInteger =
                                                      new_Integer(nextNumber);
                                                list.addLast(nextInteger);
                                                {Insertar 0}
*/
    //0 => 2 ... => n-1
    
    public synchronized int[] getArray() {
        int[] intArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            intArray[i] = list.get(i);    
        }
        return intArray;
    }
}