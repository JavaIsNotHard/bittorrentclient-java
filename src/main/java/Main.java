import com.dampcake.bencode.Type;
import com.google.gson.Gson;
import com.dampcake.bencode.Bencode;

public class Main {
  private static final Gson gson = new Gson();

  public static void main(String[] args) throws Exception {
    String command = args[0];
    if("decode".equals(command)) {
      String bencodedValue = args[1];
      Object decoded;
      try {
        decoded = decodeBencode(bencodedValue);
      } catch(RuntimeException e) {
        System.out.println(e.getMessage());
        return;
      }
      System.out.println(gson.toJson(decoded));

    } else {
      System.out.println("Unknown command: " + command);
    }

  }

  static Object decodeBencode(String bencodedString) {
    Bencode bencode = new Bencode();
    char firstLetter = bencodedString.charAt(0);
    if (Character.isDigit(firstLetter)) {
      String result = bencode.decode(bencodedString.getBytes(), Type.STRING);
      return result;
    } else if (firstLetter == 'i') {
      long result = bencode.decode(bencodedString.getBytes(), Type.NUMBER);
      return result;
    }
    else {
      throw new RuntimeException("Bencode: " + bencodedString + " is not a digit");
    }
  }
  
}
