package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.service.MsgService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.CommentMsgVO;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UnreadCountVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/v1/comment/msg")
@RestController
public class CommentMsgController {
    @Autowired
    private MsgService msgService;

//    获取树洞相关的消息
    @PostMapping("/hole/mylist")
    public Msg getMyHoleCommentMsg(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        Pager pager = msgService.getMyCommentMsgList(pagingDTO);
        return ResultUtil.success(pager);
    }

//    获取classic相关的消息
    @PostMapping("/classic/mylist")
    public Msg getMyClassicCommentMsg(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        Pager pager = msgService.getMyClassicMsgList(pagingDTO);
        return ResultUtil.success(pager);
    }

//    获取我的未读的消息总量
    @GetMapping("/unread/total")
    public Msg getUnreadMsgCount(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        Integer count = msgService.getMyUnReadMsgCount(uid);
        Map countMap = new HashMap();
        countMap.put("unread",count);
        return ResultUtil.success(countMap);
    }
//  获取我的各未读消息的数量
    @GetMapping("/unread/count")
    public Msg getAllTypeUnreadMsgCount(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        UnreadCountVO unreadCountVO = msgService.getUnReadTypeCount(uid);
        return ResultUtil.success(unreadCountVO);
    }

//    设置消息已读
    @GetMapping("/setRead")
    public Msg setMsgRead(HttpServletRequest request,@RequestParam Integer id){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        UidAndIdDTO uidAndIdDTO = new UidAndIdDTO();
        uidAndIdDTO.setId(id);
        uidAndIdDTO.setUid(uid);
        msgService.setMsgRead(uidAndIdDTO);
        return ResultUtil.success();
    }

//    根据消息id删除我的某条消息
    @GetMapping("/del")
    public Msg deleteMsgById(HttpServletRequest request,@RequestParam Integer id){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        UidAndIdDTO uidAndIdDTO = new UidAndIdDTO();
        uidAndIdDTO.setUid(uid);
        uidAndIdDTO.setId(id);
        msgService.deleteMyMsgById(uidAndIdDTO);
        return ResultUtil.success("删除成功");
    }

}
