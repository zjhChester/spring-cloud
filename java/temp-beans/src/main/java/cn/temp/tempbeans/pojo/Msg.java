package cn.temp.tempbeans.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg  implements Serializable {
    private Integer id;
    private String content;
}
