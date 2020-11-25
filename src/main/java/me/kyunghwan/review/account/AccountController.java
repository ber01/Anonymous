package me.kyunghwan.review.account;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import me.kyunghwan.review.account.dto.AccountResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> postSignUp(@Valid @RequestBody AccountRequestDto accountRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("{\"message\" : \"Bad Request\"}");
        }

        AccountResponseDto responseDto = accountService.save(accountRequestDto);
        WebMvcLinkBuilder self = linkTo(AccountController.class);
        EntityModel<AccountResponseDto> entityModel = EntityModel.of(responseDto).add(self.withSelfRel());
        return ResponseEntity.created(self.toUri()).body(entityModel);
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@Valid @RequestBody AccountRequestDto accountRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("{\"message\" : \"아이디 혹은 비밀번호를 확인하세요.\"}");
        }

        String jwtToken = accountService.createToken(accountRequestDto);
        return ResponseEntity.ok("{\"access_token\" : " + "\"Bearer " + jwtToken + "\"}");
    }

}
