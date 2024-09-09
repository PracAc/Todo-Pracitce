package org.example.w2.common;

public class ReqCounter {

    private int count;

    public int inc(){
        return ++count;
    }

    public int getCount(){
        return count;
    }
}
