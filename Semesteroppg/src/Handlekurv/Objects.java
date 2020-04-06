package Handlekurv;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Objects {
    public String kabinett;
    public String hovedkort;
    public String prosessor;
    public String skjermkort;
    public String minne;
    public String strømforskyning;
    public String harddisk1;
    public String harddisk2;
    public String CPUVifte;
    public String vifter;
    public String casemods;
    public String skjerm;
    public String tastatur;
    public String mus;
    public String hodetelefoner;


    public Objects(String kabinett, String hovedkort, String prosessor, String skjermkort,
                   String minne, String strømforskyning,
                   String harddisk1, String harddisk2,
                   String CPUVifte, String vifter,
                   String casemods, String skjerm,
                   String tastatur, String mus,
                   String hodetelefoner) {
        this.kabinett = kabinett;
        this.hovedkort = hovedkort;
        this.prosessor = prosessor;
        this.skjermkort = skjermkort;
        this.minne = minne;
        this.strømforskyning = strømforskyning;
        this.harddisk1 = harddisk1;
        this.harddisk2 = harddisk2;
        this.CPUVifte = CPUVifte;
        this.vifter = vifter;
        this.casemods = casemods;
        this.skjerm = skjerm;
        this.tastatur = tastatur;
        this.mus = mus;
        this.hodetelefoner = hodetelefoner;
    }


    public String getKabinett() {
        return kabinett;
    }

    public String getHovedkort() {
        return hovedkort;
    }

    public String getProsessor() {
        return prosessor;
    }

    public String getSkjermkort() {
        return skjermkort;
    }

    public String getMinne() {
        return minne;
    }

    public String getStrømforskyning() {
        return strømforskyning;
    }

    public String getHarddisk1() {
        return harddisk1;
    }

    public String getHarddisk2() {
        return harddisk2;
    }

    public String getCPUVifte() {
        return CPUVifte;
    }

    public String getVifter() {
        return vifter;
    }

    public String getCasemods() {
        return casemods;
    }

    public String getSkjerm() {
        return skjerm;
    }

    public String getTastatur() {
        return tastatur;
    }

    public String getMus() {
        return mus;
    }

    public String getHodetelefoner() {
        return hodetelefoner;
    }

    @Override
    public String toString (){
        return String.format("Kabinett: %s \nHovedkort: %s\nProsessor: %s\nSkjermkort: %s\nMinne: %s\n" +
                "Strømforskyning: %s\nHarddisk: %s\nHarddisk: %s\nCPU-Vifte: %s\nVifter: %s\n" +
                "Casemods: %s\nSkjerm: %s\nTastatur: %s\nMus: %s\nHodetelefoner: %s",
                kabinett, hovedkort, prosessor, skjermkort, minne,strømforskyning, harddisk1, harddisk2, CPUVifte
                , vifter,  casemods, skjerm, tastatur, mus, hodetelefoner);
    }
}
