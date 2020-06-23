package com.answer.base.service.Impl;

import com.answer.base.dao.ClassicMapper;
import com.answer.base.dto.ChangeLikeCountDTO;
import com.answer.base.dto.PagingDTO;
import com.answer.base.dto.UserLikeUnlikeClassicDTO;
import com.answer.base.entity.Classic;
import com.answer.base.exception.http.ParameterException;
import com.answer.base.service.ClassicService;
import com.answer.base.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClassicServiceImpl implements ClassicService {
    @Autowired
    private ClassicMapper classicMapper;

    @Override
    public Classic getRecommendLatest() {
        Optional<Classic> classicOptional = Optional.ofNullable(classicMapper.getRecommendLatest());
        Classic classic = classicOptional.orElseThrow(()->new ParameterException(50004));
        return classic;
    }

    @Override
    public Classic getRecommendPrevious(Integer id) {
        Optional<Classic> classicOptional = Optional.ofNullable(classicMapper.getRecommendPrevious(id));
        Classic classic = classicOptional.orElseThrow(()->new ParameterException(50004));
        return classic;
    }

    @Override
    public Classic getRecommendNext(Integer id) {
        Optional<Classic> classicOptional = Optional.ofNullable(classicMapper.getRecommendNext(id));
        Classic classic = classicOptional.orElseThrow(()->new ParameterException(50004));
        return classic;
    }

    @Override
    public void insertClassic(Classic classic) {
        Integer count = classicMapper.insertOne(classic);
        if(count==0){
            throw new ParameterException(50001);
        }

    }

    @Override
    public void RecommendClassic(Integer id) {
        Boolean bool = classicMapper.RecommendOne(id);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public void removeRecommend(Integer id) {
        Boolean bool = classicMapper.removeRecommend(id);
        if(!bool){
            throw new ParameterException(50003);
        }

    }

    @Override
    public void deleteClassic(Integer id) {
        Boolean bool = classicMapper.deleteOne(id);
        if(!bool){
            throw new ParameterException(50002);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setLike(Integer uid, Integer cid) {
        UserLikeUnlikeClassicDTO userLikeClassicDTO = UserLikeUnlikeClassicDTO.builder().uid(uid).cid(cid).build();
        Boolean bool = classicMapper.setLike(userLikeClassicDTO);
        if(!bool){
            throw new ParameterException(50003);
        }
        ChangeLikeCountDTO changeLikeCountDTO = ChangeLikeCountDTO.builder().cid(cid).isAdd(true).build();
        this.setLikeCount(changeLikeCountDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelLike(Integer uid, Integer cid) {
        UserLikeUnlikeClassicDTO userUnlikeClassicDTO = UserLikeUnlikeClassicDTO.builder().uid(uid).cid(cid).build();
        Boolean bool = classicMapper.cancelLike(userUnlikeClassicDTO);
        if(!bool){
            throw new ParameterException(50003);
        }
        ChangeLikeCountDTO changeLikeCountDTO = ChangeLikeCountDTO.builder().cid(cid).isAdd(false).build();
        this.setLikeCount(changeLikeCountDTO);
    }

    @Override
    public void setLikeCount(ChangeLikeCountDTO changeLikeCountDTO) {
        Boolean bool = classicMapper.updateLikeCount(changeLikeCountDTO);
        if(!bool){
            throw new ParameterException(50003);
        }
    }

    @Override
    public List<Classic> getMyLike(PagingDTO pagingDTO) {
        return classicMapper.getMyLike(pagingDTO);
    }

    @Override
    public List<Classic> getListByType(PagingDTO pagingDTO) {
        return classicMapper.getListByType(pagingDTO);
    }

    @Override
    public Classic getDetailById(Integer id) {
        return classicMapper.getDetailById(id);
    }

}

