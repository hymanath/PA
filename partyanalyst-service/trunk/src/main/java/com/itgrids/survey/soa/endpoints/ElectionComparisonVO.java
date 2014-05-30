
package com.itgrids.survey.soa.endpoints;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for electionComparisonVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="electionComparisonVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="casteResult" type="{http://endpoints.soa.survey.itgrids.com/}genericVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyWiseResult" type="{http://endpoints.soa.survey.itgrids.com/}surveyReportVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "electionComparisonVO", propOrder = {
    "casteResult",
    "partyWiseResult"
})
public class ElectionComparisonVO {

    @XmlElement(nillable = true)
    protected List<GenericVO> casteResult;
    @XmlElement(nillable = true)
    protected List<SurveyReportVO> partyWiseResult;

    /**
     * Gets the value of the casteResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the casteResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCasteResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericVO }
     * 
     * 
     */
    public List<GenericVO> getCasteResult() {
        if (casteResult == null) {
            casteResult = new ArrayList<GenericVO>();
        }
        return this.casteResult;
    }

    /**
     * Gets the value of the partyWiseResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyWiseResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyWiseResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurveyReportVO }
     * 
     * 
     */
    public List<SurveyReportVO> getPartyWiseResult() {
        if (partyWiseResult == null) {
            partyWiseResult = new ArrayList<SurveyReportVO>();
        }
        return this.partyWiseResult;
    }

}
