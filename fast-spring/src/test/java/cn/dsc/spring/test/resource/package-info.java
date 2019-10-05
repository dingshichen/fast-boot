/**
 * java.net.URL遗憾的是，Java的各种URL前缀的标准类和标准处理程序不足以完全访问低级资源。例如，没有URL可用于访问需要从类路径或相对于a获取的资源的标准化实现 ServletContext。虽然可以为专用URL 前缀注册新的处理程序（类似于现有的前缀处理程序 http:），但这通常非常复杂，并且 URL接口仍然缺少一些理想的功能，例如检查资源是否存在。
 *
 * {@link org.springframework.core.io.Resource} 接口是一个更强大的接口，用于抽象对低级资源的访问。
 * Resource重要的方法：
 *
 * getInputStream()：找到并打开资源，返回InputStream从资源中读取的内容。预计每次调用都会返回一个新的InputStream。调用结束后调用者有责任关闭流。
 *
 * exists()：返回 boolean指示此资源是否实际存在于物理形式的a。
 *
 * isOpen()：返回一个 boolean指示此资源是否表示具有打开流的句柄的a。如果true， InputStream不能多次读取，必须只读一次然后关闭以避免资源泄漏。将false用于所有常规资源实现，但不包括 InputStreamResource。
 *
 * getDescription()：返回此资源的描述，用于处理资源时的错误输出。这通常是完全限定的文件名或资源的实际URL
 *
 *
 * @author dingShiChen
 * @since 2019/8/26
 */
package cn.dsc.spring.test.resource;