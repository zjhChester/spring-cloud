package cn.temp.tempbeans.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("用户对象")
public class User {
//    @ApiModelProperty(value = "用户id")
//    @NotNull(message = "id不能为空")
    private Integer id;
//    @ApiModelProperty(value = "用户username")
//    @NotBlank(message = "username不能为空")
    private String username;
//    @ApiModelProperty(value = "用户password")
//    @NotBlank(message = "password不能为空")
    private String password;

    private String [] arr;

    private List<Msg> msgs;



}
