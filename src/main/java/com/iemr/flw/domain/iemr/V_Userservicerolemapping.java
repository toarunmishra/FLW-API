package com.iemr.flw.domain.iemr;
import com.google.gson.annotations.Expose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
@Entity
@Data
@Table(name="v_userservicerolemapping")
public class V_Userservicerolemapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "uSRMappingID")
	private Integer uSRMappingID;

	@Expose
	@Column(name = "UserID")
	private Integer userID;
	@Expose
	@Column(name = "Name")
	private String name;
	@Expose
	@Column(name = "UserName")
	private String userName;
	@Expose
	@Column(name = "ServiceID")
	private Integer serviceID;
	@Expose
	@Column(name = "ServiceName")
	private String serviceName;
	@Expose
	@Column(name = "StateID")
	private Integer stateID;
	@Expose
	@Column(name = "StateName")
	private String stateName;
	@Expose
	@Column(name = "WorkingDistrictID")
	private String workingDistrictID;
	@Expose
	@Column(name = "WorkingDistrictName")
	private String workingDistrictName;
	@Expose
	@Column(name = "WorkingLocationID")
	private String workingLocationID;
	@Expose
	@Column(name = "LocationName")
	private String locationName;
	@Expose
	@Column(name = "WorkingLocationAddress")
	private String workingLocationAddress;
	@Expose
	@Column(name = "RoleID")
	private Integer roleID;
	@Expose
	@Column(name = "RoleName")
	private String roleName;
	@Expose
	@Column(name = "AgentID")
	private String agentID;
	@Expose
	@Column(name = "PSMStatusID")
	private Integer pSMStatusID;
	@Expose
	@Column(name = "PSMStatus")
	private String pSMStatus;
	@Expose
	@Column(name = "UserServciceRoleDeleted")
	private Boolean userServciceRoleDeleted;
	@Expose
	@Column(name = "UserDeleted")
	private Boolean userDeleted;
	@Expose
	@Column(name = "ServiceProviderDeleted")
	private Boolean serviceProviderDeleted;
	@Expose
	@Column(name = "RoleDeleted")
	private Boolean roleDeleted;
	@Expose
	@Column(name = "ProviderServiceMappingDeleted")
	private Boolean providerServiceMappingDeleted;


	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "ServiceProviderID")
	private Integer serviceProviderID;

	@Expose
	@Column(name = "isInbound")
	private Boolean inbound;

	@Expose
	@Column(name = "isOutbound")
	private Boolean outbound;

	@Expose
	@Column(name = "blockid")
	private Integer blockID;
	@Expose
	@Column(name = "blockname")
	private String blockName;
	@Expose
	@Column(name = "villageid")
	private String villageidDb;
	@Expose
	@Column(name = "villagename")
	private String villageNameDb;
	@Transient
	private String[] villageID;
	@Transient
	private String[] villageName;

	@Expose
	@Column(name = "isSanjeevani")
	private Boolean isSanjeevani;
}