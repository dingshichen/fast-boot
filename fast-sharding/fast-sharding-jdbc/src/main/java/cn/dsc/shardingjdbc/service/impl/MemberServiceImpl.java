package cn.dsc.shardingjdbc.service.impl;

import cn.dsc.shardingjdbc.mapper.MemberMapper;
import cn.dsc.shardingjdbc.model.Member;
import cn.dsc.shardingjdbc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dingshichen
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer saveNewMember(String name) {
        return memberMapper.save(name);
    }

    @Override
    public Member findById(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }
}
