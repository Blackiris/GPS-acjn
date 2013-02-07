package fr.emse.server;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Position implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	Integer id;
	Integer pos;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Note note;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7069796640536945544L;

	public Position() {
		pos = -1;
		note = null;
	}

	public Position(Integer pos, Note note) {
		this.pos = pos;
		this.note = note;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

}
