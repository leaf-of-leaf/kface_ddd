package com.kface.kfaceddd.infrastructure.dao.id;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;

@TableName("lx_leaf_segment")
public class LeafSegmentPO {

    @TableField("biz_tag")
    private String bizTag;

    @TableField("max_id")
    private Long maxId;

    @TableField("step")
    private Integer step;

    @TableField("desc")
    private String description;

    @TableField("update_time")
    private LocalDateTime updateTime;

    // Getters and setters
    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

