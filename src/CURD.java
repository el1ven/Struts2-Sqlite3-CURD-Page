import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by el1ven on 14-5-9.
 */
public class CURD extends ActionSupport   {


    private HttpServletRequest request;
    private Connection conn;

    private String userName;
    private String userJob;
    private int userId;

    private ArrayList<News> list;
    private News news;

    //用于分页的属性
    private int pageNow = 1;//当前页面，从第一页开始
    private int pageSize = 3;//页面数据大小
    private int rowCount;//总行数
    private int pageCount;//总页数


    public CURD() throws ClassNotFoundException, SQLException {
        this.request = ServletActionContext.getRequest();
        Class.forName("org.sqlite.JDBC");
        this.conn= DriverManager.getConnection("jdbc:sqlite:test.db");
        //this.request.setAttribute("list",this.list);

    }

    public String create () throws ClassNotFoundException, SQLException {

        //分页需要修改显示函数
        this.news = new News();//初始化变量
        rowCount = this.news.count();
        pageCount = (rowCount + pageSize - 1)/pageSize;
        if(pageNow < 1){
            pageNow = 1;
        }
        if(pageNow > pageCount){
            pageNow = pageCount;
        }

        this.list = new ArrayList<News>();

        this.list = this.news.queryByPage((pageNow-1)*pageSize, pageSize);

        if(this.list != null) {
            return SUCCESS;
        }else{
            return ERROR;
        }

        }

    public String add () throws ClassNotFoundException, SQLException{

        this.list = new ArrayList<News>();

        Statement stat = this.conn.createStatement();
        //stat.executeUpdate("drop table if exists people;");
        //stat.executeUpdate("create table people (name, job);");

        PreparedStatement prep = this.conn.prepareStatement("insert into people values (?, ? ,?);");
        prep.setString(2,this.getUserName());
        prep.setString(3,this.getUserJob());
        //prep.executeQuery();
        prep.addBatch();

        this.conn.setAutoCommit(false);
        prep.executeBatch();
        this.conn.setAutoCommit(true);

        return this.create();//每次CURD操作时都要进行再一次的分页
    }

    public String change() throws ClassNotFoundException ,SQLException {

        this.list = new ArrayList<News>();
        Statement stat = this.conn.createStatement();

        PreparedStatement prep = this.conn.prepareStatement("update people set name=?,job=? where id=?");
        prep.setString(1, this.getUserName());
        prep.setString(2, this.getUserJob());
        prep.setInt(3, this.getUserId());
        prep.addBatch();

        this.conn.setAutoCommit(false);
        prep.executeBatch();
        this.conn.setAutoCommit(true);

        return this.create();//每次CURD操作时都要进行再一次的分页
    }

    public String delete() throws ClassNotFoundException,SQLException{

        this.list = new ArrayList<News>();

        Statement stat = this.conn.createStatement();
        String id1 = request.getParameter("id");
        int id2 = Integer.parseInt(id1);

        PreparedStatement prep = this.conn.prepareStatement("delete from people where id=?");
        prep.setInt(1, id2);
        prep.addBatch();

        this.conn.setAutoCommit(false);
        prep.executeBatch();
        this.conn.setAutoCommit(true);

        return this.create();//每次CURD操作时都要进行再一次的分页
    }



    public String getUserName(){
        return userName;
    }
    public String getUserJob(){
        return userJob;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserJob(String userJob){
        this.userJob = userJob;
    }
    public void setUserId(int userId){ this.userId = userId; }

    //分页属性的set, get方法
    public int getPageNow(){return pageNow;}
    public void setPageNow(int pageNow){this.pageNow = pageNow;}
    public int getPageSize(){return pageSize;}
    public void setPageSize(int pageSize){this.pageSize = pageSize;}
    public int getRowCount(){return rowCount;}
    public void setRowCount(int rowCount){this.rowCount = rowCount;}
    public int getPageCount(){return pageCount;}
    public void setPageCount(int pageCount){this.pageCount = pageCount;}
    public ArrayList<News> getList(){
        return list;
    }
    public void setList(ArrayList<News> list){
        this.list = list;

    }






}
