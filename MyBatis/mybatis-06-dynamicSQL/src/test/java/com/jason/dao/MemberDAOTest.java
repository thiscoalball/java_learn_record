package com.jason.dao;

import com.jason.pojo.Member;
import com.jason.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class MemberDAOTest {

    @Test
    public void searchMember() {
        HashMap<String,Object> params = new HashMap<>();
        params.put("gender","女");
        params.put("minAge",22);
        params.put("maxAge",45);
        params.put("city","武汉");

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
        List<Member> members = mapper.searchMember(params);

        for (Member member : members) {
            System.out.println(member);
        }
    }
}