package com.asia.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Xavier.liu
 * @date 2020/6/3 15:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserXavier implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private Integer sex;
}
