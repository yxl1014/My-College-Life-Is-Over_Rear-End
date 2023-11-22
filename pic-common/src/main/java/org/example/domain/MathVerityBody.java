package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl17
 * @Package : org.example.domain
 * @Create on : 2023/11/12 12:55
 **/


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MathVerityBody {
    private String uuid;
    private String base64Image;
}
