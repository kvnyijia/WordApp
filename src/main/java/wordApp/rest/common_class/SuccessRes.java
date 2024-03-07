package wordApp.rest.common_class;

public class SuccessRes<T> {

  public boolean success;
  public T data;
  
  public SuccessRes() {}

  public SuccessRes(boolean success, T data) {
    this.success = success;
    this.data = data;
  }
}
