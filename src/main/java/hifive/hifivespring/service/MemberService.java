package hifive.hifivespring.service;

import hifive.hifivespring.domain.Member;
import hifive.hifivespring.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 내부에서 직접넣지 않고 외부에서 넣는 방식으로 바꾼것
    //@Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }
      /*  public Long join(Member member) {

            long start = System.currentTimeMillis();

            try{
                validateDuplicateMember(member); //중복 회원 검증
                memberRepository.save(member);
                return member.getId();
            }finally{
                long finish = System.currentTimeMillis();
                long timeMs = finish - start;
                System.out.println("join = " + timeMs + "ms");
            }*/


    // 중복되는 이름을 가진 회원X
    //memberRepository.findByName(member.getName()); ==
    //Optional<Member> result = memberRepository.findByName(member.getName());
       /* //memberRepository.findByName(member.getName())
               .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */
        /* //result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            } );
        */
    //값이 있으면 멤버가 들어옴 -> 멤버에 값이 있으면 throw new IllegalAccessException 에서 존재하는 회원
    // ifPresent() 얘가 null 이 아니라 어떤 값이 있으면 이 로직이나 동작을 하는 것
    // 이것은 Optional 이기 때문에 가능, Optional 은 감싸있기 때문이다.




    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
    /*//전체 회원 조회
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }*/
}
