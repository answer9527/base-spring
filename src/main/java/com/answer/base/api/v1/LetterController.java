package com.answer.base.api.v1;

import com.answer.base.core.interceptors.ScopeLevel;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.SetLetterImageDTO;
import com.answer.base.entity.Letter;
import com.answer.base.entity.User;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.LetterService;
import com.answer.base.service.UserService;
import com.answer.base.service.WxService;
import com.answer.base.util.*;
import com.answer.base.vo.LetterVO;
import com.answer.base.vo.MonthCountVO;
import com.answer.base.vo.Pager;
import com.answer.base.vo.UserInfoVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/v1/letter")
@Validated
public class LetterController {
    @Autowired
    private LetterService letterService;
    @Autowired
    private IMailService iMailService;
    @Autowired
    private UserService userService;

    @Value("${server.projectName}")
    private String projectName;

    @Value("${wx.sendTemplateUrl}")
    private String sendTemplateUrl;

    @Autowired
    private WxService wxService;

    @PostMapping("/insert")
    @ScopeLevel(0)
    public Msg insertOne(HttpServletRequest request, @RequestBody Letter letter){
        Integer uid = JwtToken.RequestGetUid(request);
        letter.setUid(uid);
        letterService.insertOne(letter);

        Optional<User> optUser = userService.selectHasOpenid(uid);
        if(optUser.isPresent()){
            User user = optUser.get();

            String access_token = wxService.getAccessToken();
            String url = MessageFormat.format(this.sendTemplateUrl,access_token);
            FetchUtil<Map> fetchUtil = new FetchUtil();


            Map map1 = new HashMap();
            Map map2 = new HashMap();
            Map map3 = new HashMap();
            Map map4 = new HashMap();
            Map map5 = new HashMap();
            map1.put("value","恭喜您，您已成功创建一封给自己的信！");
            switch (letter.getType()){
                case 2:
                    map2.put("value","手抄信件");
                    break;
                case 3:
                    map2.put("value","语言信件");
                    break;
                default:
                    map2.put("value","普通信件");
            }
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
            map3.put("value",sdf.format(date));
            map4.put("value",user.getUserName());
            map5.put("value","15579199357");

            Map mapY = new HashMap();
            mapY.put("thing1",map1);
            mapY.put("thing4",map2);
            mapY.put("time3",map3);
            mapY.put("name2",map4);
            mapY.put("phone_number8",map5);

            Map reqMap = new HashMap();
            reqMap.put("access_token",access_token);
            reqMap.put("touser",user.getOpenId());
            reqMap.put("template_id","_eKtyezHn3dqlcX55CnC2zgvhz2_rODywcxJe4t7_VU");
            reqMap.put("data",mapY);
            reqMap.put("page","pages/classic/index");

           ResponseEntity<Map> map = fetchUtil.postForEntity(url,reqMap,Map.class);
        }


        return ResultUtil.success("创建成功！");
    }

    @GetMapping("/myletter")
    @ScopeLevel(0)
    public Msg getMyLetter(HttpServletRequest request){
        Integer uid = JwtToken.RequestGetUid(request);
        Letter letter = letterService.getMyLetter(uid);
        return ResultUtil.success(letter);
    }
//    获取我的信件列表
    @PostMapping("/myletter/list")
    @ScopeLevel(0)
    public Msg getMyLetterList(HttpServletRequest request,@RequestBody  PagingDTO pagingDTO){
        Integer uid = JwtToken.RequestGetUid(request);
        pagingDTO.setKey(uid);
        Pager<Letter> letterPager = letterService.getMyLetterList(pagingDTO);
        return ResultUtil.success(letterPager);
    }
//    获取我的某封信件详情
    @GetMapping("/myletter/detail/{id}")
    public Msg getMyLetterDetaiById(HttpServletRequest request,@PathVariable Integer id){
        Integer uid = JwtToken.RequestGetUid(request);
        LetterVO letterVO = letterService.getMyLetterDetail(uid,id);
        return ResultUtil.success(letterVO);
    }
//
    @PostMapping("/update")
    @ScopeLevel(0)
    public Msg updateMyLetter(HttpServletRequest request,@RequestBody Letter letter){
        Integer uid = JwtToken.RequestGetUid(request);
        letter.setUid(uid);
        letterService.updateLetter(letter);
        return ResultUtil.success("更新成功！");
    }

