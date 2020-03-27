package net.ecbank.fwk.admin.sys.repository;

import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import net.ecbank.fwk.admin.sys.entity.CodeGroup;
import net.ecbank.fwk.admin.sys.entity.QCodeGroup;
import static net.ecbank.fwk.admin.sys.entity.QCodeGroup.codeGroup;


@RequiredArgsConstructor
public class CodeGroupRepositoryImpl {
	
	private final JPAQueryFactory queryFactory;
	
	public List<CodeGroup> searchCodeGroup(CodeGroup codeGrp){
		
		List<CodeGroup> results = queryFactory.selectFrom(new QCodeGroup("codeGroup"))
				.where(
						codeGroupNmLike(codeGrp.getCodeGrpNm()),
						codeGroupCdEq(codeGrp.getCodeGrp())
						)
				.fetch();
		
		return results;
	}
	
	private BooleanExpression codeGroupNmLike(String codeGroupNm) {
		return hasText(codeGroupNm) ? codeGroup.codeGrpNm.contains(codeGroupNm) : null;
	}
	
	private BooleanExpression codeGroupCdEq(String codeGroupCd) {
		return hasText(codeGroupCd) ? codeGroup.codeGrp.eq(codeGroupCd) : null;
	}
}
