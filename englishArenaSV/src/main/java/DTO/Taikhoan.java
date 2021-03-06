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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Taikhoan generated by hbm2java
 */
@Entity
@Table(name = "taikhoan", catalog = "trochoitienganh")
public class Taikhoan implements java.io.Serializable {

	private Integer maTk;
	private String email;
	private String password;
	private int loaitaikhoan;
	private int trangThai;
	private Set<Ctdiem> ctdiems = new HashSet<Ctdiem>(0);
	private Set<Cauhoi> cauhois = new HashSet<Cauhoi>(0);

	public Taikhoan() {
	}

	public Taikhoan(String email, String password, int loaitaikhoan, int trangThai) {
		this.email = email;
		this.password = password;
		this.loaitaikhoan = loaitaikhoan;
		this.trangThai = trangThai;
	}

	public Taikhoan(String email, String password, int loaitaikhoan, int trangThai, Set<Ctdiem> ctdiems,
			Set<Cauhoi> cauhois) {
		this.email = email;
		this.password = password;
		this.loaitaikhoan = loaitaikhoan;
		this.trangThai = trangThai;
		this.ctdiems = ctdiems;
		this.cauhois = cauhois;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "MaTK", unique = true, nullable = false)
	public Integer getMaTk() {
		return this.maTk;
	}

	public void setMaTk(Integer maTk) {
		this.maTk = maTk;
	}

	@Column(name = "email", nullable = false, length = 65535)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "loaitaikhoan", nullable = false)
	public int getLoaitaikhoan() {
		return this.loaitaikhoan;
	}

	public void setLoaitaikhoan(int loaitaikhoan) {
		this.loaitaikhoan = loaitaikhoan;
	}

	@Column(name = "TrangThai", nullable = false)
	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taikhoan")
	public Set<Ctdiem> getCtdiems() {
		return this.ctdiems;
	}

	public void setCtdiems(Set<Ctdiem> ctdiems) {
		this.ctdiems = ctdiems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taikhoan")
	public Set<Cauhoi> getCauhois() {
		return this.cauhois;
	}

	public void setCauhois(Set<Cauhoi> cauhois) {
		this.cauhois = cauhois;
	}

}
