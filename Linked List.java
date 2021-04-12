package Odevler;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * <h1>Tek Yönlü Bağlı Liste İşlemleri</h1>
 * Marmara Üniversitesi Bilgisayar Mühendisliği Derslerini
 * içeren bir Bağlı Liste yapısı oluşturulmuştur.
 * <p>
 * Liste ders isimlerini, ders kodlarını, sömestr bilgilerini, dersin kredisi,
 * dersin kontenjanı ile ilgili değişkenleri içermektedir.
 * <p>
 * <b>Note:</b> Ulasim durumu false olan dersler devre dışı durumdadır ve bazı
 * metotlara ulaşım yetkisi yoktur. Etkinleştiklerinde aynı yerde bulunurlar ve
 * metotlara erişim sağlayabilirler.
 *
 * @author Nur Hatipoğlu
 * @version 1.0
 * @since 2014-03-31
 */
public class BagliListe {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        int secim=-1;
        Liste dersler = new Liste();
        while(secim !=0){
            System.out.println("1-Ders Ekle\n2-Dersleri Listele\n3-Ders Sil\n4-Devre Dışı Bırak\n" +
                    "5-Etkinlestir\n0-Cikis");
            System.out.println("Yapmak istediğiniz işlemi seçiniz:");
            secim=scan.nextInt();
            switch(secim){
                case 1:
                    scan.nextLine();
                    System.out.println("Ders ismi giriniz:"); String dersIsmi= scan.nextLine();
                    System.out.println("Dersin kodunu giriniz(int giriniz):"); int dersKod= scan.nextInt();
                    System.out.println("Dersin somestrını giriniz:"); int somestr= scan.nextInt();
                    System.out.println("Dersin kredisini giriniz:"); int kredi= scan.nextInt();
                    System.out.println("Dersin kontenjanını giriniz:"); int kontenjan= scan.nextInt();
                    dersler.add(dersIsmi, dersKod, somestr, kredi, kontenjan);
                    System.out.println("Ekleme tamamlandı.");
                    break;
                case 2:
                    System.out.println("1-Tum Dersleri Listele\n2-Istenilen Koddaki Dersleri Listele\n" +
                            "3-Istenilen Somestr daki Dersleri Listele\n4-Istenilen Kod Arasındaki Dersleri Listele\n" +
                            "5-Bir Sonraki Dersi Listele\n6-Aynı Somestrda Bulunan Bir Sonraki Dersi Listele\n" +
                            "7-Devre Disi Dersleri Listele");
                    System.out.println("Secim yapınız:");
                    int secim2 = scan.nextInt();
                    switch(secim2){
                        case 1:
                            dersler.listele();
                            break;
                        case 2:
                            System.out.println("Listelemek istediğiniz dersin kodunu giriniz:");
                            int kod2 = scan.nextInt();
                            dersler.getByCode(kod2);
                            break;
                        case 3:
                            System.out.println("Listelemek istediğiniz somestrı giriniz:");
                            int somestrSec = scan.nextInt();
                            dersler.listSemesterCourses(somestrSec);
                            break;
                        case 4:
                            System.out.println("Listelemek istediğiniz kod başlangıcını giriniz:");
                            int kodSec1 = scan.nextInt();
                            System.out.println("Listelemek istediğiniz kod sonunu giriniz:");
                            int kodSec2 = scan.nextInt();
                            dersler.getByRange(kodSec1,kodSec2);
                            break;
                        case 5:
                            /*System.out.println("Hangi kodlu dersten sonraki dersi listelemek istediginizi giriniz:");
                            int kod = scan.nextInt();*/
                            System.out.println("Hangi dersten sonraki dersi listelemek istediginizi giriniz:");
                            String dersSec = scan.next();
                            dersler.Next(dersSec);
                            break;
                        case 6:
                            System.out.println("Aynı semesterdaki bir sonraki dersi görmek için dersin adını giriniz:");
                            String dersBul = scan.next();
                            dersler.NextInSemester(dersBul);
                            break;
                        case 7:
                            dersler.ShowDisabled();
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Silmek istediğiniz dersin kodunu giriniz:");
                    int kod = scan.nextInt();
                    dersler.remove(kod);
                    System.out.println("Silme işlemi gerçekleştirildi.");
                    break;
                case 4:
                    System.out.println("Devre disi birakmak istediginiz dersin adini giriniz:");
                    String devreDisiIsim = scan.next();
                    dersler.disable(devreDisiIsim);
                    System.out.println("Devre disi birakildi.");
                    break;
                case 5:
                    System.out.println("Etkinlestirmek istediginiz dersin adini giriniz:");
                    String aktifIsim = scan.next();
                    dersler.enable(aktifIsim);
                    System.out.println("Etkinlestirildi.");
                    break;
                case 0:
                    System.out.println("Listedeki Ders Sayisi:"+dersler.Size());
                    System.out.println("Cikis yapildi.");
                    break;
            }
        }
    }
}
/**
 *Bu sınıf bağlı liste işlemlerinde kullanılmak için oluşturulmuştur.
 */
