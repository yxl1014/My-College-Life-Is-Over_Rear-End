使用方法：
将这个包引导其他组件当中
将MysqlComp通过 @Autowired 注入到spring的Bean当中，使用即可；

这个Component提供了 selectOne selectList insert delete update方法;这些提供的方法只需要你传入MysqlBuilder即可自动解析并且操作数据库。
MysqlBuilder:
optType : MysqlOptType 类型的一个枚举 表示操作的类型 如 CRUD
resultType ： MysqlResultType 类型的一个枚举 只有查询的时候才需要确定这个类型，其他皆为NULL
clz : 操作数据库对应的实体类 （！！！注意：数据库列名与java类名需要完全标准且一一对应） 
详情在 MysqlCommonUtil.javaFieldName2MysqlColumnsName 读代码去吧
in : clz的实例化对象 在insert操作中为插入数据库的对象   在其他操作中为 判断的条件，哪个字段有数据 那么就对应数据库当中 那一列 = 这个值
out : clz的实例化对象 只有select 操作有用，因为只有这个有返回值，为了 指定返回哪些列，若想返回这一列，那么只要为这个字段随便set一个值就行
update : clz的实例化对象 只有在update操作有用 在in 判断条件之后将update中 有值的字段修改

目前只实现了常规的 =插叙 一些其他的 大于小于范围查询没有做，我将mapper接口暴露出去了，可以自己写wrapper来通过mapper操作。
！！！但是要注意的是： 只要这个对数据库的查询使用的地方超过两次了，那么 就将你的这个wrapper 封装到MysqlComp当中 ！！！
doOpt方法我也暴露出去了， 可以自定义MysqlBuilder来直接传入查询 但是没啥必要
