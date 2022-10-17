package com.Group3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("nd_news")
public class NdNotification {

    @TableId
    private Long nid;
    private Long pid;
    private String content;
    private Long prescribeId;
    private Long gid;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    public NdNotification(Long pid, String content) {
        this.pid = pid;
        this.content = content;
    }

    public NdNotification(Long pid, String content, Date createTime) {
        this.pid = pid;
        this.content = content;
        this.createTime = createTime;
    }
}
