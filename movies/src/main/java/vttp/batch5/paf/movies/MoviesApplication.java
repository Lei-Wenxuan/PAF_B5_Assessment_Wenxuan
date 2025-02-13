package vttp.batch5.paf.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch5.paf.movies.services.MovieService;

@SpringBootApplication
public class MoviesApplication implements CommandLineRunner {

	@Value("${data.movies}")
	private String dataPath;

	@Autowired
	private MovieService movieService;

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		movieService.loadZipFile(dataPath);
	}

}
