package hajdarevic_zlatko.model;

public class Korisnik extends Osoba {
    public Korisnik(String korisnickoIme, String sifra, String mestoRada) {
        super(korisnickoIme, sifra, mestoRada);
    }
    @Override
    public String toString() {
        return "Korisnik{" +
                "korisnickoIme='" + getKorisnickoIme() + '\'' +
                ", sifra='" + getSifra() + '\'' +
                '}';
    }
}
