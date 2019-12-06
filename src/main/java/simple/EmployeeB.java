package simple;

/**
 * 员工B
 * @author lp
 */
public class EmployeeB implements Employee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工B,负责数据处理，现在开始做"+command+"工作");
    }
}
