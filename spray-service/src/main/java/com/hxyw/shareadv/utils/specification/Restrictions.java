package com.hxyw.shareadv.utils.specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * info:
 * Created by shang on 2017/5/3.
 */
public class Restrictions {

    /**
     * @Title : and
     * @Description : 并且
     */
    public static LogicalExpression and(Criterion... criterions) {
        return new LogicalExpression(criterions, Criterion.Operator.AND);
    }

    /**
     * @Title : eq
     * @Description : 等于
     */
    public static SimpleExpression eq(String fieldName, Object value) {
        return eq(fieldName, value, false);
    }

    /**
     * @Title : eq
     * @Description : 等于
     */
    public static SimpleExpression eq(String fieldName, Object value,
                                      boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.EQ);
    }

    /**
     * @Title : gt
     * @Description : 大于
     */
    public static SimpleExpression gt(String fieldName, Object value) {
        return gt(fieldName, value, false);
    }

    /**
     * @Title : gt
     * @Description : 大于
     */
    public static SimpleExpression gt(String fieldName, Object value,
                                      boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.GT);
    }

    /**
     * @Title : gte
     * @Description : 小于等于
     */
    public static SimpleExpression gte(String fieldName, Object value) {
        return gte(fieldName, value, false);
    }

    /**
     * @Title : gte
     * @Description : 小于等于
     */
    public static SimpleExpression gte(String fieldName, Object value,
                                       boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LTE);
    }

    /**
     * @Title : in
     * @Description : 包含于
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        List<SimpleExpression> expressions = new ArrayList<>();
        for (Object obj : value) {
            expressions.add(new SimpleExpression(fieldName, obj, Criterion.Operator.EQ));
        }
        return new LogicalExpression(
                expressions.toArray(new SimpleExpression[expressions.size()]),
                Criterion.Operator.OR);
    }

    /**
     * @Title : like
     * @Description : 模糊匹配
     */
    public static SimpleExpression like(String fieldName, String value) {
        return like(fieldName, value, false);
    }

    /**
     * @Title : like
     * @Description : 模糊匹配
     */
    public static SimpleExpression like(String fieldName, String value,
                                        boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LIKE);
    }

    /**
     * @Title : lt
     * @Description : 小于
     */
    public static SimpleExpression lt(String fieldName, Object value) {
        return lt(fieldName, value, false);
    }

    /**
     * @Title : lt
     * @Description : 小于
     */
    public static SimpleExpression lt(String fieldName, Object value,
                                      boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.LT);
    }

    /**
     * @Title : lte
     * @Description : 大于等于
     */
    public static SimpleExpression lte(String fieldName, Object value) {
        return lte(fieldName, value, false);
    }

    /**
     * @Title : lte
     * @Description : 大于等于
     */
    public static SimpleExpression lte(String fieldName, Object value,
                                       boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.GTE);
    }

    /**
     * @Title : ne
     * @Description : 不等于
     */
    public static SimpleExpression ne(String fieldName, Object value) {
        return ne(fieldName, value, false);
    }

    /**
     * @Title : ne
     * @Description : 不等于
     */
    public static SimpleExpression ne(String fieldName, Object value,
                                      boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Criterion.Operator.NE);
    }

    /**
     * @Title : or
     * @Description : 或者
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression(criterions, Criterion.Operator.OR);
    }
}
