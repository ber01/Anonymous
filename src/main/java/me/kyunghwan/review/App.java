package me.kyunghwan.review;

import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.account.Account;
import me.kyunghwan.review.account.AccountRepository;
import me.kyunghwan.review.account.LoginType;
import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.movie.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class App implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final GenreRepository genreRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String[] each = {"공포", "SF", "로맨스", "코미디", "드라마", "범죄"};

        for (String str : each) {
            genreRepository.save(Genre.builder()
                    .name(str)
                    .build());
        }

        Account u1 = Account.builder()
                .email("email1")
                .isVerified(false)
                .loginType(LoginType.CREDENTIAL)
                .password("password")
                .build();
        Account u2 = Account.builder()
                .email("email2")
                .isVerified(false)
                .loginType(LoginType.CREDENTIAL)
                .password("password")
                .build();

        u1.getGenres().add(genreRepository.findByName("공포"));
        u1.getGenres().add(genreRepository.findByName("로맨스"));
        u1.getGenres().add(genreRepository.findByName("SF"));

        u2.getGenres().add(genreRepository.findByName("공포"));
        u2.getGenres().add(genreRepository.findByName("로맨스"));

        Account save = accountRepository.save(u1);
        System.out.println(save);

        Account save1 = accountRepository.save(u2);
        System.out.println(save1);
    }
}
