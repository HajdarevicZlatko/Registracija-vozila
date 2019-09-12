package hajdarevic_zlatko.model;

public class Admin extends Osoba {


    public Admin(String korisnickoIme, String sifra, String mestoRada) {
        super(korisnickoIme, sifra, mestoRada);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "korisnickoIme='" + getKorisnickoIme() + '\'' +
                ", sifra='" + getSifra() + '\'' +
                '}';
    }
}
