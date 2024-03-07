package wordApp.rest.common_class;

public class RecordInt {
  
  public String key;
  public int value;

  public RecordInt(String key, int value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return "RecordInt [key=" + key + ", value=" + value + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + value;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RecordInt other = (RecordInt) obj;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    if (value != other.value)
      return false;
    return true;
  }
}
