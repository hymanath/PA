
package com.itgrids.survey.soa.endpoints;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for surveyVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="surveyVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acConstList" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="assemblyNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="boothId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="booths" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="constituency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituencyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diffPercentage" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="finalOptions" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="goodCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hamlet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hamletId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="keenCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="keenSummaryCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="keenWeak" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keenWeakCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="keenWeakOptions" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lessSamples" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="localBody" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localBodyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationScopeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationValue" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationWiseList" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="locations" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="options" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="optionsReq" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="otherOptsPerc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherOptsReq" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="panchayatId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="panchayatName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parliament" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parliamentNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="partNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="percent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publicationDateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="questionsList" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="scPerc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stPerc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="subList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="suggestionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="survey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surveyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveyLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surveyQuestionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveysList" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tehsil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tehsilId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalKeenSummary" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalKeenSummaryPercentage" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ttlKnSmmryPrcntg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ttlLctnsGtCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ttlLocationsCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="village" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="villageId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="weakCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "surveyVO", propOrder = {
    "acConstList",
    "assemblyNo",
    "boothId",
    "booths",
    "constituency",
    "constituencyId",
    "date",
    "description",
    "diffPercentage",
    "district",
    "districtId",
    "finalOptions",
    "goodCount",
    "hamlet",
    "hamletId",
    "keenCount",
    "keenSummaryCount",
    "keenWeak",
    "keenWeakCount",
    "keenWeakOptions",
    "lessSamples",
    "localBody",
    "localBodyId",
    "locationId",
    "locationName",
    "locationScopeId",
    "locationValue",
    "locationWiseList",
    "locations",
    "name",
    "options",
    "optionsReq",
    "otherOptsPerc",
    "otherOptsReq",
    "panchayatId",
    "panchayatName",
    "parliament",
    "parliamentNo",
    "partNo",
    "percent",
    "publicationDateId",
    "questionsList",
    "scPerc",
    "stPerc",
    "state",
    "stateId",
    "subList",
    "suggestionId",
    "survey",
    "surveyId",
    "surveyLocation",
    "surveyQuestionId",
    "surveysList",
    "tehsil",
    "tehsilId",
    "time",
    "total",
    "totalCount",
    "totalKeenSummary",
    "totalKeenSummaryPercentage",
    "ttlKnSmmryPrcntg",
    "ttlLctnsGtCount",
    "ttlLocationsCount",
    "userId",
    "userName",
    "village",
    "villageId",
    "weakCount"
})
public class SurveyVO {

    @XmlElement(nillable = true)
    protected List<SurveyVO> acConstList;
    protected Long assemblyNo;
    protected Long boothId;
    @XmlElement(nillable = true)
    protected List<Long> booths;
    protected String constituency;
    protected Long constituencyId;
    protected String date;
    protected String description;
    protected Double diffPercentage;
    protected String district;
    protected Long districtId;
    @XmlElement(nillable = true)
    protected List<OptionVO> finalOptions;
    protected int goodCount;
    protected String hamlet;
    protected Long hamletId;
    protected int keenCount;
    protected Long keenSummaryCount;
    protected String keenWeak;
    protected int keenWeakCount;
    @XmlElement(nillable = true)
    protected List<OptionVO> keenWeakOptions;
    protected boolean lessSamples;
    protected String localBody;
    protected Long localBodyId;
    protected Long locationId;
    protected String locationName;
    protected Long locationScopeId;
    protected Long locationValue;
    @XmlElement(nillable = true)
    protected List<SurveyVO> locationWiseList;
    @XmlElement(nillable = true)
    protected List<SurveyVO> locations;
    protected String name;
    @XmlElement(nillable = true)
    protected List<OptionVO> options;
    @XmlElement(nillable = true)
    protected List<OptionVO> optionsReq;
    protected String otherOptsPerc;
    protected Long otherOptsReq;
    protected Long panchayatId;
    protected String panchayatName;
    protected String parliament;
    protected Long parliamentNo;
    protected Long partNo;
    protected String percent;
    protected Long publicationDateId;
    @XmlElement(nillable = true)
    protected List<SurveyVO> questionsList;
    protected String scPerc;
    protected String stPerc;
    protected String state;
    protected Long stateId;
    @XmlElement(nillable = true)
    protected List<Long> subList;
    protected Long suggestionId;
    protected String survey;
    protected Long surveyId;
    protected String surveyLocation;
    protected Long surveyQuestionId;
    @XmlElement(nillable = true)
    protected List<SurveyVO> surveysList;
    protected String tehsil;
    protected Long tehsilId;
    protected String time;
    protected Long total;
    protected Long totalCount;
    protected Long totalKeenSummary;
    protected Double totalKeenSummaryPercentage;
    protected String ttlKnSmmryPrcntg;
    protected int ttlLctnsGtCount;
    protected int ttlLocationsCount;
    protected Long userId;
    protected String userName;
    protected String village;
    protected Long villageId;
    protected int weakCount;

