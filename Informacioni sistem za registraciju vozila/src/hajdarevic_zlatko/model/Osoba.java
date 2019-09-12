package hajdarevic_zlatko.model;

public abstract class Osoba {
    private String korisnickoIme;
    private  String sifra;
    private  String mestoRada;

    public Osoba(String korisnickoIme, String sifra, String mestoRada) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.mestoRada=mestoRada;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }



    public String getMestoRada() {
        return mestoRada;
    }

    public void setMestoRada(String mestoRada) {
        this.mestoRada = mestoRada;
    }

}
