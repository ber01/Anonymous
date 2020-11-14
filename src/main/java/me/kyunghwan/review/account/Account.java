package me.kyunghwan.review.account;

import lombok.*;
import me.kyunghwan.review.mygenre.MyGenre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Account extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
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

    // @Builder.Default
    @OneToMany(mappedBy = "account")
    private final Set<MyGenre> myGenres = new HashSet<>();

    public void add(MyGenre myGenre) {
        this.myGenres.add(myGenre);
    }

}
