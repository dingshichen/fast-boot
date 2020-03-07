package cn.dsc.shardingjdbc.service;

import cn.dsc.shardingjdbc.model.Member;

/**
 * @author dingshichen
 */
public interface MemberService {

    Integer saveNewMember(String name);

    Member findById(Integer id);
}
