import java.util.HashMap;
import java.util.Map;

public class Interview {
    public static void print(String str)
    {
        if(str.isEmpty())
        {
            throw new RuntimeException("Not a valid Str");
        }
        String[] strs = str.split(" ");
        Map<String,Integer> val = new HashMap<>();

        for(String tempStr: strs)
        {
            int count = val.containsKey(tempStr)? val.get(tempStr) + 1: 1;
            val.put(tempStr,count);
        }
        System.out.println(val.toString());

    }

    public static void main(String[] args)
    {

        print("dog the dog");
    }
}
