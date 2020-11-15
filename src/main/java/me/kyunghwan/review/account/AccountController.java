package me.kyunghwan.review.account;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import me.kyunghwan.review.account.dto.AccountResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> postSignUp(@RequestBody AccountRequestDto accountRequestDto) {
        AccountResponseDto responseDto = accountService.save(accountRequestDto);
        WebMvcLinkBuilder self = linkTo(AccountController.class);
        EntityModel<AccountResponseDto> entityModel = EntityModel.of(responseDto).add(self.withSelfRel());
        return ResponseEntity.created(self.toUri()).body(entityModel);
    }

}
