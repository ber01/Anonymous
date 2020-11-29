package me.kyunghwan.review.account;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import me.kyunghwan.review.account.dto.AccountResponseDto;
import me.kyunghwan.review.account.valid.AccountDtoValidator;
import me.kyunghwan.review.util.JsonUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountDtoValidator accountDTOValidator;

    @InitBinder("accountRequestDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountDTOValidator);
    }

    @PostMapping
    public ResponseEntity<?> postSignUp(@Valid @RequestBody AccountRequestDto accountRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest();
        }

        AccountResponseDto responseDto = accountService.save(accountRequestDto);
        WebMvcLinkBuilder self = linkTo(AccountController.class);
        EntityModel<AccountResponseDto> entityModel = EntityModel.of(responseDto).add(self.withSelfRel());
        return ResponseEntity.created(self.toUri()).body(entityModel);
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody AccountRequestDto accountRequestDto) {
        String jwtToken = accountService.createToken(accountRequestDto);
        return ResponseEntity.ok(JsonUtils.toJson("access_token", "Bearer " + jwtToken));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> exceptionHandler() {
        return badRequest();
    }

    private ResponseEntity<?> badRequest() {
        return ResponseEntity.badRequest().body(JsonUtils.toJson("message", "Bad Request"));
    }

}
