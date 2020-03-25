package net.ecbank.fwk.admin.sample.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.ecbank.fwk.admin.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="Z_AUTHOR") //샘플 테이블 'Z_' prefix
@TableGenerator(name="Z_AUTHOR_SEQ_GENERATOR", table="COMTECOPSEQ", pkColumnValue="Z_AUTHOR_ID", pkColumnName="TABLE_NAME", valueColumnName="NEXT_ID", allocationSize=1)
public class Author extends BaseEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.TABLE, generator="Z_AUTHOR_SEQ_GENERATOR")
	@Column(name="AUTHOR_ID")
	private Long id;
	
	private String name;
	
	
	@ToString.Exclude
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY)
	private List<Book> books;
	
	public Author(String name) {
		super();
		this.name = name;
	}

	public void addBook(Book book) {
		books.add(book);
		book.setAuthor(this);
	}
	
}
