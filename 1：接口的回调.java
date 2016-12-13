# 接口的回调 
 
 定义:在Java中，就是类A调用类B中的某个方法b，然后类B又在某个时候反过来调用类A中的某个方法a，对于A来说，这个a方法便叫做回调方法,b反过头来调用a的方法(回调方法)的这个行为叫作回调。

 补充:回调我觉得是先有方法的调用者，再有方法的实现者。其意义在于比较灵活，
      就是说方法的实现给你去做，你想怎么写这个方法 ,你说了算。这个方法只是先声明出来，这是必不可少的一步。
      你实现出来以后，后台由框架或者接口调用者来调用这个方法，得出一个直接的结果。

//接口
pubilc interface CallBack{      
    public void callbackMethod();              
} 

//被回调者
public class A implements CallBack{  // A实现接口CallBack   
       
       B b = new B();   
       public void do(){   
          b.doSomething(this); // A运行时调用B中doSomething方法,以自身传入参数，B已取得A，可以随时回调A所实现的CallBack接口中的方法   
       }                      

       public void callbackMethod(){  // 对A来说，该方法就是回调方法     
           System.out.println("callbackMethod is executing!");                  
       }              

}  

//回调者
public class B{     

     public void doSomething(CallBack cb){  // B拥有一个参数为CallBack接口类型的方法   
       System.out.println("我在处理我的事情");   
       System.out.println("then, I need invoke callbackMethod ");   
       cb.callbackMethod(); //回调  
     }      
  
}