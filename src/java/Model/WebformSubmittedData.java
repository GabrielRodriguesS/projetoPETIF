/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author PET BSI
 */
@Entity
@Table(name = "webform_submitted_data")
@NamedQueries({
    @NamedQuery(name = "WebformSubmittedData.findAll", query = "SELECT w FROM WebformSubmittedData w")})
public class WebformSubmittedData implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WebformSubmittedDataPK webformSubmittedDataPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "data")
    private String data;

    public WebformSubmittedData() {
    }

    public WebformSubmittedData(WebformSubmittedDataPK webformSubmittedDataPK) {
        this.webformSubmittedDataPK = webformSubmittedDataPK;
    }

    public WebformSubmittedData(WebformSubmittedDataPK webformSubmittedDataPK, String data) {
        this.webformSubmittedDataPK = webformSubmittedDataPK;
        this.data = data;
    }

    public WebformSubmittedData(int nid, int sid, short cid, String no) {
        this.webformSubmittedDataPK = new WebformSubmittedDataPK(nid, sid, cid, no);
    }

    public WebformSubmittedDataPK getWebformSubmittedDataPK() {
        return webformSubmittedDataPK;
    }

    public void setWebformSubmittedDataPK(WebformSubmittedDataPK webformSubmittedDataPK) {
        this.webformSubmittedDataPK = webformSubmittedDataPK;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (webformSubmittedDataPK != null ? webformSubmittedDataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebformSubmittedData)) {
            return false;
        }
        WebformSubmittedData other = (WebformSubmittedData) object;
        if ((this.webformSubmittedDataPK == null && other.webformSubmittedDataPK != null) || (this.webformSubmittedDataPK != null && !this.webformSubmittedDataPK.equals(other.webformSubmittedDataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.WebformSubmittedData[ webformSubmittedDataPK=" + webformSubmittedDataPK + " ]";
    }

}
