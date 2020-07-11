package com.answer.base.api.v1;

import com.answer.base.dto.PagingDTO;
import com.answer.base.entity.Hole;
import com.answer.base.service.HoleService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/v1/hole")
public class HoleController {
    @Autowired
    private HoleService holeService;
    @PostMapping("/insert")
    public Msg insertHole(@RequestBody Hole hole){
        holeService.insertHole(hole);
        return ResultUtil.success("添加成功");
    }

//    获取我自己的树洞
    @PostMapping("/myhole")
    public Msg getMyHole(HttpServletRequest request, @RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        List<Hole> holes = holeService.getMyHole(pagingDTO);
        return ResultUtil.success(holes);
    }

//    随机获取别人的n个树洞
    @PostMapping("/rand")
    public Msg getRandHole(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){

        return ResultUtil.success("添加成功");
    }


}
