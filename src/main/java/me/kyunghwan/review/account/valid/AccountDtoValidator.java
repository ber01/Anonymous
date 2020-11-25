package me.kyunghwan.review.account.valid;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.AccountRepository;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class AccountDtoValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AccountRequestDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountRequestDto accountRequestDto = (AccountRequestDto) target;
        if (accountRepository.existsByEmail(accountRequestDto.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{accountRequestDto.getEmail()}, "사용중인 이메일입니다.");
        }
    }

}
