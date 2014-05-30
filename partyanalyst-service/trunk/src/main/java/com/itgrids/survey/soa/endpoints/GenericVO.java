
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
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="boothOrWard" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="femaleCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="genericVO" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" minOccurs="0"/>
 *         &lt;element name="genericVOList" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="genericVOList1" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="maleCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namesList1" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="namesList2" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="olderCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="optionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="partNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surveyCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="youngerCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
    "age",
    "boothOrWard",
    "count",
    "femaleCount",
    "genericVO",
    "genericVOList",
    "genericVOList1",
    "id",
    "maleCount",
    "name",
    "namesList1",
    "namesList2",
    "olderCount",
    "optionId",
    "partNo",
    "percent",
    "regionId",
    "remarks",
    "surveyCount",
    "youngerCount"
})
public class GenericVO {

    protected Long age;
    protected boolean boothOrWard;
    protected Long count;
    protected Long femaleCount;
    protected GenericVO genericVO;
    @XmlElement(nillable = true)
    protected List<GenericVO> genericVOList;
    @XmlElement(nillable = true)
    protected List<GenericVO> genericVOList1;
    protected Long id;
    protected Long maleCount;
    protected String name;
    @XmlElement(nillable = true)
    protected List<String> namesList1;
    @XmlElement(nillable = true)
    protected List<String> namesList2;
    protected Long olderCount;
    protected Long optionId;
    protected String partNo;
    protected String percent;
    protected Long regionId;
    protected String remarks;
    protected Long surveyCount;
    protected Long youngerCount;

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

}
