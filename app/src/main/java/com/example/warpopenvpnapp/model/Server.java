package com.example.warpopenvpnapp.model;

public class Server {

    private String ovpn;
    private String ovpnUserName;
    private String ovpnUserPassword;


    public Server() {
    }

    public Server(String country, String flagUrl, String ovpn) {

        this.ovpn = ovpn;
    }

    public Server(String country, String flagUrl, String ovpn, String ovpnUserName, String ovpnUserPassword) {

        this.ovpn = ovpn;
        this.ovpnUserName = ovpnUserName;
        this.ovpnUserPassword = ovpnUserPassword;
    }



    public String getOvpn() {
        return ovpn;
    }

    public void setOvpn(String ovpn) {
        this.ovpn = ovpn;
    }

    public String getOvpnUserName() {
        return ovpnUserName;
    }

    public void setOvpnUserName(String ovpnUserName) {
        this.ovpnUserName = ovpnUserName;
    }

    public String getOvpnUserPassword() {
        return ovpnUserPassword;
    }

    public void setOvpnUserPassword(String ovpnUserPassword) {
        this.ovpnUserPassword = ovpnUserPassword;
    }
}
