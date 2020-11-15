package me.kyunghwan.review.account.dto;

import lombok.*;
import me.kyunghwan.review.account.Account;
import me.kyunghwan.review.account.LoginType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AccountRequestDto {

    private String email;

    private String password;

    private List<String> myGenres = new ArrayList<>();

    public Account toEntity(PasswordEncoder passwordEncoder) {
        // 장르를 추가하는 로직은 Service Layer로 넘길 예정
        return Account.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build();
    }

}
