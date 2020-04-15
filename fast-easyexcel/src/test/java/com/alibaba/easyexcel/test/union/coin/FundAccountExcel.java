package com.alibaba.easyexcel.test.union.coin;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dingshichen
 */
@Slf4j
public class FundAccountExcel {

    public static final String SRC = "/Users/dingshichen/Downloads/无标题.xls";

    public static final String DESC = "/Users/dingshichen/Downloads/目标.sql";

    public static final String INSERT_SQL = "insert into fund_account (id, holder_id, holder_name, holder_remark, holder_type, gmt_created) " +
            "values (%s, '%s', '%s', '%s', 1, '%s');";

    public static final String UPDATE_BALANCE = "update fund_account fa inner join (select fund_account_id, final_balance from fund_record where fund_account_id = %s order by id desc limit 1) fr on fa.id = fr.fund_account_id set fa.balance = fr.final_balance where fa.id = %s;";

    @Test
    public void test(){
        //源文件
        BufferedInputStream bi = null;
        try {
            bi = new BufferedInputStream(new FileInputStream(SRC));
            EasyExcelFactory.readBySax(bi, new Sheet(1, 1), new AnalysisEventListener(){

                List<String> sqls = new ArrayList<>();

                @Override
                public void invoke(Object o, AnalysisContext analysisContext) {
                    ArrayList s = (ArrayList) o;
                    String id = (String) s.get(0);
                    String holdeId = (String) s.get(0);
                    String holderName = (String) s.get(1);
                    String holderRemark = (String) s.get(2);
                    String gmtCreated = (String) s.get(3);
                    String sql1 = String.format(INSERT_SQL, id, holdeId, holderName, holderRemark, gmtCreated);
                    String sql2 = String.format(UPDATE_BALANCE, id, id);
                    sqls.add(sql1);
                    sqls.add(sql2);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    log.info("sql组装完毕");
                    BufferedWriter bw = null;
                    try {
                        bw = new BufferedWriter(new FileWriter(DESC));
                        for (String sql : sqls) {
                            bw.write(sql);
                            bw.newLine();
                        }
                        bw.flush();
                        log.info("sql脚本生成完毕");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (bw != null) {
                            try {
                                bw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bi != null) {
                try {
                    bi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
