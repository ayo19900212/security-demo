package com.example.demo.dal.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "用户信息")
@Data
public class User {

    /**
     * 主键
     */
    @Schema(description = "用户id", name = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名", name = "username")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", name = "password")
    private String password;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码", name = "phone")
    private String phone;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;
}
