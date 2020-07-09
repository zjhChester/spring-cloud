package cn.temp.tempbeans.api.dto;

import cn.temp.tempbeans.pojo.Msg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
//swaggerModel描述
@ApiModel("valid数据格式检测注解测试")
public class ValidDTO {

    @NotNull(message = "id不能为空")
    private Integer id;

    /***    swaggerModel成员变量描述
     *     @ApiModelProperty注解如果注释类型是integer 等基本类型包装类 需要赋初值
      */
    @ApiModelProperty("ValidDTOName")
    @NotBlank(message = "name不能为空")
    private String username;

    @ApiModelProperty("ValidDTOEmail")
    @Email(message = "邮箱格式不对")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Max(value = 10,message = "money长度超长")
    private Integer money;

    /**
     * 不写message的默认提示:cn.temp.tempbeans.conf.Exception - 最大不能超过2
     */
    @Max(value = 2)
    private Integer age;

    private String [] arr;

    private List<Msg> msgs;
}
