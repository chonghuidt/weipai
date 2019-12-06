package simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 领导
 * @author lp
 */
public class Leader implements Employee{
    //领导也是老板的员工，领导的工作就是下达指令
    //定义集合是因为会有多个员工，这里只是举例子
    //map集合里面有string:命令，Employee员工根据命令去做事情
    private Map<String,Employee>map=new HashMap<>();
    //构造方法：领导的构造方法
    public Leader(){
        //领导的构造方法里面是添加的员工
        map.put("业务逻辑",new EmployeeA());
        map.put("数据处理",new EmployeeB());
    }
    //项目经理自己不做事情
    @Override
    public void doing(String command) {

        map.get(command).doing(command);
    }
}
