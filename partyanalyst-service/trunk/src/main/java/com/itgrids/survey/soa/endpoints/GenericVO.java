
package com.itgrids.survey.soa.endpoints;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for genericVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="genericVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualFemaleCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualFemaleCountPercentage" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualTotal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualYelderVoters" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualYelderVotersPercentage" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualYoungVoters" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualYoungVotersPercentage" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualmaleCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="actualmaleCountPercentage" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="boothOrWard" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="elderPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="femaleCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="femaleFromTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="femalePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="genericVO" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" minOccurs="0"/>
 *         &lt;element name="genericVOList" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="genericVOList1" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="maleCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="maleFromTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="malePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="middleAgeFemaleVotersCumm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="middleageMaleVotersCumm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namesList1" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="namesList2" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="olderCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="olderFromTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="optionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="partNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surveyCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="surveyElderPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="surveyFemalePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="surveyMalePercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="surveyYoungerPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalFromTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalVotersCumm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="yeldervotersCumm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="youngVOtersCumm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="youngerCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="youngerFromTotal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="youngerPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "genericVO", propOrder = {
    "actualFemaleCount",
    "actualFemaleCountPercentage",
    "actualTotal",
    "actualYelderVoters",
    "actualYelderVotersPercentage",
    "actualYoungVoters",
    "actualYoungVotersPercentage",
    "actualmaleCount",
    "actualmaleCountPercentage",
    "age",
    "boothOrWard",
    "count",
    "elderPercent",
    "femaleCount",
    "femaleFromTotal",
    "femalePercent",
    "genericVO",
    "genericVOList",
    "genericVOList1",
    "id",
    "maleCount",
    "maleFromTotal",
    "malePercent",
    "middleAgeFemaleVotersCumm",
    "middleageMaleVotersCumm",
    "name",
    "namesList1",
    "namesList2",
    "olderCount",
    "olderFromTotal",
    "optionId",
    "partNo",
    "percent",
    "regionId",
    "remarks",
    "surveyCount",
    "surveyElderPercent",
    "surveyFemalePercent",
    "surveyMalePercent",
    "surveyYoungerPercent",
    "totalFromTotal",
    "totalVotersCumm",
    "yeldervotersCumm",
    "youngVOtersCumm",
    "youngerCount",
    "youngerFromTotal",
    "youngerPercent"
})
public class GenericVO {

    protected Long actualFemaleCount;
    protected Long actualFemaleCountPercentage;
    protected Long actualTotal;
    protected Long actualYelderVoters;
    protected Long actualYelderVotersPercentage;
    protected Long actualYoungVoters;
    protected Long actualYoungVotersPercentage;
    protected Long actualmaleCount;
    protected Long actualmaleCountPercentage;
    protected Long age;
    protected boolean boothOrWard;
    protected Long count;
    protected float elderPercent;
    protected Long femaleCount;
    protected int femaleFromTotal;
    protected float femalePercent;
    protected GenericVO genericVO;
    @XmlElement(nillable = true)
    protected List<GenericVO> genericVOList;
    @XmlElement(nillable = true)
    protected List<GenericVO> genericVOList1;
    protected Long id;
    protected Long maleCount;
    protected int maleFromTotal;
    protected float malePercent;
    protected Long middleAgeFemaleVotersCumm;
    protected Long middleageMaleVotersCumm;
    protected String name;
    @XmlElement(nillable = true)
    protected List<String> namesList1;
    @XmlElement(nillable = true)
    protected List<String> namesList2;
    protected Long olderCount;
    protected int olderFromTotal;
    protected Long optionId;
    protected String partNo;
    protected String percent;
    protected Long regionId;
    protected String remarks;
    protected Long surveyCount;
    protected float surveyElderPercent;
    protected float surveyFemalePercent;
    protected float surveyMalePercent;
    protected float surveyYoungerPercent;
    protected int totalFromTotal;
    protected Long totalVotersCumm;
    protected Long yeldervotersCumm;
    protected Long youngVOtersCumm;
    protected Long youngerCount;
    protected int youngerFromTotal;
    protected float youngerPercent;

