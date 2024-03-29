package org.database.mysql.domain.task.shell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @Package : org.database.mysql.domain.task.shell
 * @Create on : 2024/3/29 上午10:05
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShellExpect {
    private ExpectType expectType;
    private String expectValue;
    private String expectTarget;
}
