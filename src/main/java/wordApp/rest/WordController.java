package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wordApp.entity.Word;
import wordApp.rest.word_class.GetWordsRes;
import wordApp.rest.word_class.WordNotFoundExp;
import wordApp.service.WordService;

@CrossOrigin(maxAge = 3600)
@RestController
public class WordController {
  
  private WordService service;

  @Autowired
  public WordController(WordService service) {
    this.service = service;
  }

  @PostMapping(value = "/words", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Boolean addWord(@RequestBody Word w) {
    service.save(w);
    return true;
  }

  @GetMapping("/words")
  public GetWordsRes getWords(@RequestParam("table_id") int table_id) {
    return new GetWordsRes(
      table_id, 
      service.findAll_by_table(table_id)
    );
  }

  @GetMapping("/words/{word_id}")
  public Word getWord(@PathVariable int word_id) {
    Word theWord = service.find(word_id);
    if (theWord == null) {
      throw new WordNotFoundExp(word_id);
    }
    return theWord;
  }
}
