package vttp.batch5.paf.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.bootstrap.Dataloader;
import vttp.batch5.paf.movies.services.MovieService;


@Controller
@RequestMapping
public class MainController {

  @Autowired
  private Dataloader dataloader;
 
  @Autowired
  private MovieService movieService;

  @GetMapping("/test")
  public ResponseEntity<List<JsonObject>> test() {
      return ResponseEntity.ok(dataloader.load("../data/movies_post_2010.zip"));
  }
  

  // TODO: Task 3
   

  
  // TODO: Task 4


}
