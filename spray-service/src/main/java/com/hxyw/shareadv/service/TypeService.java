package com.hxyw.shareadv.service;

import com.hxyw.shareadv.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * info:
 * Created by shang on 2016/10/17.
 */
@Service
public class TypeService extends BaseService<Type> {

    public Page<Type> findAllApi(Specification<Type> specification, Pageable pageable) {
        return typeRepository.findAll(specification, pageable);
    }

}
