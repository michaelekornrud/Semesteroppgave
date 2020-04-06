package FullførOrdre;

public class FullførOrdre {
    public String fornavn;
    public String etternavn;
    public String adresse;
    public int postnummer;
    public String poststed;

    public FullførOrdre(String fornavn, String etternavn, String adresse, int postnummer, String poststed) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.poststed = poststed;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public String getPoststed() {
        return poststed;
    }

}
