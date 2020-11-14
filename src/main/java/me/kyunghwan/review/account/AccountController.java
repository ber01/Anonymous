package me.kyunghwan.review.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/accounts/")
@RestController
public class AccountController {

    @PostMapping
    public ResponseEntity<?> postSignUp() {

        return null;
    }

}
