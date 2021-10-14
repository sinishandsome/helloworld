package com.atguigu.mybatis.test;

import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenxin
 * @create 2021-09-09 13:44
 */
public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test() throws IOException {
        String resource = "mybatis_config.xml";
        //获取sqlSession实例，可以执行已经映射的sql语句
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        //sql映射在EmployeeMapping
        Employee employee = sqlSession.selectOne("com.atguigu.mybatis.EmployeeMapper.selectEmp", 1);
        System.out.println(employee);
        sqlSession.close();
    }

    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //获取接口实现类对象（接口的动态代理对象）
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(mapper);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
