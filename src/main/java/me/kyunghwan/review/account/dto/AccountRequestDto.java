package me.kyunghwan.review.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kyunghwan.review.account.Account;
import me.kyunghwan.review.account.LoginType;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AccountRequestDto {

    @Email @NotBlank
    private String email;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}") @NotBlank
    private String password;

    @Builder.Default
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
