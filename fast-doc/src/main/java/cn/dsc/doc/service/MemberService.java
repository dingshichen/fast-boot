package cn.dsc.doc.service;

import cn.dsc.doc.entity.Member;

/**
 * 会员服务类
 *
 * @see {@link Member}
 * @author dingshichen
 * @version 1.0
 */
public interface MemberService {

    /**
     * 根据名称查询会员
     * <p>输入一个会员名，查询出这个会员名的会员
     * <br>返回的会员可能是null对象
     * @param name 姓名
     * @return 会员
     */
    Member findMemberByName(String name);

    /**
     * @deprecated
     * @param age 年龄
     * @return 会员
     */
    @Deprecated
    Member findMemberByAge(Integer age);


    void saveMember(String name, Integer age) throws Exception;
}