class Liste{
    Ders ilk=null, son=null;
    /*ilk ve son kullanarak listenin başını ve sonunu işaret ediyoruz.
    listenin ilk ini kullanarak yeni elemanlara ulaşabiliyor olacağız.Son kulllanarak listenin sonuna eleman ekleyecegiz.
    cunku listeyi ilk olusturdugumuzda listenin icinde hic eleman yok(bos).*/

    /**
     * Metot, kullanıcı dersin belirtilen parametre bilgilerini girdiğinde bu node bağlı listenin sonuna eklenir.
     * @param dersIsmi add metotunun ilk parametresidir.
     * @param dersKod add metotunun ikinci parametresidir.
     * @param somestr add metotunun üçüncü parametresidir.
     * @param kredi add metotunun dördüncü parametresidir.
     * @param kontenjan add metotunun beşinci parametresidir.
     */
    public void add(String dersIsmi, int dersKod, int somestr, int kredi, int kontenjan){
        Ders ders = new Ders(dersIsmi,dersKod,somestr,kredi,kontenjan);
        if(ilk==null){
            //liste bos
            //eger null ise listeye hiç eleman eklenmemiş demektir.
            //listede ilk ve tek olan yeni oluşturulmuş ders nesnesidir.
            //ilk ve son da listedeki tek eleman olan dersi gostermelidir.
            ilk=ders;
            son=ders;
        }
        else{
            //liste dolu
            //eger eklenen ilk kayıt değilse, kayıt son un sonuna eklenir.
            son.next=ders; //son dedigimizin next i normalde null dı cunku sonrası yoktu.Simdi son un next ini yeni olusturdugum ders yaptım.
            //son dan sonraki ders ise sonuncu da ders dir.
            son= ders; //son denilen; node a yani ekledigim yeni eleman olan ders e isaret etmeli.
        }
    }

