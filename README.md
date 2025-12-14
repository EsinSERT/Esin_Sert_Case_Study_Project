# Selenium UI Test Otomasyonu – Insider QA İş Akışı

## 📌 Proje Hakkında
Bu proje, **Java**, **Selenium WebDriver**, **TestNG** ve **Maven** kullanılarak geliştirilmiş
basit bir UI test otomasyonu çalışmasıdır.

Projenin amacı, Insider web sitesindeki **QA iş ilanları akışını**
uçtan uca otomasyon ile test etmektir.
Bu çalışma, kişisel öğrenme sürecimin bir parçası olarak hazırlanmıştır.

---

## 🎯 Test Senaryosu
- Ana sayfa açılır
- QA Careers sayfasına gidilir
- QA pozisyonları listelenir
- Lokasyon ve departman filtreleri uygulanır
- İş ilanı listesinin geldiği doğrulanır
- Listelenen ilanların filtrelere uygun olduğu kontrol edilir
- İlk iş ilanının detay sayfası (View Role) açılır

---

## 🛠 Kullanılan Teknolojiler
- Java
- Selenium WebDriver
- TestNG
- Maven
- IntelliJ IDEA

---

## ▶ Testleri Çalıştırma
1. Proje IntelliJ IDEA ile açılır
2. Maven bağımlılıklarının yüklendiğinden emin olunur
3. Testler aşağıdaki yöntemlerden biriyle çalıştırılabilir:
    - `testng.xml` dosyası üzerinden
    - Test sınıfına sağ tıklayıp **Run** seçilerek

---

## 📝 Notlar
Bu proje, Selenium pratikleri yapmak, sayfa nesne modeli (Page Object) yapısını
öğrenmek ve temel assertion kullanımlarını pekiştirmek amacıyla oluşturulmuştur.


# n11 Arama Yük Testi (JMeter)

## Amaç

Bu projede n11.com web sitesinin arama modülü ve arama sonrası ürün listeleme davranışı Apache JMeter kullanılarak test edilmiştir.

Test, canlı ortama zarar vermemek amacıyla düşük yük altında ve sınırlı sayıda kullanıcı ile çalıştırılmıştır. Amaç, sistemin temel performansını ve stabilitesini gözlemlemektir.

## Kullanılan Araçlar

- Apache JMeter 5.6.x
- Java JDK 11+
- Google Chrome

## Test Senaryosu

Test sırasında aşağıdaki adımlar uygulanmıştır:

- Kullanıcı n11 mobil ana sayfasına (m.n11.com) gider
- "ayna" kelimesi ile arama yapar
- Arama sonuç sayfasının başarıyla yüklendiği doğrulanır
- Ürün listeleme alanının görüntülendiği kontrol edilir

## Test Yapılandırması

- Kullanıcı Sayısı: 2
- Ramp-up Süresi: 2 saniye
- Loop Count: 2
- Protokol: HTTPS
- Platform: Mobil (m.n11.com)

## Doğrulamalar (Assertions)

- HTTP Response Code kontrolü (200 OK)
- Response Assertion ile arama sonuç sayfası doğrulaması
- Hata oranı kontrolü (%0)

## Testin Çalıştırılması

1. Apache JMeter açılır
2. n11-search-load-test.jmx dosyası yüklenir
3. Test başlatılır (Run)
4. Sonuçlar Summary Report üzerinden incelenir

## Not

Bu çalışma, gerçek sisteme zarar vermemek amacıyla düşük kullanıcı sayısı ile gerçekleştirilmiştir. Test, stress testi değil; fonksiyonel doğrulama ve temel performans gözlemi amacıyla yapılmıştır.
