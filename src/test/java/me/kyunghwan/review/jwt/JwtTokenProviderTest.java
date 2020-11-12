package me.kyunghwan.review.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest
class JwtTokenProviderTest {

    private static final String USERNAME = "username";
    private static final String BEARER = "Bearer ";

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @DisplayName("JWT 생성에 성공하는 테스트")
    @Test
    void test1() {
        String token = createToken();
        assertThat(token).isNotNull();
    }

    @DisplayName("JWT 에서 유저의 이름을 꺼내오는 테스트")
    @Test
    void test2() {
        String token = createToken();
        String username = jwtTokenProvider.getUsername(token);
        assertThat(username).isEqualTo(USERNAME);
    }

    @DisplayName("JWT 유효성을 검사하는 테스트")
    @Test
    void test3() {
        String token = createToken();
        boolean valid = jwtTokenProvider.validateToken(BEARER + token);
        assertThat(valid).isTrue();
    }

    private String createToken() {
        String username = USERNAME;
        return jwtTokenProvider.createToken(username, getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }

}