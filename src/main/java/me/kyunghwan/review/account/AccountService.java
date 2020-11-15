package me.kyunghwan.review.account;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import me.kyunghwan.review.account.dto.AccountResponseDto;
import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.movie.GenreRepository;
import me.kyunghwan.review.mygenre.MyGenre;
import me.kyunghwan.review.mygenre.MyGenreRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final GenreRepository genreRepository;
    private final MyGenreRepository myGenreRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountResponseDto save(AccountRequestDto accountRequestDto) {
        Account account = accountRepository.save(accountRequestDto.toEntity(passwordEncoder));
        for (String name : accountRequestDto.getMyGenres()) {
            Genre genre = genreRepository.findByName(name);
            account.add(myGenreRepository.save(MyGenre.builder()
                    .account(account)
                    .genre(genre)
                    .build()));
        }
        return new AccountResponseDto(account);
    }

}
