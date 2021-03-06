package xyz.zjhwork.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment  implements Serializable {
    private Integer id;
    private Integer exceptionId;
    private String userId;
    private String time;
    private String content;
    private String remark;
}