    //Bu metot bağlı listede bulunan derslerin bilgilerini listeler.
    public void listele(){
        //int elemanSayisi=0;
        Ders gecici = ilk;//gecici tum node ların uzerinde gezecek.Listeyi bastan yazdırmaya baslıycaz o yuzden ilk den basla.
        //gecici nin isaret ettigi null olmadıgı surece yeni bir elemana gidebiliriz.
        while(gecici != null){
            //eger gecici null a eşit değilse yani liste boş değilse listeyi listelesin.
            System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan+" "+gecici.isUlasim());
            gecici = gecici.next; //gecici yi ilertletmemiz gerekiyor ki bir sonraki node u(elemanı) isaret etsin.
            //elemanSayisi++;
        }
        System.out.println("Listelendi.");
        //System.out.println("Liste Boyutu/Ders Sayisi:"+elemanSayisi);
    }

    //Bu metot bağlı listenin boyutunu bulur..
    public int Size(){
        int elemanSayisi=0;
        Ders gecici = ilk;//gecici tum node ların uzerinde gezecek.Listeyi bastan yazdırmaya baslıycaz o yuzden ilk den basla.
        //gecici nin isaret ettigi null olmadıgı surece yeni bir elemana gidebiliriz.
        while(gecici != null){
            elemanSayisi++;
            gecici=gecici.next;
        }
        return elemanSayisi;
    }

    /**
     * Bu metot kullanıcının kodunu girdiği dersin bağlı listeden silme işlevini gerçekleştirir.
     * @param dersKod remove metotunun ilk parametresidir.
     */
    public void remove(int dersKod){
        Ders gecici = ilk ,bironceki=ilk;
        while(gecici != null){
            if(gecici.dersKod == dersKod){
                break;
            }
            //1-2-3-4 mesela 3 ü silmek istiyorum 2 nin sonraki verisi 4 olması lazım. 1-2-4 olmalı.
            //bironceki adli degisken ile 2 ye ulaşırım.
            bironceki = gecici; //bu kod bir onceki elemanı tutar.
            gecici = gecici.next; //liste uzerinde ileriliyoruz(silinecek elemanı bulana kadar.)
        }
        if(gecici == null){
            System.out.println("Silmek istediginiz ders listede bulunamadi.");
        }
        if( ilk == son){
            //listede 1 eleman varsa. Bas ve son aynı yeri isaret eder.
            ilk=null;
            son=null;
        }
        else{
            //eger listede 1 eleman yoksa
            if(gecici == ilk){
                //eger ilk elemanı silmek istiyorsak.İlk  gecici nin next indeki elemanı gösterir.Böylece ilk eleman silinmiş oldu.
                ilk = gecici.next;
            }
            else if(gecici == son){
                //eger sonuncu elemanı silmek istiyorsak son, sondan bironceki elemanı gosterir ve bironcekinin next i null olur.Böylece son eleman silinmiş olur.
                son= bironceki;
                bironceki.next=null;
            }
            else{
                //ortada ise; yani 1-2-3-4 düşün 3 ü silmek istiyorum 3 gecici oldu. 2 nin next i 3 ün nextine eşit olur.
                bironceki.next = gecici.next;
            }
        }
    }

    /**
     * Bu metot kullanıcının kodunu girdiği derslerin bilgilerini konsolda listeleme işlevini gerçekleştirir.
     * @param dersKod getByCode metotunun ilk parametresidir.
     */
    public void getByCode(int dersKod){
        Ders gecici = ilk;
        while(gecici != null){
            if(gecici.dersKod == dersKod){
                //eger gecici null a eşit değilse yani liste boş değilse listeyi listelesin.
                System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan);
                gecici = gecici.next; //gecici yi bir sonraki node gösteriyor null olmadığı sürece node ları listeler.
            }
            else{
                gecici = gecici.next;
            }
        }
        System.out.println("Listelendi.");
    }

    /**
     * Bu metot kullanıcının semestr bilgisini girdiği derslerin bilgilerini konsolda listeleme işlevini gerçekleştirir.
     * @param semester remove metotunun ilk parametresidir.
     */
    public void listSemesterCourses(int semester){
        Ders gecici = ilk;
        while(gecici != null){
            if(gecici.somestr == semester){
                //eger gecici null a eşit değilse yani liste boş değilse listeyi listelesin.
                System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan);
                gecici = gecici.next; //gecici yi bir sonraki node gösteriyor null olmadığı sürece node ları listeler.
            }
            else{
                gecici = gecici.next;
            }
        }
        System.out.println("Listelendi.");
    }

    /**
     * Bu metot kullanıcının girdiği iki ders kodu arasındaki derslerin bilglerini listeleme işlevini gerçekleştirir.
     * @param start_index getByRange metotunun ilk parametresidir.
     * @param last_index getByRange metotunun ikinci parametresidir.
     */
    public void getByRange(int start_index, int last_index){
        Ders gecici = ilk;
        while(gecici != null){
            if(gecici.dersKod <= last_index && gecici.dersKod >= start_index){
                //eger gecici null a eşit değilse yani liste boş değilse listeyi listelesin.
                System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan);
                gecici = gecici.next; //gecici yi bir sonraki node gösteriyor null olmadığı sürece node ları listeler.
            }
            else{
                gecici = gecici.next;
            }
        }
        System.out.println("Listelendi.");
    }

    public void NextKod(int dersKod){
        Ders gecici = ilk;
        int sayac=0;
        while(gecici != null){
            if(gecici.dersKod == dersKod){
                if(sayac==1){
                    System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan);
                    break;
                }
                else{
                    sayac++;
                    gecici = gecici.next;
                }
            }
            else{
                gecici = gecici.next;
            }
        }
    }

    /**
     * Bu metot kullanıcının ismini girdiği girdiği dersten sonraki dersin bilglerini listeleme işlevini gerçekleştirir.
     * @param ders Next metotunun ilk parametresidir.
     */
    public void Next(String ders){
        Ders gecici = ilk;
        boolean kontrol=true;
        while(gecici != null){
            if(gecici.dersIsmi.equals(ders) && gecici.isUlasim()==false){
                System.out.println("Disable durumunda oldugunuz icin bu metota ulaşamazsınız.");
                kontrol=false;
                break;
            }
            else{
                gecici = gecici.next;
            }
        }
        gecici=ilk;
        if(kontrol){
            while(gecici != null){
                if(gecici.dersIsmi.equals(ders)){
                    gecici = gecici.next;
                    break;
                }
                else{
                    gecici = gecici.next;
                }
            }
            System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan);
        }

    } //ulasamaz

    /**
     * Bu metot kullanıcının ismini girdiği girdiği dersten aynı semestr a sahip sonraki  dersin bilglerini listeleme işlevini gerçekleştirir.
     * @param ders Next metotunun ilk parametresidir.
     */
    public void NextInSemester(String ders){
        Ders gecici = ilk;
        int semester=0;
        boolean kontrol=true;
        while(gecici != null){
            if(gecici.dersIsmi.equals(ders) && gecici.isUlasim()==false){
                System.out.println("Disable durumunda oldugunuz icin bu metota ulaşamazsınız.");
                kontrol=false;
                break;
            }
            else{
                gecici = gecici.next;
            }
        }
        gecici=ilk;
        if(kontrol){
            while(gecici != null){
                if(gecici.dersIsmi.equals(ders)){
                    semester = gecici.somestr;
                    System.out.println("Somestr:"+semester);
                    gecici = gecici.next;
                    break;
                }
                else{
                    gecici = gecici.next;
                }
            }
            while(gecici !=null){
                if(gecici.somestr == semester){
                    System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan);
                    break;
                }
                else{
                    gecici =gecici.next;
                }
            }
        }

    } //ulasamaz

    /**
     * Bu metot kullanıcının ismini girdiği girdiği dersi devre dışı bırakma işlevini gerçekleştirir.
     * @param ders disable metotunun ilk parametresidir.
     */
    public void disable(String ders){
        Ders gecici=ilk;
        while(gecici != null){
            if(gecici.dersIsmi.equals(ders)){
                gecici.setUlasim(false);
                break;
            }
            else{
                gecici = gecici.next;
            }
        }
    }

    /**
     * Bu metot kullanıcının ismini girdiği girdiği dersi etkinleştirme işlevini gerçekleştirir.
     * @param ders enable metotunun ilk parametresidir.
     */
    public void enable(String ders){
        Ders gecici=ilk;
        while(gecici != null){
            if(gecici.dersIsmi.equals(ders)){
                gecici.setUlasim(true);
                break;
            }
            else{
                gecici = gecici.next;
            }
        }
    }

    //Bu metot devre dışı olan derslerin bilgilerini listeler.
    public void ShowDisabled(){
        Ders gecici = ilk;
        while(gecici != null){
            if(gecici.isUlasim() == false){
                //eger gecici null a eşit değilse yani liste boş değilse listeyi listelesin.
                System.out.println(gecici.dersIsmi+" "+gecici.dersKod+" "+gecici.somestr+" "+gecici.kredi+" "+gecici.kontenjan+" "+gecici.isUlasim());
                gecici = gecici.next; //gecici yi bir sonraki node gösteriyor null olmadığı sürece node ları listeler.
            }
            else{
                gecici = gecici.next;
            }
        }
        System.out.println("Listelendi.");
    }
}

