package io.luan.hypercube.entity;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 一个计算出来的事实值。
 */
public class ComputedFact extends Fact {

    private String formula;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public Quantity getQuantity() {
        throw new NotImplementedException();
    }
}
