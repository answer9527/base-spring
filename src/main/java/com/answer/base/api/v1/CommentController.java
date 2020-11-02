package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Comment;
import com.answer.base.entity.CommentMsg;
import com.answer.base.service.CommentService;
import com.answer.base.service.MsgService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MsgService msgService;
    @PostMapping("/selectByCid")
    public Msg selectCommentByClassicId(@RequestBody PagingDTO pagingDTO){
        Pager<Comment> pager = commentService.selectCommentByClassicId(pagingDTO);
        return ResultUtil.success(pager);
    }

//    新增
    @PostMapping("/insert")
    public Msg insertComment(HttpServletRequest request, @RequestBody Comment comment){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        comment.setUid(uid);
        Comment _comment = commentService.insertComment(comment);
        CommentMsg commentMsg = new CommentMsg();
        commentMsg.setType(1);
        commentMsg.setCommentId(_comment.getId());

//        所有的classic评论都不会对作者发出消息所以 is_root为false,  且comment的uid_r为0或者null 的时候说明是视听根评论不需要发送
        commentMsg.setIsRoot(false);
        if(_comment.getUid_r()!=null){
            commentMsg.setUid(_comment.getUid_r());
            msgService.insertMsg(commentMsg);
        }
        return ResultUtil.success("评论成功！");
    }


//  删除classic中我的评论
    @GetMapping("/del/{id}")
    public Msg delComment(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        UidAndIdDTO uidAndIdDTO = new UidAndIdDTO();
        uidAndIdDTO.setUid(uid);
        uidAndIdDTO.setId(id);
        commentService.delComment(uidAndIdDTO);
        return ResultUtil.success("删除成功！");
    }

//    管理员删除评论
    @GetMapping("/power/del/{id}")
    public Msg PowerDelComment(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        commentService.powerDelComment(id);
        return ResultUtil.success("删除成功！");
    }


}