    @PostMapping("/list")
    @ScopeLevel(9)
    public Msg getLetterList(@RequestBody PagingDTO pagingDTO){
        Pager<LetterVO> letterList = letterService.getLetterList(pagingDTO);
        return  ResultUtil.success(letterList);
    }

//    获取按年月月份获取数量
    @GetMapping("/month/planCount")
    @ScopeLevel(9)
    public Msg getLetterMonthPlan(@RequestParam @Min(0) Integer y,@RequestParam @Max(value = 12,message = "月份最大不超过12") @Min(value = 0,message = "月份不小于0")  Integer m ){
        List<MonthCountVO> countVOList = letterService.getLetterMonthPlan(y,m);
        return ResultUtil.success(countVOList);
    }

//    按年月日获取letter列表
    @PostMapping("/byTime")
    @ScopeLevel(9)
    public Msg getLetterListByTime(@RequestParam @Min(0) Integer y,@RequestParam @Max(12) @Min(0)  Integer m, @RequestParam @Max(31) Integer d,@RequestBody PagingDTO pagingDTO){
        Pager<LetterVO> letterVOPager = letterService.getLetterListByTime(y,m,d,pagingDTO);
        return ResultUtil.success(letterVOPager);
    }

    @GetMapping("/send")
    @ScopeLevel(9)
    public Msg sendLetter(@RequestParam Integer id){
       LetterVO sending_letter = letterService.getPlanLetterById(id);
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(projectName).append(sending_letter.getTitle());
       if(sending_letter.getType()==1){

           iMailService.sendHtmlMail(sending_letter.getEmail(),stringBuilder.toString(),sending_letter.getContent());
       }else{
            iMailService.sendFileMail(sending_letter.getEmail(),stringBuilder.toString(),sending_letter.getContent(),sending_letter.getImage());
       }
       letterService.setLetterOverById(id);
       return ResultUtil.success("邮寄成功！");
//        iMailService.sendSimpleMail("568427129@qq.com","测试","6666");
//        iMailService.sendHtmlMail("568427129@qq.com","测试","<img class=\"normal\" width=\"430px\" data-loadfunc=\"0\" src=\"https://pics4.baidu.com/feed/71cf3bc79f3df8dc13ff1b7405b0e58c451028ba.jpeg?token=5ef85bc821931c09a1fe58d0cb33a4c2\" data-loaded=\"0\">");
//        iMailService.sendFileMail("568427129@qq.com","测试","<h1>给自己的信</h1>","pom.xml");

    }
//    管理员获取信件详情
    @GetMapping("/byId/{id}")
    @ScopeLevel(9)
    public Msg PublicGetLetterById(@PathVariable Integer id){
        LetterVO letterVO = letterService.getPublicLetterById(id);
        return ResultUtil.success(letterVO);
    }
//    删除自己的信件
    @GetMapping("/myletter/del/{id}")
    public Msg delMyLetter(HttpServletRequest request,@PathVariable Integer id){
        Integer uid = JwtToken.RequestGetUid(request);
        letterService.delMyLetter(uid,id);
        return ResultUtil.success("删除成功！");
    }

    @PostMapping("/set/image")
    @ScopeLevel(9)
    public Msg setLetterImage(@RequestBody SetLetterImageDTO setLetterImageDTO){
        letterService.setLetterImage(setLetterImageDTO.getId(),setLetterImageDTO.getImage());
        Optional<User> optUser = userService.selectHasOpenid(setLetterImageDTO.getUid());
        if(optUser.isPresent()){
            User user = optUser.get();
            String access_token = wxService.getAccessToken();
            String url = MessageFormat.format(this.sendTemplateUrl,access_token);
            FetchUtil<Map> fetchUtil = new FetchUtil();


            Map map1 = new HashMap();
            Map map2 = new HashMap();
            Map map3 = new HashMap();


            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记

            map1.put("value","给自己的信！");
            map2.put("value","您好，管理员已上传您的自己的信的文件（手抄信件或语音信件），请注意查收！");
            map3.put("value",sdf.format(date));



            Map mapY = new HashMap();
            mapY.put("thing1",map1);
            mapY.put("thing2",map2);
            mapY.put("time3",map3);


            Map reqMap = new HashMap();
            reqMap.put("access_token",access_token);
            reqMap.put("touser",user.getOpenId());
            reqMap.put("template_id","I1b2bPBUAe6rItaCaOMGvctNcZpK2ckNCf9qXfkIwbg");
            reqMap.put("data",mapY);
            reqMap.put("page","pages/classic/index");

            ResponseEntity<Map> map = fetchUtil.postForEntity(url,reqMap,Map.class);
        }
        return ResultUtil.success("设置成功！");
    }
}
