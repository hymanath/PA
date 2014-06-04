
package com.itgrids.survey.soa.endpoints;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for optionVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="optionVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="averageBoothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="averageBoothIdsList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="avgPercs" type="{http://www.w3.org/2001/XMLSchema}double" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="badBoothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="badBoothIdsList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="caste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="castePercs">
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
 *         &lt;element name="correctionPerc" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="correctionPercs" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="criteriaMatched" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="goodBoothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="goodBoothIdsList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hasRemark" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="hasSubQuestion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="keenCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="locationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="option" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="optionsList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="party" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="partyMappingOptionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="percent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percentage" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="percents" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="question" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="statusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="subOptionList" type="{http://endpoints.soa.survey.itgrids.com/}optionVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subquestion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subquestionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalPercentage" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="veryBadBoothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="veryBadBoothIdsList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="veryGoodBoothCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="veryGoodBoothIdsList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="votesObtained" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "optionVO", propOrder = {
    "age",
    "averageBoothCount",
    "averageBoothIdsList",
    "avgPercs",
    "badBoothCount",
    "badBoothIdsList",
    "caste",
    "castePercs",
    "correctionPerc",
    "correctionPercs",
    "count",
    "criteriaMatched",
    "goodBoothCount",
    "goodBoothIdsList",
    "hasRemark",
    "hasSubQuestion",
    "keenCount",
    "locationId",
    "locationName",
    "name",
    "option",
    "optionId",
    "optionsList",
    "party",
    "partyId",
    "partyMappingOptionId",
    "percent",
    "percentage",
    "percents",
    "question",
    "questionId",
    "statusId",
    "subOptionList",
    "subquestion",
    "subquestionType",
    "total",
    "totalPercentage",
    "veryBadBoothCount",
    "veryBadBoothIdsList",
    "veryGoodBoothCount",
    "veryGoodBoothIdsList",
    "votesObtained"
})
public class OptionVO {

    protected String age;
    protected Long averageBoothCount;
    @XmlElement(nillable = true)
    protected List<Long> averageBoothIdsList;
    @XmlElement(nillable = true)
    protected List<Double> avgPercs;
    protected Long badBoothCount;
    @XmlElement(nillable = true)
    protected List<Long> badBoothIdsList;
    protected String caste;
    @XmlElement(required = true)
    protected OptionVO.CastePercs castePercs;
    protected Double correctionPerc;
    @XmlElement(nillable = true)
    protected List<OptionVO> correctionPercs;
    protected Long count;
    protected boolean criteriaMatched;
    protected Long goodBoothCount;
    @XmlElement(nillable = true)
    protected List<Long> goodBoothIdsList;
    protected Boolean hasRemark;
    protected Boolean hasSubQuestion;
    protected Long keenCount;
    protected Long locationId;
    protected String locationName;
    protected String name;
    protected String option;
    protected Long optionId;
    @XmlElement(nillable = true)
    protected List<String> optionsList;
    protected String party;
    protected Long partyId;
    protected Long partyMappingOptionId;
    protected String percent;
    protected Double percentage;
    @XmlElement(nillable = true)
    protected List<OptionVO> percents;
    protected String question;
    protected Long questionId;
    protected Long statusId;
    @XmlElement(nillable = true)
    protected List<OptionVO> subOptionList;
    protected String subquestion;
    protected String subquestionType;
    protected Long total;
    protected Double totalPercentage;
    protected Long veryBadBoothCount;
    @XmlElement(nillable = true)
    protected List<Long> veryBadBoothIdsList;
    protected Long veryGoodBoothCount;
    @XmlElement(nillable = true)
    protected List<Long> veryGoodBoothIdsList;
    protected Long votesObtained;

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAge(String value) {
        this.age = value;
    }

