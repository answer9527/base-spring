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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/v1/comment/msg")
@RestController
public class CommentMsgController {
    @Autowired
    private MsgService msgService;
    @PostMapping("/hole/mylist")
    public Msg getMyHoleCommentMsg(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        Pager pager = msgService.getMyCommentMsgList(pagingDTO);
        return ResultUtil.success(pager);
    }

    @PostMapping("/classic/mylist")
    public Msg getMyClassicCommentMsg(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        Pager pager = msgService.getMyClassicMsgList(pagingDTO);
        return ResultUtil.success(pager);
    }
}
