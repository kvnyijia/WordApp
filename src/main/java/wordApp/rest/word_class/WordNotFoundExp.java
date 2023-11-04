package wordApp.rest.word_class;

public class WordNotFoundExp extends RuntimeException {

  public WordNotFoundExp(int arg0) {
    super("Word not found: " + arg0);
  }
}
