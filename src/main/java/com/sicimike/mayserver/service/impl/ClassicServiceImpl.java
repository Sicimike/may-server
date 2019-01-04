package com.sicimike.mayserver.service.impl;

import com.sicimike.mayserver.dto.ClassicDTO;
import com.sicimike.mayserver.entity.Classic;
import com.sicimike.mayserver.entity.UserClassic;
import com.sicimike.mayserver.enums.EnumLikeStatus;
import com.sicimike.mayserver.enums.EnumResponseCode;
import com.sicimike.mayserver.exception.ResourceNotFoundException;
import com.sicimike.mayserver.repository.ClassicRepository;
import com.sicimike.mayserver.repository.UserClassicRepository;
import com.sicimike.mayserver.service.ClassicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author sicimike
 * @create 2018-12-13 19:11
 */
@Service
public class ClassicServiceImpl implements ClassicService {

    @Autowired
    private ClassicRepository classicRepository;

    @Autowired
    private UserClassicRepository userClassicRepository;

    @Override
    public Classic findLatestOne() {
        Classic classic = classicRepository.findLatestOne();
        return classic;
    }

    @Override
    public Classic findPreOne(Integer index) {
        return classicRepository.findPreOne(index);
    }

    @Override
    public Classic findNextOne(Integer index) {
        return classicRepository.findNextOne(index);
    }

    @Override
    public Classic findById(Long id) {

        Optional<Classic> optional = classicRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(EnumResponseCode.RESOURCE_NOT_FOUND.getMessage());
        }
        Classic classic = optional.get();
        return classic;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void like(String openid, Long artId, Integer type, Boolean isUpdate) {
        if (isUpdate) {
            userClassicRepository.like(openid, artId);
        } else {
            UserClassic userClassic = new UserClassic();
            userClassic.setUserOpenid(openid);
            userClassic.setClassicId(artId);
            userClassic.setLikeStatus(EnumLikeStatus.LIKE.getStatus());
            userClassicRepository.save(userClassic);
        }
        classicRepository.like(artId, type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancel(String openid, Long artId, Integer type) {
        userClassicRepository.cancel(openid, artId);
        classicRepository.cancel(artId, type);
    }

    @Override
    public ClassicDTO findFavorInfo(Long id, Integer type) {
        ClassicDTO classicDTO = classicRepository.findFavorInfo(id, type);
        return classicDTO;

    }

    @Override
    public UserClassic findByUserIdAndClassicId(String openid, Long classicId) {
        return userClassicRepository.findByUserOpenidAndClassicId(openid, classicId);
    }

    @Override
    public Long favorCount(String openid, Integer status) {
        return userClassicRepository.countByUserOpenidAndLikeStatus(openid, status);
    }

    @Override
    public List<Classic> classicFavor(String openid, Integer status) {
        return classicRepository.findClassicFavor(openid, status);
    }

}
