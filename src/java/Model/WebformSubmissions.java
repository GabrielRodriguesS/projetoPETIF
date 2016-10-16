/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author PET BSI
 */
@Entity
@Table(name = "webform_submissions")
@NamedQueries({
    @NamedQuery(name = "WebformSubmissions.findAll", query = "SELECT w FROM WebformSubmissions w")})
public class WebformSubmissions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sid")
    private Integer sid;
    @Basic(optional = false)
    @Column(name = "nid")
    private int nid;
    @Basic(optional = false)
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @Column(name = "is_draft")
    private short isDraft;
    @Basic(optional = false)
    @Column(name = "submitted")
    private int submitted;
    @Column(name = "remote_addr")
    private String remoteAddr;

    public WebformSubmissions() {
    }

    public WebformSubmissions(Integer sid) {
        this.sid = sid;
    }

    public WebformSubmissions(Integer sid, int nid, int uid, short isDraft, int submitted) {
        this.sid = sid;
        this.nid = nid;
        this.uid = uid;
        this.isDraft = isDraft;
        this.submitted = submitted;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public short getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(short isDraft) {
        this.isDraft = isDraft;
    }

    public int getSubmitted() {
        return submitted;
    }

    public void setSubmitted(int submitted) {
        this.submitted = submitted;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebformSubmissions)) {
            return false;
        }
        WebformSubmissions other = (WebformSubmissions) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.WebformSubmissions[ sid=" + sid + " ]";
    }
    
}
