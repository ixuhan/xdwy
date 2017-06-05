**搭建SSM框架步骤（Struts2 + Spring + Mybatis）**</br>
1.构建配置文件</br>
    WEB-INF/web.xml</br>
    struts.xml</br>
    resources/applicationContext.xml</br>
2.配置文件构建完成后用Generator生成Mybatis代码</br>
    生成的代码文件类型</br>
    *Mapper.java - 对应我们之前的DAO层，即数据库调用层，它是一个接口，*Mapper.xml是它的实现</br>
    model层</br>
    会生成model和modelExample</br>
    区别：model是基础的模型，modelExample是Criteria的实现(通俗易懂的说，就是用函数实现sql逻辑，具体区别见test文件)</br>
3.代码运行流程</br>
    由于添加了注解</br>
    action - 在有@Controller注解的类中解析用户访问的Servlet，在action会注入service</br>
    service - 在*ServiceImpl会注入之前Mybatis生成的*Mapper.Java，在Impl中对*Mapper.java进行调用</br>
