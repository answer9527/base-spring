package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.service.MsgService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.CommentMsgVO;
import com.answer.base.vo.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/v1/comment/msg")
@RestController
public class CommentMsgController {
    @Autowired
    private MsgService msgService;
    @PostMapping("/mylist")
    public Msg getMyCommentMsg(HttpServletRequest request, PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        Pager pager = msgService.getMyCommentMsgList(pagingDTO);
        return ResultUtil.success(pager);
    }
}
