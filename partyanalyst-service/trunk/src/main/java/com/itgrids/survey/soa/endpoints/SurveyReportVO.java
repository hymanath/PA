
package com.itgrids.survey.soa.endpoints;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for surveyReportVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="surveyReportVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ageSelected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allQuestions" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="areRange" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="areaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="boothId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="booths" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="considerDates" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="considerProfileFields" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="constituency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituencyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="constituencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="constituencyNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="constnNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="credPercentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="dblPercentage" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="effectedInErrorCount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="endAge" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="errorCorrection" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fieldCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fromFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isForRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="keenCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="keenWeakCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="keenWeakListSummary" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="localElectionBodyIds" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="locationTypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationValues" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="locationsList" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mandal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maximum" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="muncipality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="optionsList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="panchayat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parliamentConstituencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parliamentConstituencyNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="percent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percent1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="percentFloat" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="percentage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="profileFieldId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="profileFieldOptionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="questionIds" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="questionList" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="questionsList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rangeVal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="regionIds" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scopeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="spanCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="spnCnt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="srvyAnswrIds" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="startAge" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="subFieldsMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="subList" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subList1" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subLocations" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subProfileFieldId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="subProfileFieldValues" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subQuestionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveyCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveyMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="surveyMap1">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="surveyQuestionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveysList" type="{http://endpoints.soa.survey.itgrids.com/}surveyVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tehsil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="todaysbooths" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalBoothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalVoterCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voterCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="voterPercent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="voterPercentFloat" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="weakCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="withOptionType1" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="withOptionType2" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "surveyReportVO", propOrder = {
    "ageSelected",
    "allQuestions",
    "areRange",
    "areaType",
    "boothCount",
    "boothId",
    "booths",
    "code",
    "considerDates",
    "considerProfileFields",
    "constituency",
    "constituencyId",
    "constituencyName",
    "constituencyNo",
    "constnNo",
    "count",
    "credPercentage",
    "dblPercentage",
    "effectedInErrorCount",
    "endAge",
    "endDate",
    "errorCorrection",
    "fieldCount",
    "fromFilter",
    "id",
    "isForRange",
    "keenCount",
    "keenWeakCount",
    "keenWeakListSummary",
    "localElectionBodyIds",
    "locationTypeId",
    "locationValues",
    "locationsList",
    "mandal",
    "maximum",
    "muncipality",
    "name",
    "optionId",
    "optionsList",
    "panchayat",
    "parliamentConstituencyName",
    "parliamentConstituencyNo",
    "partNo",
    "partyId",
    "percent",
    "percent1",
    "percentFloat",
    "percentage",
    "profileFieldId",
    "profileFieldOptionId",
    "questionIds",
    "questionList",
    "questionsList",
    "rangeVal",
    "regionId",
    "regionIds",
    "scope",
    "scopeId",
    "spanCount",
    "spnCnt",
    "srvyAnswrIds",
    "startAge",
    "startDate",
    "subFieldsMap",
    "subList",
    "subList1",
    "subLocations",
    "subProfileFieldId",
    "subProfileFieldValues",
    "subQuestionId",
    "surveyCount",
    "surveyId",
    "surveyMap",
    "surveyMap1",
    "surveyQuestionId",
    "surveysList",
    "tehsil",
    "todaysbooths",
    "total",
    "totalBoothCount",
    "totalCount",
    "totalVoterCount",
    "url",
    "voterCount",
    "voterPercent",
    "voterPercentFloat",
    "weakCount",
    "withOptionType1",
    "withOptionType2"
})
public class SurveyReportVO {

