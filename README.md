# EJB-Demo

This repository demonstrates how EJBs can be shared between webapps. We have 3 projects:
- __api__ java-project that merely provides the interface `MyBean` 
- __webapp1__ war-project that provides an implementation of the bean
- __webapp2__ war-project that uses the bean

## How does it work?

The implementation class `MyBeanImpl` defines the EJB by means of the `@Stateless`-Annotation
```java
@Stateless(name = "MyBean")
public class MyBeanImpl implements MyBean {
```
You can see the bean in action at http://localhost:8080/webapp1/rest/hello

webapp2's Resource gets the bean injected through the `EJB`-Annotation
```java
public class HelloResource {
    @EJB(lookup = "java:global/webapp1/MyBean!api.MyBean")
    private MyBean bean;
```

In order to work, you have to place two resource files in webapp2's `META-INF`
- `beans.xml` an (at least) empty bean descriptor
- `MANIFEST.MF` that declares the dependency to webapp1 (wildfly-syntax)