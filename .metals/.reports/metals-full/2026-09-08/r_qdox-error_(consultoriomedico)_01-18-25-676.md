error id: file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Model/Receta.java
file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Model/Receta.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[27,2]

error in qdox parser
file content:
```java
offset: 426
uri: file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Model/Receta.java
text:
```scala
package com.example.consultoriomedico.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data
@Table(name="receta")
public class Receta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_receta")
    private Integer idReceta;


    @ManyToOne
    @JoinColumn(name="id_paciente")
    private Paciente paciente;


<<@@<<<<< HEAD
    /**
     * Se agregó porque el equipo alteró la tabla "receta" con
     * ALTER TABLE receta ADD COLUMN id_cita ... (06/09/2026) pero
     * la entidad Java todavía no tenía el campo — sin esto, Hibernate
     * ignoraba la columna por completo.
     */
    @ManyToOne
    @JoinColumn(name="id_cita")
    private CitaMedica citaMedica;


=======
>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71
    private String medico;


    @Column(name="num_colegiatura")
    private String numColegiatura;


    @Column(name="fecha_emision")
    private LocalDate fechaEmision;


    @Column(name="archivo_adjunto")
    private String archivoAdjunto;

}
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

QDox parse error in file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Model/Receta.java