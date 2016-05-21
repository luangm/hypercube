package io.luan.hypercube.entity;

import java.math.BigDecimal;

/**
 * 代表了一个值，包含Value, Unit.
 * 不可变
 */
public class Quantity {

    private BigDecimal value;
    private String unit;

    private Quantity() {
        // Disable
    }

    public Quantity(double value, String unit) {
        this(BigDecimal.valueOf(value), unit);
    }

    public Quantity(long value, String unit) {
        this(BigDecimal.valueOf(value), unit);
    }

    public Quantity(int value, String unit) {
        this(BigDecimal.valueOf(value), unit);
    }

    public Quantity(BigDecimal value, String unit) {
        setValue(value);
        setUnit(unit);
    }

    public String getUnit() {
        return unit;
    }

    private void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    private void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getValue() + " " + getUnit();
    }
}
