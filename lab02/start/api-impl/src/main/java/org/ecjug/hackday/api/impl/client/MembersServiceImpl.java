/**
 * 
 */
package org.ecjug.hackday.api.impl.client;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.ecjug.hackday.api.MembersService;
import org.ecjug.hackday.domain.model.Member;
import org.ecjug.hackday.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ecabrerar
 * @date Aug 2, 2019
 *
 */
@Slf4j
@ApplicationScoped
public class MembersServiceImpl implements MembersService {

    @Inject
    private MemberRepository memberRepository;

    @Override
    public List<Member> list() {
        return memberRepository.list();
    }

    @Override
    public Member add(Member member) {
        return memberRepository.add(member);
    }
    
}
    
