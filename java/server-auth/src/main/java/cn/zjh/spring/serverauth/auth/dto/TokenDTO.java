package cn.zjh.spring.serverauth.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {
    private String accessToken;
    private String refreshToken;

    public TokenDTO() {

    }
    public TokenDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
