package com.example.springbatchdemo;

import java.util.ArrayList;
import java.util.List;


/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: spring-batch-demo
 * @Package: com.example.springbatchdemo
 * @ClassName: Metaspace
 * @Description: []
 * @Author: [xf huang]
 * @Date: 9/27/2021 11:42 AM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
public class Metaspace extends ClassLoader {

    //public static List<Class<?>> createClasses() {
    //    // 类持有
    //    List<Class<?>> classes = new ArrayList<Class<?>>();
    //    // 循环1000w次生成1000w个不同的类。
    //    for (int i = 0; i < 10000000; ++i) {
    //        ClassWriter cw = new ClassWriter(0);
    //        // 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
    //        cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null,
    //            "java/lang/Object", null);
    //        // 定义构造函数<init>方法
    //        MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
    //            "()V", null, null);
    //        // 第一个指令为加载this
    //        mw.visitVarInsn(Opcodes.ALOAD, 0);
    //        // 第二个指令为调用父类Object的构造函数
    //        mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
    //            "<init>", "()V");
    //        // 第三条指令为return
    //        mw.visitInsn(Opcodes.RETURN);
    //        mw.visitMaxs(1, 1);
    //        mw.visitEnd();
    //        Metaspace test = new Metaspace();
    //        byte[] code = cw.toByteArray();
    //        // 定义类
    //        Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
    //        classes.add(exampleClass);
    //    }
    //    return classes;
    //}
}
