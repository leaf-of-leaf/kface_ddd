package com.kface.kfaceddd.common;

import org.javers.core.diff.custom.CustomValueComparator;

import java.util.Date;
import java.util.Objects;

public class DateComparator implements CustomValueComparator<Date> {

    @Override
    public boolean equals(Date a, Date b) {
        if (Objects.isNull(a) || Objects.isNull(b)) return false;
        return a.getTime() == b.getTime();
    }

    @Override
    public String toString(Date value) {
        return Objects.nonNull(value) ? value.toString() : "null";
    }

    @Override
    public boolean handlesNulls() {
        return CustomValueComparator.super.handlesNulls();
    }
}
