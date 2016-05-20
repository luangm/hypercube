package io.luan.hypercube.entity;

import java.util.Map;

/**
 * 一个事实值，必须有：
 * - Concept（概念）
 * - Value （具体的值，包含单位）
 * - Date （时间，可能是瞬时的也可以是时期）
 * - 零到多个Dimension的Member
 */
public class Fact {

    private Long id;
    private Context context;
    private Concept concept;
    private Period period;
    private Map<Dimension, Member> dimensionMembers;
    private Quantity quantity;

    public Concept getConcept() {
        return concept;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Map<Dimension, Member> getDimensionMembers() {
        return dimensionMembers;
    }

    public void setDimensionMembers(Map<Dimension, Member> dimensionMembers) {
        this.dimensionMembers = dimensionMembers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
