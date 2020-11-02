package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UidAndIdDTO;
import com.answer.base.entity.Hole;
import com.answer.base.service.HoleService;
import com.answer.base.util.JwtToken;
import com.answer.base.util.Msg;
import com.answer.base.util.ResultUtil;
import com.answer.base.vo.Pager;
import com.answer.base.vo.RandHoleVO;
import com.answer.base.vo.SingleHoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/hole")
public class HoleController {
    @Autowired
    private HoleService holeService;
    @PostMapping("/insert")
    public Msg insertHole(HttpServletRequest request,@RequestBody Hole hole){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        hole.setUid(uid);
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
        Map holeMap = new HashMap();
//        过审的特殊处理
        holeMap.put("holes",holes);
        holeMap.put("createScope",false);
        return ResultUtil.success(holeMap);
    }

//    随机获取别人的n个树洞
    @PostMapping("/rand")
    public Msg getRandHole(HttpServletRequest request,@RequestBody PagingDTO pagingDTO){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        pagingDTO.setKey(uid);
        List<RandHoleVO> holeVOS = holeService.getRandHole(pagingDTO);
        return ResultUtil.success(holeVOS);
    }

//    获取树洞的详情
    @GetMapping("/getById/{id}")
    @ScopeLevel(0)
    public Msg getHoleById(@PathVariable Integer id){
        SingleHoleVO singleHoleVO = holeService.getHoleById(id);
        return ResultUtil.success(singleHoleVO);
    }


//    删除我的树洞功能
    @GetMapping("/del/{id}")
    public Msg delHole(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("Authorization");
        Integer uid = JwtToken.TokenGetUid(token);
        UidAndIdDTO uidAndIdDTO = new UidAndIdDTO();
        uidAndIdDTO.setUid(uid);
        uidAndIdDTO.setId(id);
        holeService.delHole(uidAndIdDTO);
        return ResultUtil.success();
    }

//    分页获取树洞列表
    @PostMapping("/list")
    public Msg getHoleList(@RequestBody  PagingDTO pagingDTO){
        Pager<SingleHoleVO> holeList = holeService.getHoleList(pagingDTO);
        return ResultUtil.success(holeList);
    }
}
