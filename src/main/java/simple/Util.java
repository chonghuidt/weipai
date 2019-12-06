package simple;


import java.util.HashMap;
import java.util.Map;

public class Util {
    static Map<Integer, String> map;
    static {
        map = new HashMap<Integer, String>();
        //定义员工功能的集合
        map.put(1,"业务逻辑");
        map.put(2,"数据处理");

    }
    public static String getValue(Integer a){

        return map.get(a);
    }
}
