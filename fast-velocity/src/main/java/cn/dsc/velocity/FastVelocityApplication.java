package cn.dsc.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dingshichen
 */
public class FastVelocityApplication {

    public static void main(String[] args) throws IOException {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
        ve.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
        ve.init();
        // 获取模板文件
        Template t = ve.getTemplate("template.vm");
        // 设置变量
        VelocityContext ctx = new VelocityContext();
        ctx.put("apiName", "根据业务单号和业务类型查询资金余额变更流水");
        ctx.put("apiDesc", "根据业务单号和业务类型查询资金余额变更流水");
        ctx.put("apiPath", "/fundRecord/getByTranslation");
        ctx.put("cmdClass", "cn.uniondrug.asset.dto.command.FundRecordGetTransCmd");
        ctx.put("coClass", "cn.uniondrug.asset.dto.clientobject.FundRecordCO");
        ctx.put("apiClass", "cn.uniondrug.asset.control.FundRecordController");
        List<Param> list = new ArrayList<>();

        Param param1 = new Param();
        param1.setRequired("是");
        param1.setType("String");
        param1.setName("name");
        param1.setValue("");
        param1.setImportant("必须是中文名");
        param1.setDesc("姓名");

        Param param2 = new Param();
        param2.setRequired("");
        param2.setType("Integer");
        param2.setName("age");
        param2.setValue("18");
        param2.setImportant("");
        param2.setDesc("年龄");

        list.add(param1);
        list.add(param2);

        ctx.put("params", list);
        // 输出
        PrintWriter writer = new PrintWriter("/Users/dingshichen/IdeaProjects/fast/fast-boot/fast-velocity/src/main/resources/create.md");
        t.merge(ctx, writer);
        writer.flush();
        writer.close();
    }
}
