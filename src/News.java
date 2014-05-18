import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

/**
 * Created by el1ven on 14-5-10.
 */
public class News {
    private int id;
    private String name;//对应数据库里面的字段
    private String job;
    private ArrayList <News> info;

    //设置get,set方法
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setJob(String job){
        this.job = job;
    }
    public int getId(){return this.id; }
    public String getName(){
        return this.name;
    }
    public String getJob(){
        return this.job;
    }

    //以下方法用于分页
    public ArrayList<News> queryByPage(int pageNow,int pageSize) throws ClassNotFoundException,SQLException {

        this.info = new ArrayList<News>();

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");

        PreparedStatement prep = conn.prepareStatement("select * from people limit ?,?;");
        prep.setInt(1, pageNow);
        prep.setInt(2, pageSize);

        ResultSet rs = prep.executeQuery();

        while(rs.next()){
            News news  = new News();
            news.setId(rs.getInt(1));
            news.setName(rs.getString("name"));
            news.setJob(rs.getString("job"));
            this.info.add(news);
            System.out.println(this.info.size());
        }

        rs.close();
        prep.close();
        conn.close();

        return this.info;
    }

    //查询数据库中的总行数
    public int count() throws ClassNotFoundException,SQLException{

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select count(*) from people;");

        rs.next();//游标指向第一行
        int i = rs.getInt(1);//获取总行数

        rs.close();
        st.close();
        conn.close();

        return i;
    }

}
