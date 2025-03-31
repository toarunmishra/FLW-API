
package com.iemr.flw.domain.iemr;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="m_UserDemographics")
@Data
public class M_UserDemographics {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Expose
	   @Column(name="DemographicID")
	   private Integer demographicID;
	   @Expose
	   @Column(name="UserID")
	   private  Integer userID; 
	   @Expose
	   @Column(name="FathersName")
	   private String fathersName;
	   @Expose
	   @Column(name="MothersName")
	   private String  mothersName;
	   @Expose
	   @Column(name="CommunityID")
	   private Integer communityID; 
	   @Expose
	   @Column(name="ReligionID")
	   private Integer religionID;
	   @Expose
	   @Column(name="AddressLine1")
	   private String addressLine1;
	   @Expose
	   @Column(name="AddressLine2")
	   private String addressLine2;
	   @Expose
	   @Column(name="PermAddressLine1")
	   private String permAddressLine1;
	   @Expose
	   @Column(name="PermAddressLine2")
	   private String permAddressLine2;
	   @Expose
	   @Column(name="PermanentAddress")
	   private String permanentAddress;
	   
	   
	   @Expose
	   @Column(name="PermStateID")
	   private Integer permStateID;
	   
	   @Expose
	   @Column(name="PermDistrictID")
	   private Integer permDistrictID;
	   @Expose
	   @Column(name="PermPinCode")
	   private Integer permPinCode;
	   @Expose
	   @Column(name="CityID")
	   private Integer cityID;
	   @Expose
	   @Column(name="StateID")
	   private Integer stateID;
	   @Expose
	   @Column(name="CountryID")
	   private Integer countryID;
	   @Expose
	   @Column(name="PinCode")
	   private String pinCode;
	   @Expose
	   @Column(name="IsPresent")
	   private Boolean isPresent;
	   @Expose
	   @Column(name="IsPermanent")
	   private Boolean isPermanent;
	   @Expose
	   @Column(name="Deleted",insertable = false, updatable = true)
	   private Boolean deleted;
	   @Expose
	   @Column(name="CreatedBy")
	   private String createdBy;
	   @Expose
	   @Column(name="CreatedDate",insertable = false, updatable = false)
	   private Timestamp createdDate;
	   @Expose
	   @Column(name="ModifiedBy")
	   private String modifiedBy;
	   @Expose
	   @Column(name="LastModDate",insertable = false, updatable = false)
	   private Timestamp lastModDate;
	   
	   @Expose
	   @Column(name="DistrictID")
	   private Integer districtID;
	   

	}



