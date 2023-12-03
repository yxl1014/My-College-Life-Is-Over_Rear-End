package example.mapper.transaction;

import example.entity.database.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/3 下午6:20
 */
@Mapper
public interface TransactionMapper {
    /**
     * description:
     * @paramType [example.entity.database.Transaction]
     * @param transaction:
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-12-03 下午6:23
     */
    public int insert(Transaction transaction);
    /**
     * description:
     * @paramType [example.entity.database.Transaction]
     * @param transaction:
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-12-03 下午6:23
     */
    public int delete(Transaction transaction);
    /**
     * description:
     * @paramType [example.entity.database.Transaction]
     * @param transaction:
     * @returnType: int
     * @author: GodHammer
     * @date: 2023-12-03 下午6:24
     */
    public int update(Transaction transaction);
    /**
     * description:
     * @paramType [example.entity.database.Transaction]
     * @param transaction:
     * @returnType: java.util.List<example.entity.database.Transaction>
     * @author: GodHammer
     * @date: 2023-12-03 下午6:24
     */
    public List<Transaction> selectRecordsOfOneSender(Transaction transaction);

    /**
     * description:
     * @paramType [example.entity.database.Transaction]
     * @param transaction:
     * @returnType: java.util.List<example.entity.database.Transaction>
     * @author: GodHammer
     * @date: 2023-12-03 下午6:24
     */
    public List<Transaction> selectAll(Transaction transaction);
}
