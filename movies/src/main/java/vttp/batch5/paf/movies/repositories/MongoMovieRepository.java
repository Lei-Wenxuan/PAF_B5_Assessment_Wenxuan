package vttp.batch5.paf.movies.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

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
     * db.imdb.updateOne(
     * { _id: 'imdb_id' },
     * {
     * $set: { title: 'title', directors: 'directors', overview: '<overview>',
     * tagline: '<tagline>', genres: '<genres>', imdb_rating: rating, imdb_votes:
     * votes },
     * },
     * { upsert: true }
     * )
     */
    //
    public void batchInsertMovies(List<Movie> movies) {
        for (Movie m : movies) {
            Query query = Query.query(Criteria.where("_id").is(m.getImdbId()));

            Update updateOps = new Update()
                    .set("title", m.getTitle())
                    .set("directors", m.getDirector())
                    .set("overview", m.getOverview())
                    .set("tagline", m.getTagline())
                    .set("genres", m.getGenres())
                    .set("imdb_rating", m.getImdbRating())
                    .set("imdb_votes", m.getImdbVotes());

            template.upsert(query, updateOps, Document.class, "imdb");
        }
        // List<Document> docsToInsert = movies.stream()
        // .map(m -> {
        // return Json.createObjectBuilder()
        // .add("_id", m.getImdbId())
        // .add("title", m.getTitle())
        // .add("directors", m.getDirector())
        // .add("overview", m.getOverview())
        // .add("tagline", m.getTagline())
        // .add("genres", m.getGenres())
        // .add("imdb_rating", m.getImdbRating())
        // .add("imdb_votes", m.getImdbVotes())
        // .build();
        // })
        // .map(j -> {
        // return Document.parse(j.toString());
        // })
        // .toList();

        // template.insert(docsToInsert, "imdb");

    }

    // TODO: Task 2.4
    // You can add any number of parameters and return any type from the method
    // You can throw any checked exceptions from the method
    // Write the native Mongo query you implement in the method in the comments
    //
    // native MongoDB query here
    //
    public void logError() {

    }

    // TODO: Task 3
    // Write the native Mongo query you implement in the method in the comments
    //
    /*
     * db.imdb.aggregate([
     * { $match: { directors : { $ne: "" } } },
     * {
     * $group: {
     * _id: "$directors",
     * count: { $sum: 1 },
     * movies: {
     * $push: {
     * imdb_id: '$_id'
     * }
     * }
     * }
     * },
     * { $sort: { count: -1 } },
     * { $limit: 5 }
     * ])
     */
    //
    public List<Document> getTopDirectors(int limit) {

        MatchOperation matchNotNull = Aggregation.match(Criteria.where("directors").ne(""));

        GroupOperation groupByDirectors = Aggregation.group("directors")
                .count().as("count")
                .push(
                        new BasicDBObject()
                                .append("imdb_id", "$_id"))
                .as("movieIds");

        SortOperation sortByCount = Aggregation.sort(Sort.Direction.DESC, "count");

        LimitOperation limitOps = Aggregation.limit(limit);

        Aggregation pipeline = Aggregation.newAggregation(matchNotNull, groupByDirectors, sortByCount, limitOps);

        return template.aggregate(pipeline, "imdb", Document.class).getMappedResults();
    }

}
