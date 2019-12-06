package springmvc;

import springmvc.MemberController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 前端控制器
 * @author lp
 */
public class DispatcherServlet extends HttpServlet {
    //定义所有的映射集合，处理请求的时候，是干嘛的？
    //servlet：生命周期 ：init(),doPost(),doGet(),service(),destory()
    //通过你配置的信息去寻找。信息就保存在这个集合里面
    private List<Handler> handlerMapping = new ArrayList<>();
    //初始化
    public  void init()throws ServletException {
        try {
            //利用反射模拟从某一个控制器获取内容:反射是动态的获取类的内容（属性方法）
            //模拟的是前端控制器，通过xml或者注解找到某一个handler
        Class<?> memberControllerClass = MemberController.class;
        //放入集合

            //把当前这个对象的所有的方法传到集合里面去
        handlerMapping.add(new Handler()
                .setController(memberControllerClass.newInstance())
                .setMethod(memberControllerClass.getMethod("getMemberById", new Class[]{String.class}))
                .setUrl("/web/getMemberById.json"));
    }catch (Exception e){
           e.printStackTrace();
        }
    }
    //执行处理
    private  void doDispatcher(HttpServletRequest request, HttpServletResponse response){
        //servlet:通过注解给了他一个访问路径：localhost:8080/delet  dispacherservlet去调度 这么多路径保存在哪里？保存handlerMapping,方法，路径
        // 1 获取请求的url，每个url对应一个servlet,url浏览器输入
        String uri = request.getRequestURI();
        //servlert拿到url以后，进行判断和选择，根据用户请求找到对应类里面的方法
        //通过拿到的url去handlerMapping进行遍历比对
        Handler handler=null;
        for (Handler h:handlerMapping) {  //foreach遍历
            if(uri.equals(h.getUrl())){  //前端的访问和hadlermapping里面某个路径相等，说明能够访问
                handler=h;     //找到了对应的控制器hadler(controller)
                break;
            }

        }
        //2 将具体任务分发给对应的方法
        Object object=null;
        try {
            //反射里面的调用方法，反射里面调用方法
            object=handler.getMethod().invoke(handler.getController(),request.getParameter("id"));
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        // 3 获取方法执行的结果，通过response返回
//        response.getWriter().write();


    }
    //service方法，servlet里面的动作或者行为
    protected  void service(HttpServletRequest request,HttpServletResponse response){
        try {
            //表示执行doDispatcher方法，调用doPost(),doGet()
            doDispatcher(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //实际上生命周期还有一个destory
    //要么就是退出，要么就是销毁刚才的handler实例
    //内部类控制器
    class Handler{
        private Object controller;   //控制器
        private Method method;       //方法
        private  String url;         //路径
        public  Object getController(){
            return controller;
        }
        public Handler setController(Object controller){
            this.controller=controller;
            return  this;
        }
        public Method getMethod(){
            return  method;
        }
        public Handler setMethod(Method method){
            this.method=method;
            return this;
        }
        public String getUrl(){
            return  url;
        }
        public  Handler setUrl(String url){
            this.url=url;
            return this;
        }
    }
}
