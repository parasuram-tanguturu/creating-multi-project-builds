[Creating Multi-project Builds](https://guides.gradle.org/creating-multi-project-builds/)

# **Create a root project**
```
$ mkdir creating-multi-project-builds
$ cd creating-multi-project-builds
$ gradle init
```
1. configure repository for `allprojects`
2. set the version for each of the modules via the `subproject` block

# **Add a Groovy library subproject**
```
$ mkdir greeting-library
```
1. Create a build script in the greeting-library
2. add `greeting-library` to root project
3. ---write code & test case --

# **Add a Java application sub-project**
```
mkdir greeter
```
1. Create a build script in the `greeter`
2. add `greeter` to root project
3. Use `mainClassName` to set the entry point. (The assigned class must have a standard main method)
4. ---write code (use `greeting-library` functionality in it )& test case --
5.  ~~as pof now compile error will be there as dependency not define~~
6.  link the artifacts of one subproject `greeter` to the dependencies of another sub-project `greeting-library` 
... 
    1. Use the `project(NAME)` syntax to add the artifacts of one subproject to the dependencies of another subproject.
    2. Run the build again, which should now succeed. (`gradlew :greeter:test`)

**It is possible to run any task in any sub-project from the top (or any other subproject), by using the format `:SUBPROJECT:TASK `as the task name.**

# **Add documentation as sub-project**
1.Start by adding the `Asciidoctor` plugin to a `plugins` block to the top of the **root project build script**.but don't apply.
...
  1. The use of `apply false` adds the plugin to the overall project, but does not add it to the root project.
  
```
mkdir docs
```
1. Create a build script in the `docs`
...
   1. Apply the `Asciidoctor` plugin to this subproject. This technique lets you selectively apply plugins to sub-projects while defining all of the plugins in the root project.
   2. Tell the plugin to look for a document called `greeter.adoc` in the default source folder `src/docs/asciidoc`
   3. Adds `asciidoctor` task into the build lifecycle so that if build is executed for the top-level project, then documentation will be built as well.
2. add `docs` to root project
3. Add a document called `greeter.adoc` in the Asciidoctor source folder, `src/docs/asciidoc`
...
   1.  The documentation artifact will appear in `docs/build/asciidoc/html5`.
 
 ---
 
#  **Include the documentation in the distribution archive**
1. Add the generated documentation to the distribution.
...
    1. distZip 
    2. distTar 
    3. Use `project(:NAME)` to reference another project and its tasks.
    4. If you unpack one of the archives (greeter-1.0.zip or greeter-1.0.tar in the greeter/build/distributions directory) you will see the documentation included in the html5 folder.

---
# **Refactor common build script code**
1.  At this point you might have noticed that you have common script code in both greeting-library and greeter sub-projects build scripts.
2. A key feature of Gradle is the ability to place such common build script code in the root project.

```
gradlew clean build
```