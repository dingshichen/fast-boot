package cn.dsc.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author dingshichen
 */
public class TestParser {

    public static final String FILE_PATH = "/Users/dingshichen/CodeProject/IdeaProjects/fast/fast-boot/fast-parser/src/main/java/cn/dsc/parser/control/UserController.java";

    public static final String METHOD_NAME = "getByName";

    @SneakyThrows
    @Test
    public void test() {
        CompilationUnit compilationUnit = StaticJavaParser.parse(new File(FILE_PATH));
        ClassOrInterfaceDeclaration controller = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(RuntimeException::new);
        //类名
        String controllerName = getClassSingleName(controller);
        System.out.println("类名 : " + controllerName);

        //全限定名
        String controllerFullName = getClassFullName(controller);
        System.out.println("类全限定名 : " + controllerFullName);

        //接口父路径
        String parentPath = getParentPath(controller);
        System.out.println("类接口访问父路径 : " + parentPath);

        //获取类作者
        String author = getAuthor(controller);
        System.out.println("作者 : " + author);

        //获取类描述
        String description = getDescription(controller);
        System.out.println("类描述 : " + description);

        //获取接口方法
        MethodDeclaration method = getMethod(controller, METHOD_NAME);

        //获取接口路径
        String methodPath = getMethodPath(method);
        System.out.println("接口路径 : " + methodPath);

        //获取接口请求方式
        String methodWay = getMethodWay(method);
        System.out.println("接口请求方式 : " + methodWay);

        //最终接口路径
        String realPath = getRealPath(parentPath, methodPath);
        System.out.println("最终接口路径 : " + realPath);

        //获取接口作者
        String methodAuthor = getAuthor(method);
        System.out.println("接口作者 : " + methodAuthor);

        //获取接口描述
        String methodDescription = getDescription(method);
        System.out.println("接口描述 : " + methodDescription);

        //获取接口入参
        Parameter parameter = getParameter(method);

        System.out.println("-- 入参部分 --");
        processParameter(parameter.getTypeAsString(), FILE_PATH, compilationUnit);

        //获取返回值
        ClassOrInterfaceType returnType = getReturn(method);

        processReturn(returnType, FILE_PATH, compilationUnit);
    }

    private void processReturn(ClassOrInterfaceType returnType, String filePath, CompilationUnit compilationUnit) {
        System.out.println("-- 返回值部分 --");
        Optional<NodeList<Type>> typeArguments = returnType.getTypeArguments();
        NodeList<Type> types = typeArguments.orElseThrow(RuntimeException::new);
        ClassOrInterfaceType arg = types.get(0).asClassOrInterfaceType();
        String classSingleName = getClassSingleName(arg);
        if (Objects.equals("PagingBody", classSingleName)) {
            NodeList<Type> nodes = arg.getTypeArguments().orElseThrow(RuntimeException::new);
            ClassOrInterfaceType node = nodes.get(0).asClassOrInterfaceType();
            classSingleName = getClassSingleName(node);
        }
        processParameter(classSingleName, filePath, compilationUnit);
    }

    /**
     * 获取返回值
     * @param method
     * @return
     */
    private ClassOrInterfaceType getReturn(MethodDeclaration method) {
        return method.getType().asClassOrInterfaceType();
    }

    /**
     * 入参字段不需要递归的
     */
    private static final List<String> BASE_JAVA_TYPES = Lists.newArrayList("Boolean", "Integer", "Long",
            "Double", "Float", "Byte", "Short", "Character", "String", "BigDecimal", "List<Boolean>", "List<Integer>",
            "List<Long>", "List<Double>", "List<Float>", "List<Byte>", "List<Short>", "List<Character>", "List<String>",
            "List<BigDecimal>");

    /**
     * 不符合入参字段类型规范的
     */
    private static final List<String> LOW_BASE_JAVA_TYPES = Lists.newArrayList("int", "boolean", "long",
            "double", "float", "byte");

