package org.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl17
 * @Package : org.mysql.domain
 * @Create on : 2023/12/17 14:08
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Power {
    private Integer powerId;
    private String powerName;
    private Short powerType;
    private Timestamp powerCreateTime;
    private String powerNotes;
}
