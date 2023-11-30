/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.service;

import java.io.Serializable;

/**
 *
 * @author jguzm
 */
public class TiendaTO implements Serializable {

    private int idTienda;
    private String nomTienda;
    private String descripcionTienda;
    private String categoriaTienda;
    private String fk_idUser;

    public TiendaTO() {
    }

    public TiendaTO(int idTienda, String nomTienda, String descripcionTienda, String categoriaTienda, String fk_idUser) {
        this.idTienda = idTienda;
        this.nomTienda = nomTienda;
        this.descripcionTienda = descripcionTienda;
        this.categoriaTienda = categoriaTienda;
        this.fk_idUser = fk_idUser;
    }

    public TiendaTO(String nomTienda, String descripcionTienda, String categoriaTienda, String fk_idUser) {
        this.nomTienda = nomTienda;
        this.descripcionTienda = descripcionTienda;
        this.categoriaTienda = categoriaTienda;
        this.fk_idUser = fk_idUser;
    }

    public TiendaTO(String nomTienda, String descripcionTienda, String categoriaTienda) {
        this.nomTienda = nomTienda;
        this.descripcionTienda = descripcionTienda;
        this.categoriaTienda = categoriaTienda;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }

    public String getDescripcionTienda() {
        return descripcionTienda;
    }

    public void setDescripcionTienda(String descripcionTienda) {
        this.descripcionTienda = descripcionTienda;
    }

    public String getCategoriaTienda() {
        return categoriaTienda;
    }

    public void setCategoriaTienda(String categoriaTienda) {
        this.categoriaTienda = categoriaTienda;
    }

    public String getFk_idUser() {
        return fk_idUser;
    }

    public void setFk_idUser(String fk_idUser) {
        this.fk_idUser = fk_idUser;
    }

    @Override
    public String toString() {
        return "TiendaTO{" + "idTienda=" + idTienda + ", nomTienda=" + nomTienda + ", descripcionTienda=" + descripcionTienda + ", categoriaTienda=" + categoriaTienda + ", fk_idUser=" + fk_idUser + '}';
    }

}
