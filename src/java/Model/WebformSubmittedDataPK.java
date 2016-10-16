/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author PET BSI
 */
@Embeddable
public class WebformSubmittedDataPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nid")
    private int nid;
    @Basic(optional = false)
    @Column(name = "sid")
    private int sid;
    @Basic(optional = false)
    @Column(name = "cid")
    private short cid;
    @Basic(optional = false)
    @Column(name = "no")
    private String no;

    public WebformSubmittedDataPK() {
    }

    public WebformSubmittedDataPK(int nid, int sid, short cid, String no) {
        this.nid = nid;
        this.sid = sid;
        this.cid = cid;
        this.no = no;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public short getCid() {
        return cid;
    }

    public void setCid(short cid) {
        this.cid = cid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nid;
        hash += (int) sid;
        hash += (int) cid;
        hash += (no != null ? no.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebformSubmittedDataPK)) {
            return false;
        }
        WebformSubmittedDataPK other = (WebformSubmittedDataPK) object;
        if (this.nid != other.nid) {
            return false;
        }
        if (this.sid != other.sid) {
            return false;
        }
        if (this.cid != other.cid) {
            return false;
        }
        if ((this.no == null && other.no != null) || (this.no != null && !this.no.equals(other.no))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.WebformSubmittedDataPK[ nid=" + nid + ", sid=" + sid + ", cid=" + cid + ", no=" + no + " ]";
    }
    
}
