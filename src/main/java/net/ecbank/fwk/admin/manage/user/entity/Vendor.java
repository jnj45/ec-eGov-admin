package net.ecbank.fwk.admin.manage.user.entity;

import net.ecbank.fwk.admin.common.entity.BaseEntity;

/*@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@Table(name="EF_VENDOR")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"}) */
public class Vendor extends BaseEntity {
	
	/*@Id
	@Column(name="VENDOR_CD")
	private String vendorCd;
	
	@Column(name="VENDOR_NM")
	private String vendorNm;
	
	@Column(name="VENDOR_BIZ_NO")
	private String vendorBizNo;
	
	@Column(name="VENDOR_REPRE_NM")
	private String vendorRepreNm;
	
	@Column(name="BIZ_CLASS")
	private String bizClass;
	
	@Column(name="BIZ_TYPE")
	private String bizType;
	
	@Column(name="ADDR")
	private String addr;
	
	@Column(name="FAX")
	private String fax;
	
	@Column(name="TEL")
	private String tel;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="USE_YN")
	private String useYn;
	
	@Column(name="STATUS")
	private String status;
	
	@JsonIgnore
	@OneToMany(mappedBy="vendor",fetch=FetchType.LAZY)
	private List<VendorUser> vendorUsers = new ArrayList<VendorUser>();*/
	
}
