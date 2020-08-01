package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.CommentMsg;
import com.answer.base.entity.Hole;
import com.answer.base.entity.HoleComment;
import com.answer.base.service.HoleCommentService;
import com.answer.base.service.HoleService;
import com.answer.base.service.MsgService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.Pager;
import com.answer.base.vo.SingleHoleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/v1/hole/comment")
public class HoleCommentController {
    @Autowired
    private HoleCommentService holeCommentService;
    @Autowired
    private HoleService holeService;
    @Autowired
    private MsgService msgService;
    @PostMapping("/selectByHid")
    public Msg getHoleCommentByHid(@RequestBody PagingDTO pagingDTO){
        PageHelper.startPage(pagingDTO.getPage(),pagingDTO.getSize());
        PageInfo<HoleComment> pageInfo = new PageInfo<>(holeCommentService.getHoleCommentByHid(pagingDTO));
        Pager pager = new Pager(pageInfo);
        return ResultUtil.success(pager);
    }

//    评论树洞
    @PostMapping("/insert")
    public Msg insertHoleComment(HttpServletRequest request,@RequestBody HoleComment holeComment){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        holeComment.setUid(uid);
        HoleComment  _holeComment = holeCommentService.insertHoleComment(holeComment);
        Integer h_com_id = _holeComment.getId();
        SingleHoleVO hole = holeService.getHoleById(_holeComment.getHid());

        CommentMsg commentMsg = new CommentMsg();
        commentMsg.setType(2);
        commentMsg.setCommentId(h_com_id);

//        给树洞作者的消息
        if(hole.getUid()!=uid){
            commentMsg.setUid(hole.getUid());
            msgService.insertMsg(commentMsg);
        }
//         给被回复评论的人的消息
        if(_holeComment.getPid()!=null){
            commentMsg.setIsRoot(false);
            commentMsg.setUid(_holeComment.getUid_r());
            msgService.insertMsg(commentMsg);
        }
        return ResultUtil.success();
    }

//    删除树洞评论
    @GetMapping("/del/{id}")
    public Msg delHoleComment(HttpServletRequest request, @PathVariable Integer id){
        String token = request.getHeader("Authorization");
        Integer uid =JwtToken.TokenGetUid(token);
        UidAndIdDTO uidAndIdDTO = new UidAndIdDTO();
        uidAndIdDTO.setId(id);
        uidAndIdDTO.setUid(uid);
        holeCommentService.delHoleComment(uidAndIdDTO);
        return ResultUtil.success("删除成功");
    }
}