    /**
     * 处理入参
     * @param typeSingleName 字段类型类简名
     * @param parentFilePath 所在文件的绝对路径
     * @param compilationUnit 上级 CompilationUnit
     */
    @SneakyThrows
    private void processParameter(String typeSingleName, String parentFilePath, CompilationUnit compilationUnit) {
        //找到入参的那个导包
        Optional<ImportDeclaration> importDeclarationOptional = compilationUnit.getImports()
                .stream()
                .filter(e -> e.getNameAsString().endsWith(typeSingleName))
                .findFirst();
        //获取参数 class 文件
        File file = getParameterFile(typeSingleName, parentFilePath, importDeclarationOptional);
        CompilationUnit parameterUnit = StaticJavaParser.parse(file);
        ClassOrInterfaceDeclaration cmd = parameterUnit.findFirst(ClassOrInterfaceDeclaration.class)
                .orElseThrow(RuntimeException::new);

        cmd.getMembers().forEach(e -> {
            FieldDeclaration fieldDeclaration = e.asFieldDeclaration();
            //参数字段类型
            String type = getFieldType(fieldDeclaration);
            System.out.println("参数字段类型 : " + type);

            //参数字段名
            String fieldName = getFieldName(fieldDeclaration);
            System.out.println("参数字段名 : " + fieldName);

            //参数必要性
            String required = getRequired(fieldDeclaration);
            System.out.println("参数的必要性 : " + required);

            //字段默认值
            String defaultValue = getDefaultValue(fieldDeclaration);
            System.out.println("字段默认值 : " + defaultValue);

            //字段要求
            String important = getImportant(fieldDeclaration);
            System.out.println("字段要求 : " + important);

            //字段描述
            String detail = getDetail(fieldDeclaration);
            System.out.println("字段描述 : " + detail);
        });

        //需要递归
        List<FieldDeclaration> customizedJavaTypes = cmd.getMembers().stream()
                .filter(this::needRecursion)
                .map(BodyDeclaration::asFieldDeclaration)
                .collect(Collectors.toList());

        //递归
        customizedJavaTypes.forEach(e -> {
            String fieldType = getFieldType(e);
            //如果是集合类型需要处理集合
            if (fieldType.startsWith("List<")) {
                processParameter(fieldType.substring(5, fieldType.indexOf(">")), file.getPath(), parameterUnit);
            } else {
                processParameter(getFieldType(e), file.getPath(), parameterUnit);
            }
        });
    }

    /**
     * 是否需要递归的入参字段
     * @param bodyDeclaration
     * @return
     */
    private boolean needRecursion(BodyDeclaration<?> bodyDeclaration) {
        FieldDeclaration fieldDeclaration = bodyDeclaration.asFieldDeclaration();
        String type = getFieldType(fieldDeclaration);
        return !BASE_JAVA_TYPES.contains(type);
    }

    /**
     * 获取字段类型
     * @param fieldDeclaration
     * @return
     */
    private String getFieldType(FieldDeclaration fieldDeclaration) {
        String type = fieldDeclaration.getVariable(0).getType().asString();
        Assert.isTrue(!LOW_BASE_JAVA_TYPES.contains(type), type + " 不符合入参字段类型规范");
        return type;
    }

    /**
     * 字段描述
     * @param fieldDeclaration
     * @return
     */
    private String getDetail(FieldDeclaration fieldDeclaration) {
        String content = fieldDeclaration.getComment()
                .orElseThrow(() -> new RuntimeException("参数缺少注释"))
                .asJavadocComment()
                .getContent();
        Assert.isTrue(content.contains("\n     * "), "入参字段注释格式不正确，请格式化他");
        String substring = content.substring(8);
        return substring.substring(0, substring.indexOf("\n"));
    }

    /**
     * 入参要求
     * @param fieldDeclaration
     * @return
     */
    private String getImportant(FieldDeclaration fieldDeclaration) {
        JavadocComment javadocComment = fieldDeclaration.getComment()
                .orElseThrow(() -> new RuntimeException("参数缺少注释"))
                .asJavadocComment();
        return getCommentValue(javadocComment, "@description");
    }

    /**
     * 获取字段默认值
     * @param fieldDeclaration
     * @return
     */
    private String getDefaultValue(FieldDeclaration fieldDeclaration) {
        VariableDeclarator variable = fieldDeclaration.getVariable(0);
        if (variable.getInitializer().isPresent()) {
            return variable.getInitializer().get().toString();
        }
        return "";
    }

    /**
     * 参数字段名
     * @param fieldDeclaration
     * @return
     */
    private String getFieldName(FieldDeclaration fieldDeclaration) {
        VariableDeclarator variable = fieldDeclaration.getVariable(0);
        return variable.getNameAsString();
    }

    /**
     * 参数的必要性
     * @param fieldDeclaration
     * @return
     */
    private String getRequired(FieldDeclaration fieldDeclaration) {
        Optional<AnnotationExpr> optional1 = fieldDeclaration.getAnnotationByName("NotEmpty");
        if (optional1.isPresent()) {
            return "是";
        }
        Optional<AnnotationExpr> optional2 = fieldDeclaration.getAnnotationByName("NotNull");
        if (optional2.isPresent()) {
            return "是";
        }
        return "";
    }

    /**
     * 获取入参 file
     *
     * @param typeSingleName
     * @param parentFilePath
     * @param importDeclarationOptional
     * @return
     */
    private File getParameterFile(String typeSingleName, String parentFilePath, Optional<ImportDeclaration> importDeclarationOptional) {
        if (importDeclarationOptional.isPresent()) {
            String nameAsString = importDeclarationOptional.get().getNameAsString();
            String replace = nameAsString.replace(".", "/");
            int i = parentFilePath.indexOf("/src/main/java/");
            return new File(parentFilePath.substring(0, i + 15) + replace + ".java");
        } else {
            int i = parentFilePath.lastIndexOf("/");
            return new File(parentFilePath.substring(0, i + 1) + typeSingleName + ".java");
        }
    }

