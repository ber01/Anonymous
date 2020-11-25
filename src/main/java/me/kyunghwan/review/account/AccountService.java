package me.kyunghwan.review.account;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import me.kyunghwan.review.account.dto.AccountResponseDto;
import me.kyunghwan.review.jwt.JwtTokenProvider;
import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.movie.GenreRepository;
import me.kyunghwan.review.mygenre.MyGenre;
import me.kyunghwan.review.mygenre.MyGenreRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final GenreRepository genreRepository;
    private final MyGenreRepository myGenreRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
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

    public String createToken (AccountRequestDto accountRequestDto) {
        String email = accountRequestDto.getEmail();
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        AccountAdapter accountAdapter = new AccountAdapter(account);

        String jwtToken = jwtTokenProvider.createToken(accountAdapter.getUsername(), accountAdapter.getAuthorities());
        return jwtToken;
    }
}