    /**
     * Gets the value of the averageBoothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAverageBoothCount() {
        return averageBoothCount;
    }

    /**
     * Sets the value of the averageBoothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAverageBoothCount(Long value) {
        this.averageBoothCount = value;
    }

    /**
     * Gets the value of the averageBoothIdsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the averageBoothIdsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAverageBoothIdsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getAverageBoothIdsList() {
        if (averageBoothIdsList == null) {
            averageBoothIdsList = new ArrayList<Long>();
        }
        return this.averageBoothIdsList;
    }

    /**
     * Gets the value of the avgPercs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the avgPercs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvgPercs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    public List<Double> getAvgPercs() {
        if (avgPercs == null) {
            avgPercs = new ArrayList<Double>();
        }
        return this.avgPercs;
    }

    /**
     * Gets the value of the badBoothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBadBoothCount() {
        return badBoothCount;
    }

    /**
     * Sets the value of the badBoothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBadBoothCount(Long value) {
        this.badBoothCount = value;
    }

    /**
     * Gets the value of the badBoothIdsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the badBoothIdsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBadBoothIdsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getBadBoothIdsList() {
        if (badBoothIdsList == null) {
            badBoothIdsList = new ArrayList<Long>();
        }
        return this.badBoothIdsList;
    }

    /**
     * Gets the value of the caste property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaste() {
        return caste;
    }

    /**
     * Sets the value of the caste property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaste(String value) {
        this.caste = value;
    }

    /**
     * Gets the value of the castePercs property.
     * 
     * @return
     *     possible object is
     *     {@link OptionVO.CastePercs }
     *     
     */
    public OptionVO.CastePercs getCastePercs() {
        return castePercs;
    }

    /**
     * Sets the value of the castePercs property.
     * 
     * @param value
     *     allowed object is
     *     {@link OptionVO.CastePercs }
     *     
     */
    public void setCastePercs(OptionVO.CastePercs value) {
        this.castePercs = value;
    }

    /**
     * Gets the value of the correctionPerc property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCorrectionPerc() {
        return correctionPerc;
    }

    /**
     * Sets the value of the correctionPerc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCorrectionPerc(Double value) {
        this.correctionPerc = value;
    }

    /**
     * Gets the value of the correctionPercs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the correctionPercs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCorrectionPercs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getCorrectionPercs() {
        if (correctionPercs == null) {
            correctionPercs = new ArrayList<OptionVO>();
        }
        return this.correctionPercs;
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
     * Gets the value of the criteriaMatched property.
     * 
     */
    public boolean isCriteriaMatched() {
        return criteriaMatched;
    }

    /**
     * Sets the value of the criteriaMatched property.
     * 
     */
    public void setCriteriaMatched(boolean value) {
        this.criteriaMatched = value;
    }

