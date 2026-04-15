# Data Structures in Java

一个用 Java 手动实现常见数据结构与经典题目的学习仓库。

## 项目目标
- 手写实现常见数据结构
- 理解底层原理和复杂度
- 配套测试保证正确性
- 沉淀 LeetCode/实战案例
- 按专题输出教程文档

## 目录
- [数组](src/main/java/datastructures/array/docs/array.md)
- [链表](docs/02-linked-list.md)
- [栈](docs/03-stack.md)
- [队列](docs/04-queue.md)
- [哈希表](docs/05-hash-table.md)
- [树](docs/06-tree.md)
- [图](docs/07-graph.md)

## 已完成内容
- [ ] 数组
- [ ] 链表
- [ ] 栈
- [ ] 队列
- [ ] 哈希表
- [ ] 树
- [ ] 图

## 运行测试
```bash
mvn test

---

## Maven 项目最小配置

你用 Maven 最省心，直接放一个最小版 `pom.xml`。

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yourname</groupId>
    <artifactId>data-structures-java</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.10.2</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>
        </plugins>
    </build>
</project>