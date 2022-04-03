package com.jason.dao;

import com.jason.pojo.Member;

import java.util.HashMap;
import java.util.List;

public interface MemberDAO {

    //在多条件查询中 如果查询条件不确定 则要使用HashMap作为参数
    public List<Member> searchMember(HashMap<String,Object> params);
}
