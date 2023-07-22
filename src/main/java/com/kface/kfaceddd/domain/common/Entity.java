package com.kface.kfaceddd.domain.common;

import java.io.Serializable;

public interface Entity<KEY> extends Serializable {

    KEY getIdentify();

}
