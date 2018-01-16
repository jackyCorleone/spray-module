package com.hxyw.shareadv.service;

import com.hxyw.shareadv.entity.BaoZouPicTx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jacky on 2018/1/11.
 */
@Service
public class BaoZouPicTxService extends BaseService<BaoZouPicTx> {

    public Page<BaoZouPicTx> findAllApi(Specification<BaoZouPicTx> specification, Pageable pageable) {
        return baoZouPicTxRepository.findAll(specification, pageable);
    }
    public BaoZouPicTx findOneApi(Integer id) {
        return baoZouPicTxRepository.findOne(id);
    }
}