    protected String ageSelected;
    protected boolean allQuestions;
    protected Double areRange;
    protected String areaType;
    protected Long boothCount;
    protected Long boothId;
    @XmlElement(nillable = true)
    protected List<String> booths;
    protected String code;
    protected boolean considerDates;
    protected boolean considerProfileFields;
    protected String constituency;
    protected Long constituencyId;
    protected String constituencyName;
    protected Long constituencyNo;
    protected Long constnNo;
    protected Long count;
    protected double credPercentage;
    protected double dblPercentage;
    protected boolean effectedInErrorCount;
    protected Long endAge;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected boolean errorCorrection;
    protected Long fieldCount;
    protected String fromFilter;
    protected Long id;
    protected String isForRange;
    protected int keenCount;
    protected int keenWeakCount;
    @XmlElement(nillable = true)
    protected List<OptionVO> keenWeakListSummary;
    @XmlElement(nillable = true)
    protected List<Long> localElectionBodyIds;
    protected Long locationTypeId;
    @XmlElement(nillable = true)
    protected List<Long> locationValues;
    @XmlElement(nillable = true)
    protected List<SurveyVO> locationsList;
    protected boolean mandal;
    protected boolean maximum;
    protected String muncipality;
    protected String name;
    protected Long optionId;
    @XmlElement(nillable = true)
    protected List<String> optionsList;
    protected String panchayat;
    protected String parliamentConstituencyName;
    protected String parliamentConstituencyNo;
    protected String partNo;
    protected Long partyId;
    protected String percent;
    protected Integer percent1;
    protected Float percentFloat;
    protected String percentage;
    protected Long profileFieldId;
    protected Long profileFieldOptionId;
    @XmlElement(nillable = true)
    protected List<Long> questionIds;
    @XmlElement(nillable = true)
    protected List<GenericVO> questionList;
    @XmlElement(nillable = true)
    protected List<String> questionsList;
    protected Long rangeVal;
    protected Long regionId;
    @XmlElement(nillable = true)
    protected List<Long> regionIds;
    protected String scope;
    protected Long scopeId;
    protected Long spanCount;
    protected int spnCnt;
    @XmlElement(nillable = true)
    protected List<Long> srvyAnswrIds;
    protected Long startAge;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    protected SurveyReportVO.SubFieldsMap subFieldsMap;
    @XmlElement(nillable = true)
    protected List<SurveyReportVO> subList;
    @XmlElement(nillable = true)
    protected List<SurveyReportVO> subList1;
    @XmlElement(nillable = true)
    protected List<Long> subLocations;
    protected Long subProfileFieldId;
    @XmlElement(nillable = true)
    protected List<Long> subProfileFieldValues;
    protected Long subQuestionId;
    protected Long surveyCount;
    protected Long surveyId;
    @XmlElement(required = true)
    protected SurveyReportVO.SurveyMap surveyMap;
    @XmlElement(required = true)
    protected SurveyReportVO.SurveyMap1 surveyMap1;
    protected Long surveyQuestionId;
    @XmlElement(nillable = true)
    protected List<SurveyVO> surveysList;
    protected String tehsil;
    @XmlElement(nillable = true)
    protected List<String> todaysbooths;
    protected Long total;
    protected Long totalBoothCount;
    protected Long totalCount;
    protected Long totalVoterCount;
    protected String url;
    protected Long voterCount;
    protected String voterPercent;
    protected Float voterPercentFloat;
    protected int weakCount;
    @XmlElement(nillable = true)
    protected List<Long> withOptionType1;
    @XmlElement(nillable = true)
    protected List<Long> withOptionType2;

    /**
     * Gets the value of the ageSelected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgeSelected() {
        return ageSelected;
    }

    /**
     * Sets the value of the ageSelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgeSelected(String value) {
        this.ageSelected = value;
    }

    /**
     * Gets the value of the allQuestions property.
     * 
     */
    public boolean isAllQuestions() {
        return allQuestions;
    }

    /**
     * Sets the value of the allQuestions property.
     * 
     */
    public void setAllQuestions(boolean value) {
        this.allQuestions = value;
    }

