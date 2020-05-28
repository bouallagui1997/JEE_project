package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



/**
 * The persistent class for the Responses database table.
 * 
 */
@Entity
@Table(name="Responses")
@NamedQuery(name="Respons.findAll", query="SELECT r FROM Respons r")
public class Respons implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ResponseId")
	private int responseId;

	@Column(name="date_resp")
	private Date dateResp;

	@Column(name="detail_resp")
	private String detailResp;

	@Column(name="title_resp")
	private String titleResp;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="QuestionId")
	private Question question;

	public Respons() {
	}

	public int getResponseId() {
		return this.responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public Date getDateResp() {
		return this.dateResp;
	}

	public void setDateResp(Date dateResp) {
		this.dateResp = dateResp;
	}

	public String getDetailResp() {
		return this.detailResp;
	}

	public void setDetailResp(String detailResp) {
		this.detailResp = detailResp;
	}

	public String getTitleResp() {
		return this.titleResp;
	}

	public void setTitleResp(String titleResp) {
		this.titleResp = titleResp;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}