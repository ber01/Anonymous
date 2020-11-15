package me.kyunghwan.review.account.dto;

import lombok.Getter;
import me.kyunghwan.review.account.Account;
import me.kyunghwan.review.account.LoginType;
import me.kyunghwan.review.mygenre.MyGenre;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountResponseDto {

    private Long idx;

    private String email;

    private LoginType loginType;

    private Boolean isVerified;

    private List<String> myGenres = new ArrayList<>();

    public AccountResponseDto(Account account) {
        this.idx = account.getIdx();
        this.email = account.getEmail();
        this.loginType = account.getLoginType();
        this.isVerified = account.getIsVerified();
        for (MyGenre myGenre : account.getMyGenres()) {
            this.myGenres.add(myGenre.getGenre().getName());
        }
    }

}
