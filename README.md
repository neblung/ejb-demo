# EJB-Demo

This repository demonstrates how EJBs can be shared between webapps. We have 3 projects:
- __api__ java-project that merely provides the bean's [interface](api/src/main/java/api/MyBean.java)
- __webapp1__ war-project that provides an [implementation](webapp1/src/main/java/webapp1/impl/MyBeanImpl.java) of the bean
- __webapp2__ war-project that [uses](webapp2/src/main/java/webapp2/rest/HelloResource.java) the bean

## How does it work?

The implementation class `MyBeanImpl` defines the EJB by means of the `@Stateless`-Annotation
```java
@Stateless(name = "MyBean")
public class MyBeanImpl implements MyBean {
```
You can see the bean in action at http://localhost:8080/webapp1/rest/hello

The bean gets injected into webapp2's resource through the `EJB`-Annotation
```java
public class HelloResource {
    @EJB(lookup = "java:global/webapp1/MyBean!api.MyBean")
    private MyBean bean;
```

You can see the shared bean in action at http://localhost:8080/webapp2/rest/hello

In order to make webapp2 _find_ the Bean, you have to place two resource files in `src/main/resources/META-INF/`
- [`beans.xml`](webapp2/src/main/resources/META-INF/beans.xml) an (at least) empty bean descriptor
- [`MANIFEST.MF`](webapp2/src/main/resources/META-INF/MANIFEST.MF) that declares the dependency to webapp1 (wildfly-syntax)

## How to build and run?
If you have docker installed, simply execute:
```bash
./build-and-run.sh
```
