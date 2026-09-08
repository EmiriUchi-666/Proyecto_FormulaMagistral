error id: file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Service/UsuarioService.java
file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Service/UsuarioService.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[52,2]

error in qdox parser
file content:
```java
offset: 1100
uri: file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Service/UsuarioService.java
text:
```scala
package com.example.consultoriomedico.Service;

import com.example.consultoriomedico.Model.Usuario;
import com.example.consultoriomedico.Repository.UsuarioRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;


    public UsuarioService(
            UsuarioRepository repository,
            BCryptPasswordEncoder encoder
    ){
        this.repository = repository;
        this.encoder = encoder;
    }



    public List<Usuario> listar(){
        return repository.findAll();
    }



    public Usuario guardar(Usuario usuario){

        usuario.setPasswordHash(
                encoder.encode(usuario.getPasswordHash())
        );

        return repository.save(usuario);
    }



    public Usuario buscar(Integer id){
        return repository.findById(id)
                .orElse(null);
    }



<<@@<<<<< HEAD
    /** Usado para resolver al usuario actualmente logueado (por su email) al registrar ventas/movimientos. */
    public Usuario buscarPorEmail(String email){
        return repository.findByEmail(email);
    }



=======
>>>>>>> 4aa01638f118a77226d52029b8c22d2f96991c71
    public void eliminar(Integer id){
        repository.deleteById(id);
    }

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

QDox parse error in file:///C:/Users/Antony/Documents/Proyecto_FM/src/main/java/com/example/consultoriomedico/Service/UsuarioService.java