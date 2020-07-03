package com.answer.base.api.v1;

import com.answer.base.entity.Comment;
import com.answer.base.service.CommentService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/selectByCid/{cid}")
    public Msg selectCommentByClassicId(@PathVariable Integer cid){
        List<Comment> commentList = commentService.selectCommentByClassicId(cid);
        return ResultUtil.success(commentList);
    }

    @PostMapping("/insert")
    public Msg insertComment(HttpServletRequest request, @RequestBody Comment comment){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        comment.setUid(uid);
        commentService.insertComment(comment);
        return ResultUtil.success("评论成功！");
    }
}
