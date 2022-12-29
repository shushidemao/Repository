package com.wy.springcloud.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class ECNTokenDTO implements Serializable {

    private String userName;

    private String systemID;

    private String tag;

    private Long timeStamp;

    private String sign;

    private String generatetime;

    private String accesstoken;

    private String expiretime;
}
