package com.example.fatihn.tabloyutview02;

public class VeriKisilerModel {

    private String id;
    private String ad;
    private String telefon;
    private String adres;
    private String tutar;

    //public List<VeriKisilerModel> veriKisilerModelList;

    public VeriKisilerModel() {

    }

    public VeriKisilerModel(String id, String ad, String telefon, String adres, String tutar) {
        this.setId(id);
        this.setAd(ad);
        this.setTelefon(telefon);
        this.setAdres(adres);
        this.setTutar(tutar);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTutar() {
        return tutar;
    }

    public void setTutar(String tutar) {
        this.tutar = tutar;
    }
}
