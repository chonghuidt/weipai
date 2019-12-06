package simple;

/**
 * 定义员工接口：员工是不是有多个，doing方法,不同的员工做不同的事情
 * @author lp
 */
public interface Employee {
    //定义方法做事情，根据收到的命令
    public void doing(String command);
}
