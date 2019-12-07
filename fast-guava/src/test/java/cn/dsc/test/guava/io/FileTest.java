package cn.dsc.test.guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * 文件操作工具类
 * @author dingshichen
 */
public class FileTest {

    /**
     * 读取文件
     */
    @Test
    public void read() throws FileNotFoundException {
        File file = new File("/Users/dingshichen/Downloads/test.txt");
        BufferedReader bufferedReader = Files.newReader(file, Charsets.UTF_8);
    }

    /**
     * 按行读取
     * @throws IOException
     */
    @Test
    public void readLine() throws IOException {
        File file = new File("/Users/dingshichen/Downloads/test.txt");
        List<String> strings = Files.readLines(file, Charsets.UTF_8);
        System.out.println(strings);
    }

    /**
     * 复制
     */
    @Test
    public void copy() throws IOException {
        File file = new File("/Users/dingshichen/Downloads/test.txt");
        Files.copy(file, new File("/Users/dingshichen/Downloads/test2.txt"));
    }

    /*
     * Files.deleteDirectoryContents(File directory); //删除文件夹下的内容(包括文件与子文件夹)
     * Files.deleteRecursively(File file); //删除文件或者文件夹
     * Files.move(File from, File to); //移动文件
     */

}
