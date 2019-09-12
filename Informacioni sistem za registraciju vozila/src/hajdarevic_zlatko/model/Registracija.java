package hajdarevic_zlatko.model;

import hajdarevic_zlatko.model.Automobil;
import hajdarevic_zlatko.model.Korisnik;

public class Registracija {
    private Korisnik korisnik;
    private Automobil automobil;

    public Registracija(Korisnik korisnik, Automobil automobil) {
        this.korisnik = korisnik;
        this.automobil = automobil;

    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    @Override
    public String toString() {
        return "Registracija{" +
                "korisnik=" + korisnik +
                ", automobil=" + automobil +
                '}';
    }
}
