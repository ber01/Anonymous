package me.kyunghwan.review.account;

import lombok.*;
import me.kyunghwan.review.movie.Genre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(nullable = false)
    private Boolean isVerified;

    @ManyToMany
    @JoinTable(name = "account_genre",
            joinColumns = @JoinColumn(name = "account_idx"),
            inverseJoinColumns = @JoinColumn(name = "genre_idx")
    )
    private final Set<Genre> genres = new HashSet<>();

}
