package simple;

/**
 * 定义老板
 * @author lp
 */
public class Boss {
    //传达命令，有权利拿到领导，指挥领导
    public  void command(String command,Leader leader){

        leader.doing(command);
    }
}
