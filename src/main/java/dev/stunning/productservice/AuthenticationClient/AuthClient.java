package dev.stunning.productservice.AuthenticationClient;

import dev.stunning.productservice.AuthenticationClient.dtos.ValidateTokenRequestDto;
import dev.stunning.productservice.AuthenticationClient.dtos.ValidateTokenResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {
    private RestTemplateBuilder restTemplateBuilder;

    public AuthClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ValidateTokenResponseDto validate(String token, Long userId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);

        ResponseEntity<ValidateTokenResponseDto> l = restTemplate.postForEntity(
                "http://localhost:9000/auth/validate",
                request,
                ValidateTokenResponseDto.class
        );
        return l.getBody();
    }


}
