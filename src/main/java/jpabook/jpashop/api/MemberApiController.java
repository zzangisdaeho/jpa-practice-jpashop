package jpabook.jpashop.api;

import jpabook.jpashop.entity.Member;
import jpabook.jpashop.service.MemberService;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

//@Controller
//@ResponseBody
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long join = memberService.join(member);

        return new CreateMemberResponse(join);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

//    @Date는 @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 모두 합친 것!
//    @EqualsAndHashCode의 call super의 기본값은 false이다
    @Data
    @ToString
//    @Setter // json은 deserialize(역직렬화)(byte to java)작업 시 : 기본 생성자로 객체 생성 후, setter를 통해 읽은 값을 넣어준다
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
//    @Getter // json은 serialize(직렬화)(java to byte)작업 시 : 넘겨준 객체(넘겨주기 떄문에 기본 생성자 필요 없음)를 getter를 통해 읽은 다음 json으로 만들어 넘겨준다
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        @NonNull
        private Long id;
    }
}