    /**
     * Gets the value of the actualFemaleCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualFemaleCount() {
        return actualFemaleCount;
    }

    /**
     * Sets the value of the actualFemaleCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualFemaleCount(Long value) {
        this.actualFemaleCount = value;
    }

    /**
     * Gets the value of the actualFemaleCountPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualFemaleCountPercentage() {
        return actualFemaleCountPercentage;
    }

    /**
     * Sets the value of the actualFemaleCountPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualFemaleCountPercentage(Long value) {
        this.actualFemaleCountPercentage = value;
    }

    /**
     * Gets the value of the actualTotal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualTotal() {
        return actualTotal;
    }

    /**
     * Sets the value of the actualTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualTotal(Long value) {
        this.actualTotal = value;
    }

    /**
     * Gets the value of the actualYelderVoters property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualYelderVoters() {
        return actualYelderVoters;
    }

    /**
     * Sets the value of the actualYelderVoters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualYelderVoters(Long value) {
        this.actualYelderVoters = value;
    }

    /**
     * Gets the value of the actualYelderVotersPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualYelderVotersPercentage() {
        return actualYelderVotersPercentage;
    }

    /**
     * Sets the value of the actualYelderVotersPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualYelderVotersPercentage(Long value) {
        this.actualYelderVotersPercentage = value;
    }

    /**
     * Gets the value of the actualYoungVoters property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualYoungVoters() {
        return actualYoungVoters;
    }

    /**
     * Sets the value of the actualYoungVoters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualYoungVoters(Long value) {
        this.actualYoungVoters = value;
    }

    /**
     * Gets the value of the actualYoungVotersPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualYoungVotersPercentage() {
        return actualYoungVotersPercentage;
    }

    /**
     * Sets the value of the actualYoungVotersPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualYoungVotersPercentage(Long value) {
        this.actualYoungVotersPercentage = value;
    }

    /**
     * Gets the value of the actualmaleCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualmaleCount() {
        return actualmaleCount;
    }

    /**
     * Sets the value of the actualmaleCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualmaleCount(Long value) {
        this.actualmaleCount = value;
    }

    /**
     * Gets the value of the actualmaleCountPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActualmaleCountPercentage() {
        return actualmaleCountPercentage;
    }

    /**
     * Sets the value of the actualmaleCountPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActualmaleCountPercentage(Long value) {
        this.actualmaleCountPercentage = value;
    }

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAge(Long value) {
        this.age = value;
    }

    /**
     * Gets the value of the boothOrWard property.
     * 
     */
    public boolean isBoothOrWard() {
        return boothOrWard;
    }

