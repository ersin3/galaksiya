#  Galaksiya-Springboot-CRUD-app
postgresql  Veritabanı ve Restful API kullanan Spring boot CRUD projesi

##  Uygulamayı Docker olmadan başlatmak için gereksinimler
- [ JDK 11 ](jdk-11.0.14_windows-x64_bin.exe)
- [ PostgreSQL 13]
- [  IDE   ]
###  Docker olmadan yerel olarak çalıştırma adımları
    * "Uygulama klasorune IDE üzerinden açın. src\main\resources\application.properties dosyasina girin.
    * "Daha sonra username ve password bilgilerinizi kendi bilgileriniz ile degistirin.
    * "PostreSQL veri tabanınızdan London isimli bir veri tabanı oluşturun.
    * Bu kadarı yeterli code first calistirdigimiz icin table ve kolonları kendisi ekleyecektir.
    * "src\main\java galaksiya\demo\DemoApplication Main classina sağ tıklayarak run edin.
    * "http://localhost:8082/swagger-ui.html yerel aginizdan apiyi inceleyebilirsiniz.

##  Uygulamayı Docker-compose ile başlatmak için gereksinimler
- [ Docker ]
### Docker-compose ile uygulamayı yerel olarak başlatma
    * "Komut satırından uygulamanın bulunduğu klasore gidin.
    * "docker-compose -f docker-compose.yml up komutunu girin ve uygulamanızı ayaga kaldırın.
    * "http://localhost:8082/swagger-ui.html agindan apiyi inceleyebilirsiniz.