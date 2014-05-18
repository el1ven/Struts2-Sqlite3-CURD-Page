   终于弄好了Struts2的分页功能，在之前的CURD基础之上,发现之前代码逻辑有些混乱，应该把连接数据库之类的操作放到一个单独的类中，而不是在bean里或是action类里面进行大量SQL语句的书写。
   
Step1:在News中增加了两个方法，queryByPage和count, count用于统计数据库中的记录总数, queryByPage返回一个存有news的list,这个list是分页之后应该显示的结果。

Step2:应为在jsp和action中都设置了value和相应属性的对应，所以我们不必把list放入到request中，直接设置get,set属性使用即可。

Step3:把原来CURD函数成功返回的SUCCESS改为return this.create();//每次CURD操作时都要进行再一次的分页, 这样就可以同步操作了, 我暂时是这种方法实现的, 不知道是不是主流的想法。

Step4:     
prep.addBatch();   
this.conn.setAutoCommit(false);    
prep.executeBatch();    
this.conn.setAutoCommit(true);                 
这个应该在sqlite查询的时候必须写上的，我测试之后select不用写，其他操作必须写，要不不执行。

Step5:分页的时候在success.jsp显示的时候用到了struts2的标签, 现在还不是特别明白, 不过马上就要学到了, 到时候来update journal, 哈哈~