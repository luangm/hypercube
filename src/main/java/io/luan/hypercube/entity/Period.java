package io.luan.hypercube.entity;

import java.time.LocalDate;

/**
 * 代表了Fact对应的时间。
 * 可以是Instant，也可能是一段时间
 */
public class Period {

    private boolean isDuration;
    private LocalDate instantDate;
    private LocalDate endDate;

    public Period(LocalDate instantDate) {
        this.instantDate = instantDate;
    }

    public Period(LocalDate startDate, LocalDate endDate) {
        this.instantDate = startDate;
        this.endDate = endDate;
        this.isDuration = true;
    }

    public LocalDate getEndDate() {
        if (isDuration) {
            return endDate;
        }
        return null;
    }

    public LocalDate getInstantDate() {
        if (!isDuration) {
            return instantDate;
        }
        return null;
    }

    public boolean getIsDuration() {
        return isDuration;
    }

    public LocalDate getStartDate() {
        if (isDuration) {
            return instantDate;
        }
        return null;
    }
}
