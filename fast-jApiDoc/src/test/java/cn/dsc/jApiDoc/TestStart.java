package cn.dsc.jApiDoc;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

/**
 * @author dingshichen
 */
public class TestStart {

    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("/Users/dingshichen/CodeProject/IdeaProjects/fast/fast-boot/fast-jApiDoc/src"); // 项目根目录
        config.setProjectName("fast-jApiDoc"); // 项目名称
        config.setApiVersion("V1.01");       // 声明该API的版本
        config.setDocsPath("docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        config.addPlugin(new MarkdownDocPlugin());
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
