package cn.zjh.spring.serverauth.auth.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class AutoDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Builder
    public AutoDTO() {
    }
    @Builder
    public AutoDTO(@NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空") String password) {
        this.username = username;
        this.password = password;
    }
}
