package me.kyunghwan.review.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kyunghwan.review.moviegenre.MovieGenre;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID")
    private Long idx;

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String name;

    @Column
    private Integer runningTime;

    @Column
    private LocalDate openingDate;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "movie")
    private final Set<MovieGenre> movieGenres = new HashSet<>();

    public void add(MovieGenre movieGenre) {
        this.movieGenres.add(movieGenre);
    }

}