    /**
     * 获取接口入参
     * @param method
     * @return
     */
    private Parameter getParameter(MethodDeclaration method) {
        NodeList<Parameter> parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            Optional<AnnotationExpr> optional = parameter.getAnnotationByName("RequestBody");
            if (optional.isPresent()) {
                return parameter;
            }
        }
        throw new RuntimeException("目前只支持入参在 requestBody 中的解口");
    }

    /**
     * 获取接口请求方式
     * <p>暂只做 POST 请求</p>
     * @param method
     * @return
     */
    private String getMethodWay(MethodDeclaration method) {
        return "POST";
    }

    /**
     * 最终接口路径
     * @param parentPath
     * @param methodPath
     * @return
     */
    private String getRealPath(String parentPath, String methodPath) {
        if (StringUtils.isEmpty(parentPath)) {
            return trimPrex(methodPath);
        }
        return trimPrex(parentPath) + trimPrex(methodPath);
    }

    /**
     * 处理路径分隔符
     * @param path
     * @return
     */
    private String trimPrex(String path) {
        if (path.startsWith("/")) {
            return path;
        }
        return "/" + path;
    }

    /**
     * 获取接口路径
     * @param method
     * @return
     */
    private String getMethodPath(MethodDeclaration method) {
        Optional<AnnotationExpr> optional = method.getAnnotationByName("PostMapping");
        AnnotationExpr annotationExpr = optional.orElseThrow(() -> new RuntimeException("目前只支持 @PostMapping 注解的接口，后续正在计划"));
        return getAnnotationValue(annotationExpr);
    }

    /**
     * 获取接口方法
     * @param controller
     * @param methodName
     * @return
     */
    private MethodDeclaration getMethod(ClassOrInterfaceDeclaration controller, String methodName) {
        List<MethodDeclaration> methods = controller.getMethodsByName(methodName);
        Assert.isTrue(methods.size() == 1, "需要生成接口的 method 名称有问题！我们约定 controller 里的 rest api 方法名是不重复的。");
        return methods.get(0);
    }


    /**
     * 获取描述
     * @param nodeWithJavadoc
     * @return
     */
    private String getDescription(NodeWithJavadoc<?> nodeWithJavadoc) {
        JavadocComment javadocComment = nodeWithJavadoc.getJavadocComment()
                .orElseThrow(RuntimeException::new);
        return getCommentValue(javadocComment, "@description");
    }

    /**
     * 获取作者
     * @param nodeWithJavadoc
     * @return
     */
    private String getAuthor(NodeWithJavadoc<?> nodeWithJavadoc) {
        JavadocComment javadocComment = nodeWithJavadoc.getJavadocComment()
                .orElseThrow(RuntimeException::new);
        return getCommentValue(javadocComment, "@author");
    }

    /**
     * 获取指定注释的值
     * @param javadocComment
     * @param commentName
     * @return
     */
    private String getCommentValue(JavadocComment javadocComment, String commentName) {
        String content = javadocComment.getContent();
        int authorIndex = content.indexOf(commentName);
        if (authorIndex < 0) {
            return "";
        }
        String substring = content.substring(authorIndex + commentName.length() + 1);
        return substring.substring(0, substring.indexOf("\n"));
    }

    /**
     * 获取接口父路径
     * @param controller
     * @return
     */
    private String getParentPath(ClassOrInterfaceDeclaration controller) {
        Optional<AnnotationExpr> optionalAnnotationExpr = controller.getAnnotationByName("RequestMapping");
        return optionalAnnotationExpr.map(this::getAnnotationValue).orElse(null);
    }

    /**
     * 获取类简称
     * @param nodeWithSimpleName
     * @return
     */
    private String getClassSingleName(NodeWithSimpleName<?> nodeWithSimpleName) {
        return nodeWithSimpleName.getNameAsString();
    }

    /**
     * 获取类全限定名
     * @param classOrInterfaceDeclaration
     * @return
     */
    private String getClassFullName(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        return classOrInterfaceDeclaration.getFullyQualifiedName()
                .orElseThrow(() -> new RuntimeException("获取不到正确的类全限定名"));
    }

    /**
     * 获取注解的值
     * @param annotationExpr
     * @return
     */
    private String getAnnotationValue(AnnotationExpr annotationExpr) {
        SingleMemberAnnotationExpr singleMemberAnnotationExpr = annotationExpr.asSingleMemberAnnotationExpr();
        StringLiteralExpr stringLiteralExpr = singleMemberAnnotationExpr.getMemberValue().asStringLiteralExpr();
        return stringLiteralExpr.asString();
    }
}
