package hajdarevic_zlatko;
import hajdarevic_zlatko.model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Server {
    public static List<Osoba> listaKorisnika;
    public static List<Automobil> listaAutomobila;
    public static List<Registracija> listaRegistracija;


    public static void main(String[] args) throws IOException {

        listaKorisnika = new ArrayList<>();
        listaRegistracija=new ArrayList<>();
        listaKorisnika.add(new Korisnik("Milica", "1","BG"));
        listaKorisnika.add(new Admin("admin", "admin","BG"));
        listaKorisnika.add(new Korisnik("Dusan", "2","LO"));
        listaKorisnika.add(new Korisnik("Zlatko", "3","BG"));
        listaKorisnika.add(new Korisnik("Nebojsa", "4","PA"));
        listaAutomobila = new ArrayList<>();
        listaAutomobila.add(new Automobil("Zivojin", "Misic", "BG-432-111", LocalDate.of(2010,5,12),3000,150,2009, "BMW"));
        listaAutomobila.add(new Automobil("Aleksandar", "Markovic", "LO-123-567", LocalDate.of(2020,5,12), 2000, 110, 2005, "MERCEDES"));
        listaAutomobila.add(new Automobil("Zlatko", "Hajdarevic", "BG-666-888", LocalDate.of(2019,5,12), 5000, 210, 2018, "HAMMER"));
        listaAutomobila.add(new Automobil("Nebojsa", "Dzakula", "BG-111-111", LocalDate.of(2020,4,11), 3500, 170, 2019, "BMW"));

        System.out.println("Server je pokrenut...");
        ServerSocket server = new ServerSocket(9001);
        int brojKlijenta = 1;
        try {
            while (true) {
                new Konekcija(server.accept(), brojKlijenta++).
                        start();
            }
        } finally {
            server.close();
        }
    }

    public static class Konekcija extends Thread {
        private Socket soket;
        private int brojKlijenta;
        private Osoba korisnik;


        public Konekcija(Socket soket, int brojKlijenta) {
            this.soket = soket;
            this.brojKlijenta = brojKlijenta;
        }

        @Override
        public void run() {
            try {
                Scanner input = new Scanner(soket.getInputStream());
                PrintWriter output = new PrintWriter(soket.getOutputStream(),true);
                boolean postoji=false;
                System.out.println(brojKlijenta + ": se uspesno konektovao");
                System.out.println(brojKlijenta + ": Unosi ime i sifru");
                Thread.sleep(500);
                output.println("Unesite username: ");
                output.flush();
                String ime = input.nextLine();
                Thread.sleep(500);
                output.println("Unesite lozinku: ");
                output.flush();
                String sifra = input.nextLine();
                for (Osoba k : listaKorisnika) {
                    if (k.getKorisnickoIme().equals(ime) &&
                            k.getSifra().equals(sifra)) {
                        Thread.sleep(500);
                        System.out.println(brojKlijenta + " se uspesno ulogovao");
                        korisnik=k;
                        postoji= true;
                    }
                }

                if (!postoji) {
                    Thread.sleep(500);
                    output.println("Losi kredincijali-konekcija se prekida!!!");
                    System.out.println(brojKlijenta + " se nije ulogovao");
                    Thread.sleep(3000);
                    soket.close();
                }
                else{

                    while(true){
                        if(korisnik instanceof Admin){
                            System.out.println("Admin se ulogovao");
                            System.out.println("Izbor");
                            Thread.sleep(500);
                            output.println("Pregled svih registracija(1), dodavanje novog korisnika(2), pregled korisnika(3),izlaz(q)");
                            output.flush();
                            String izbor=input.nextLine();
                            Thread.sleep(500);
                            izbor=izbor.toLowerCase();
                            if (izbor.equals("1")){
                                System.out.println("Pregled registracija");
                                if(listaRegistracija.isEmpty()){

                                    output.println("Lista je prazna");
                                }
                                else{
                                    for(Registracija r:listaRegistracija){
                                    output.println(r.toString());
                                }
                                }

                                continue;
                            }
                            else if (izbor.equals("2")){
                                System.out.println("Registrovanje korisnika");
                                output.println("Unesite username");
                                String imeKorisnikaUnosUSistem=input.nextLine();
                                output.println("Unesite lozinku");
                                String lozinkaKorisnikaUnosUSistem=input.nextLine();
                                boolean korisnikPostoji_Sistem_Za_Unos_Korisnika=false;
                                for(Osoba k:listaKorisnika){

                                    if(k.getKorisnickoIme().equals(imeKorisnikaUnosUSistem)){
                                        output.println("Korisnik vec postoji radnja je prekinula");
                                        korisnikPostoji_Sistem_Za_Unos_Korisnika=true;
                                        break;

                                    }

                                }
                                if (korisnikPostoji_Sistem_Za_Unos_Korisnika){
                                    continue;
                                }

                                output.println("Unosenje novog korisnika je zapoceto");
                                System.out.println("Unos novog korisnika od strane admina");
                                listaKorisnika.add(new Korisnik(imeKorisnikaUnosUSistem, lozinkaKorisnikaUnosUSistem, korisnik.getMestoRada()));
                                imeKorisnikaUnosUSistem=null;
                                lozinkaKorisnikaUnosUSistem=null;
                                output.println("Korisnik je unet u sistem");



                            }
                            else if (izbor.equals("3")){
                                System.out.println("Pregled korisnika");
                                if(listaKorisnika.isEmpty()){
                                    output.println("Lista je prazna");
                                    continue;
                                }
                                for(Osoba k:listaKorisnika){
                                     output.println(k.toString());

                                }


                                continue;

                            }
                            else if(izbor.equals("q")){
                                output.println("Prijatan ostatak dana!!!");
                                Thread.sleep(3000);

                                soket.close();
                                break;
                            }

                        }
                        else{System.out.println("Izbor");
                            Thread.sleep(500);
                            output.println("Unos novog vozila (1) postupak registracije vozila u sistemu(2),za izlaz(q)");
                            output.flush();

                            Thread.sleep(200);
                            output.println("Izbor:");
                            output.flush();

                            String izbor=input.nextLine();
                            Thread.sleep(500);
                            izbor=izbor.toLowerCase();
                            if (izbor.equals("1")){
                                System.out.println("Unos novog vozila");
                                registracijaNovogVozila((Korisnik) korisnik,input,output);
                                continue;

                            }
                            else if (izbor.equals("2")){
                                System.out.println("Registrovanje vozila u sistemu");
                                boolean rezultat=registracijaPostojecegVozila(input, output, (Korisnik) korisnik);
                                if (rezultat){
                                    Thread.sleep(1000);
                                    output.println("Vozilo uspesno registrovano");
                                    System.out.println("Proces zavrsen");
                                    continue;

                                }
                                output.println("Greska. Pokusajte ponovo");
                                System.out.println("Greska. Registracija nije obavljena");
                                continue;

                            }
                            else if(izbor.equals("q")){
                                output.println("Prijatan ostatak dana!!!");
                                Thread.sleep(3000);

                                soket.close();
                                break;
                            }
                            else{
                                output.println("Pogresan izbor izaberite '1' ili '2'");
                                continue;
                            }}


                    }

                    }






            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        private boolean registracijaPostojecegVozila(Scanner input, PrintWriter output, Korisnik korisnik) throws ParseException, InterruptedException {
            output.println("Unesite registraciju: ");
            output.flush();
            String registracija = input.nextLine();
            if(proveraTablica(registracija)){
                System.out.println(brojKlijenta + ": Registracija=" +registracija);
                for (Automobil a:listaAutomobila){
                    if(registracija.equals(a.getTablica())){


                        LocalDate dateCurrent = LocalDate.now();
                        LocalDate dateReg=a.getRegistrovanDo();
                        System.out.println("Vozilo je zadnji put registrovano:");
                        System.out.println(a.getRegistrovanDo().toString());
                        if(dateCurrent.isBefore(a.getRegistrovanDo())){
                            a.setRegistrovanDo(dateReg.plusYears(1));
                            System.out.println("Vozilo je registrovano unapred");
                            //Registracija registracija1=new Registracija(korisnik,a);
                            System.out.println(korisnik.toString());
                            System.out.println(a.toString());
                            listaRegistracija.add(new Registracija(korisnik,a));
                            stampanjeNalepnice(a,output);
                            return true;


                        }
                        else if(dateCurrent.isAfter(a.getRegistrovanDo())){
                            a.setRegistrovanDo(dateCurrent.plusYears(1));
                            System.out.println("Vozilo je registrovano nakon isteka registracije");
                            listaRegistracija.add(new Registracija(korisnik,a));
                            stampanjeNalepnice(a,output);
                            return true;

                        }
                        else {
                            a.setRegistrovanDo(dateCurrent.plusYears(1));
                            listaRegistracija.add(new Registracija(korisnik,a));
                            System.out.println("Vozilo je registrovano na dan registracije");
                            stampanjeNalepnice(a,output);
                            return true;
                        }

                    }
                }
                output.println("Ne postoji data tablica");
                output.flush();
                return false;
            }
            else {
                output.println("Los format registracije");
                return false;
            }


        }



        private void registracijaNovogVozila(Korisnik korisnik, Scanner input, PrintWriter output) throws InterruptedException {
            output.println("Unesite ime vlasnika: ");
            output.flush();
            String ime=input.nextLine();
            Thread.sleep(500);
            output.println("Unesite prezime vlasnika: ");
            output.flush();
            String prezime=input.nextLine();
            Thread.sleep(500);
            String tablica=kreirajTablicu();
            boolean proveraTabliceUnos=true;
            while(proveraTabliceUnos){

                for (Automobil a:listaAutomobila){
                    if(a.getTablica().equals(tablica)){
                        break;
                    }

                }
                proveraTabliceUnos=false;
            }
            int kubikaza;
            int konjaza;
            int godiste;
             String marka;
            output.println("Unesite kubikazu:");
            kubikaza=input.nextInt();
            output.println("Unesite konjazu:");
            konjaza=input.nextInt();
            output.println("Unesite godiste:");
            godiste=input.nextInt();
            output.println("Unesite marku:");
            marka=input.nextLine();
            Automobil automobil=new Automobil(ime,prezime,tablica,(LocalDate.now().plusYears(1)),kubikaza,konjaza,godiste,marka);
            listaAutomobila.add(automobil);
            listaRegistracija.add(new Registracija(korisnik,automobil));
            output.println("Registracija novog vozila uspesna");
            System.out.println("Novo vozilo uneto u listu");
            stampanjeNalepnice(automobil,output);







        }
        public boolean proveraTablica(String tablica){
            String pattern ="[A-Z]{2}-\\d{3}-\\d{3}";


            Pattern r = Pattern.compile(pattern);


            Matcher m = r.matcher(tablica);
            if(!m.matches()){
                return false;
            }

            return true;
        }
        public void stampanjeNalepnice(Automobil a, PrintWriter output) throws InterruptedException {
            output.println("-----STAMPANJE NALEPNICE-----");
            output.flush();

            Thread.sleep(500);
            output.println("---------------------");
            output.flush();
            Thread.sleep(500);
            output.println("|     "+a.getTablica()+"    |");
            output.flush();
            Thread.sleep(500);
            output.println("|     "+a.getRegistrovanDo().toString()+"    |");
            output.flush();
            Thread.sleep(500);
            output.println("---------------------");

        }
        public String kreirajTablicu(){
            Random r=new Random();
            String tablica=""+korisnik.getMestoRada()+"-";
            for(int i=0;i<7;i++){
                if(i==3){
                    tablica=tablica+"-";
                    continue;
                }
                tablica=tablica+(r.nextInt(10));
            }
            System.out.println(tablica);
            return tablica;
        }
    }
}