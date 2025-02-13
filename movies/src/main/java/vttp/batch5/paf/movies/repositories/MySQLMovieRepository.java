package vttp.batch5.paf.movies.repositories;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MySQLMovieRepository {

  public static final String SQL_BATCH_INSERT_MOVIE = """
        insert into imdb (imdb_id, vote_average, vote_count, release_date, revenue, budget, runtime)
        values (?, ?, ?, ?, ?, ?, ?)
        on duplicate key update imdb_id = ?;
      """;

  public static final String SQL_GET_MOVIE_BY_ID = """
      select * from imdb
        where imdb_id = ?
      """;

  @Autowired
  private JdbcTemplate template;

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public void batchInsertMovies(List<Movie> movies) {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    for(Movie m: movies) {
      template.update(SQL_BATCH_INSERT_MOVIE, m.getImdbId(), m.getVoteAverage(), m.getVoteCount(), df.format(m.getReleaseDate()), m.getRevenue(), m.getBudget(), m.getRuntime(), m.getImdbId());
    }

  }

  // TODO: Task 3
  public Optional<SqlRowSet> getMovieRowSetById(String id) {
    SqlRowSet rs = template.queryForRowSet(SQL_GET_MOVIE_BY_ID, id);

    if (!rs.next())
      return Optional.empty();

    return Optional.of(rs);
  }

}
