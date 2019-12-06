package simple;

/**
 * 员工A
 * @author lp
 */
public class EmployeeA implements Employee {
    //调用员工a的时候,给他一个conmmand
    @Override
    public void doing(String command) {
            System.out.println("我是员工A,我负责业务逻辑，现在开始做"+command+"工作");
    }
}
