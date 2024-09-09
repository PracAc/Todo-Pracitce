package org.example.w2.todo;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoVO {
    private Integer tno;
    private String title;
    private String writer;
    private String dueStr;
    private Timestamp dueDate;
    private Time dueTime;
    private boolean finish;
    private boolean delflag;
}
