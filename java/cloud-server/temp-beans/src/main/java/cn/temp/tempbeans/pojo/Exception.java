package cn.temp.tempbeans.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Exception implements Serializable {
    private Integer id;
    private String title;
//    private String desc;
    private String content;
    private String createTime;
    private String author;
    private String remark;
//    private String type;
    private Integer views;
}