/**
 *Bu sınıf bağlı listenin node unu oluşturmak için kullanılır.
 */
class Ders{
    //node un içindekiler
    String dersIsmi;
    int dersKod,somestr,kredi,kontenjan;
    Ders next; //dersin bir sonraki dersi tutması için node olacak.Bir sonraki dersi tutan node tanımladık.
    private boolean ulasim=true;

    /**
     * Kurucu metot girilen bilgileri bu sınıftaki değişkenlere atar.
     * @param dersIsmi Ders kurucu metotunun ilk parametresidir.
     * @param dersKod Ders kurucu metotunun ikinci parametresidir.
     * @param somestr Ders kurucu metotunun üçüncü parametresidir.
     * @param kredi Ders kurucu metotunun dördüncü parametresidir.
     * @param kontenjan Ders kurucu metotunun beşinci parametresidir.
     */
    public Ders(String dersIsmi, int dersKod, int somestr, int kredi, int kontenjan) {
        this.dersIsmi = dersIsmi;
        this.dersKod = dersKod;
        this.somestr = somestr;
        this.kredi = kredi;
        this.kontenjan = kontenjan;
        this.next = null;//yeni eleman olusturdugumuzda sirada baska eleman olmadıgını varsayıyoruz.
        this.ulasim=ulasim;
    }

    /**
     * Bu metot private tipinde olan ulasim değişkenin değerine diğer sınıflardan erişmek için oluşturulmuştur.
     */
    public boolean isUlasim() {
        return ulasim;
    }

    /**
     * Bu metot private tipinde olan ulasim değişkenin değerine diğer sınıflardan değiştirmek için oluşturulmuştur.
     */
    public void setUlasim(boolean ulasim) {
        this.ulasim = ulasim;
    }
}
