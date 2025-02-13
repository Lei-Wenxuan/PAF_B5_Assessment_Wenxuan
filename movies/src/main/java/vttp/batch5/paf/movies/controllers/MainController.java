package vttp.batch5.paf.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batch5.paf.movies.services.MovieService;

@Controller
@RequestMapping
public class MainController {

  @Autowired
  private MovieService movieService;

  // TODO: Task 3
  @GetMapping(path = "/api/summary", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getProlificDirectors(@RequestParam(defaultValue = "20") int count) {
    return ResponseEntity.ok(movieService.getProlificDirectors(count).toString());
  }

  // TODO: Task 4
  @GetMapping("/api/summary/pdf")
  public String getMethodName(@RequestParam String param) {
    return new String();
  }

}
