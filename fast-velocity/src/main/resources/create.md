# 根据业务单号和业务类型查询资金余额变更流水
> 根据业务单号和业务类型查询资金余额变更流水

* **接口** : `POST /fundRecord/getByTranslation`
* **入参** : `cn.uniondrug.asset.dto.command.FundRecordGetTransCmd`
* **出参** : `cn.uniondrug.common.domain.Result<cn.uniondrug.asset.dto.clientobject.FundRecordCO>`
* **文件** : `cn.uniondrug.asset.control.FundRecordController`


> cn.uniondrug.asset.dto.command.FundRecordGetTransCmd

| 必要性 | 类型 | 字段名 | 默认值 | 入参要求 | 描述 |
| :-- | :-- | :-- | :-- | :-- | :-- |
| 是 | String | name |  | 必须是中文名 | 姓名 |
|  | Integer | age | 18 |  | 年龄 |
