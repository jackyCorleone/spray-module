package com.hxyw.shareadv.pipeline;

import com.hxyw.shareadv.utils.specification.Criteria;
import com.hxyw.shareadv.entity.BaoZouPicTx;
import com.hxyw.shareadv.repository.BaoZouPicTxRepository;
import com.hxyw.shareadv.utils.specification.Restrictions;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by jacky on 2018/1/12.
 */
@Repository
public class QiuShiBaiKePipeline implements Pipeline {
    @Autowired
    private BaoZouPicTxRepository baoZouPicTxRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().contains("qiushibaike")) {
                BaoZouPicTx funny=(BaoZouPicTx) entry.getValue();
                Criteria<BaoZouPicTx> criteria = new Criteria<>();
                criteria.add(Restrictions.eq("label", funny.getLabel()));
                if (baoZouPicTxRepository.findOne(criteria) == null) {//检查链接是否已存在
                    funny.setTypeId(1);//gif
                    funny.setCreateDate(new Date());
                    funny.setStatus(1);
                    funny.setModifyDate(new Date());
                    baoZouPicTxRepository.save(funny);
                }
            }

        }
    }
}