    /**
     * Sets the value of the boothOrWard property.
     * 
     */
    public void setBoothOrWard(boolean value) {
        this.boothOrWard = value;
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
     * Gets the value of the elderPercent property.
     * 
     */
    public float getElderPercent() {
        return elderPercent;
    }

    /**
     * Sets the value of the elderPercent property.
     * 
     */
    public void setElderPercent(float value) {
        this.elderPercent = value;
    }

    /**
     * Gets the value of the femaleCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFemaleCount() {
        return femaleCount;
    }

    /**
     * Sets the value of the femaleCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFemaleCount(Long value) {
        this.femaleCount = value;
    }

    /**
     * Gets the value of the femaleFromTotal property.
     * 
     */
    public int getFemaleFromTotal() {
        return femaleFromTotal;
    }

    /**
     * Sets the value of the femaleFromTotal property.
     * 
     */
    public void setFemaleFromTotal(int value) {
        this.femaleFromTotal = value;
    }

    /**
     * Gets the value of the femalePercent property.
     * 
     */
    public float getFemalePercent() {
        return femalePercent;
    }

    /**
     * Sets the value of the femalePercent property.
     * 
     */
    public void setFemalePercent(float value) {
        this.femalePercent = value;
    }

    /**
     * Gets the value of the genericVO property.
     * 
     * @return
     *     possible object is
     *     {@link GenericVO }
     *     
     */
    public GenericVO getGenericVO() {
        return genericVO;
    }

    /**
     * Sets the value of the genericVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericVO }
     *     
     */
    public void setGenericVO(GenericVO value) {
        this.genericVO = value;
    }

    /**
     * Gets the value of the genericVOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericVOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenericVOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericVO }
     * 
     * 
     */
    public List<GenericVO> getGenericVOList() {
        if (genericVOList == null) {
            genericVOList = new ArrayList<GenericVO>();
        }
        return this.genericVOList;
    }

    /**
     * Gets the value of the genericVOList1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericVOList1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenericVOList1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericVO }
     * 
     * 
     */
    public List<GenericVO> getGenericVOList1() {
        if (genericVOList1 == null) {
            genericVOList1 = new ArrayList<GenericVO>();
        }
        return this.genericVOList1;
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
     * Gets the value of the maleCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaleCount() {
        return maleCount;
    }

    /**
     * Sets the value of the maleCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaleCount(Long value) {
        this.maleCount = value;
    }

    /**
     * Gets the value of the maleFromTotal property.
     * 
     */
    public int getMaleFromTotal() {
        return maleFromTotal;
    }

    /**
     * Sets the value of the maleFromTotal property.
     * 
     */
    public void setMaleFromTotal(int value) {
        this.maleFromTotal = value;
    }

    /**
     * Gets the value of the malePercent property.
     * 
     */
    public float getMalePercent() {
        return malePercent;
    }

    /**
     * Sets the value of the malePercent property.
     * 
     */
    public void setMalePercent(float value) {
        this.malePercent = value;
    }

    /**
     * Gets the value of the middleAgeFemaleVotersCumm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMiddleAgeFemaleVotersCumm() {
        return middleAgeFemaleVotersCumm;
    }

    /**
     * Sets the value of the middleAgeFemaleVotersCumm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMiddleAgeFemaleVotersCumm(Long value) {
        this.middleAgeFemaleVotersCumm = value;
    }

    /**
     * Gets the value of the middleageMaleVotersCumm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMiddleageMaleVotersCumm() {
        return middleageMaleVotersCumm;
    }

    /**
     * Sets the value of the middleageMaleVotersCumm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMiddleageMaleVotersCumm(Long value) {
        this.middleageMaleVotersCumm = value;
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
     * Gets the value of the namesList1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namesList1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamesList1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNamesList1() {
        if (namesList1 == null) {
            namesList1 = new ArrayList<String>();
        }
        return this.namesList1;
    }

    /**
     * Gets the value of the namesList2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namesList2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamesList2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNamesList2() {
        if (namesList2 == null) {
            namesList2 = new ArrayList<String>();
        }
        return this.namesList2;
    }

    /**
     * Gets the value of the olderCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOlderCount() {
        return olderCount;
    }

    /**
     * Sets the value of the olderCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOlderCount(Long value) {
        this.olderCount = value;
    }

    /**
     * Gets the value of the olderFromTotal property.
     * 
     */
    public int getOlderFromTotal() {
        return olderFromTotal;
    }

    /**
     * Sets the value of the olderFromTotal property.
     * 
     */
    public void setOlderFromTotal(int value) {
        this.olderFromTotal = value;
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
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
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
     * Gets the value of the surveyElderPercent property.
     * 
     */
    public float getSurveyElderPercent() {
        return surveyElderPercent;
    }

    /**
     * Sets the value of the surveyElderPercent property.
     * 
     */
    public void setSurveyElderPercent(float value) {
        this.surveyElderPercent = value;
    }

    /**
     * Gets the value of the surveyFemalePercent property.
     * 
     */
    public float getSurveyFemalePercent() {
        return surveyFemalePercent;
    }

    /**
     * Sets the value of the surveyFemalePercent property.
     * 
     */
    public void setSurveyFemalePercent(float value) {
        this.surveyFemalePercent = value;
    }

    /**
     * Gets the value of the surveyMalePercent property.
     * 
     */
    public float getSurveyMalePercent() {
        return surveyMalePercent;
    }

    /**
     * Sets the value of the surveyMalePercent property.
     * 
     */
    public void setSurveyMalePercent(float value) {
        this.surveyMalePercent = value;
    }

    /**
     * Gets the value of the surveyYoungerPercent property.
     * 
     */
    public float getSurveyYoungerPercent() {
        return surveyYoungerPercent;
    }

    /**
     * Sets the value of the surveyYoungerPercent property.
     * 
     */
    public void setSurveyYoungerPercent(float value) {
        this.surveyYoungerPercent = value;
    }

    /**
     * Gets the value of the totalFromTotal property.
     * 
     */
    public int getTotalFromTotal() {
        return totalFromTotal;
    }

    /**
     * Sets the value of the totalFromTotal property.
     * 
     */
    public void setTotalFromTotal(int value) {
        this.totalFromTotal = value;
    }

    /**
     * Gets the value of the totalVotersCumm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalVotersCumm() {
        return totalVotersCumm;
    }

    /**
     * Sets the value of the totalVotersCumm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalVotersCumm(Long value) {
        this.totalVotersCumm = value;
    }

    /**
     * Gets the value of the yeldervotersCumm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getYeldervotersCumm() {
        return yeldervotersCumm;
    }

    /**
     * Sets the value of the yeldervotersCumm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setYeldervotersCumm(Long value) {
        this.yeldervotersCumm = value;
    }

    /**
     * Gets the value of the youngVOtersCumm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getYoungVOtersCumm() {
        return youngVOtersCumm;
    }

    /**
     * Sets the value of the youngVOtersCumm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setYoungVOtersCumm(Long value) {
        this.youngVOtersCumm = value;
    }

    /**
     * Gets the value of the youngerCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getYoungerCount() {
        return youngerCount;
    }

    /**
     * Sets the value of the youngerCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setYoungerCount(Long value) {
        this.youngerCount = value;
    }

    /**
     * Gets the value of the youngerFromTotal property.
     * 
     */
    public int getYoungerFromTotal() {
        return youngerFromTotal;
    }

    /**
     * Sets the value of the youngerFromTotal property.
     * 
     */
    public void setYoungerFromTotal(int value) {
        this.youngerFromTotal = value;
    }

    /**
     * Gets the value of the youngerPercent property.
     * 
     */
    public float getYoungerPercent() {
        return youngerPercent;
    }

    /**
     * Sets the value of the youngerPercent property.
     * 
     */
    public void setYoungerPercent(float value) {
        this.youngerPercent = value;
    }

}
