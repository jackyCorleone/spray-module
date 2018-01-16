package com.hxyw.shareadv.service;

import com.hxyw.shareadv.entity.Beautiful;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * info:
 * Created by shang on 16/7/21.
 */
@Service
public class BeautifulService extends BaseService<Beautiful>{

    public Page<Beautiful> findAllApi(Specification<Beautiful> specification, Pageable pageable) {
        return beautifulRepository.findAll(specification, pageable);
    }

}
