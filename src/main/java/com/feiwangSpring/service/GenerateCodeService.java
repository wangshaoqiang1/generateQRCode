package com.feiwangSpring.service;

import java.util.Map;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/17 13:37
 */
public interface GenerateCodeService {

    public Map generateCodeByContent(String Content,Integer size,Integer color);
}