    /**
     * Gets the value of the acConstList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acConstList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcConstList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyVO }
     * 
     * 
     */
    public List<SurveyVO> getAcConstList() {
        if (acConstList == null) {
            acConstList = new ArrayList<SurveyVO>();
        }
        return this.acConstList;
    }

    /**
     * Gets the value of the assemblyNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssemblyNo() {
        return assemblyNo;
    }

    /**
     * Sets the value of the assemblyNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssemblyNo(Long value) {
        this.assemblyNo = value;
    }

    /**
     * Gets the value of the boothId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBoothId() {
        return boothId;
    }

    /**
     * Sets the value of the boothId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBoothId(Long value) {
        this.boothId = value;
    }

    /**
     * Gets the value of the booths property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the booths property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBooths().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getBooths() {
        if (booths == null) {
            booths = new ArrayList<Long>();
        }
        return this.booths;
    }

    /**
     * Gets the value of the constituency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstituency() {
        return constituency;
    }

    /**
     * Sets the value of the constituency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstituency(String value) {
        this.constituency = value;
    }

    /**
     * Gets the value of the constituencyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConstituencyId() {
        return constituencyId;
    }

    /**
     * Sets the value of the constituencyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConstituencyId(Long value) {
        this.constituencyId = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the diffPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiffPercentage() {
        return diffPercentage;
    }

    /**
     * Sets the value of the diffPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiffPercentage(Double value) {
        this.diffPercentage = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the districtId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * Sets the value of the districtId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDistrictId(Long value) {
        this.districtId = value;
    }

    /**
     * Gets the value of the finalOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the finalOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinalOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getFinalOptions() {
        if (finalOptions == null) {
            finalOptions = new ArrayList<OptionVO>();
        }
        return this.finalOptions;
    }

    /**
     * Gets the value of the goodCount property.
     * 
     */
    public int getGoodCount() {
        return goodCount;
    }

    /**
     * Sets the value of the goodCount property.
     * 
     */
    public void setGoodCount(int value) {
        this.goodCount = value;
    }

    /**
     * Gets the value of the hamlet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHamlet() {
        return hamlet;
    }

    /**
     * Sets the value of the hamlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHamlet(String value) {
        this.hamlet = value;
    }

    /**
     * Gets the value of the hamletId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHamletId() {
        return hamletId;
    }

    /**
     * Sets the value of the hamletId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHamletId(Long value) {
        this.hamletId = value;
    }

    /**
     * Gets the value of the keenCount property.
     * 
     */
    public int getKeenCount() {
        return keenCount;
    }

    /**
     * Sets the value of the keenCount property.
     * 
     */
    public void setKeenCount(int value) {
        this.keenCount = value;
    }

    /**
     * Gets the value of the keenSummaryCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getKeenSummaryCount() {
        return keenSummaryCount;
    }

    /**
     * Sets the value of the keenSummaryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setKeenSummaryCount(Long value) {
        this.keenSummaryCount = value;
    }

    /**
     * Gets the value of the keenWeak property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeenWeak() {
        return keenWeak;
    }

    /**
     * Sets the value of the keenWeak property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeenWeak(String value) {
        this.keenWeak = value;
    }

    /**
     * Gets the value of the keenWeakCount property.
     * 
     */
    public int getKeenWeakCount() {
        return keenWeakCount;
    }

    /**
     * Sets the value of the keenWeakCount property.
     * 
     */
    public void setKeenWeakCount(int value) {
        this.keenWeakCount = value;
    }

    /**
     * Gets the value of the keenWeakOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keenWeakOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeenWeakOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getKeenWeakOptions() {
        if (keenWeakOptions == null) {
            keenWeakOptions = new ArrayList<OptionVO>();
        }
        return this.keenWeakOptions;
    }

    /**
     * Gets the value of the lessSamples property.
     * 
     */
    public boolean isLessSamples() {
        return lessSamples;
    }

    /**
     * Sets the value of the lessSamples property.
     * 
     */
    public void setLessSamples(boolean value) {
        this.lessSamples = value;
    }

