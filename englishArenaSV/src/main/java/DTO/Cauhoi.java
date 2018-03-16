package DTO;
// Generated Oct 17, 2017 9:07:48 PM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cauhoi generated by hbm2java
 */
@Entity
@Table(name = "cauhoi", catalog = "trochoitienganh")
public class Cauhoi implements java.io.Serializable {

	private Integer maCh;
	private Taikhoan taikhoan;
	private String noiDung;
	private int capDo;
	private Set<Cautraloi> cautralois = new HashSet<Cautraloi>(0);

	public Cauhoi() {
	}

	public Cauhoi(Taikhoan taikhoan, String noiDung, int capDo) {
		this.taikhoan = taikhoan;
		this.noiDung = noiDung;
		this.capDo = capDo;
	}

	public Cauhoi(Taikhoan taikhoan, String noiDung, int capDo, Set<Cautraloi> cautralois) {
		this.taikhoan = taikhoan;
		this.noiDung = noiDung;
		this.capDo = capDo;
		this.cautralois = cautralois;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "MaCH", unique = true, nullable = false)
	public Integer getMaCh() {
		return this.maCh;
	}

	public void setMaCh(Integer maCh) {
		this.maCh = maCh;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaTK", nullable = false)
	public Taikhoan getTaikhoan() {
		return this.taikhoan;
	}

	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}

	@Column(name = "NoiDung", nullable = false, length = 65535)
	public String getNoiDung() {
		return this.noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	@Column(name = "CapDo", nullable = false)
	public int getCapDo() {
		return this.capDo;
	}

	public void setCapDo(int capDo) {
		this.capDo = capDo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cauhoi")
	public Set<Cautraloi> getCautralois() {
		return this.cautralois;
	}

	public void setCautralois(Set<Cautraloi> cautralois) {
		this.cautralois = cautralois;
	}

}