package wordApp.rest.table_class;

public class TableErrorRes {

  private int status;
  private String msg;
  private long timeStamp;

  public TableErrorRes() { }

  public TableErrorRes(int status, String msg, long timeStamp) {
    this.status = status;
    this.msg = msg;
    this.timeStamp = timeStamp;
  }

  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public long getTimeStamp() {
    return timeStamp;
  }
  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }
}
