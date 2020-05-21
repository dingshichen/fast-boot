package com.alibaba.easyexcel.test.union.coin;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dingshichen
 */
public class FundRecordDetailExcel {

    public static final String SRC = "/Users/dingshichen/Downloads/fund_record_detail.xlsx";

    @Test
    public void test() throws FileNotFoundException {
        List<Integer> ids = new ArrayList<>();

        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(SRC));
        EasyExcelFactory.readBySax(bi, new Sheet(1, 0), new AnalysisEventListener<List<String>>(){

            @Override
            public void invoke(List<String> o, AnalysisContext analysisContext) {
                ids.add(Integer.parseInt(o.get(0)));
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("读取完毕");
            }
        });


        check(ids);
    }

    private void check(List<Integer> ids) {
        for (int i = 1; i < ids.size(); i++) {
            int beforeId = ids.get(i - 1);
            int current = ids.get(i);
            int count = current - beforeId;
            if (count != 1) {
                for (int j = 1; j < count; j++) {
                    System.out.println("缺少id : " + (beforeId + j));
                }
            }
        }
    }


}