    /**
     * Gets the value of the localBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalBody() {
        return localBody;
    }

    /**
     * Sets the value of the localBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalBody(String value) {
        this.localBody = value;
    }

    /**
     * Gets the value of the localBodyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLocalBodyId() {
        return localBodyId;
    }

    /**
     * Sets the value of the localBodyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLocalBodyId(Long value) {
        this.localBodyId = value;
    }

    /**
     * Gets the value of the locationId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLocationId(Long value) {
        this.locationId = value;
    }

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

    /**
     * Gets the value of the locationScopeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLocationScopeId() {
        return locationScopeId;
    }

    /**
     * Sets the value of the locationScopeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLocationScopeId(Long value) {
        this.locationScopeId = value;
    }

    /**
     * Gets the value of the locationValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLocationValue() {
        return locationValue;
    }

    /**
     * Sets the value of the locationValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLocationValue(Long value) {
        this.locationValue = value;
    }

    /**
     * Gets the value of the locationWiseList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locationWiseList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocationWiseList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyVO }
     * 
     * 
     */
    public List<SurveyVO> getLocationWiseList() {
        if (locationWiseList == null) {
            locationWiseList = new ArrayList<SurveyVO>();
        }
        return this.locationWiseList;
    }

    /**
     * Gets the value of the locations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyVO }
     * 
     * 
     */
    public List<SurveyVO> getLocations() {
        if (locations == null) {
            locations = new ArrayList<SurveyVO>();
        }
        return this.locations;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the options property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getOptions() {
        if (options == null) {
            options = new ArrayList<OptionVO>();
        }
        return this.options;
    }

    /**
     * Gets the value of the optionsReq property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionsReq property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionsReq().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getOptionsReq() {
        if (optionsReq == null) {
            optionsReq = new ArrayList<OptionVO>();
        }
        return this.optionsReq;
    }

    /**
     * Gets the value of the otherOptsPerc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherOptsPerc() {
        return otherOptsPerc;
    }

    /**
     * Sets the value of the otherOptsPerc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherOptsPerc(String value) {
        this.otherOptsPerc = value;
    }

    /**
     * Gets the value of the otherOptsReq property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOtherOptsReq() {
        return otherOptsReq;
    }

    /**
     * Sets the value of the otherOptsReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOtherOptsReq(Long value) {
        this.otherOptsReq = value;
    }

    /**
     * Gets the value of the panchayatId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPanchayatId() {
        return panchayatId;
    }

    /**
     * Sets the value of the panchayatId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPanchayatId(Long value) {
        this.panchayatId = value;
    }

    /**
     * Gets the value of the panchayatName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPanchayatName() {
        return panchayatName;
    }

    /**
     * Sets the value of the panchayatName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPanchayatName(String value) {
        this.panchayatName = value;
    }

    /**
     * Gets the value of the parliament property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParliament() {
        return parliament;
    }

    /**
     * Sets the value of the parliament property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParliament(String value) {
        this.parliament = value;
    }

    /**
     * Gets the value of the parliamentNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParliamentNo() {
        return parliamentNo;
    }

    /**
     * Sets the value of the parliamentNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParliamentNo(Long value) {
        this.parliamentNo = value;
    }

    /**
     * Gets the value of the partNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPartNo() {
        return partNo;
    }

    /**
     * Sets the value of the partNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPartNo(Long value) {
        this.partNo = value;
    }

    /**
     * Gets the value of the percent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercent() {
        return percent;
    }

    /**
     * Sets the value of the percent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercent(String value) {
        this.percent = value;
    }

    /**
     * Gets the value of the publicationDateId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPublicationDateId() {
        return publicationDateId;
    }

    /**
     * Sets the value of the publicationDateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPublicationDateId(Long value) {
        this.publicationDateId = value;
    }

    /**
     * Gets the value of the questionsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the questionsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestionsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyVO }
     * 
     * 
     */
    public List<SurveyVO> getQuestionsList() {
        if (questionsList == null) {
            questionsList = new ArrayList<SurveyVO>();
        }
        return this.questionsList;
    }

    /**
     * Gets the value of the scPerc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScPerc() {
        return scPerc;
    }

    /**
     * Sets the value of the scPerc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScPerc(String value) {
        this.scPerc = value;
    }

    /**
     * Gets the value of the stPerc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStPerc() {
        return stPerc;
    }

    /**
     * Sets the value of the stPerc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStPerc(String value) {
        this.stPerc = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the stateId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStateId() {
        return stateId;
    }

    /**
     * Sets the value of the stateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStateId(Long value) {
        this.stateId = value;
    }

    /**
     * Gets the value of the subList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSubList() {
        if (subList == null) {
            subList = new ArrayList<Long>();
        }
        return this.subList;
    }

    /**
     * Gets the value of the suggestionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSuggestionId() {
        return suggestionId;
    }

    /**
     * Sets the value of the suggestionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSuggestionId(Long value) {
        this.suggestionId = value;
    }

    /**
     * Gets the value of the survey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurvey() {
        return survey;
    }

    /**
     * Sets the value of the survey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurvey(String value) {
        this.survey = value;
    }

    /**
     * Gets the value of the surveyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSurveyId() {
        return surveyId;
    }

    /**
     * Sets the value of the surveyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSurveyId(Long value) {
        this.surveyId = value;
    }

    /**
     * Gets the value of the surveyLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurveyLocation() {
        return surveyLocation;
    }

    /**
     * Sets the value of the surveyLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurveyLocation(String value) {
        this.surveyLocation = value;
    }

    /**
     * Gets the value of the surveyQuestionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSurveyQuestionId() {
        return surveyQuestionId;
    }

    /**
     * Sets the value of the surveyQuestionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSurveyQuestionId(Long value) {
        this.surveyQuestionId = value;
    }

    /**
     * Gets the value of the surveysList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surveysList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurveysList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyVO }
     * 
     * 
     */
    public List<SurveyVO> getSurveysList() {
        if (surveysList == null) {
            surveysList = new ArrayList<SurveyVO>();
        }
        return this.surveysList;
    }

    /**
     * Gets the value of the tehsil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTehsil() {
        return tehsil;
    }

    /**
     * Sets the value of the tehsil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTehsil(String value) {
        this.tehsil = value;
    }

    /**
     * Gets the value of the tehsilId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTehsilId() {
        return tehsilId;
    }

    /**
     * Sets the value of the tehsilId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTehsilId(Long value) {
        this.tehsilId = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotal(Long value) {
        this.total = value;
    }

    /**
     * Gets the value of the totalCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalCount(Long value) {
        this.totalCount = value;
    }

    /**
     * Gets the value of the totalKeenSummary property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalKeenSummary() {
        return totalKeenSummary;
    }

    /**
     * Sets the value of the totalKeenSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalKeenSummary(Long value) {
        this.totalKeenSummary = value;
    }

    /**
     * Gets the value of the totalKeenSummaryPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalKeenSummaryPercentage() {
        return totalKeenSummaryPercentage;
    }

    /**
     * Sets the value of the totalKeenSummaryPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalKeenSummaryPercentage(Double value) {
        this.totalKeenSummaryPercentage = value;
    }

    /**
     * Gets the value of the ttlKnSmmryPrcntg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTtlKnSmmryPrcntg() {
        return ttlKnSmmryPrcntg;
    }

    /**
     * Sets the value of the ttlKnSmmryPrcntg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTtlKnSmmryPrcntg(String value) {
        this.ttlKnSmmryPrcntg = value;
    }

    /**
     * Gets the value of the ttlLctnsGtCount property.
     * 
     */
    public int getTtlLctnsGtCount() {
        return ttlLctnsGtCount;
    }

    /**
     * Sets the value of the ttlLctnsGtCount property.
     * 
     */
    public void setTtlLctnsGtCount(int value) {
        this.ttlLctnsGtCount = value;
    }

    /**
     * Gets the value of the ttlLocationsCount property.
     * 
     */
    public int getTtlLocationsCount() {
        return ttlLocationsCount;
    }

    /**
     * Sets the value of the ttlLocationsCount property.
     * 
     */
    public void setTtlLocationsCount(int value) {
        this.ttlLocationsCount = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserId(Long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the village property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVillage() {
        return village;
    }

    /**
     * Sets the value of the village property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVillage(String value) {
        this.village = value;
    }

    /**
     * Gets the value of the villageId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVillageId() {
        return villageId;
    }

    /**
     * Sets the value of the villageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVillageId(Long value) {
        this.villageId = value;
    }

    /**
     * Gets the value of the weakCount property.
     * 
     */
    public int getWeakCount() {
        return weakCount;
    }

    /**
     * Sets the value of the weakCount property.
     * 
     */
    public void setWeakCount(int value) {
        this.weakCount = value;
    }

}
