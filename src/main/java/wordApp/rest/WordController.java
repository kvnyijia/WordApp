package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wordApp.entity.Table;
import wordApp.entity.Word;
import wordApp.rest.word_class.CreateWordReq;
import wordApp.rest.word_class.GetWordsRes;
import wordApp.rest.word_class.WordNotFoundExp;
import wordApp.service.TableService;
import wordApp.service.WordService;

@CrossOrigin(maxAge = 3600)
@RestController
public class WordController {
  
  private WordService service;
  private TableService tService;

  @Autowired
  public WordController(WordService service, TableService tService) {
    this.service = service;
    this.tService = tService;
  }

  @PostMapping(value = "/words", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Boolean addWord(@RequestBody CreateWordReq req) {
    Table theTable = tService.find(req.getTable_id());
    if (theTable == null) {
      return false;
    }
    Word w = new Word(
      theTable,
      req.getTerm(),
      req.getMeaning(),
      req.getPicture_url()
    );
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

  @DeleteMapping("/words/{word_id}")
  @ResponseBody
  public boolean deleteWord(@PathVariable("word_id") Integer word_id) {
    if (service.find(word_id) == null) {
      throw new WordNotFoundExp(word_id);
    }
    service.delete(word_id);
    return true;
  }
}
