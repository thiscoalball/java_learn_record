import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jason.dao.StudentMapper;
import com.jason.pojo.Student;
import com.jason.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void getStudentList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Student> userList = sqlSession.getMapper(StudentMapper.class).getStudentList();
        for (Student student : userList) {
            System.out.println(student);
        }
        sqlSession.close();
    }

    @Test
    public void getStudentById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Student student = sqlSession.getMapper(StudentMapper.class).getStudentById(1);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void insertStudent(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Student student = new Student(0, "100008", "marry", "女", 2);
        int i = sqlSession.getMapper(StudentMapper.class).insertStudent(student);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateStudent() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Student student = new Student(8, "100006", "marry", "女", 2);
        int i = sqlSession.getMapper(StudentMapper.class).updateStudent(student);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void pagingPlugins(){
       StudentMapper studentMapper = MybatisUtils.getSqlSession().getMapper(StudentMapper.class);
       //第一个参数是页数 第二个参数是一页的容量
        PageHelper.startPage(2,2);
        List<Student> studentList = studentMapper.getStudentListByGender("女");
        PageInfo<Student> studentPageInfo= new PageInfo<>(studentList);
        List<Student> list = studentPageInfo.getList();
        for (Student student : list) {
            System.out.println(student);
        }
        System.out.println(studentPageInfo.toString());
    }
}
