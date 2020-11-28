package me.kyunghwan.review.movie;

import com.google.gson.Gson;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import me.kyunghwan.review.movie.dto.MovieInfo;
import me.kyunghwan.review.movie.dto.MovieResponseDto;
import me.kyunghwan.review.moviegenre.MovieGenre;
import me.kyunghwan.review.moviegenre.MovieGenreRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final Gson gson;

    public MovieInfo[] readJson(String path) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(path));
        return gson.fromJson(reader, MovieInfo[].class);
    }

    private MovieGenre saveMovieGenre(Movie movie, Genre genre) {
        return movieGenreRepository.save(MovieGenre.builder()
                .movie(movie)
                .genre(genre)
                .build());
    }

    @Transactional
    public void saveMovie(String path) throws IOException {
        MovieInfo[] movieInfos = readJson(path);
        for (MovieInfo movieInfo : movieInfos) {
            Movie movie = movieRepository.save(movieInfo.toEntity());
            List<Long> idList = movieInfo.getMovieGenres();
            for (Long id : idList) {
                Genre genre = genreRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(""));
                MovieGenre movieGenre = saveMovieGenre(movie, genre);
                movie.add(movieGenre);
                genre.add(movieGenre);
            }
        }
    }

    public String findMovieList() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieResponseDto> movieResponseDtoList = new ArrayList<>();
        for (Movie movie : movieList) {
            MovieResponseDto movieResponseDto = new MovieResponseDto(movie);
            movieResponseDtoList.add(movieResponseDto);
        }
        return gson.toJson(movieResponseDtoList);
    }

    public Movie findMovie(Long idx) throws Exception {
        return movieRepository.findById(idx).orElseThrow(() -> new NotFoundException(idx + ""));
    }

    /*
    public String txtToJson(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String str = br.readLine();
                if (str == null) break;
                String[] split = str.split("/ ");
                String code = split[0].trim();
                String name = split[1].trim();
                int runningTime = Integer.parseInt(split[2].trim());
                String[] date = split[3].trim().split("\\.");
                LocalDate openingDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                String imageUrl = split[4].trim();
                String[] genres = split[5].split(",");

                String[] names = {"드라마", "판타지", "서부", "공포", "멜로/로맨스", "모험", "스릴러", "느와르", "컬트", "다큐멘터리", "코미디", "가족", "미스터리", "전쟁", "애니메이션", "범죄", "뮤지컬", "SF", "액션", "무협", "에로", "서스펜스", "서사", "블랙코미디", "실험", "공연실황"};

                List<Integer> order = new ArrayList<>();
                for (String s : genres) {
                    int index = 1;
                    for (String abc : names) {
                        if (abc.equals(s)) {
                            order.add(index);
                            break;
                        }
                        index++;
                    }
                }

                Collections.sort(order);

                MovieInfo build = MovieInfo.builder()
                        .code(code)
                        .name(name)
                        .runningTime(runningTime)
                        .openingDate(openingDate)
                        .imageUrl(imageUrl)
                        .movieGenres(order)
                        .build();

                String s = gson.toJson(build);
                sb.append(s).append(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = sb.substring(0, sb.length() - 1);
        str += "]";
        return str;
    }
     */

}
