package vttp.batch5.paf.movies.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import vttp.batch5.paf.movies.models.Movie;

@Repository
public class MongoMovieRepository {

    @Autowired
    private MongoTemplate template;

 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 /* 
    db.imdb.insertMany([
        { _id: '<imdb_id>', title: '<title>', directors: '<directors>', overview: '<overview>', tagline: '<tagline>', genres: '<genres>', imdb_rating: '<imdb_rating>', imdb_votes: '<imdb_votes>' },
        { ... }
    ])
  */
 //
 public void batchInsertMovies(List<Movie> movies) {

    List<Document> docsToInsert = movies.stream()
        .map(m -> {
            return Json.createObjectBuilder()
                .add("_id", m.getImdbId())
                .add("title", m.getTitle())
                .add("directors", m.getDirector())
                .add("overview", m.getOverview())
                .add("tagline", m.getTagline())
                .add("genres", m.getGenres())
                .add("imdb_rating", m.getImdbRating())
                .add("imdb_votes", m.getImdbVotes())
                .build();
        })
        .map(j -> {
            return Document.parse(j.toString());
        })
        .toList();
    
    template.insert(docsToInsert, "imdb");

 }

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
 public void logError() {

 }

 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //


}
