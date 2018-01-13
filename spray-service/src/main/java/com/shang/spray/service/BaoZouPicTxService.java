package com.shang.spray.service;

import com.shang.spray.entity.BaoZouPicTx;
import com.shang.spray.repository.BaoZouPicTxRepository;
import javax.annotation.Resource;
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
