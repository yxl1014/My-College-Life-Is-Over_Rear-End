package org.database.mysql.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.database.mysql.domain.task.shell.HttpType;
import org.database.mysql.domain.task.shell.ShellExpect;
import org.database.mysql.domain.task.shell.ShellType;

import java.util.List;
import java.util.Map;

/**
 * {编号，IP，端口，协议类型，url，前置条件，参数头<Key,Value>，参数<Key,Value>，请求体，预计结果(结果格式、字段、目标值)}
 *
 * @author Administrator
 * @Package : org.database.mysql.domain.task
 * @Create on : 2024/3/29 上午9:58
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskShell {
    private Integer shellIndex;
    private String shellIp;
    private Integer shellPort;
    private ShellType shellType;
    private String shellUrl;
    private HttpType shellHttpType;
    private Integer shellPreCondition;
    private Map<String, String> shellHeader;
    private Map<String, String> shellParam;
    private String shellBody;
    private List<ShellExpect> shellExpect;
}
