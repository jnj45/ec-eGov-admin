package net.ecbank.fwk.admin.sample.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.ecbank.fwk.admin.entity.BaseEntity;

//@Data
@Getter @Setter
@ToString(of= {"id","title","totalPage"})
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="Z_BOOK") //샘플 테이블 'Z_' prefix
@TableGenerator(name="Z_BOOK_SEQ_GENERATOR", table="COMTECOPSEQ", pkColumnValue="Z_BOOK_ID", pkColumnName="TABLE_NAME", valueColumnName="NEXT_ID", allocationSize=1)
public class Book extends BaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="Z_BOOK_SEQ_GENERATOR")
	@Column(name="BOOK_ID")
	private Long id;
	
	private String title;
	
	private Long totalPage;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private Author author;

	public Book(String title, long totalPage, Author author) {
		super();
		this.title = title;
		this.totalPage = totalPage;
		this.author = author;
	}
	
	public void changeAuthor(Author author) {
		this.author = author;
		author.getBooks().add(this);
	}
	
}
