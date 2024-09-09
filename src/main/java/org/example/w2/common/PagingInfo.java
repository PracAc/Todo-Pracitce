package org.example.w2.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingInfo {

    private int page; // current page
//    private int size; // size
    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    public PagingInfo(int page, int size, int total){
        this.page = page <= 0 ? 1 : page;

        end = (int) (Math.ceil(this.page / 10.0) * 10);
        start = end - 9;

        prev = start == 1 ? false : true;

        if( end * size < total ) {
            next = true;
        }else {
            next = false;
            end =  (int) (Math.ceil(total / (double)size));
        }//end else

    }

}
