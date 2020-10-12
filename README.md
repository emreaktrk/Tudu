# Tudu

> Geçikme için özür dilerim.. Geçikmemin de sebebini açıklamak isterim
 Mevcut işimin yoğunluğuna rahmen size kaliteli bir proje teslim etmek istedim. 
 Bunun için enterprise projelerde de kullanılabilecek kalitedeki yapıları kullanayı uygun gördüm.
 In memory'de başladığım data katmanı Database ile değiştirdim. 
 DDD ile birlikte MVVM kullandım. Olabildiğince reactive app yazmaya çalıştım.

## Data
> Data katmanındanki Repository Inject edilen DataSource'ları consume etmektedir.
  Database'den gelen ResultSet; entity'den Mapper'lar ile DTO'lara dönüşüp
  üst katmana Domain'e iletilir.

## Domain
> UseCase'ler Data katmanının caller-site i diyebiliriz. 
  App module'un Data katmanına doğrudan erişim yoktur. 
  Buradaki UseCase'ler Business Logic'de barındabilir. 
  Async, merge, debounce vb işlemler Couroutine ve Flow ile burada yapılır.
  Bununla birlikte Data tarafından gelen datalar Mapper ile Value Object'e dönüştürülür
  ve App katmanına UI'da gösterilmesi için iletilir.

## App
> Projemizin executable module'üdür. UI'da buton tıklaması ile başlayan süreçte
  Data Binding ile ViewModel'da bir func call edilir. Inject edilen UseCase'ler
  kullanılır.

## Presentation
> App'i beslemek için UI componentlerinin geliştirildiği module'dür.
  Bu uygualama için geliştirilecek pek birşey olmasa da reusable component'lerin
  geliştirildiği ve Data ve UI için Binding Adapter'lar burada geliştirilir.
  Atomic Design bu componentlerin geliştirilmesi için tercih ettiğim bir teknik olur.
  Atoms -> Molecules -> Organism -> Template -> Page flow a sahiptir.


![Arch Diagram](art/arch.png?raw=true "Arch Diagram")
![Depdency Diagram](art/dependency.png?raw=true "Dependency Diagram")
![App](art/vid.gif?raw=true "App")

Aşağıda liste halinde belirtmek gerekirse;


  - %100 Kotlin
  - Domain Driven Design kullanıldı.
  - Presentation katmanında MVVM kullanıldı.

# Features

  - Hilt ile Dependeny Injection kullanıldı.
  - Flow ve LiveData ile birlikte Data ve View Binding kullanıldı.
  - Data katmanında repository pattern kullanıdı. 
  Room ile Database (One to Many relationlar dahil) geliştirilmesi yapıldı. 
  Aynı şekilde Database üzerinde Flow ve Coroutines ile threading ve Observable yapılar kuruldu.
 
# In progress

  - Todo add screen
  - Alarm Manager
  - Staging
  - Map
  - Atomic Design UI Components
