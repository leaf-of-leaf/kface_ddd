package com.kface.kfaceddd.application.cqrs.cmd;

import java.io.Serializable;

public class OrderAndLessonInfoCmd implements Serializable {

    private Long lessonId;

    private String orderNo;

    private String title;

}
