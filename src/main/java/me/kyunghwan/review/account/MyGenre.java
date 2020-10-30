package me.kyunghwan.review.account;

import me.kyunghwan.review.movie.Genre;

import javax.persistence.*;

@Entity
public class MyGenre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MYGENRE_ID")
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

}
