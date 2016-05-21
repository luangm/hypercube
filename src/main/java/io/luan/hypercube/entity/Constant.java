package io.luan.hypercube.entity;

import java.util.Map;

/**
 * Constant 是固定值, 拥有Context, Concept, 可以多维度(Dimension)
 * 但是不根据时间改变.
 *
 * 如需要根据时间变化的, 用Fact
 */
public class Constant {

    private Long id;
    private Context context;
    private Concept concept;
    private Map<Dimension, Member> dimensionMembers;
    private Quantity quantity;

    public Constant() {
        //
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Map<Dimension, Member> getDimensionMembers() {
        return dimensionMembers;
    }

    public void setDimensionMembers(Map<Dimension, Member> dimensionMembers) {
        this.dimensionMembers = dimensionMembers;
    }
}
