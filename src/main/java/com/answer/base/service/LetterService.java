package com.answer.base.service;

import com.answer.base.entity.Letter;

public interface LetterService {
    void insertOne(Letter letter);
    Letter getMyLetter(Integer uid);
    void updateLetter(Letter letter);
}
