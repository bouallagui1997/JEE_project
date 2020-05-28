package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Questions database table.
 * 
 */
@Entity
@Table(name="Questions")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QuestionId")
	private int questionId;

	private int categories;

	@Column(name="date_question")
	private Date dateQuestion;

	@Column(name="detail_question")
	private String detailQuestion;

	@Column(name="title_question")
	private String titleQuestion;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	//bi-directional many-to-one association to Respons
	@OneToMany(mappedBy="question")
	private List<Respons> responses;

	public Question() {
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getCategories() {
		return this.categories;
	}

	public void setCategories(int categories) {
		this.categories = categories;
	}

	public Date getDateQuestion() {
		return this.dateQuestion;
	}

	public void setDateQuestion(Date dateQuestion) {
		this.dateQuestion = dateQuestion;
	}

	public String getDetailQuestion() {
		return this.detailQuestion;
	}

	public void setDetailQuestion(String detailQuestion) {
		this.detailQuestion = detailQuestion;
	}

	public String getTitleQuestion() {
		return this.titleQuestion;
	}

	public void setTitleQuestion(String titleQuestion) {
		this.titleQuestion = titleQuestion;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Respons> getResponses() {
		return this.responses;
	}

	public void setResponses(List<Respons> responses) {
		this.responses = responses;
	}

	public Respons addRespons(Respons respons) {
		getResponses().add(respons);
		respons.setQuestion(this);

		return respons;
	}

	public Respons removeRespons(Respons respons) {
		getResponses().remove(respons);
		respons.setQuestion(null);

		return respons;
	}

}