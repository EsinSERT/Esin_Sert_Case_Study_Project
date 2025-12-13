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