    /**
     * Gets the value of the areRange property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAreRange() {
        return areRange;
    }

    /**
     * Sets the value of the areRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAreRange(Double value) {
        this.areRange = value;
    }

    /**
     * Gets the value of the areaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaType() {
        return areaType;
    }

    /**
     * Sets the value of the areaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaType(String value) {
        this.areaType = value;
    }

    /**
     * Gets the value of the boothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBoothCount() {
        return boothCount;
    }

    /**
     * Sets the value of the boothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBoothCount(Long value) {
        this.boothCount = value;
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
     * {@link String }
     * 
     * 
     */
    public List<String> getBooths() {
        if (booths == null) {
            booths = new ArrayList<String>();
        }
        return this.booths;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the considerDates property.
     * 
     */
    public boolean isConsiderDates() {
        return considerDates;
    }

    /**
     * Sets the value of the considerDates property.
     * 
     */
    public void setConsiderDates(boolean value) {
        this.considerDates = value;
    }

    /**
     * Gets the value of the considerProfileFields property.
     * 
     */
    public boolean isConsiderProfileFields() {
        return considerProfileFields;
    }

    /**
     * Sets the value of the considerProfileFields property.
     * 
     */
    public void setConsiderProfileFields(boolean value) {
        this.considerProfileFields = value;
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
     * Gets the value of the constituencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstituencyName() {
        return constituencyName;
    }

    /**
     * Sets the value of the constituencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstituencyName(String value) {
        this.constituencyName = value;
    }

    /**
     * Gets the value of the constituencyNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConstituencyNo() {
        return constituencyNo;
    }

    /**
     * Sets the value of the constituencyNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConstituencyNo(Long value) {
        this.constituencyNo = value;
    }

    /**
     * Gets the value of the constnNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getConstnNo() {
        return constnNo;
    }

    /**
     * Sets the value of the constnNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setConstnNo(Long value) {
        this.constnNo = value;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCount(Long value) {
        this.count = value;
    }

    /**
     * Gets the value of the credPercentage property.
     * 
     */
    public double getCredPercentage() {
        return credPercentage;
    }

    /**
     * Sets the value of the credPercentage property.
     * 
     */
    public void setCredPercentage(double value) {
        this.credPercentage = value;
    }

    /**
     * Gets the value of the dblPercentage property.
     * 
     */
    public double getDblPercentage() {
        return dblPercentage;
    }

    /**
     * Sets the value of the dblPercentage property.
     * 
     */
    public void setDblPercentage(double value) {
        this.dblPercentage = value;
    }

    /**
     * Gets the value of the effectedInErrorCount property.
     * 
     */
    public boolean isEffectedInErrorCount() {
        return effectedInErrorCount;
    }

    /**
     * Sets the value of the effectedInErrorCount property.
     * 
     */
    public void setEffectedInErrorCount(boolean value) {
        this.effectedInErrorCount = value;
    }

    /**
     * Gets the value of the endAge property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEndAge() {
        return endAge;
    }

    /**
     * Sets the value of the endAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEndAge(Long value) {
        this.endAge = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the errorCorrection property.
     * 
     */
    public boolean isErrorCorrection() {
        return errorCorrection;
    }

    /**
     * Sets the value of the errorCorrection property.
     * 
     */
    public void setErrorCorrection(boolean value) {
        this.errorCorrection = value;
    }

    /**
     * Gets the value of the fieldCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFieldCount() {
        return fieldCount;
    }

    /**
     * Sets the value of the fieldCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFieldCount(Long value) {
        this.fieldCount = value;
    }

    /**
     * Gets the value of the fromFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromFilter() {
        return fromFilter;
    }

    /**
     * Sets the value of the fromFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromFilter(String value) {
        this.fromFilter = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the isForRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsForRange() {
        return isForRange;
    }

    /**
     * Sets the value of the isForRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsForRange(String value) {
        this.isForRange = value;
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
     * Gets the value of the keenWeakListSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keenWeakListSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeenWeakListSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getKeenWeakListSummary() {
        if (keenWeakListSummary == null) {
            keenWeakListSummary = new ArrayList<OptionVO>();
        }
        return this.keenWeakListSummary;
    }

    /**
     * Gets the value of the localElectionBodyIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localElectionBodyIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalElectionBodyIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLocalElectionBodyIds() {
        if (localElectionBodyIds == null) {
            localElectionBodyIds = new ArrayList<Long>();
        }
        return this.localElectionBodyIds;
    }

    /**
     * Gets the value of the locationTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLocationTypeId() {
        return locationTypeId;
    }

    /**
     * Sets the value of the locationTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLocationTypeId(Long value) {
        this.locationTypeId = value;
    }

    /**
     * Gets the value of the locationValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locationValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocationValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLocationValues() {
        if (locationValues == null) {
            locationValues = new ArrayList<Long>();
        }
        return this.locationValues;
    }

    /**
     * Gets the value of the locationsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locationsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocationsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyVO }
     * 
     * 
     */
    public List<SurveyVO> getLocationsList() {
        if (locationsList == null) {
            locationsList = new ArrayList<SurveyVO>();
        }
        return this.locationsList;
    }

    /**
     * Gets the value of the mandal property.
     * 
     */
    public boolean isMandal() {
        return mandal;
    }

    /**
     * Sets the value of the mandal property.
     * 
     */
    public void setMandal(boolean value) {
        this.mandal = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     */
    public boolean isMaximum() {
        return maximum;
    }

    /**
     * Sets the value of the maximum property.
     * 
     */
    public void setMaximum(boolean value) {
        this.maximum = value;
    }

    /**
     * Gets the value of the muncipality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMuncipality() {
        return muncipality;
    }

    /**
     * Sets the value of the muncipality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMuncipality(String value) {
        this.muncipality = value;
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
     * Gets the value of the optionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOptionId() {
        return optionId;
    }

    /**
     * Sets the value of the optionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOptionId(Long value) {
        this.optionId = value;
    }

    /**
     * Gets the value of the optionsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOptionsList() {
        if (optionsList == null) {
            optionsList = new ArrayList<String>();
        }
        return this.optionsList;
    }

    /**
     * Gets the value of the panchayat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPanchayat() {
        return panchayat;
    }

    /**
     * Sets the value of the panchayat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPanchayat(String value) {
        this.panchayat = value;
    }

    /**
     * Gets the value of the parliamentConstituencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParliamentConstituencyName() {
        return parliamentConstituencyName;
    }

    /**
     * Sets the value of the parliamentConstituencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParliamentConstituencyName(String value) {
        this.parliamentConstituencyName = value;
    }

    /**
     * Gets the value of the parliamentConstituencyNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParliamentConstituencyNo() {
        return parliamentConstituencyNo;
    }

    /**
     * Sets the value of the parliamentConstituencyNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParliamentConstituencyNo(String value) {
        this.parliamentConstituencyNo = value;
    }

    /**
     * Gets the value of the partNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNo() {
        return partNo;
    }

    /**
     * Sets the value of the partNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNo(String value) {
        this.partNo = value;
    }

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPartyId(Long value) {
        this.partyId = value;
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
     * Gets the value of the percent1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPercent1() {
        return percent1;
    }

    /**
     * Sets the value of the percent1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPercent1(Integer value) {
        this.percent1 = value;
    }

    /**
     * Gets the value of the percentFloat property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPercentFloat() {
        return percentFloat;
    }

    /**
     * Sets the value of the percentFloat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPercentFloat(Float value) {
        this.percentFloat = value;
    }

    /**
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercentage(String value) {
        this.percentage = value;
    }

    /**
     * Gets the value of the profileFieldId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProfileFieldId() {
        return profileFieldId;
    }

    /**
     * Sets the value of the profileFieldId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProfileFieldId(Long value) {
        this.profileFieldId = value;
    }

    /**
     * Gets the value of the profileFieldOptionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProfileFieldOptionId() {
        return profileFieldOptionId;
    }

    /**
     * Sets the value of the profileFieldOptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProfileFieldOptionId(Long value) {
        this.profileFieldOptionId = value;
    }

    /**
     * Gets the value of the questionIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the questionIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestionIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getQuestionIds() {
        if (questionIds == null) {
            questionIds = new ArrayList<Long>();
        }
        return this.questionIds;
    }

    /**
     * Gets the value of the questionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the questionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuestionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericVO }
     * 
     * 
     */
    public List<GenericVO> getQuestionList() {
        if (questionList == null) {
            questionList = new ArrayList<GenericVO>();
        }
        return this.questionList;
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
     * {@link String }
     * 
     * 
     */
    public List<String> getQuestionsList() {
        if (questionsList == null) {
            questionsList = new ArrayList<String>();
        }
        return this.questionsList;
    }

    /**
     * Gets the value of the rangeVal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRangeVal() {
        return rangeVal;
    }

    /**
     * Sets the value of the rangeVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRangeVal(Long value) {
        this.rangeVal = value;
    }

    /**
     * Gets the value of the regionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Sets the value of the regionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRegionId(Long value) {
        this.regionId = value;
    }

    /**
     * Gets the value of the regionIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regionIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegionIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getRegionIds() {
        if (regionIds == null) {
            regionIds = new ArrayList<Long>();
        }
        return this.regionIds;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
    }

    /**
     * Gets the value of the scopeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getScopeId() {
        return scopeId;
    }

    /**
     * Sets the value of the scopeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setScopeId(Long value) {
        this.scopeId = value;
    }

    /**
     * Gets the value of the spanCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSpanCount() {
        return spanCount;
    }

    /**
     * Sets the value of the spanCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSpanCount(Long value) {
        this.spanCount = value;
    }

    /**
     * Gets the value of the spnCnt property.
     * 
     */
    public int getSpnCnt() {
        return spnCnt;
    }

    /**
     * Sets the value of the spnCnt property.
     * 
     */
    public void setSpnCnt(int value) {
        this.spnCnt = value;
    }

    /**
     * Gets the value of the srvyAnswrIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the srvyAnswrIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSrvyAnswrIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSrvyAnswrIds() {
        if (srvyAnswrIds == null) {
            srvyAnswrIds = new ArrayList<Long>();
        }
        return this.srvyAnswrIds;
    }

    /**
     * Gets the value of the startAge property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartAge() {
        return startAge;
    }

    /**
     * Sets the value of the startAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartAge(Long value) {
        this.startAge = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the subFieldsMap property.
     * 
     * @return
     *     possible object is
     *     {@link SurveyReportVO.SubFieldsMap }
     *     
     */
    public SurveyReportVO.SubFieldsMap getSubFieldsMap() {
        return subFieldsMap;
    }

    /**
     * Sets the value of the subFieldsMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurveyReportVO.SubFieldsMap }
     *     
     */
    public void setSubFieldsMap(SurveyReportVO.SubFieldsMap value) {
        this.subFieldsMap = value;
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
     * {@link SurveyReportVO }
     * 
     * 
     */
    public List<SurveyReportVO> getSubList() {
        if (subList == null) {
            subList = new ArrayList<SurveyReportVO>();
        }
        return this.subList;
    }

    /**
     * Gets the value of the subList1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subList1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubList1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyReportVO }
     * 
     * 
     */
    public List<SurveyReportVO> getSubList1() {
        if (subList1 == null) {
            subList1 = new ArrayList<SurveyReportVO>();
        }
        return this.subList1;
    }

    /**
     * Gets the value of the subLocations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subLocations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubLocations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSubLocations() {
        if (subLocations == null) {
            subLocations = new ArrayList<Long>();
        }
        return this.subLocations;
    }

    /**
     * Gets the value of the subProfileFieldId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubProfileFieldId() {
        return subProfileFieldId;
    }

    /**
     * Sets the value of the subProfileFieldId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubProfileFieldId(Long value) {
        this.subProfileFieldId = value;
    }

    /**
     * Gets the value of the subProfileFieldValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subProfileFieldValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubProfileFieldValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSubProfileFieldValues() {
        if (subProfileFieldValues == null) {
            subProfileFieldValues = new ArrayList<Long>();
        }
        return this.subProfileFieldValues;
    }

    /**
     * Gets the value of the subQuestionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubQuestionId() {
        return subQuestionId;
    }

    /**
     * Sets the value of the subQuestionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubQuestionId(Long value) {
        this.subQuestionId = value;
    }

    /**
     * Gets the value of the surveyCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSurveyCount() {
        return surveyCount;
    }

    /**
     * Sets the value of the surveyCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSurveyCount(Long value) {
        this.surveyCount = value;
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
     * Gets the value of the surveyMap property.
     * 
     * @return
     *     possible object is
     *     {@link SurveyReportVO.SurveyMap }
     *     
     */
    public SurveyReportVO.SurveyMap getSurveyMap() {
        return surveyMap;
    }

    /**
     * Sets the value of the surveyMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurveyReportVO.SurveyMap }
     *     
     */
    public void setSurveyMap(SurveyReportVO.SurveyMap value) {
        this.surveyMap = value;
    }

    /**
     * Gets the value of the surveyMap1 property.
     * 
     * @return
     *     possible object is
     *     {@link SurveyReportVO.SurveyMap1 }
     *     
     */
    public SurveyReportVO.SurveyMap1 getSurveyMap1() {
        return surveyMap1;
    }

    /**
     * Sets the value of the surveyMap1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurveyReportVO.SurveyMap1 }
     *     
     */
    public void setSurveyMap1(SurveyReportVO.SurveyMap1 value) {
        this.surveyMap1 = value;
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
     * Gets the value of the todaysbooths property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the todaysbooths property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTodaysbooths().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTodaysbooths() {
        if (todaysbooths == null) {
            todaysbooths = new ArrayList<String>();
        }
        return this.todaysbooths;
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
     * Gets the value of the totalBoothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalBoothCount() {
        return totalBoothCount;
    }

    /**
     * Sets the value of the totalBoothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalBoothCount(Long value) {
        this.totalBoothCount = value;
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
     * Gets the value of the totalVoterCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalVoterCount() {
        return totalVoterCount;
    }

    /**
     * Sets the value of the totalVoterCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalVoterCount(Long value) {
        this.totalVoterCount = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the voterCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVoterCount() {
        return voterCount;
    }

    /**
     * Sets the value of the voterCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVoterCount(Long value) {
        this.voterCount = value;
    }

    /**
     * Gets the value of the voterPercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoterPercent() {
        return voterPercent;
    }

    /**
     * Sets the value of the voterPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoterPercent(String value) {
        this.voterPercent = value;
    }

    /**
     * Gets the value of the voterPercentFloat property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getVoterPercentFloat() {
        return voterPercentFloat;
    }

    /**
     * Sets the value of the voterPercentFloat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setVoterPercentFloat(Float value) {
        this.voterPercentFloat = value;
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

    /**
     * Gets the value of the withOptionType1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the withOptionType1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWithOptionType1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getWithOptionType1() {
        if (withOptionType1 == null) {
            withOptionType1 = new ArrayList<Long>();
        }
        return this.withOptionType1;
    }

    /**
     * Gets the value of the withOptionType2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the withOptionType2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWithOptionType2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getWithOptionType2() {
        if (withOptionType2 == null) {
            withOptionType2 = new ArrayList<Long>();
        }
        return this.withOptionType2;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class SubFieldsMap {

        protected List<SurveyReportVO.SubFieldsMap.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SurveyReportVO.SubFieldsMap.Entry }
         * 
         * 
         */
        public List<SurveyReportVO.SubFieldsMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<SurveyReportVO.SubFieldsMap.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Long key;
            protected Double value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setValue(Double value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class SurveyMap {

        protected List<SurveyReportVO.SurveyMap.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SurveyReportVO.SurveyMap.Entry }
         * 
         * 
         */
        public List<SurveyReportVO.SurveyMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<SurveyReportVO.SurveyMap.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected SurveyReportVO value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link SurveyReportVO }
             *     
             */
            public SurveyReportVO getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link SurveyReportVO }
             *     
             */
            public void setValue(SurveyReportVO value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class SurveyMap1 {

        protected List<SurveyReportVO.SurveyMap1 .Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SurveyReportVO.SurveyMap1 .Entry }
         * 
         * 
         */
        public List<SurveyReportVO.SurveyMap1 .Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<SurveyReportVO.SurveyMap1 .Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected SurveyReportVO value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link SurveyReportVO }
             *     
             */
            public SurveyReportVO getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link SurveyReportVO }
             *     
             */
            public void setValue(SurveyReportVO value) {
                this.value = value;
            }

        }

    }

}
