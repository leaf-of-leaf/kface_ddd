package com.kface.kfaceddd.common.utils;

import com.kface.kfaceddd.common.DateComparator;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.BigDecimalComparatorWithFixedEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ObjectTrackingUtil {

    private static final Javers javers;

    static {
        javers = JaversBuilder.javers()
                .registerValue(BigDecimal.class, new BigDecimalComparatorWithFixedEquals())
                .registerValue(Date.class, new DateComparator())
                .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
                .build();
    }

    public static <T> Diff compare(T oldObj, T newObj) {
        return javers.compare(oldObj, newObj);
    }

    /**
     * 只保留值变动,对象新增或者修改
     * 初始化与销毁的变动不记录
     * @param diff
     * @return
     */
    public static List<Change> getCommonChange(Diff diff) {
        if (Objects.isNull(diff)) {
            return Collections.emptyList();
        }
        return diff.getChanges(change -> (change.getClass().equals(ValueChange.class)) || change instanceof ObjectRemoved || change instanceof NewObject);
    }

}
