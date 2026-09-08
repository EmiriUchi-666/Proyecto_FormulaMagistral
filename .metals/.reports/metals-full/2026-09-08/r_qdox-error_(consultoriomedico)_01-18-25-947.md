error id: file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/config/SecurityConfig.java
file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/config/SecurityConfig.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[9,1]

error in qdox parser
file content:
```java
offset: 385
uri: file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/config/SecurityConfig.java
text:
```scala
package com.example.consultoriomedico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

<@@<<<<<< HEAD
/**
 * Seguridad combinada del proyecto:
 *
 *  - La API REST (/proveedores, /materias-primas, /ventas, /reportes,
 *    etc.) queda SIN autenticación, porque el entregable del curso se
 *    prueba con Postman directamente.
 *  - El frontend (Thymeleaf, todo bajo /app/**) SÍ pide login real
 *    contra la tabla "usuario" (ver CustomUserDetailsService).
 *
 * Si más adelante el entregable exige proteger también la API,
 * basta con quitar sus rutas de permitAll() aquí.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

=======
@Configuration
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
<<<<<<< HEAD
          .csrf(csrf -> csrf.ignoringRequestMatchers(
              // La API REST no usa formularios HTML, así que no necesita token CSRF.
              "/proveedores/**", "/unidades-medida/**", "/materias-primas/**",
              "/lotes-materia-prima/**", "/movimientos-inventario/**",
              "/ventas/**", "/ventas-detalle/**", "/reportes/**",
              "/pacientes/**", "/recetas/**", "/citas/**", "/roles/**", "/usuarios/**",
              "/categorias-formula/**", "/formulas/**", "/formula-ingredientes/**"
          ))
          .authorizeHttpRequests(auth -> auth
              // Recursos estáticos y login del frontend: libres.
              .requestMatchers("/css/**", "/js/**", "/app/login").permitAll()
              // Toda la API REST: libre (se prueba con Postman).
              .requestMatchers(
                  "/proveedores/**", "/unidades-medida/**", "/materias-primas/**",
                  "/lotes-materia-prima/**", "/movimientos-inventario/**",
                  "/ventas/**", "/ventas-detalle/**", "/reportes/**",
                  "/pacientes/**", "/recetas/**", "/citas/**", "/roles/**", "/usuarios/**",
                  "/categorias-formula/**", "/formulas/**", "/formula-ingredientes/**"
              ).permitAll()
              // El resto del frontend (todo lo que cuelga de /app/**) sí requiere login.
              .requestMatchers("/app/**").authenticated()
              .anyRequest().permitAll()
          )
          .formLogin(form -> form
              .loginPage("/app/login")
              .loginProcessingUrl("/app/login")
              .defaultSuccessUrl("/app/", true)
              .permitAll()
          )
          .logout(logout -> logout
              .logoutUrl("/app/logout")
              .logoutSuccessUrl("/app/login?logout")
              .permitAll()
=======
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              .anyRequest().permitAll()
>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71
          );

        return http.build();
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71

```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:562)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:693)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:690)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:690)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:942)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1090)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:614)
	java.base/java.lang.Thread.run(Thread.java:1516)
```
#### Short summary: 

QDox parse error in file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/config/SecurityConfig.java