    /**
     * Gets the value of the goodBoothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGoodBoothCount() {
        return goodBoothCount;
    }

    /**
     * Sets the value of the goodBoothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGoodBoothCount(Long value) {
        this.goodBoothCount = value;
    }

    /**
     * Gets the value of the goodBoothIdsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goodBoothIdsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoodBoothIdsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getGoodBoothIdsList() {
        if (goodBoothIdsList == null) {
            goodBoothIdsList = new ArrayList<Long>();
        }
        return this.goodBoothIdsList;
    }

    /**
     * Gets the value of the hasRemark property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasRemark() {
        return hasRemark;
    }

    /**
     * Sets the value of the hasRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasRemark(Boolean value) {
        this.hasRemark = value;
    }

    /**
     * Gets the value of the hasSubQuestion property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasSubQuestion() {
        return hasSubQuestion;
    }

    /**
     * Sets the value of the hasSubQuestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasSubQuestion(Boolean value) {
        this.hasSubQuestion = value;
    }

    /**
     * Gets the value of the keenCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getKeenCount() {
        return keenCount;
    }

    /**
     * Sets the value of the keenCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setKeenCount(Long value) {
        this.keenCount = value;
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
     * Gets the value of the option property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOption() {
        return option;
    }

    /**
     * Sets the value of the option property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOption(String value) {
        this.option = value;
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
     * Gets the value of the party property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParty() {
        return party;
    }

    /**
     * Sets the value of the party property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParty(String value) {
        this.party = value;
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
     * Gets the value of the partyMappingOptionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPartyMappingOptionId() {
        return partyMappingOptionId;
    }

    /**
     * Sets the value of the partyMappingOptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPartyMappingOptionId(Long value) {
        this.partyMappingOptionId = value;
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
     * Gets the value of the percentage property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * Sets the value of the percentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPercentage(Double value) {
        this.percentage = value;
    }

    /**
     * Gets the value of the percents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the percents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPercents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getPercents() {
        if (percents == null) {
            percents = new ArrayList<OptionVO>();
        }
        return this.percents;
    }

    /**
     * Gets the value of the question property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the value of the question property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestion(String value) {
        this.question = value;
    }

    /**
     * Gets the value of the questionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Sets the value of the questionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQuestionId(Long value) {
        this.questionId = value;
    }

    /**
     * Gets the value of the statusId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStatusId() {
        return statusId;
    }

    /**
     * Sets the value of the statusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStatusId(Long value) {
        this.statusId = value;
    }

    /**
     * Gets the value of the subOptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subOptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubOptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionVO }
     * 
     * 
     */
    public List<OptionVO> getSubOptionList() {
        if (subOptionList == null) {
            subOptionList = new ArrayList<OptionVO>();
        }
        return this.subOptionList;
    }

    /**
     * Gets the value of the subquestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubquestion() {
        return subquestion;
    }

    /**
     * Sets the value of the subquestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubquestion(String value) {
        this.subquestion = value;
    }

    /**
     * Gets the value of the subquestionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubquestionType() {
        return subquestionType;
    }

    /**
     * Sets the value of the subquestionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubquestionType(String value) {
        this.subquestionType = value;
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
     * Gets the value of the totalPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPercentage() {
        return totalPercentage;
    }

    /**
     * Sets the value of the totalPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPercentage(Double value) {
        this.totalPercentage = value;
    }

    /**
     * Gets the value of the veryBadBoothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVeryBadBoothCount() {
        return veryBadBoothCount;
    }

    /**
     * Sets the value of the veryBadBoothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVeryBadBoothCount(Long value) {
        this.veryBadBoothCount = value;
    }

    /**
     * Gets the value of the veryBadBoothIdsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the veryBadBoothIdsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVeryBadBoothIdsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getVeryBadBoothIdsList() {
        if (veryBadBoothIdsList == null) {
            veryBadBoothIdsList = new ArrayList<Long>();
        }
        return this.veryBadBoothIdsList;
    }

    /**
     * Gets the value of the veryGoodBoothCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVeryGoodBoothCount() {
        return veryGoodBoothCount;
    }

    /**
     * Sets the value of the veryGoodBoothCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVeryGoodBoothCount(Long value) {
        this.veryGoodBoothCount = value;
    }

    /**
     * Gets the value of the veryGoodBoothIdsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the veryGoodBoothIdsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVeryGoodBoothIdsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getVeryGoodBoothIdsList() {
        if (veryGoodBoothIdsList == null) {
            veryGoodBoothIdsList = new ArrayList<Long>();
        }
        return this.veryGoodBoothIdsList;
    }

    /**
     * Gets the value of the votesObtained property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVotesObtained() {
        return votesObtained;
    }

    /**
     * Sets the value of the votesObtained property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVotesObtained(Long value) {
        this.votesObtained = value;
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
    public static class CastePercs {

        protected List<OptionVO.CastePercs.Entry> entry;

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
         * {@link OptionVO.CastePercs.Entry }
         * 
         * 
         */
        public List<OptionVO.CastePercs.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<OptionVO.CastePercs.Entry>();
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

}
