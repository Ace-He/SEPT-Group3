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
public class NdNews {
    @TableId
    private Long nid;
    private Long pid;
    private String content;
    private Long prescribeId;
    private Long gid;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    public NdNews(Long pid, String content) {
        this.pid = pid;
        this.content = content;
    }

    public NdNews(Long pid, String content, Date createTime) {
        this.pid = pid;
        this.content = content;
        this.createTime = createTime;
    }
}
