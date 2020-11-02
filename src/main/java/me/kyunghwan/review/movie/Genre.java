package me.kyunghwan.review.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kyunghwan.review.moviegenre.MovieGenre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Genre {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENRE_ID")
    private Long idx;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "genre")
    private final Set<MovieGenre> movieGenres = new HashSet<>();

    public void add(MovieGenre movieGenre) {
        this.movieGenres.add(movieGenre);
    }

}
