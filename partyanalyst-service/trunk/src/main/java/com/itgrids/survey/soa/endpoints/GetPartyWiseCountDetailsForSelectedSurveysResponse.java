
package com.itgrids.survey.soa.endpoints;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPartyWiseCountDetailsForSelectedSurveysResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPartyWiseCountDetailsForSelectedSurveysResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://endpoints.soa.survey.itgrids.com/}electionComparisonVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPartyWiseCountDetailsForSelectedSurveysResponse", propOrder = {
    "_return"
})
public class GetPartyWiseCountDetailsForSelectedSurveysResponse {

    @XmlElement(name = "return")
    protected ElectionComparisonVO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ElectionComparisonVO }
     *     
     */
    public ElectionComparisonVO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectionComparisonVO }
     *     
     */
    public void setReturn(ElectionComparisonVO value) {
        this._return = value;
    }

}
