package cn.dsc.ss;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import org.junit.Test;

/**
 * 摘要
 * @author dingshichen
 */
public class HutoolDigester {

    /**
     * MD5 摘要
     */
    @Test
    public void md5() {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String content = "重要的事情说三遍";
        String sign = md5.digestHex(content);
        System.out.println("使用 md5 摘要算法后得到的固定长度字符串：" + sign);
    }

    /**
     * SHA256 摘要
     */
    @Test
    public void sha1() {
        String content = "光天化日";
        String sign = DigestUtil.sha256Hex(content);
        System.out.println("使用 sha256 算法后得到的固定长度字符串：" + sign);
    }
}
