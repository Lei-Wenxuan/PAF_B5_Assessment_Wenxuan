package vttp.batch5.paf.movies.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MySQLMovieRepository {

  public static final String SQL_BATCH_INSERT_MOVIE = """
        insert into imdb (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime) values (?, ?, ?, ?, ?, ?, ?);
      """;

  @Autowired
  private JdbcTemplate template;

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public int batchInsertMovies(List<Movie> movies) {
    List<Object[]> params = movies.stream()
        .map(m -> new Object[] {
            m.getImdbId(), m.getVoteAverage(), m.getVoteCount(), m.getReleaseDate(), m.getRevenue(), m.getBudget(),
            m.getRuntime()
        })
        .collect(Collectors.toList());

    int added = template.update(SQL_BATCH_INSERT_MOVIE, params);

    return added;
  }

  // TODO: Task 3

}
