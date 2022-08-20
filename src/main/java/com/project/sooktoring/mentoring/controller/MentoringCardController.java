package com.project.sooktoring.mentoring.controller;

import com.project.sooktoring.user.util.CurrentUser;
import com.project.sooktoring.user.util.UserPrincipal;
import com.project.sooktoring.mentoring.dto.request.MentoringCardRequest;
import com.project.sooktoring.mentoring.dto.response.MentoringCardFromResponse;
import com.project.sooktoring.mentoring.dto.response.MentoringCardToResponse;
import com.project.sooktoring.mentoring.service.MentoringCardService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "멘토링 감사카드 API")
@RequiredArgsConstructor
@RequestMapping("/mentoring/card")
@RestController
public class MentoringCardController {

    private final MentoringCardService mentoringCardService;

    @Operation(summary = "내가 쓴 감사카드 리스트 조회")
    @GetMapping("/from")
    public List<MentoringCardFromResponse> getMentoringCardListFromMe(@CurrentUser UserPrincipal currentUser) {
        return mentoringCardService.getMentoringCardListFromMe(currentUser.getUserId());
    }

    @Operation(summary = "내가 쓴 감사카드 상세 조회")
    @GetMapping("/from/{mtrCardId}")
    public MentoringCardFromResponse getMentoringCardFromMe(@CurrentUser UserPrincipal currentUser,
                                                            @PathVariable Long mtrCardId) {
        return mentoringCardService.getMentoringCardFromMe(currentUser.getUserId(), mtrCardId);
    }

    @Operation(summary = "감사카드 작성", description = "해당 멘토링 종료 상태일 때에만 작성 가능")
    @PostMapping("/from/{mtrId}")
    public String saveMtrCard(@CurrentUser UserPrincipal userPrincipal,
                              @PathVariable Long mtrId,
                              @RequestBody @Validated MentoringCardRequest mtrCardRequest) {
        mentoringCardService.save(userPrincipal.getUserId(), mtrId, mtrCardRequest);
        return "감사카드 작성이 완료되었습니다.";
    }

    @Operation(summary = "감사카드 수정")
    @PutMapping("/from/{mtrCardId}")
    public String updateMtrCard(@CurrentUser UserPrincipal userPrincipal,
                                @PathVariable Long mtrCardId,
                                @RequestBody @Validated MentoringCardRequest mtrCardRequest) {
        mentoringCardService.update(userPrincipal.getUserId(), mtrCardId, mtrCardRequest);
        return "감사카드 수정이 완료되었습니다.";
    }

    @Operation(summary = "감사카드 삭제")
    @DeleteMapping("/from/{mtrCardId}")
    public String deleteMtrCard(@CurrentUser UserPrincipal userPrincipal,
                                @PathVariable Long mtrCardId) {
        mentoringCardService.delete(userPrincipal.getUserId(), mtrCardId);
        return "감사카드 삭제가 완료되었습니다.";
    }

    @Operation(summary = "나에게 온 감사카드 리스트 조회", description = "only 멘토")
    @GetMapping("/to")
    public List<MentoringCardToResponse> getMentoringCardListToMe(@CurrentUser UserPrincipal currentUser) {
        return mentoringCardService.getMentoringCardListToMe(currentUser.getUserId());
    }
}
