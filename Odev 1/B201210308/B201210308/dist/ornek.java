package odev;
import java.util.UUID;
public class Motor {
private String motorNo;
private boolean calisiyor;
/**
* Varsayılan kurucu fonksiyon denemeee
*/
public Motor() {
this.motorNo = UUID.randomUUID().toString();
/* Başlangıçta false */calisiyor = false;
}
/**
*
* @param motorNo UUID olarak motor id
* @return motor instance
*/
public Motor(String motorNo) {
/*
* Varolan bir motor no ile oluşturuluyor.
*/
this.motorNo = motorNo;
calisiyor = false; // false yapılıyor

}
public void calistir() {
/**
* calisiyor true yapılıyor denemeeeee // * / denemee
*/
calisiyor = true;
}
/**
* Motorun durdurulması denemeee// /* deneme *  / * / denemmee
*/
public void durdur() {
	
String desen ="\\ deneme /* deneme */ /** deneme */ // /** /*{"
calisiyor = false;
}
public String getMotorNo() {
// motor no getir
return motorNo;
}
@Override
public String toString() {
// durum belirlenmesi //
String durum = calisiyor ? "Motor Çalışıyor." : "Motor Çalışmıyor";/* deneme */
return durum;
}
}