
package com.itgrids.survey.soa.endpoints;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.itgrids.survey.soa.endpoints package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPartyWiseCountDetailsForSelectedSurveys_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getPartyWiseCountDetailsForSelectedSurveys");
    private final static QName _GetPartyWiseCountDetailsForSelectedSurveysResponse_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getPartyWiseCountDetailsForSelectedSurveysResponse");
    private final static QName _GetTop5CastePeopleOpnionOnPartyStateWide_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getTop5CastePeopleOpnionOnPartyStateWide");
    private final static QName _GetTop5CastePeopleOpnionOnPartyResponse_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getTop5CastePeopleOpnionOnPartyResponse");
    private final static QName _GetTop5CastePeopleOpnionOnPartyStateWideResponse_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getTop5CastePeopleOpnionOnPartyStateWideResponse");
    private final static QName _GetGenderWiseReportForSurveyAnalysisResponse_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getGenderWiseReportForSurveyAnalysisResponse");
    private final static QName _GetTop5CastePeopleOpnionOnParty_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getTop5CastePeopleOpnionOnParty");
    private final static QName _GetGenderWiseReportForSurveyAnalysis_QNAME = new QName("http://endpoints.soa.survey.itgrids.com/", "getGenderWiseReportForSurveyAnalysis");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.itgrids.survey.soa.endpoints
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SurveyVO }
     * 
     */
    public SurveyVO createSurveyVO() {
        return new SurveyVO();
    }

    /**
     * Create an instance of {@link GetTop5CastePeopleOpnionOnPartyStateWideResponse }
     * 
     */
    public GetTop5CastePeopleOpnionOnPartyStateWideResponse createGetTop5CastePeopleOpnionOnPartyStateWideResponse() {
        return new GetTop5CastePeopleOpnionOnPartyStateWideResponse();
    }

    /**
     * Create an instance of {@link OptionVO.CastePercs.Entry }
     * 
     */
    public OptionVO.CastePercs.Entry createOptionVOCastePercsEntry() {
        return new OptionVO.CastePercs.Entry();
    }

    /**
     * Create an instance of {@link GetTop5CastePeopleOpnionOnParty }
     * 
     */
    public GetTop5CastePeopleOpnionOnParty createGetTop5CastePeopleOpnionOnParty() {
        return new GetTop5CastePeopleOpnionOnParty();
    }

    /**
     * Create an instance of {@link SurveyReportVO.SubFieldsMap.Entry }
     * 
     */
    public SurveyReportVO.SubFieldsMap.Entry createSurveyReportVOSubFieldsMapEntry() {
        return new SurveyReportVO.SubFieldsMap.Entry();
    }

    /**
     * Create an instance of {@link OptionVO.CastePercs }
     * 
     */
    public OptionVO.CastePercs createOptionVOCastePercs() {
        return new OptionVO.CastePercs();
    }

    /**
     * Create an instance of {@link GetTop5CastePeopleOpnionOnPartyStateWide }
     * 
     */
    public GetTop5CastePeopleOpnionOnPartyStateWide createGetTop5CastePeopleOpnionOnPartyStateWide() {
        return new GetTop5CastePeopleOpnionOnPartyStateWide();
    }

    /**
     * Create an instance of {@link SurveyReportVO.SurveyMap1 .Entry }
     * 
     */
    public SurveyReportVO.SurveyMap1 .Entry createSurveyReportVOSurveyMap1Entry() {
        return new SurveyReportVO.SurveyMap1 .Entry();
    }

    /**
     * Create an instance of {@link GenericVO }
     * 
     */
    public GenericVO createGenericVO() {
        return new GenericVO();
    }

    /**
     * Create an instance of {@link SurveyReportVO.SurveyMap }
     * 
     */
    public SurveyReportVO.SurveyMap createSurveyReportVOSurveyMap() {
        return new SurveyReportVO.SurveyMap();
    }

    /**
     * Create an instance of {@link SurveyReportVO.SurveyMap.Entry }
     * 
     */
    public SurveyReportVO.SurveyMap.Entry createSurveyReportVOSurveyMapEntry() {
        return new SurveyReportVO.SurveyMap.Entry();
    }

    /**
     * Create an instance of {@link GetGenderWiseReportForSurveyAnalysisResponse }
     * 
     */
    public GetGenderWiseReportForSurveyAnalysisResponse createGetGenderWiseReportForSurveyAnalysisResponse() {
        return new GetGenderWiseReportForSurveyAnalysisResponse();
    }

    /**
     * Create an instance of {@link GetTop5CastePeopleOpnionOnPartyResponse }
     * 
     */
    public GetTop5CastePeopleOpnionOnPartyResponse createGetTop5CastePeopleOpnionOnPartyResponse() {
        return new GetTop5CastePeopleOpnionOnPartyResponse();
    }

    /**
     * Create an instance of {@link ElectionComparisonVO }
     * 
     */
    public ElectionComparisonVO createElectionComparisonVO() {
        return new ElectionComparisonVO();
    }

    /**
     * Create an instance of {@link GetPartyWiseCountDetailsForSelectedSurveysResponse }
     * 
     */
    public GetPartyWiseCountDetailsForSelectedSurveysResponse createGetPartyWiseCountDetailsForSelectedSurveysResponse() {
        return new GetPartyWiseCountDetailsForSelectedSurveysResponse();
    }

    /**
     * Create an instance of {@link SurveyReportVO }
     * 
     */
    public SurveyReportVO createSurveyReportVO() {
        return new SurveyReportVO();
    }

    /**
     * Create an instance of {@link SurveyReportVO.SurveyMap1 }
     * 
     */
    public SurveyReportVO.SurveyMap1 createSurveyReportVOSurveyMap1() {
        return new SurveyReportVO.SurveyMap1();
    }

    /**
     * Create an instance of {@link SurveyReportVO.SubFieldsMap }
     * 
     */
    public SurveyReportVO.SubFieldsMap createSurveyReportVOSubFieldsMap() {
        return new SurveyReportVO.SubFieldsMap();
    }

    /**
     * Create an instance of {@link GetPartyWiseCountDetailsForSelectedSurveys }
     * 
     */
    public GetPartyWiseCountDetailsForSelectedSurveys createGetPartyWiseCountDetailsForSelectedSurveys() {
        return new GetPartyWiseCountDetailsForSelectedSurveys();
    }

    /**
     * Create an instance of {@link GetGenderWiseReportForSurveyAnalysis }
     * 
     */
    public GetGenderWiseReportForSurveyAnalysis createGetGenderWiseReportForSurveyAnalysis() {
        return new GetGenderWiseReportForSurveyAnalysis();
    }

    /**
     * Create an instance of {@link OptionVO }
     * 
     */
    public OptionVO createOptionVO() {
        return new OptionVO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPartyWiseCountDetailsForSelectedSurveys }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getPartyWiseCountDetailsForSelectedSurveys")
    public JAXBElement<GetPartyWiseCountDetailsForSelectedSurveys> createGetPartyWiseCountDetailsForSelectedSurveys(GetPartyWiseCountDetailsForSelectedSurveys value) {
        return new JAXBElement<GetPartyWiseCountDetailsForSelectedSurveys>(_GetPartyWiseCountDetailsForSelectedSurveys_QNAME, GetPartyWiseCountDetailsForSelectedSurveys.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPartyWiseCountDetailsForSelectedSurveysResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getPartyWiseCountDetailsForSelectedSurveysResponse")
    public JAXBElement<GetPartyWiseCountDetailsForSelectedSurveysResponse> createGetPartyWiseCountDetailsForSelectedSurveysResponse(GetPartyWiseCountDetailsForSelectedSurveysResponse value) {
        return new JAXBElement<GetPartyWiseCountDetailsForSelectedSurveysResponse>(_GetPartyWiseCountDetailsForSelectedSurveysResponse_QNAME, GetPartyWiseCountDetailsForSelectedSurveysResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTop5CastePeopleOpnionOnPartyStateWide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getTop5CastePeopleOpnionOnPartyStateWide")
    public JAXBElement<GetTop5CastePeopleOpnionOnPartyStateWide> createGetTop5CastePeopleOpnionOnPartyStateWide(GetTop5CastePeopleOpnionOnPartyStateWide value) {
        return new JAXBElement<GetTop5CastePeopleOpnionOnPartyStateWide>(_GetTop5CastePeopleOpnionOnPartyStateWide_QNAME, GetTop5CastePeopleOpnionOnPartyStateWide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTop5CastePeopleOpnionOnPartyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getTop5CastePeopleOpnionOnPartyResponse")
    public JAXBElement<GetTop5CastePeopleOpnionOnPartyResponse> createGetTop5CastePeopleOpnionOnPartyResponse(GetTop5CastePeopleOpnionOnPartyResponse value) {
        return new JAXBElement<GetTop5CastePeopleOpnionOnPartyResponse>(_GetTop5CastePeopleOpnionOnPartyResponse_QNAME, GetTop5CastePeopleOpnionOnPartyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTop5CastePeopleOpnionOnPartyStateWideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getTop5CastePeopleOpnionOnPartyStateWideResponse")
    public JAXBElement<GetTop5CastePeopleOpnionOnPartyStateWideResponse> createGetTop5CastePeopleOpnionOnPartyStateWideResponse(GetTop5CastePeopleOpnionOnPartyStateWideResponse value) {
        return new JAXBElement<GetTop5CastePeopleOpnionOnPartyStateWideResponse>(_GetTop5CastePeopleOpnionOnPartyStateWideResponse_QNAME, GetTop5CastePeopleOpnionOnPartyStateWideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGenderWiseReportForSurveyAnalysisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getGenderWiseReportForSurveyAnalysisResponse")
    public JAXBElement<GetGenderWiseReportForSurveyAnalysisResponse> createGetGenderWiseReportForSurveyAnalysisResponse(GetGenderWiseReportForSurveyAnalysisResponse value) {
        return new JAXBElement<GetGenderWiseReportForSurveyAnalysisResponse>(_GetGenderWiseReportForSurveyAnalysisResponse_QNAME, GetGenderWiseReportForSurveyAnalysisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTop5CastePeopleOpnionOnParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getTop5CastePeopleOpnionOnParty")
    public JAXBElement<GetTop5CastePeopleOpnionOnParty> createGetTop5CastePeopleOpnionOnParty(GetTop5CastePeopleOpnionOnParty value) {
        return new JAXBElement<GetTop5CastePeopleOpnionOnParty>(_GetTop5CastePeopleOpnionOnParty_QNAME, GetTop5CastePeopleOpnionOnParty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGenderWiseReportForSurveyAnalysis }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.soa.survey.itgrids.com/", name = "getGenderWiseReportForSurveyAnalysis")
    public JAXBElement<GetGenderWiseReportForSurveyAnalysis> createGetGenderWiseReportForSurveyAnalysis(GetGenderWiseReportForSurveyAnalysis value) {
        return new JAXBElement<GetGenderWiseReportForSurveyAnalysis>(_GetGenderWiseReportForSurveyAnalysis_QNAME, GetGenderWiseReportForSurveyAnalysis.class, null, value);
    }

}
