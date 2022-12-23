package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); //회원 조회
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정할인정책

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원조회
        //난 모르겠고 멤버 줄테니까 폴리시 너가 알아서 하고 나한테 결과만 넘겨줘
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인정책에 회원넘기기

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
