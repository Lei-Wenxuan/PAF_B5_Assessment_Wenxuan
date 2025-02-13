package vttp.batch5.paf.movies.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.bootstrap.Dataloader;
import vttp.batch5.paf.movies.models.Movie;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

@Service
public class MovieService {

  @Autowired
  private Dataloader dataloader;

  @Autowired
  private MySQLMovieRepository sqlMovieRepo;

  @Autowired
  private MongoMovieRepository mongoMovieRepo;

  // TODO: Task 2

  public void loadZipFile(String dataPath) {
    batchUpdateMovies(dataloader.load(dataPath));
  }

  public void batchUpdateMovies(List<JsonObject> jsonList) {
    List<List<JsonObject>> partitions = batchJsonObjectList(jsonList);

    for (List<JsonObject> batchedJsonObject: partitions) {
      try {
        List<Movie> batchedMovies = jsonListToMovieList(batchedJsonObject);
        System.out.println(batchedMovies.size());
        sqlMovieRepo.batchInsertMovies(batchedMovies);
        mongoMovieRepo.batchInsertMovies(batchedMovies);
      } catch (Exception e) {
        System.err.println("[Skipped batch] Error: %s".formatted(e.getMessage()));
        continue;
      }
    }

  }

  public List<List<JsonObject>> batchJsonObjectList(List<JsonObject> jsonList) {
    int partitionSize = 25;
    List<List<JsonObject>> partitions = new ArrayList<>();

    for (int i=0; i < jsonList.size(); i += partitionSize) {
        partitions.add(jsonList.subList(i, Math.min(i + partitionSize, jsonList.size())));
    }

    return partitions;
  }

  public List<Movie> jsonListToMovieList(List<JsonObject> jsonList) {
    return jsonList.stream()
        .map(j -> {
          Movie m = new Movie();
          m.setImdbId(j.getString("imdb_id"));
          m.setVoteAverage((float) j.getJsonNumber("vote_average").doubleValue());
          m.setVoteCount(j.getInt("vote_count"));
          try {
            m.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(j.getString("release_date")));
          } catch (ParseException e) {
            System.err.println("Error parsing date (Json to Movie)");
            e.printStackTrace();
          }
          m.setRevenue((float) j.getJsonNumber("revenue").doubleValue());
          m.setBudget((float) j.getJsonNumber("budget").doubleValue());
          m.setRuntime(j.getInt("runtime"));
          m.setTitle(j.getString("title"));
          m.setDirector(j.getString("director"));
          m.setOverview(j.getString("overview"));
          m.setTagline(j.getString("tagline"));
          m.setGenres(j.getString("genres"));
          m.setImdbRating(j.getInt("imdb_rating"));
          m.setImdbVotes(j.getInt("imdb_votes"));
          return m;
        }).toList();
  }

  // TODO: Task 3
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void getProlificDirectors() {
  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport() {

  }

}
