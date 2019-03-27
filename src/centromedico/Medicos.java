/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centromedico;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Roberto Bravo
 */
@Entity
@Table(name = "medicos", catalog = "centro_medico", schema = "")
@NamedQueries({
    @NamedQuery(name = "Medicos.findAll", query = "SELECT m FROM Medicos m")
    , @NamedQuery(name = "Medicos.findByIdMedico", query = "SELECT m FROM Medicos m WHERE m.idMedico = :idMedico")
    , @NamedQuery(name = "Medicos.findByNombreMedico", query = "SELECT m FROM Medicos m WHERE m.nombreMedico = :nombreMedico")
    , @NamedQuery(name = "Medicos.findByApellidoMedico", query = "SELECT m FROM Medicos m WHERE m.apellidoMedico = :apellidoMedico")
    , @NamedQuery(name = "Medicos.findByEspecialidad", query = "SELECT m FROM Medicos m WHERE m.especialidad = :especialidad")})
public class Medicos implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idMedico")
    private Integer idMedico;
    @Column(name = "nombreMedico")
    private String nombreMedico;
    @Column(name = "apellidoMedico")
    private String apellidoMedico;
    @Column(name = "especialidad")
    private String especialidad;
    @Lob
    @Column(name = "experiencia")
    private String experiencia;

    public Medicos() {
    }

    public Medicos(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        Integer oldIdMedico = this.idMedico;
        this.idMedico = idMedico;
        changeSupport.firePropertyChange("idMedico", oldIdMedico, idMedico);
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        String oldNombreMedico = this.nombreMedico;
        this.nombreMedico = nombreMedico;
        changeSupport.firePropertyChange("nombreMedico", oldNombreMedico, nombreMedico);
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        String oldApellidoMedico = this.apellidoMedico;
        this.apellidoMedico = apellidoMedico;
        changeSupport.firePropertyChange("apellidoMedico", oldApellidoMedico, apellidoMedico);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        String oldEspecialidad = this.especialidad;
        this.especialidad = especialidad;
        changeSupport.firePropertyChange("especialidad", oldEspecialidad, especialidad);
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        String oldExperiencia = this.experiencia;
        this.experiencia = experiencia;
        changeSupport.firePropertyChange("experiencia", oldExperiencia, experiencia);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedico != null ? idMedico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.idMedico == null && other.idMedico != null) || (this.idMedico != null && !this.idMedico.equals(other.idMedico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "centromedico.Medicos[ idMedico=" + idMedico + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
