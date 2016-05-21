package io.luan.hypercube.entity;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 一个计算出来的事实值。
 */
public class ComputedFact extends Fact {

    private String formula;
    private Quantity quantityCache;
    private boolean cacheValid;

    public ComputedFact() {
        //
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public Quantity getQuantity() {
        if (getHypercube() == null) {
            throw new RuntimeException("Fact is not attached to Hypercube");
        }

        if (quantityCache != null && cacheValid) {
            return quantityCache;
        }

        Quantity result = getHypercube().getQuantity(this);
        setQuantity(result);

        return result;
    }

    @Override
    public void setQuantity(Quantity quantity) {
        quantityCache = quantity;
        cacheValid = true;
    }
}
