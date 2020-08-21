package xyz.zjhwork.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exception  implements Serializable {
    private Integer id;
    private String title;
    private String desc;
    private String content;
    private String createTime;
    private String author;
    private String remark;
    private String type;
    private Integer views;
}
