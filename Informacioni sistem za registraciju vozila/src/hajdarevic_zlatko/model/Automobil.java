package hajdarevic_zlatko.model;

import java.time.LocalDate;

public class Automobil {

        private String ime;
        private String prezime;
        private String tablica;
        private LocalDate registrovanDo;
        private int kubikaza;
        private int konjaza;
        private int godiste;
        private String marka;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public Automobil(String ime, String prezime, String tablica, LocalDate registrovanDo, int kubikaza, int konjaza, int godiste, String marka) {
        this.ime = ime;
        this.prezime = prezime;
        this.tablica = tablica;
        this.registrovanDo = registrovanDo;
        this.kubikaza = kubikaza;
        this.konjaza = konjaza;
        this.godiste = godiste;
        this.marka = marka;
    }

    public double getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public int getKonjaza() {
        return konjaza;
    }

    public void setKonjaza(int konjaza) {
        this.konjaza = konjaza;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }


    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTablica() {
        return tablica;
    }

    public void setTablica(String tablica) {
        this.tablica = tablica;
    }

    public LocalDate getRegistrovanDo() {
        return registrovanDo;
    }

    public void setRegistrovanDo(LocalDate registrovanDo) {
        this.registrovanDo = registrovanDo;
    }

    public String getIme() {
            return ime;
        }

        public void setIme(String ime) {
            this.ime = ime;
        }

    @Override
    public String toString() {
        return "Automobil{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", tablica='" + tablica + '\'' +
                ", registrovanDo='" + registrovanDo + '\'' +
                ", kubikaza=" + kubikaza +
                ", konjaza=" + konjaza +
                ", godiste=" + godiste +
                ", marka='" + marka + '\'' +
                '}';
    }
}
